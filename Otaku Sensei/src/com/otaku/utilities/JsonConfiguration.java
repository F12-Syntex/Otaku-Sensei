package com.otaku.utilities;

public enum JsonConfiguration {

	Accounts();
	
	public static String getFileExtension(JsonConfiguration config) {
	
		if(config == JsonConfiguration.Accounts) return "data";
		
		return null;
		
	}
	
	
	
}
