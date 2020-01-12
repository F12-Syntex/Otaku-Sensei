package com.otaku.utilities;

import java.util.concurrent.ThreadLocalRandom;

public class Random {

	
	public static String booleanToYesOrNo(String status) {
		if(status.equalsIgnoreCase("true")) {
			return "Yes";
		}
			return "No";
	}
	
	public static boolean chance(int perc) {
		
		int randomNumber = ThreadLocalRandom.current().nextInt(100) + 1;
		
		if(randomNumber <= perc) return true;
		
		return false;
	}
	
}
