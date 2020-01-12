package com.otaku.modules.help;

import java.awt.Color;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.otaku.authentication.Wrapper;
import com.otaku.command.COMMAND_TYPE;
import com.otaku.command.Command;
import com.otaku.command.CommandGenres;
import com.otaku.identification.Identification;
import com.otaku.listeners.command.Modules;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class help extends Command{
	
	
	public help(Wrapper wrapper) {
		super(wrapper);
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Help";
	}

	@Override
	public String[] prefix() {
		// TODO Auto-generated method stub
		return new String[] {"help", "commands", "h", "helpme", "command", "cmd", "cmds", "os"};
	}

	@Override
	public String desc() {
		// TODO Auto-generated method stub
		return "returns all the commands for this bot.";
	}
	
	@Override
	public COMMAND_TYPE type() {
		// TODO Auto-generated method stub
		return COMMAND_TYPE.HELP;
	}

	@Override
	public Permission permission() {
		// TODO Auto-generated method stub
		return Permission.MESSAGE_WRITE;
	}

	@Override
	public String usage() {
		// TODO Auto-generated method stub
		return this.prefix + " <command>";
	}

	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void invoke(MessageReceivedEvent e, String arguments) {
			
			String prefix = e.getMessage().getContentRaw().split(" ")[1];
			
			for(Command command : Modules.modules) {
				
				for(String cuurentprefix : command.prefix()) {
				
					if(prefix.equalsIgnoreCase(cuurentprefix)) {
				
			        EmbedBuilder builder = new EmbedBuilder().setTitle(prefix + " info" + " " + wrapper.getGenres().getCommandGenre(command.type()).getEmoji()).setColor(Color.CYAN);
			        
			        builder.appendDescription("\n");
			        
			        
			        StringBuilder prefixBuilder = new StringBuilder();
			        
			        for(int i = 0; i < command.prefix().length; i++) {
			        	if(i < command.prefix().length - 1) {
			        	 	prefixBuilder.append(command.prefix()[i] + ", ");						   
			        	}else {
			        	 	prefixBuilder.append(command.prefix()[i]);						        		
			        	}
			        }
			        
			        
			        String ifNullpermission = "None.";
			        String permission;
			        
			        try {
			            permission = ""+command.permission().toString();
			        }catch (Exception e2) {
			        	permission = ifNullpermission;
			        }
			        
			        
			        builder.appendDescription(command.desc());
			        
			        builder.addField(new Field("Prefix", Identification.prefix + prefixBuilder.toString(), false));
			        builder.addField(new Field("Usage", command.usage(), true));
			        builder.addField(new Field("Permission", permission, true));
			        
			        builder.setFooter("remmeber the prefix '" + Identification.prefix + "'", e.getAuthor().getAvatarUrl());
			        builder.setTimestamp(Instant.now());
			        
			        e.getChannel().sendMessage(builder.build()).queue();
			        return;
			        
					}
			}
			
			}
			
			
	        EmbedBuilder builder = new EmbedBuilder().setColor(Color.CYAN);
	        
	        builder.appendDescription("Could not find a command with prefix `" + prefix + "`");
	        
	        e.getChannel().sendMessage(builder.build()).queue();
	        
	        return;
		
		
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public void emptyArguments(MessageReceivedEvent e) {
		
	    EmbedBuilder builder = new EmbedBuilder().setTitle("List of commands").setColor(Color.CYAN);
		
	    builder.appendDescription("Quick tip. Here are a list of commands! In order to veiew detailed information about a command please type " + Identification.prefix + this.prefix()[0] + " <command>");
	    
		COMMAND_TYPE[] genres2 = COMMAND_TYPE.values();
	    
		for(COMMAND_TYPE type : genres2) {
			builder.appendDescription("\n");
			builder.appendDescription("\n");
			CommandGenres genre = wrapper.getGenres().getCommandGenre(type);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("\n");
			
			List<String> CurrentCommands = new ArrayList<String>();
			
		for(int i = 0; i < Modules.modules.size(); i++) {
			
			if(Modules.modules.get(i).type() != type) continue;
			
			
			CurrentCommands.add(Modules.modules.get(i).prefix()[0]);
		
		}
		
		for(int i = 0; i < CurrentCommands.size(); i++) {

			if((i+1) < CurrentCommands.size()) {
				sb.append("`" + CurrentCommands.get(i) + "`, ");	
			}else {
				sb.append("`" + CurrentCommands.get(i) + "`");
			}	

		}
		
		String emoji = genre.getEmoji();
		String genretype = type.toString().replace("_", "").toLowerCase() + " commands";
		String desc = sb.toString();
		
			Field tempField = new Field(emoji + " " + "" +genretype, desc, false);	
			builder.addField(tempField);
		
		}
	    
	    builder.setFooter("remmeber the prefix '" + Identification.prefix + "'", e.getAuthor().getAvatarUrl());
	    builder.setTimestamp(Instant.now());
	    
	    e.getChannel().sendMessage(builder.build()).queue();
			
		
	}
	
}
