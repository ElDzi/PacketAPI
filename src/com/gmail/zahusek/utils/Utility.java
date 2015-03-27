    public List<Location> cyrcle(Location l, int r, boolean s, boolean h){
    	List<Location> loc = new ArrayList<Location>();
    	 
    	 
    	for(int x = (r * -1); x <= r; x++) {
    	    for(int y = (r * -1); y <= r; y++) {
    	        for(int z = (r * -1); z <= r; z++) {
    	        	int ny = s == true ? y : 0;
    	            Location b = new Location(l.getWorld(), l.getBlockX() + x, l.getBlockY() + ny, l.getBlockZ() + z);
    	 
    	 
    	            if(b.distance(l) > r)
    	                continue; 
    	 
    	            if(b.distance((l)) < r - 1 && h == true)
    	                continue; 
    	 
    	            loc.add(b);
    	        }
    	    }
    	}
		return loc;
    }
