package com.gmail.zahusek.api.barapi;

import java.lang.reflect.Method;

import javax.security.auth.Refreshable;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.zahusek.packets.PacketPlayOutEntityDestroy;
import com.gmail.zahusek.packets.PacketPlayOutEntityMetadata;
import com.gmail.zahusek.packets.PacketPlayOutSpawnEntityLiving;
import com.gmail.zahusek.utils.PacketSend;
import com.gmail.zahusek.utils.Reflection;
import com.gmail.zahusek.utils.Reflection.Type;

public class BarObject {
	
	private String meesage;
	private float health;

	public BarObject(String m, float h) {
		this.meesage = m;
		this.health = h;
	}
	public BarObject(String m) {
		this.meesage = m;
		this.health = 200.0F;
	}
	public String getMessage() {
		return this.meesage;
	}
	public float getHealth() {
		return this.health;
	}
	public static void update(Player p, BarObject o) {
		PacketSend ps = new PacketSend(p);
		PlayerObject po;
		if(PlayerObject.getPlayer(p.getName()) == null) {
			Dragon d = new Dragon(p.getWorld());
			po = new PlayerObject(p.getName(), d.getId(), d.getDataWatcher());
			PacketPlayOutSpawnEntityLiving pposel = new PacketPlayOutSpawnEntityLiving(d.getEntity());
			ps.sendPacket(pposel.getPacket());
			setWatcher(o.getMessage(), o.getHealth(), po.getID(), po.getDataWatcher(), ps);
		}
		else {
			po = PlayerObject.getPlayer(p.getName());
			setWatcher(o.getMessage(), o.getHealth(), po.getID(), po.getDataWatcher(), ps);
		}
		po.setBarObject(o);
	}
	public static void remove(Player p) {
		PlayerObject po = PlayerObject.getPlayer(p.getName());
		if(po == null) return;
		PacketSend ps = new PacketSend(p);
		PacketPlayOutEntityDestroy ppoed = new PacketPlayOutEntityDestroy(po.getID());
		ps.sendPacket(ppoed.getPacket());
		PlayerObject.list.remove(po);
	}
	public static boolean check(String s) {
		return PlayerObject.getPlayer(s) != null;
	}
	private static void setWatcher(String m, float h, int id, Object datawatcher, PacketSend ps) {
		DataWatcher dw = new DataWatcher(datawatcher);
		dw.setName(m);
		dw.setHealth(h);
		PacketPlayOutEntityMetadata ppoem = new PacketPlayOutEntityMetadata(id, dw.getDataWatcher());
		ps.sendPacket(ppoem.getPacket());
	}
	private static class Dragon {
		
		private Object datawatcher, dragon;
		private int id;
		public Dragon(World w) {
			int i = -1;
			Object dw = null, d = null;
			
			try {
				Object world = w.getClass().getMethod("getHandle").invoke(w, new Object[0]);
				d = Reflection.getClass("EntityEnderDragon", Type.NET).getConstructor(Reflection.getClass("World", Type.NET)).newInstance(world);
				i = (int) d.getClass().getMethod("getId").invoke(d, new Object[0]);
				dw = d.getClass().getMethod("getDataWatcher").invoke(d, new Object[0]);
			} catch(Exception e) { e.printStackTrace(); }
			
			this.dragon = d;
			this.id = i;
			this.datawatcher = dw;
		}
		public Object getEntity() {
			return dragon;
		}
		public Object getDataWatcher() {
			return datawatcher;
		}
		public int getId() {
			return id;
		}
	}
	private static class DataWatcher {
		
		private Object datawatcher;
		private Method dw;
		
		public DataWatcher(Object datawatcher) {
			this.datawatcher = datawatcher;
			Method d = null;
			try {
				d = this.datawatcher.getClass().getMethod("watch", int.class, Object.class);
			} catch (Exception e) { e.printStackTrace(); }
			this.dw = d;
			other(0, (byte) 0x20);
			other(7, (int) 0);
			other(8, (byte) 0x20);
		}
		public void setName(String s) {
			try {
				dw.invoke(datawatcher, 10, (String) s);
				dw.invoke(datawatcher, 11, (byte) 1);
			} catch (Exception e) { e.printStackTrace(); }
		}
		public void setHealth(float f) {
			try {
				dw.invoke(datawatcher, 6, (float) f);
			} catch (Exception e) { e.printStackTrace();}
		}
		public void other(int i, Object o) {
			try {
				dw.invoke(datawatcher, i, o);
			} catch (Exception e) { e.printStackTrace();}
		}
		public Object getDataWatcher() {
			return this.datawatcher;
		}
	}
	public static void refresh(final Player p, Plugin plg) {
		PlayerObject po = PlayerObject.getPlayer(p.getName());
		if(po == null) return;
		final BarObject bo = po.getBarObject();
		new BukkitRunnable() {
			@Override
			public void run() {
				remove(p);
				update(p, bo);
			}
		}.runTaskLater(plg, 3L);
	}
	public static class BarManager implements Listener {
		public static void unregister() {
			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
				remove(p);
			}
		}
		private static Plugin plg;
		public static void register(Plugin p) {
			Bukkit.getServer().getPluginManager().registerEvents(new BarManager(), p);
			plg = p;
		}
		@EventHandler(priority = EventPriority.MONITOR)
		public void playerquitevt(PlayerQuitEvent e) {
			remove(e.getPlayer());
		}
		@EventHandler(priority = EventPriority.MONITOR)
		public void playerteleportevt(PlayerChangedWorldEvent e) {
			Player p = e.getPlayer();
			refresh(p, plg);
		}
		//
		@EventHandler(priority = EventPriority.MONITOR)
		public void playerrespawnevt(PlayerRespawnEvent e) {
			Player p = e.getPlayer();
			refresh(p, plg);
		}
	}
}
