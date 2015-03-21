package com.gmail.zahusek.utils;

public enum PacketPriority {
	HIGHEST(0), HIGH(1), NORMAL(2), LOW(3), LOWEST(4);
	
	private final int value;
   
	private PacketPriority(int value) {
        this.value = value;
    }
   
	public int getValue() {
        return value;
    }
}
