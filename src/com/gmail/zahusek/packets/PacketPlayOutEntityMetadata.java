package com.gmail.zahusek.packets;

import com.gmail.zahusek.utils.Reflection;
import com.gmail.zahusek.utils.Reflection.Type;

public class PacketPlayOutEntityMetadata {
	
	private int id;
	private Object datawatcher;
	
	private Class<?> classpacket = Reflection.getClass("PacketPlayOutEntityMetadata", Type.NET);
	
	public PacketPlayOutEntityMetadata(int id, Object datawatcher) {
		this.id = id;
		this.datawatcher = datawatcher;
	}
	
	public int getID() {
		return this.id;
	}
	public Object getDataWatcher() {
		return this.datawatcher;
	}
	
	public Object getPacket() {
		Object packet = null;
		try {
			packet = classpacket.getConstructor(int.class, Reflection.getClass("DataWatcher", Type.NET), boolean.class).newInstance(this.id, this.datawatcher, true);
		} catch (Exception e) { e.printStackTrace(); }
		return packet;
	}
}
