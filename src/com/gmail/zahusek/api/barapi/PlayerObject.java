package com.gmail.zahusek.api.barapi;

import java.util.ArrayList;

public class PlayerObject {
	
	public static ArrayList<PlayerObject> list = new ArrayList<PlayerObject>();
	
	private String name;
	private Object datawatcher;
	private int id;

	public PlayerObject(String name, int id, Object datawatcher) {
		this.name = name;
		this.id = id;
		this.datawatcher = datawatcher;
		list.add(this);
	}
	
	public String getName() {
		return this.name;
	}
	public Object getDataWatcher() {
		return this.datawatcher;
	}
	public Object setDataWatcher(Object datawatcher) {
		return this.datawatcher = datawatcher;
	}
	public int getID() {
		return this.id;
	}
	public static PlayerObject getPlayer(String name) {
		for(PlayerObject po : list) if(po.getName().equals(name)) return po;
		return null;
	}
}
