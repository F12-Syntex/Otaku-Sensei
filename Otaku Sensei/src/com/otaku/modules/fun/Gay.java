package com.otaku.modules.fun;

import java.awt.Color;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

import com.otaku.authentication.Wrapper;
import com.otaku.command.COMMAND_TYPE;
import com.otaku.command.Command;
import com.otaku.identification.Identification;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Gay extends Command{

	public Gay(Wrapper wrapper) {
		super(wrapper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "gay tracker";
	}

	@Override
	public String[] prefix() {
		// TODO Auto-generated method stub
		return new String[] {"gaycheck"};
	}

	@Override
	public String desc() {
		// TODO Auto-generated method stub
		return "checks the gay levels of a user";
	}

	@Override
	public COMMAND_TYPE type() {
		// TODO Auto-generated method stub
		return COMMAND_TYPE.FUN;
	}

	@Override
	public void invoke(MessageReceivedEvent e, String argument) {
		// TODO Auto-generated method stub
		
		if(e.getMessage().getMentionedMembers().size() == 0) {
			
			this.emptyArguments(e);
			return;
			
		}

		String loader = "█";
		
		StringBuilder metre = new StringBuilder();
		
		int level = ThreadLocalRandom.current().nextInt(10);
		
		int decimal = ThreadLocalRandom.current().nextInt(10);
		
		for(int i = 0; i < level; i++) {
			metre.append(loader);
		}
		
		Member mentionedUser = e.getMessage().getMentionedMembers().get(0);
		
		String conclusion = "";
		
		if(level <= 1) {
			conclusion = "Straight";
		}else if(level == 2) {
			conclusion = "Straight";
		}else if(level == 3) {
			conclusion = "Doubtfull";
		}else if(level == 4) {
			conclusion = "Questionable";
		}else if(level == 5) {
			conclusion = "Tingly";
		}else if(level == 6) {
			conclusion = "Gay";
		}else if(level == 7) {
			conclusion = "Very gay!";
		}else if(level == 8) {
			conclusion = "dude fettish";
		}else if(level >= 9) {
			conclusion = "slutty guy ( dude fettish )";
		}
		
        EmbedBuilder builder = new EmbedBuilder().setTitle("â�¯  Gay checker â�®").setColor(Color.PINK);
        
        builder.appendDescription("Here's what i think of this person");

        builder.addField("User", mentionedUser.getEffectiveName(), false);
        builder.addField("Gay metre", metre.toString(), true);
        builder.addField("Gay Score", level + "" + decimal + "/100", true);
        builder.addField("Conclusion", conclusion, false);
        
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
		return this.prefix()[0] + " <user>";
	}

	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void emptyArguments(MessageReceivedEvent e) {
		
        EmbedBuilder builder = new EmbedBuilder().setTitle("Invalid usage").setColor(Color.RED);
		
        builder.appendDescription("Usage: " + Identification.prefix + this.usage());
        builder.appendDescription("\n");
        
        builder.setFooter("please @ someone senpai", e.getAuthor().getAvatarUrl());
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
