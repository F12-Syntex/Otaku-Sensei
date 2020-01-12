package com.otaku.utilities;

public class Formatter {
	
	public static String[] formatJSON(String json) {
		
		String[] values = json.split(",");
		
		StringBuilder format = new StringBuilder();
		
		for(String i : values) {
			
			format.append("    " + "\n" + i + ",");
			
		}
		
		return format.toString().split("\n");
		
		
	}

}
