package com.otaku.command;

public class CommandGenres {

	COMMAND_TYPE type;
	String emoji;
	
	public CommandGenres(COMMAND_TYPE type, String emoji) {
		this.type = type;
		this.emoji = emoji;
	}

	public COMMAND_TYPE getType() {
		return type;
	}

	public void setType(COMMAND_TYPE type) {
		this.type = type;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}
	
}
