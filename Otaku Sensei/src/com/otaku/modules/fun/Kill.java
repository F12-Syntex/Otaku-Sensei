package com.otaku.modules.fun;

import java.awt.Color;
import java.time.Instant;

import com.otaku.authentication.Wrapper;
import com.otaku.command.COMMAND_TYPE;
import com.otaku.command.Command;
import com.otaku.identification.Identification;
import com.otaku.utilities.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Kill extends Command{

	public Kill(Wrapper wrapper) {
		super(wrapper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "kill";
	}

	@Override
	public String[] prefix() {
		// TODO Auto-generated method stub
		return new String[] {"kill"};
	}

	@Override
	public String desc() {
		// TODO Auto-generated method stub
		return "kills <user>";
	}
	
	@Override
	public COMMAND_TYPE type() {
		// TODO Auto-generated method stub
		return COMMAND_TYPE.FUN;
	}

	@Override
	public void invoke(MessageReceivedEvent e, String argument) {

		
		String name = argument.substring(1);
	
		
        EmbedBuilder builder = new EmbedBuilder().setColor(Color.MAGENTA);
		
        
        if(e.getAuthor().getName().equalsIgnoreCase(argument.substring(1))) {
        
            builder.appendDescription(":warning:" + "$uicide is never the answer!");
            
            builder.setFooter("Attempted $uicide...", e.getAuthor().getAvatarUrl());
            builder.setTimestamp(Instant.now());
            
            e.getChannel().sendMessage(builder.build()).queue();
            return;
        	
        }
        
        if(e.getMessage().getMentionedMembers().size() != 0) {
        	
        	Member MemberTagged = e.getMessage().getMentionedMembers().get(0);
        	
        	if(MemberTagged.getEffectiveName().equalsIgnoreCase(e.getMember().getEffectiveName())) {
                
                builder.appendDescription(":warning:" + "$uicide is never the answer!");
                
                builder.setFooter("Attempted $uicide...", e.getAuthor().getAvatarUrl());
                builder.setTimestamp(Instant.now());
                
                e.getChannel().sendMessage(builder.build()).queue();
                return;
            	
        	}
        	
        }
        
        
        if(e.getMessage().getMentionedMembers().size() == 0) {

            Member MemberTagged = e.getMessage().getMentionedMembers().get(0);

            name = MemberTagged.getAsMention();
        
        }
        
        
        
        if(Random.chance(30)) {
            builder.appendDescription(e.getAuthor().getName() + " brought a stick to a sword fight ");
            builder.appendDescription("\n");
            builder.appendDescription(e.getAuthor().getName() + " was killed by " + name);
            builder.appendDescription("\n");
            
            builder.setFooter("Attempted murder...", e.getAuthor().getAvatarUrl());
            builder.setTimestamp(Instant.now());
            
            e.getChannel().sendMessage(builder.build()).queue();
            return;
        }
        
        if(Random.chance(50)) {
            builder.appendDescription(e.getAuthor().getName() + "'s blade Almost killed " + name + " :dagger:");
            builder.setFooter("Attempted murder...", e.getAuthor().getAvatarUrl());
            builder.setTimestamp(Instant.now());
            
            e.getChannel().sendMessage(builder.build()).queue();
            return;
        }
        if(Random.chance(100)) {
            builder.appendDescription(e.getAuthor().getName() + " killed " + name + ":boom: :boom: :boom:");
            builder.appendDescription("\n");
            
            builder.setFooter("Attempted murder...", e.getAuthor().getAvatarUrl());
            builder.setTimestamp(Instant.now());
            
            e.getChannel().sendMessage(builder.build()).queue();
        } 
        
		
		
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
