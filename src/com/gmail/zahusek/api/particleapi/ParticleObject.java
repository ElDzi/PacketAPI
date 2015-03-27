package com.gmail.zahusek.api.particleapi;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.gmail.zahusek.packets.PacketPlayOutWorldParticles;
import com.gmail.zahusek.utils.PacketSend;

public class ParticleObject {
	
	public enum Effect {
		explode("explode"),
		largeexplode("largeexplode"),
		hugeexplosion("hugeexplosion"),
		fireworksSpark("fireworksSpark"),
		bubble("bubble"),
		splash("splash"),
		wake("wake"),
		suspended("suspended"),
		depthsuspend("depthsuspend"),
		crit("crit"),
		magicCrit("magicCrit"),
		smoke("smoke"),
		largesmoke("largesmoke"),
		spell("spell"),
		instantSpell("instantSpell"),
		mobSpell("mobSpell"),
		mobSpellAmbient("mobSpellAmbient"),
		witchMagic("witchMagic"),
		dripWater("dripWater"),
		dripLava("dripLava"),
		angryVillager("angryVillager"),
		happyVillager("happyVillager"),
		townaura("townaura"),
		note("note"),
		portal("portal"),
		enchantmenttable("enchantmenttable"),
		flame("flame"),
		lava("lava"),
		footstep("footstep"),
		reddust("reddust"),
		snowballpoof("snowballpoof"),
		slime("slime"),
		heart("heart"),
		cloud("cloud"),
		snowshovel("snowshovel");
		private final String name;
		   
		private Effect(String name) {
	        this.name = name;
	    }
	   
		public String getName() {
	        return name;
	    }
	}
	public enum MaterialEffect {
		iconcrack("iconcrack_%id%_%data%"),
		blockcrack("blockcrack_%id%_%data%");
		private final String name;
		   
		private MaterialEffect(String name) {
	        this.name = name;
	    }
	   
		public String getName() {
	        return name;
	    }
	}
	private String e;
	private Location l;
	private float ox, oy, oz, s;
	private int a;
	
	public ParticleObject(Effect e, Location l, int a, float s, float ox, float oy, float oz) {
		this.e = e.getName();
		this.l = l;
		this.a = a;
		this.s = s;
		this.ox = ox;
		this.oy = oy;
		this.oz = oz;
		
	}
	public ParticleObject(MaterialEffect e, int id, int data, Location l, int a, float s, float ox, float oy, float oz) {
		this.e = e.getName().replace("%id%", String.valueOf(id)).replace("%data%", String.valueOf(data));
		this.l = l;
		this.a = a;
		this.s = s;
		this.ox = ox;
		this.oy = oy;
		this.oz = oz;
	}
	public String getEffect() {
		return this.e;
	}
	public Location getLocation() {
		return this.l;
	}
	public float getOX() {
		return this.ox;
	}
	public float getOY() {
		return this.oy;
	}
	public float getOZ() {
		return this.oz;
	}
	public float getSpeed() {
		return this.s;
	}
	public int getAmount() {
		return this.a;
	}
	public void update(Player p, ParticleObject o) {
		PacketSend ps = new PacketSend(p);
		Location l = o.getLocation();
		PacketPlayOutWorldParticles ppowp = new PacketPlayOutWorldParticles(o.getEffect(), (float)l.getX(), (float)l.getY(), (float)l.getZ(), o.getOX(), o.getOY(), o.getOZ(), o.getSpeed(), o.getAmount());
		ps.sendPacket(ppowp.getPacket());
	}

}
