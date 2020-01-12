package com.otaku.modules.help;

import java.awt.Color;

import com.otaku.authentication.Wrapper;
import com.otaku.command.COMMAND_TYPE;
import com.otaku.command.Command;
import com.otaku.identification.Identification;
import com.otaku.utilities.Facts;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Otaku extends Command{

	public Otaku(Wrapper wrapper) {
		super(wrapper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Otaku sensei";
	}

	@Override
	public String[] prefix() {
		// TODO Auto-generated method stub
		return new String[] {"otaku", "bestbotever", "invite"};
	}

	@Override
	public String desc() {
		// TODO Auto-generated method stub
		return "returns the bots description";
	}
	
	@Override
	public COMMAND_TYPE type() {
		// TODO Auto-generated method stub
		return COMMAND_TYPE.HELP;
	}

	@Override
	public void invoke(MessageReceivedEvent event, String argument) {
		
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
		return 5;
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void emptyArguments(MessageReceivedEvent e) {

		
		String invite = "https://discordapp.com/api/oauth2/authorize?client_id=664491202858713113&permissions=8&scope=bot";
		
        EmbedBuilder builder = new EmbedBuilder().setTitle(Identification.name, invite);
        
        builder.appendDescription(Facts.getDescription());
        
        builder.appendDescription("\n");
        builder.appendDescription("\n");
        builder.addField("Japanese Name", "オタク先生", true).addField("English Name", "Otaku Sensei", true);
        
        builder.setColor(Color.PINK);
        builder.setFooter("Quick tip, '" + Facts.quickTip() +"'", e.getAuthor().getAvatarUrl());
        builder.setImage("https://giffiles.alphacoders.com/183/183296.gif");
        
        e.getChannel().sendMessage(builder.build()).queue();	
		
		
	}

}
