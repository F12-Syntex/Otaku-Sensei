package com.otaku.command;

public class CommandDupeCheck {

	private String commandName;
	private String prefix;
	
	public CommandDupeCheck(String commandName, String prefix) {
		
		this.commandName = commandName;
		this.prefix = prefix;
		
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
	
	
	
	
}
