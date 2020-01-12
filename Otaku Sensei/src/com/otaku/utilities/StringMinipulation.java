package com.otaku.utilities;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.otaku.identification.Identification;

public class StringMinipulation {

	public static String cut(String text, int amount) {
		
		if(text.length() <= amount) return text;
		
		return text.substring(0, amount) + "...";
		
	}
	
	public static String cutEnd(String text, int amount) {
		
		if(text.length() <= amount) return "";
	
		return text.substring(0, text.length() - amount);
		
	}
	
	public static boolean CheckString(String prefix, String[] array) {
		
		prefix = prefix.split(" ")[0];
		
		for(String i : array) {
			if(prefix.equalsIgnoreCase(Identification.prefix + i)) {
				return true;
			}
		}
		return false;
	}
	
	public static String getStringInArray(String prefix, String[] array) {
		
		prefix = prefix.split(" ")[0];
		
		for(String i : array) {
			if(prefix.equalsIgnoreCase(Identification.prefix + i)) {
				return i;
			}
		}
		return "";
		
	}
	
	public static String capitalize(String word) {
		
		StringBuilder sb = new StringBuilder();
		
		try {
		
		for(String i : word.split(" ")) {
			sb.append(i.substring(0, 1).toUpperCase() + i.substring(1) + " ");
		}
		
		return sb.toString();
		
		}catch (Throwable e) {
		
		return word;
		}
	}
	
	public static String prettyPrint(String json) {
		try {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");
        scriptEngine.put("jsonString", json);
        scriptEngine.eval("result = JSON.stringify(JSON.parse(jsonString), null, 2)");
        String prettyPrintedJson = (String) scriptEngine.get("result"); 
        return prettyPrintedJson;
		}catch (Exception e) {
			e.printStackTrace();
			return json;
		}
	}
	
	
}
