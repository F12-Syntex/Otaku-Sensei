package com.otaku.listeners.command;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import com.otaku.authentication.Wrapper;
import com.otaku.command.Command;
import com.otaku.identification.Identification;
import com.otaku.utilities.StringMinipulation;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandInvoke extends ListenerAdapter{

	public static HashMap<String, ArrayList<String>> reactionData = new HashMap<String, ArrayList<String>>();
	
	private final int prefix_length = Identification.prefix.length();
	
	private Wrapper wrapper;
	
	@Override
	public void onMessageUpdate(MessageUpdateEvent event) {
		super.onMessageUpdate(event);
	}
	
	
	public CommandInvoke(Wrapper wrapper) {
	
		this.setWrapper(wrapper);
		
	}
	
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {

		String userID = e.getAuthor().getId();
		
		new Thread() {
			
			@Override
			public void run() {
		
		
		for(Command command : Modules.modules) {
		
			
			if(StringMinipulation.CheckString(e.getMessage().getContentRaw().split(" ")[0], command.prefix())){
				
				if(!e.getMember().hasPermission(command.permission()) && !Identification.isAdmin(userID)) {

					EmbedBuilder builder = new EmbedBuilder().setColor(Color.RED);
					
			        builder.appendDescription("You dont have the required permissions.");
			        e.getChannel().sendMessage(builder.build()).queue();
					
			        e.getMessage().delete().queue();
					return;
				}
				
				try {
					
					if(!command.active()) {
						EmbedBuilder builder = new EmbedBuilder().setColor(Color.RED);
						builder.appendDescription("Sorry, this command is currently disabled");
						e.getChannel().sendMessage(builder.build()).queue();
						return;
					}

					
					
				String prefixUsed = StringMinipulation.getStringInArray(e.getMessage().getContentRaw(), command.prefix());
				String arguments = (e.getMessage().getContentRaw().substring(prefixUsed.length() + prefix_length));
				
				if(arguments.startsWith(" ")) {
					arguments.substring(1);
				}
				
				
				if(arguments.isEmpty()) {
					command.emptyArguments(e);
				}else {
					command.invoke(e, arguments);	
				}
				
				return;
								
				
				}catch (Throwable e2) {
					
					EmbedBuilder builder = new EmbedBuilder().setColor(Color.RED);
					
			        e2.printStackTrace();
					
			        builder.appendDescription(e2.getLocalizedMessage());
			        e.getChannel().sendMessage(builder.build()).queue();
			        return;			
				
				
			}
				}
		
				}
				
				
				}
		
		}.start();
			
		if(e.getAuthor().isBot()) return;

		
		
	}


	public Wrapper getWrapper() {
		return wrapper;
	}


	public void setWrapper(Wrapper wrapper) {
		this.wrapper = wrapper;
	}
	
}
