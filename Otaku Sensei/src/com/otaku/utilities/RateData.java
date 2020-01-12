package com.otaku.utilities;

public class RateData {

	private String argument;
	private int value;
	
	public RateData(String argument, int value) {
		
		this.argument = argument;
		this.value = value;
		
		
	}

	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
