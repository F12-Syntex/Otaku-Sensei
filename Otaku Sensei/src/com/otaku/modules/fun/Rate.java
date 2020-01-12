package com.otaku.modules.fun;

import java.awt.Color;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.otaku.authentication.Wrapper;
import com.otaku.command.COMMAND_TYPE;
import com.otaku.command.Command;
import com.otaku.identification.Identification;
import com.otaku.utilities.RateData;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Rate extends Command{

	public Rate(Wrapper wrapper) {
		super(wrapper);
		// TODO Auto-generated constructor stub
	}
	private List<RateData> RateData = new ArrayList<RateData>();
	
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "rate";
	}

	@Override
	public String[] prefix() {
		// TODO Auto-generated method stub
		return new String[] {"rate"};
	}

	@Override
	public String desc() {
		// TODO Auto-generated method stub
		return "rates something";
	}
	
	@Override
	public COMMAND_TYPE type() {
		// TODO Auto-generated method stub
		return COMMAND_TYPE.FUN;
	}

	@Override
	public void invoke(MessageReceivedEvent e, String argument) {


        EmbedBuilder builder = new EmbedBuilder().setColor(Color.MAGENTA);
		
		if(argument.isEmpty()) {
			builder.setTitle("Invalid usage");
			builder.appendDescription("Usage: " + this.usage());
	        builder.setFooter("whoopsy, something happend", e.getAuthor().getAvatarUrl());
	        builder.setTimestamp(Instant.now());
	        MessageBuilder messagebuilder = new MessageBuilder();
	        messagebuilder.setEmbed(builder.build());
	        e.getChannel().sendMessage(builder.build()).queue();
			return;
		}
		
		String rate = argument.substring(1).toUpperCase();

	
		int RandomValue;
		
		if(this.containsKey(rate)) {
			RandomValue = this.getValue(rate).getValue();
		}else {
			RandomValue = ThreadLocalRandom.current().nextInt(10) + 1;
			this.RateData.add(new RateData(rate, RandomValue));
		}
		
		
		String emoji = "";
	
		if(RandomValue == 10) {
			emoji = ":sparkling_heart: ";
		}else if(RandomValue >= 5) {
			emoji = ":heart:";
		}else {
			emoji = ":broken_heart:";
		}
		
		builder.appendDescription(":thinking: hmmm... i rate **" + rate + "**" + " a " + RandomValue + "/" + "10! " + emoji);
		
		e.getChannel().sendMessage(builder.build()).queue();
		
	}
	

	@Override
	public Permission permission() {
		// TODO Auto-generated method stub
		return Permission.MESSAGE_WRITE;
	}

	@Override
	public String usage() {
		// TODO Auto-generated method stub
		return this.prefix()[0] + " <something>";
	}
	
	public boolean containsKey(String key) {
		for(RateData data : RateData) {
			if(data.getArgument().equals(key)) return true;
		}
		return false;
	}
	public RateData getValue(String key) {
		for(RateData data : RateData) {
			if(data.getArgument().equals(key)) return data;
		}
		return null;
	}

	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void emptyArguments(MessageReceivedEvent e) {
		// TODO Auto-generated method stub
        EmbedBuilder builder = new EmbedBuilder().setTitle("Invalid usage").setColor(Color.RED);
		
        builder.appendDescription("Usage: " + Identification.prefix + this.prefix + " <anime>");
        builder.appendDescription("\n");
        
        builder.setFooter("Whoopsy", e.getAuthor().getAvatarUrl());
        builder.setTimestamp(Instant.now());
        
        e.getChannel().sendMessage(builder.build()).queue();
		return;
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
