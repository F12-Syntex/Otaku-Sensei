package com.otaku.animelist;

import java.awt.Color;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONObject;

import com.otaku.authentication.Wrapper;
import com.otaku.command.COMMAND_TYPE;
import com.otaku.command.Command;
import com.otaku.connection.Connection;
import com.otaku.identification.Identification;
import com.otaku.utilities.Numbers;
import com.otaku.utilities.StringMinipulation;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Catogary extends Command{

	public Catogary(Wrapper wrapper) {
		super(wrapper);
		// TODO Auto-generated constructor stub
	}

	private AnimeGenre AnimeGenre = new AnimeGenre();
	private MangaGenre MangaGenre = new MangaGenre();
	
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "catog";
	}

	@Override
	public String[] prefix() {
		// TODO Auto-generated method stub
		return new String[] {"catog", "genres"};
	}

	@Override
	public String desc() {
		// TODO Auto-generated method stub
		return "returns a genres data or lists them";
	}

	@Override
	public COMMAND_TYPE type() {
		// TODO Auto-generated method stub
		return COMMAND_TYPE.SEARCH;
	}

	@Override
	public void invoke(MessageReceivedEvent e, String argument) {
		
		String text[] = argument.split(" ");
		
		if(text.length < 2) {
	        EmbedBuilder builder = new EmbedBuilder().setTitle("Invalid usage").setColor(Color.RED);
			
	        builder.appendDescription("Usage: " + Identification.prefix + this.usage());
	        builder.appendDescription("\n");
	        
	        e.getChannel().sendMessage(builder.build()).queue();
			return;
		}
		
		
		if(!(text[0].equalsIgnoreCase("anime") || text[0].equalsIgnoreCase("manga"))) {
	        EmbedBuilder builder = new EmbedBuilder().setTitle("Invalid usage").setColor(Color.RED);
			
	        builder.appendDescription("Usage: " + Identification.prefix + this.usage());
	        builder.appendDescription("\n");
	        
	        e.getChannel().sendMessage(builder.build()).queue();
			return;
		}
		
		try {
            
			
			if(text[1].equalsIgnoreCase("list")) {
				
	            String title = "Types";
	            
	            EmbedBuilder builder = new EmbedBuilder();
	            
	            builder.setTitle(title);
	            
	            if(text[0].equalsIgnoreCase("anime")) {
	            
	            	List<String> names = this.AnimeGenre.getNames();
	            	
	            	for(int i = 0; i < names.size(); i++) {
	            		
	            		if(i % 3 == 0) {
	            		builder.appendDescription("\n");	
	            		}
	            		
	            		
	            		if((i+1) < names.size()) {
		            		builder.appendDescription("`" + (i+1) + "`. " + names.get(i) + ", ");	
	            		}else {
		            		builder.appendDescription("`" + (i+1) + "`. " + names.get(i));
	            		}
	            		
	            	}
	                	
	            }else {
	            
	            	List<String> names = this.MangaGenre.getNames();
	            	
	            	for(int i = 0; i < names.size(); i++) {
	            		
	            		if(i % 3 == 0) {
	            		builder.appendDescription("\n");	
	            		}
	            		
	            		
	            		if((i+1) < names.size()) {
		            		builder.appendDescription("`" + (i+1) + "`. " + names.get(i) + ", ");	
	            		}else {
		            		builder.appendDescription("`" + (i+1) + "`. " + names.get(i));
	            		}
	            		
	            	}
            	
	            }
	            e.getChannel().sendMessage(builder.build()).queue();
				return;
			
			}
			
			int ID;
			
			if(text[0].equalsIgnoreCase("anime")) {
				ID = AnimeGenre.getIdByName(text[1]);
			}else {
				ID = MangaGenre.getIdByName(text[1]);
			}
			
			
			if(ID == 0) ID = Integer.parseInt(text[1]);
			
            JSONObject info = Connection.connect("https://api.jikan.moe/v3/genre/" + text[0] + "/" + ID);
            
            System.out.println(info);
            
            EmbedBuilder builder = new EmbedBuilder();

            String title = info.getJSONObject("mal_url").getString("name");
            String Type = info.getJSONObject("mal_url").getString("type");
            String URL = info.getJSONObject("mal_url").getString("url");
            int item_count = info.getInt("item_count");
            
            int randomIndex = ThreadLocalRandom.current().nextInt(info.getJSONArray(text[0]).length());
            
            JSONArray Anime = info.getJSONArray(text[0]);
            
            String Image = Anime.getJSONObject(randomIndex).getString("image_url");
            
            builder.setTitle(title, URL);
            
            builder.addField("Type", StringMinipulation.capitalize(Type), true);
            builder.addField("Animes", item_count+"", true);
            
            StringBuilder recommendations = new StringBuilder();

            Integer[] index = Numbers.getRandomIndexs(Anime.length());
            
            for(int i = 0; i < 10; i++) {
            	
            	String Current_title = Anime.getJSONObject(index[i]).getString("title");
            	String Current_Type = Anime.getJSONObject(index[i]).getString("type");
            	
            	recommendations.append("`" + (i+1) + "`. " + Current_title + " (" + Current_Type + ")");
            	recommendations.append("\n");
            }
            
            
            builder.addField("Recommendation(s)", recommendations.toString(), false);
            
            builder.setThumbnail(Image);
            
            
	        e.getChannel().sendMessage(builder.build()).queue();
			return;
		
		
		} catch (Throwable event) {

			event.printStackTrace();
			
	        EmbedBuilder builder = new EmbedBuilder().setTitle("Error").setColor(Color.RED);
			
	        builder.appendDescription("Server returned HTTP response code: 500. \n");
	        builder.appendDescription("Please try again later.");
	        builder.appendDescription("\n");
	        
	        builder.setFooter("Whoopsy", e.getAuthor().getAvatarUrl());
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
		return this.prefix()[0] + " <anime/manga> <name/id/list>";
	}

	public AnimeGenre getAnimeGenre() {
		return AnimeGenre;
	}

	public void setAnimeGenre(AnimeGenre animeGenre) {
		AnimeGenre = animeGenre;
	}

	public MangaGenre getMangaGenre() {
		return MangaGenre;
	}

	public void setMangaGenre(MangaGenre mangaGenre) {
		MangaGenre = mangaGenre;
	}

	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void emptyArguments(MessageReceivedEvent e) {
	
	        EmbedBuilder builder = new EmbedBuilder().setTitle("Invalid usage").setColor(Color.RED);
			
	        builder.appendDescription("Usage: " + Identification.prefix + this.usage());
	        builder.appendDescription("\n");
	        
	        e.getChannel().sendMessage(builder.build()).queue();
			return;	
	}
	
}
