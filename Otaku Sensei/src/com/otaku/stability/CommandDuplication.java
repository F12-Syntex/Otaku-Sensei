package com.otaku.stability;

import java.util.ArrayList;
import java.util.List;

import com.otaku.command.Command;
import com.otaku.command.CommandDupeCheck;

public class CommandDuplication {
	
	private List<Command> commands;
	
	public CommandDuplication(List<Command> commands) {
		this.commands = commands;
	}
	
	public void init() {
		
		List<CommandDupeCheck> blocked = this.duplicates();
		
		boolean flag = false;
		
		for(CommandDupeCheck i : blocked) {
			flag = true;
			System.out.println("Duplicate command: ( " + i.getCommandName() + " ) : " + i.getPrefix());
		}
		
		if(flag) System.exit(-1);
	}

	private List<CommandDupeCheck> duplicates() {
		
		
		List<CommandDupeCheck> list = new ArrayList<>();
		List<CommandDupeCheck> blocked = new ArrayList<>();

		
		for(Command command : this.commands) {
			
			for(String i  : command.prefix()) {
				
				boolean flag = false;
				
				for(CommandDupeCheck x : list) {
					
					if(x.getPrefix().equalsIgnoreCase(i)) {
						
						if(!flag) {
							blocked.add(new CommandDupeCheck(command.name(), i));
							flag = true;
						}
						
						blocked.add(x);
					}
				}
				  list.add(new CommandDupeCheck(command.name(), i));
			}
		
		}
		
		return blocked;
		
	}
	
}
