package com.gmail.zahusek.api;

import java.util.ArrayList;

public class PlayerObject {
	
	public static ArrayList<PlayerObject> list = new ArrayList<PlayerObject>();
	
	private String name;
	private ArrayList<String> fakeplayers;
	private int x, z;

	public PlayerObject(String name) {
		this.name = name;
		this.fakeplayers = new ArrayList<String>();
		this.x = 0;
		this.z = 0;
		list.add(this);
	}
	
	public String getName() {
		return this.name;
	}
	public ArrayList<String> getFakeplayers() {
		return this.fakeplayers;
	}
	public int getX() {
		return this.x;
	}
	public int getZ() {
		return this.z;
	}
	
	private char[] colors = {'a','b','c','d','e','f','r','0','1','2','3','4','5','6','7','8','9'};
	private String fakeplayer() {
		
		String fake = null;
		
		fake = "§" + this.colors[this.x] + "§" + this.colors[this.z];
		
		this.z++;
		
		if(this.z >= 17) {
			this.z = 0;
			this.x++;
		}
		
		if(this.x >= 17) 
			fake = null;
	    
	    return fake;
	}
	public void addFakePlayer() {
		this.fakeplayers.add(fakeplayer());
	}
	
	public String getId(int i) {
		return this.fakeplayers.get(i);
	}
	public static PlayerObject getPlayer(String name) {
		for(PlayerObject po : list) if(po.getName().equals(name)) return po;
		return null;
	}
}
