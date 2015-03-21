package com.gmail.zahusek;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.gmail.zahusek.api.TabObject;

public class MyTab extends TabObject {
	
	SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
	
	public MyTab() {
		addSlot("§2[###]", "§6Welcome §7^^  §6!", "§2[###]");
		
		addSlot("§2[###]");
		
		String data = format.format(new Date());
		setSlot(4, "§dGodzina:§5 {H}:{M}:{S}".replace("{H}", data.split(":")[0]).replace("{M}", data.split(":")[1]).replace("{S}", data.split(":")[2]));
		
		addSlot("§2[###]");
	}
}
