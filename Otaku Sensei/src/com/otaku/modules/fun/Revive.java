package com.otaku.modules.fun;

import java.awt.Color;
import java.time.Instant;

import com.otaku.authentication.Wrapper;
import com.otaku.command.COMMAND_TYPE;
import com.otaku.command.Command;
import com.otaku.identification.Identification;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Revive extends Command{

	public Revive(Wrapper wrapper) {
		super(wrapper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "revive";
	}

	@Override
	public String[] prefix() {
		// TODO Auto-generated method stub
		return new String[] {"revive"};
	}

	@Override
	public String desc() {
		// TODO Auto-generated method stub
		return "revive me";
	}

	@Override
	public void invoke(MessageReceivedEvent e, String argument) {
	}
	
	@Override
	public COMMAND_TYPE type() {
		// TODO Auto-generated method stub
		return COMMAND_TYPE.FUN;
	}


	@Override
	public Permission permission() {
		// TODO Auto-generated method stub
		return Permission.MESSAGE_WRITE;
	}

	@Override
	public String usage() {
		// TODO Auto-generated method stub
		return this.prefix()[0] + " <user>";
	}

	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void emptyArguments(MessageReceivedEvent e) {
		// TODO Auto-generated method stub
		
        EmbedBuilder builder = new EmbedBuilder().setTitle("Revived!").setColor(Color.RED);
		
        builder.appendDescription(e.getAuthor().getName() + " revived " + e.getMessage().getContentRaw().substring(Identification.prefix.length() + this.getCommandPrefix(e).length()) + "!");
        builder.appendDescription("\n");
        
        builder.setFooter(e.getMessage().getContentRaw().substring(Identification.prefix.length() + this.getCommandPrefix(e).length()) + " is back!", e.getAuthor().getAvatarUrl());
        builder.setTimestamp(Instant.now());
        
        e.getChannel().sendMessage(builder.build()).queue();
		
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
