package com.otaku.modules.currency;

import java.awt.Color;

import com.otaku.accounts.UserAccounts;
import com.otaku.authentication.Wrapper;
import com.otaku.command.COMMAND_TYPE;
import com.otaku.command.Command;
import com.otaku.utilities.Money;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Balance extends Command{

	public Balance(Wrapper wrapper) {
		super(wrapper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "balance";
	}

	@Override
	public String[] prefix() {
		// TODO Auto-generated method stub
		return new String[] {"balance", "bal", "money", "cash", "tokens", "currency"};
	}

	@Override
	public String desc() {
		// TODO Auto-generated method stub
		return "balance";
	}
	
	@Override
	public COMMAND_TYPE type() {
		// TODO Auto-generated method stub
		return COMMAND_TYPE.CURRENCY;
	}

	@Override
	public void invoke(MessageReceivedEvent e, String argument) {
		
			EmbedBuilder builder = new EmbedBuilder().setColor(Color.MAGENTA);

					if(e.getMessage().getMentionedUsers().size() > 0) {
						
						User user = e.getMessage().getMentionedMembers().get(0).getUser();
			            
						if(user.getId().equals(e.getAuthor().getId())) {
				            builder.appendDescription("Your balance is: **" + UserAccounts.getAccount(e.getMember().getUser(), e.getGuild()).getMoney().getAmount() + Money.CURRECY_SYMBOL + "**");
				            e.getChannel().sendMessage(builder.build()).queue();
				            return;
						}
						
						
						builder.appendDescription(user.getName() + "'s balance is: **" + UserAccounts.getAccount(user, e.getGuild()).getMoney().getAmount() + Money.CURRECY_SYMBOL + "**");
			            e.getChannel().sendMessage(builder.build()).queue();
			            return;
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
		return this.prefix()[0] + " [@User]";
	}

	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void emptyArguments(MessageReceivedEvent e) {
		// TODO Auto-generated method stub
		
		EmbedBuilder builder = new EmbedBuilder().setColor(Color.MAGENTA);
		
        builder.appendDescription("Your balance is: **" + UserAccounts.getAccount(e.getMember().getUser(), e.getGuild()).getMoney().getAmount() + Money.CURRECY_SYMBOL + "**");
        
        e.getChannel().sendMessage(builder.build()).queue();
		
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
