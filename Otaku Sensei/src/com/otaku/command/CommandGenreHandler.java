package com.otaku.command;

import java.util.ArrayList;
import java.util.List;

public class CommandGenreHandler {

	private List<CommandGenres> commands = new ArrayList<CommandGenres>();
	
	public CommandGenreHandler() {
		
	}
	
	public void init() {
		
		this.commands.add(new CommandGenres(COMMAND_TYPE.HELP, ":pencil:"));
		this.commands.add(new CommandGenres(COMMAND_TYPE.SEARCH, ":mag:"));
		this.commands.add(new CommandGenres(COMMAND_TYPE.FUN, ":partying_face:"));
		this.commands.add(new CommandGenres(COMMAND_TYPE.CURRENCY, ":money_with_wings:"));
		
	}

	public List<CommandGenres> getCommands() {
		return commands;
	}

	public void setCommands(List<CommandGenres> commands) {
		this.commands = commands;
	}
	
	public void addCommand(CommandGenres command) {
		this.commands.add(command);
	}
	public void removeCommand(CommandGenres command) {
		this.commands.remove(command);
	}
	
	public CommandGenres getCommandGenre(COMMAND_TYPE type) {
		for(CommandGenres genre : commands) {
			if(genre.getType() == type) return genre;
		}
		return null;
	}

	
}
