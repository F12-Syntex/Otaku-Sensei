package com.otaku.identification;

public class Identification {

	public static String name = "Otaku Sensei";
	public static String prefix = "."; //bot prefix
	public static String version = "1.0.0"; //bot version
	
	public static String[] admins = new String[]{
		
		"234004050201280512", //Syntex
		"347838306052210688"  //Beni
			
	};
	 
	
	//user is admin?
    public static boolean isAdmin(String id) {
    	for(String i : admins) {
    		if(i.equals(id)) return true;
    	}
    	return false;
    }
}
