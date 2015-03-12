package com.gmail.thezaha;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.thezaha.api.TabObject;
import com.gmail.thezaha.api.TabObject.TabManager;

public class Main extends JavaPlugin {
	
//	public static void main(String[] args) {
//	}
	
	@Override
	public void onDisable() {
		TabManager.destory();
		super.onDisable();
	}
	@Override
	public void onEnable() {
		TabManager.register(this);
		new BukkitRunnable() {
			@Override
			public void run() {
				customtab();
			}
		}.runTaskTimer(this, 3L, 3L);
		super.onEnable();
	}
	SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
	public void customtab() {
		String data = format.format(new Date());
		TabObject to = new TabObject();
		to.addSlot("§2[###]", "§6Welcome §7^^  §6!", "§2[###]");
		to.addSlot("§2[###]");
		to.setSlot(4, "§dGodzina:§5 {H} §5:{M}:{S}".replace("{H}", data.split(":")[0]).replace("{M}", data.split(":")[1]).replace("{S}", data.split(":")[2]));
		to.addSlot("§2[###]");
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			if(!TabObject.check(p)) TabObject.clear(p);
			TabObject.update(p, to);
		}
	}
}
