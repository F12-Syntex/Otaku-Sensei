package com.otaku.authentication;

public class Engine {
	
	public static void main(String[] args) {
	
		Wrapper Otaku = new Wrapper();
		
		try {
			Otaku.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not start bot...");
		}
		
		
	}

}
