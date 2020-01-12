package com.otaku.listeners.xp;

import com.otaku.accounts.UserAccounts;
import com.otaku.accounts.UserData;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TalkXP extends ListenerAdapter{
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		User user = e.getAuthor();
		Guild guild = e.getGuild();
		
		UserData account = UserAccounts.getAccount(user, guild);
		
		account.getXp().give();
		account.save(guild);
		
	}

}
