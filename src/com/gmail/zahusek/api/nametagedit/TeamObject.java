package com.gmail.zahusek.api.nametagedit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.zahusek.ppost.PacketPlayOutScoreboardTeam;
import com.gmail.zahusek.ppost.PacketPlayOutScoreboardTeam.ScoreboardTeamType;
import com.gmail.zahusek.utils.PacketSend;

public class TeamObject {
	
	private String prefix, suffix;
	
	public TeamObject() {
		this.prefix = "";
		this.suffix = "";
	}
	public String getPrefix() {
		return this.prefix;
	}
	public String getSuffix() {
		return this.suffix;
	}
	
	public void setPrefix(String s) {
		String prefix = "";
		for (int i = 0; i < s.length() && i < 16; i++) {
			prefix += s.charAt(i);
		}
		this.prefix = prefix;
	}
	public void setSuffix(String s) {
		String suffix = "";
		for (int i = 0; i < s.length() && i < 16; i++) {
			suffix += s.charAt(i);
		}
		this.suffix = suffix;
	}
	public void setAll(String p, String s) {
		setPrefix(p);
		setSuffix(s);
	}
	
	public void update(Player p, TeamObject o) {
		for(Player online : getOnline()) {
			if(online == p) continue;
			
			PacketSend ps = new PacketSend(online);
			PacketPlayOutScoreboardTeam ppost = new PacketPlayOutScoreboardTeam(p.getName());
			ps.sendPacket(ppost.getPacket(ScoreboardTeamType.CREATE));
			
			ppost.setPrefix(o.getPrefix());
			ppost.setSuffix(o.getSuffix());
			ps.sendPacket(ppost.getPacket(ScoreboardTeamType.UPDATE));
			
			ppost.setPlayers(p.getName());
			ps.sendPacket(ppost.getPacket(ScoreboardTeamType.ADD));
		}
	}
	private List<Player> getOnline(){
		List<Player> list = new ArrayList<Player>();
		for(Player p : Bukkit.getServer().getOnlinePlayers())
			list.add(p);
		return list;
	}
	public void clear(String s) {
		
	}
	public boolean check(String s) {
		return PlayerObject.getPlayer(s) != null;
	}
}
