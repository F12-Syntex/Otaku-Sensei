package com.otaku.utilities;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class Users {
	
	public static String getUserPermissions(JDA jda, TextChannel channel) {
		
		StringBuilder sb = new StringBuilder();
	
		int i = 1;
		
		for(Member member : channel.getMembers()) {

			i = 1;
			
			for(Permission permission : member.getPermissions()) {
				sb.append("\n");
				sb.append("[" + member.getEffectiveName() + "] Permission(" + i + ")" + " -> " + permission.toString());
				i++;
			}
			
			
		}
		
		return sb.toString();
	}

}
