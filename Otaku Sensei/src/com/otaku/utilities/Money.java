package com.otaku.utilities;

import java.util.ArrayList;
import java.util.List;

import com.otaku.accounts.UserAccounts;
import com.otaku.accounts.UserData;

import net.dv8tion.jda.api.entities.Guild;

public class Money {
	
	public static String CURRECY_SYMBOL = "¥";
	
	public static List<UserData> getTopBalance(Guild guild, int MAX){
		
		List<UserData> accounts = UserAccounts.getAccounts(guild);
		
		List<UserData> top = new ArrayList<UserData>();
		
		for(int x = 0; x < MAX; x++) {
	
			UserData currentHigh = new UserData("", "", 0, 0);
			
			for(int i = 0; i < accounts.size(); i++) {
			
				if(i == 0) {
					currentHigh = accounts.get(i);
					continue;
				}
				
				UserData currentAccount = accounts.get(i);
				
				if(currentHigh.getMoney().getAmount() < currentAccount.getMoney().getAmount()) {
					currentHigh = currentAccount;
				}
			}
			
			if(currentHigh.getID().equals("")) {
				break;
			}
			
			top.add(currentHigh);
			accounts.remove(currentHigh);
			
	}
		
		return top;
		
	}

}
