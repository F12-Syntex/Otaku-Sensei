package com.otaku.authentication;

import com.otaku.command.CommandGenreHandler;
import com.otaku.identification.Identification;
import com.otaku.listeners.command.CommandInvoke;
import com.otaku.listeners.command.Modules;
import com.otaku.listeners.xp.TalkMoney;
import com.otaku.listeners.xp.TalkXP;
import com.otaku.stability.CommandDuplication;
import com.otaku.wrapper.Client;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class Wrapper implements EventListener{
	
	public JDA jda;
	
	private CommandGenreHandler genres;
	private CommandDuplication DuplicationHandler;
	
	public void init() throws Exception {
		
		this.genres = new CommandGenreHandler();
		this.genres.init();
		
		Modules.init(this);
		
		this.DuplicationHandler = new CommandDuplication(Modules.getModules());
		this.DuplicationHandler.init();
		
		//create the jda builder
		JDABuilder builder = new JDABuilder(Client.TOKEN);
	    
	    //set activity (like "playing Something")
	    builder.setActivity(Activity.watching("Hentai?!"));
	    
	    //add event listeners
	    builder.addEventListeners(this);
	    builder.addEventListeners(new CommandInvoke(this));
	    builder.addEventListeners(new TalkMoney());
	    builder.addEventListeners(new TalkXP());
	    
	    //build the jda
	    this.jda = builder.build();
	    
	    //prevents actions to be executed before connection has been established
	    this.jda.awaitReady();
	    
	
	}
	
    @Override
    public void onEvent(GenericEvent event){
        if (event instanceof ReadyEvent) {
            System.out.println(Identification.name + " is active");
        }
    }

	public JDA getJda() {
		return jda;
	}

	public void setJda(JDA jda) {
		this.jda = jda;
	}

	public CommandGenreHandler getGenres() {
		return genres;
	}

	public void setGenres(CommandGenreHandler genres) {
		this.genres = genres;
	}

}
