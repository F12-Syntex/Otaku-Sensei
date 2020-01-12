package com.otaku.utilities;

import java.util.concurrent.ThreadLocalRandom;

import com.otaku.identification.Identification;

public class Facts {
	
	public static final String[] tips = new String[] {
			
			"Use " + Identification.prefix + "help get a list of commands!"			
			
	};
	
	public static String quickTip() {
		
		String tip = Facts.tips[ThreadLocalRandom.current().nextInt(Facts.tips.length)];
		
		return tip;
		
	}
	
	
	public static String getDescription() {
		return "to lazy to come up with a description";
	}
	

}
