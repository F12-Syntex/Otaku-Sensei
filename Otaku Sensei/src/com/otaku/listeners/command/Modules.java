package com.otaku.listeners.command;

import java.util.ArrayList;
import java.util.List;

import com.otaku.animelist.Anime;
import com.otaku.animelist.Catogary;
import com.otaku.animelist.Manga;
import com.otaku.authentication.Wrapper;
import com.otaku.command.Command;
import com.otaku.modules.currency.Balance;
import com.otaku.modules.fun.Flip;
import com.otaku.modules.fun.Gay;
import com.otaku.modules.fun.Kill;
import com.otaku.modules.fun.Rate;
import com.otaku.modules.fun.Revive;
import com.otaku.modules.help.Otaku;
import com.otaku.modules.help.help;

public class Modules {
	
	public static List<Command> modules = new ArrayList<Command>();
	
	public static void register(Command module) {
		Modules.modules.add(module);
	}
	public static List<Command> getModules(){
		return Modules.modules;
	}
	
	
	public static void init(Wrapper wrapper) {
		
		//register help commands
		new help(wrapper); 
		new Otaku(wrapper);
		
		//register search commands
		new Anime(wrapper);
		new Manga(wrapper);
		new Catogary(wrapper);
		
		//register fun commands
		new Flip(wrapper);
		new Gay(wrapper);
		new Kill(wrapper);
		new Rate(wrapper);
		new Revive(wrapper);
		
		//register currency commands
		new Balance(wrapper);
		
	}
	
	

}
