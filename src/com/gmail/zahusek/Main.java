package com.gmail.thezaha;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.thezaha.api.TabObject;

public class Main extends JavaPlugin {

//	public static void main(String[] args) {
//	}
	
	private Main plugin;
	public Main getPlugin() { return plugin; }
	
	@Override
	public void onDisable() {
		this.plugin = null;
		TabObject.destory();
		super.onDisable();
	}
	@Override
	public void onEnable() {
		this.plugin = this;
		super.onEnable();
	}
	
	
	@Override
	public boolean onCommand (CommandSender sender, Command command,
			String label, String[] args) {
		if(!command.getName().equalsIgnoreCase("test")) return false;
		Player p = (Player) sender;
		
		//new constructor and methods - Work In Progress, changes not have been introduced yet ...
		
		TabObject to = new TabObject(PacketPriority.NORMAL/*TabList Priority*/);
		//TabObject newTabObject = new TabObject(PacketPriority.NORMAL/*TabList Priority*/, -1 /*Ping*/);
		
		/**add to TabList - now, expanded to more methods**/
		to.addSlot("Slot 1")
		to.addSlot(1,"Slot 2")
		to.setSlot(0, "Edited Slot 1");
		TabObject.setTab(p, to);
		//TabObject.newSetTab(p, to);
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("create")) {
				TabObject.setTab(p, o);
			}
			else if(args[0].equalsIgnoreCase("clear")) {
				TabObject.clearTab(p);
			}
			else if(args[0].equalsIgnoreCase("default")) {
				TabObject.defaultTab(p);
			}
		}
		return super.onCommand(sender, command, label, args);
	}
}
