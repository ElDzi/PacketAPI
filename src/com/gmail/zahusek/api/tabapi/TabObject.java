package com.gmail.zahusek.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.zahusek.packetplayoutplayerinfo.PacketPlayOutPlayerInfo;
import com.gmail.zahusek.packetplayoutplayerinfo.PacketPlayOutPlayerInfo.PlayerInfoType;
import com.gmail.zahusek.packetplayoutscoreboardteam.PacketPlayOutScoreboardTeam;
import com.gmail.zahusek.packetplayoutscoreboardteam.PacketPlayOutScoreboardTeam.ScoreboardTeamType;
import com.gmail.zahusek.utils.PacketSend;

public class TabObject {
	
	private List<String> messages;
	
	public TabObject() {
		this.messages = new ArrayList<String>();
	}
	public void addSlot(String... messages) {
		for(String msg : messages) this.messages.add(msg);
	}
	public void setSlot(int slot, String message) {
		while(slot >= this.messages.size()) {
			this.messages.add("");
		}
		this.messages.set(slot, message);
	}
	private List<String> getList() {
		return this.messages;
	}
	public static boolean check(Player p) {
		return PlayerObject.getPlayer(p.getName()) != null;
	}
	
	public static void update(Player p, TabObject o) {
		PacketSend ps = new PacketSend(p);
		PlayerObject po = PlayerObject.getPlayer(p.getName()) == null ? new PlayerObject(p.getName()) : PlayerObject.getPlayer(p.getName()) ;
		for(int i = 0; i < o.getList().size(); i++) {
			PacketPlayOutScoreboardTeam ppost;
			if(i >= po.getFakeplayers().size()) {
				po.addFakePlayer();
				ppost = new PacketPlayOutScoreboardTeam(po.getId(i));
				ps.sendPacket(new PacketPlayOutPlayerInfo(po.getId(i), true, 9999).getPacket(PlayerInfoType.OTHER));
				ps.sendPacket(ppost.getPacket(ScoreboardTeamType.CREATE));
				ppost.setPlayers(po.getId(i));
				ps.sendPacket(ppost.getPacket(ScoreboardTeamType.ADD));
				
			}
			ppost =  new PacketPlayOutScoreboardTeam(po.getId(i));
			String msg = o.getList().get(i) ;
			String one = msg.length() <= 16 ? msg : msg.substring(0, 16);
			ppost.setPrefix(one);
			String two = "";
			if(msg.length() > 16) {
				two = msg.substring(16, msg.length()).length()+2 > 16 ? "§r" + msg.substring(16, 30) : "§r" + msg.substring(16, msg.length());
				int idx = one.lastIndexOf("§");
				if(idx != -1) two = one.substring(idx, idx+2) + two.substring(2, two.length());
				ppost.setSuffix(two);
			}
			ps.sendPacket(ppost.getPacket(ScoreboardTeamType.UPDATE));
		}
	}
	public static void classic(Player p) {
		clear(p);
		PacketSend ps = new PacketSend(p);
		for(Player online : Bukkit.getServer().getOnlinePlayers()) {
			ps.sendPacket(new PacketPlayOutPlayerInfo(online.getPlayerListName()).getPacket(PlayerInfoType.CREATE));
		}
	}
	public static void clear(Player p) {
		PacketSend ps = new PacketSend(p);
		for(Player online : Bukkit.getServer().getOnlinePlayers()) {
			ps.sendPacket(new PacketPlayOutPlayerInfo(online.getPlayerListName()).getPacket(PlayerInfoType.DELETE));
		}
		if(PlayerObject.getPlayer(p.getName()) != null) {
			PlayerObject po = PlayerObject.getPlayer(p.getName());
			for(int i = 0; i < po.getFakeplayers().size(); i++) {
				ps.sendPacket(new PacketPlayOutPlayerInfo(po.getId(i)).getPacket(PlayerInfoType.DELETE));
				ps.sendPacket(new PacketPlayOutScoreboardTeam(po.getId(i)).getPacket(ScoreboardTeamType.DELETE));
			}
			PlayerObject.list.remove(po);
		}
	}
	public static class TabManager implements Listener {
		
		public static void unregister() {
			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
				clear(p);
				classic(p);
			}
		}
		public static void register(Plugin p) {
			Bukkit.getServer().getPluginManager().registerEvents(new TabManager(), p);
		}
		@EventHandler(priority = EventPriority.MONITOR)
		public void playerquitevt(PlayerQuitEvent e) {
			clear(e.getPlayer());
		}
		@EventHandler(priority = EventPriority.MONITOR)
		public void playerjointevt(PlayerJoinEvent e) {
			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
				if(p==e.getPlayer())continue; if(check(p)== false) continue;
				clear(p);
			}
		}
	}
}
