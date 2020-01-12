package com.otaku.command;

import java.awt.Color;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.otaku.authentication.Wrapper;
import com.otaku.identification.Identification;
import com.otaku.listeners.command.Modules;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
	
	public abstract String name();
	public abstract String[] prefix();
	public abstract String desc();
	public abstract String usage();
	public abstract Permission permission();
	public abstract int ID();
	public abstract COMMAND_TYPE type();
	public abstract void invoke(MessageReceivedEvent e, String arguments);
	public abstract void emptyArguments(MessageReceivedEvent e);
	public abstract boolean active();
	
	
	protected JDA discord;
	protected Wrapper wrapper;
	protected String prefix;
	
	public Command(Wrapper wrapper) {
		this.wrapper = wrapper;
		this.discord = wrapper.jda;
		Modules.register(this);
		this.prefix = this.prefix()[0];
	}
	
	public String getCommandPrefix(MessageReceivedEvent e) {

		String rawMessage = e.getMessage().getContentRaw();
		String prefix = this.getStringMatchArray(rawMessage, this.prefix());
		
		return prefix;
	}
	
	public String getCommandPrefix(GuildMessageReceivedEvent e) {

		String rawMessage = e.getMessage().getContentRaw();
		String prefix = this.getStringMatchArray(rawMessage, this.prefix());
		
		return prefix;
	}
	
	public EmbedBuilder getInvalidUsage() {
        EmbedBuilder builder = new EmbedBuilder().setTitle("Invalid usage").setColor(Color.RED);
		
        builder.appendDescription("Usage: " + Identification.prefix + this.usage());
        builder.appendDescription("\n");
        
        builder.setFooter("Whoopsy, invalid args buddy!", this.discord.getSelfUser().getAvatarUrl());
        builder.setTimestamp(Instant.now());
        
        return builder;
	}
	public String getMessage(String message) {
		return "```" + message + "```";
	}

	public List<Command> getGenreCommands(COMMAND_TYPE type){
		
		List<Command> commands = new ArrayList<Command>();
		
		for(Command command : Modules.modules) {
			if(command.type() == type) commands.add(command);
		}
		
		return commands;		
	}
	
	private String getStringMatchArray(String prefix, String[] array) {
		
		for(String i : array) {
			if(prefix.startsWith(Identification.prefix + i)) {
				return i;
			}
		}
		return "";
		
	}
	public JDA getDiscord() {
		return discord;
	}
	public void setDiscord(JDA discord) {
		this.discord = discord;
	}
	public Wrapper getWrapper() {
		return wrapper;
	}
	public void setWrapper(Wrapper wrapper) {
		this.wrapper = wrapper;
	}
	
	
}