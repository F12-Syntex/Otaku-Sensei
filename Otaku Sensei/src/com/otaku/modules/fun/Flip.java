package com.otaku.modules.fun;

import java.awt.Color;
import java.time.Instant;
import java.util.Random;

import com.otaku.authentication.Wrapper;
import com.otaku.command.COMMAND_TYPE;
import com.otaku.command.Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Flip extends Command{

	public Flip(Wrapper wrapper) {
		super(wrapper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "flip";
	}

	@Override
	public String[] prefix() {
		// TODO Auto-generated method stub
		return new String[] {"flip"};
	}

	@Override
	public String desc() {
		// TODO Auto-generated method stub
		return "returns the value of a flipped coin";
	}

	@Override
	public COMMAND_TYPE type() {
		// TODO Auto-generated method stub
		return COMMAND_TYPE.FUN;
	}

	@Override
	public void invoke(MessageReceivedEvent e, String argument) {
		// TODO Auto-generated method stub
	
		this.emptyArguments(e);
		
	}
	

	@Override
	public Permission permission() {
		// TODO Auto-generated method stub
		return Permission.MESSAGE_WRITE;
	}

	@Override
	public String usage() {
		// TODO Auto-generated method stub
		return this.prefix()[0];
	}

	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void emptyArguments(MessageReceivedEvent e) {
		// TODO Auto-generated method stub
		String flag = "";
		
        
		if(new Random().nextBoolean()) {
			flag = "Heads";
		}else {
			flag = "Tails";
		}
		
		
        EmbedBuilder builder = new EmbedBuilder().setTitle("Coin flip...").setColor(Color.GREEN);
		
        builder.appendDescription("Flipping a coin.... :game_die:");
        builder.appendDescription("\n");
        builder.appendDescription("\n");
        builder.appendDescription("Coin landed on **_" + flag +"_**");
        
        builder.setFooter("Coin flip", e.getAuthor().getAvatarUrl());
        builder.setTimestamp(Instant.now());
        
        e.getChannel().sendMessage(builder.build()).queue();
		
		
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
