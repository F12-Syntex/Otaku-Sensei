package com.otaku.utilities;

public class CommandUtils {
	
	public static String LinkIntoClickable(String link, String name) {
		return "[" + name + "](" + link + ")";
	}
	
	public static String LinkIntoClickable(String link) {
		
		if(link.isEmpty()) return "`N/A`";
		
		return "[Click me.](" + link + ")";
	}
	
}
