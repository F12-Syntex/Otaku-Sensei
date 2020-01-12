package com.otaku.animelist;

import java.awt.Color;
import java.time.Instant;

import org.json.JSONArray;
import org.json.JSONObject;

import com.otaku.authentication.Wrapper;
import com.otaku.command.COMMAND_TYPE;
import com.otaku.command.Command;
import com.otaku.connection.Connection;
import com.otaku.identification.Identification;
import com.otaku.utilities.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Manga extends Command{

	public Manga(Wrapper wrapper) {
		super(wrapper);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "manga";
	}

	@Override
	public String[] prefix() {
		// TODO Auto-generated method stub
		return new String[] {"manga", "mangasearch"};
	}

	@Override
	public String desc() {
		// TODO Auto-generated method stub
		return "returns a manga's data";
	}

	@Override
	public COMMAND_TYPE type() {
		// TODO Auto-generated method stub
		return COMMAND_TYPE.SEARCH;
	}

	@Override
	public void invoke(MessageReceivedEvent e, String argument) {
		
		String text = argument;
		
		
		text = text.replaceAll("\\s+","-");
		text = text.toLowerCase();
		
	
		try {
			
			System.out.println("https://api.jikan.moe/v3/search/manga?q=" + text + "/&page=1");
			
            JSONObject json = Connection.connect("https://api.jikan.moe/v3/search/manga?q=" + text + "/&page=1");
	        
            int ID = json.getJSONArray("results").getJSONObject(0).getInt("mal_id");
            
            JSONObject info = Connection.connect("https://api.jikan.moe/v3/manga/" + ID);
            
            System.out.println(info);
            
            String title = info.getString("title");
            
            Object Description = info.get("synopsis").toString().replace("null", "`N/A`");
            Object imageURL = info.get("image_url").toString().replace("null", "`N/A`");
            Object rank = info.get("rank").toString().replace("null", "`N/A`");
            Object status = info.get("status").toString().replace("null", "`N/A`");
            Object type = info.get("type").toString().replace("null", "`N/A`");
            Object volumes = info.get("volumes").toString().replace("null", "`N/A`");
            Object chapters = info.get("chapters").toString().replace("null", "`N/A`");
            Object publishing = info.get("publishing").toString().replace("null", "`N/A`");
            Object favorites = info.get("favorites").toString().replace("null", "`N/A`");
            
            String title_japanese =  info.getString("title_japanese").toString().replace("null", "`N/A`");
            
            Object syn;
            
            if(info.getJSONArray("title_synonyms").length() == 0) {
            	syn = title;
            }else {
            	syn = info.getJSONArray("title_synonyms").get(0).toString().replace("null", "`N/A`");
            }
            
            
            double score = -1;
            
            
            try {
            	
            score =  info.getDouble("score");
            
            }catch (org.json.JSONException event) {
				
			}
            
            
            
            
            
            
            
            StringBuilder genresBuilder = new StringBuilder();

            JSONArray genres =  info.getJSONArray("genres");
            
            for(int i = 0; i < genres.length(); i++) {
            	if(!(i < (genres.length() - 1))) {
            		genresBuilder.append("#" + genres.getJSONObject(i).getString("name"));
                    break;		
            	}
            		genresBuilder.append("#" + genres.getJSONObject(i).getString("name") + ", ");
            }	
						
			EmbedBuilder builder;
			
			if(syn.equals(title)) {
				builder = new EmbedBuilder().setTitle("**" + title + " #" + rank + "**", "https://myanimelist.net/manga/" + ID).setColor(Color.GREEN);
			}else {
				builder = new EmbedBuilder().setTitle("**" + title + " (" + syn + ")"+ " #" + rank + "**", "https://myanimelist.net/manga/" + ID).setColor(Color.GREEN);
			}
	        
	        builder.appendDescription(Description + "");
	        
	        
	        
	        
	        builder.addField("Type", type+"", true);
	        builder.addField("Japanese Title", title_japanese+"", true);
	        

	        builder.addField("Publishing", Random.booleanToYesOrNo(publishing+"") + "", true);
	        builder.addField("Status", status + "", true);
	        
	        builder.addField("Volume(s)", volumes + "", true);
	        builder.addField("Chapter(s)", chapters + "", true);
	        
	        
	        if(score < 0) {
		        builder.addField("Rating", "N/A", true);
	        }else {
		        builder.addField("Rating", score + " (" + this.getRatingScore(score) + ")", true);	        	
	        }
	        
	        builder.addField("Likes", favorites+"", true);	    
	        //favorites
	        
	        builder.addField("Genres", genresBuilder.toString(), false);
	        builder.setThumbnail(imageURL+"");
	        
	        
	        builder.setFooter("found the manga your looking for?", e.getAuthor().getAvatarUrl());
	        builder.setTimestamp(Instant.now());
	        
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

	private String getRatingScore(Double rating) {
		
		String RatingScore = "Unknown";
		
		int ratingAsInteger = (int) rating.doubleValue();
		
		if(ratingAsInteger == 10) RatingScore = "Masterpiece";
		if(ratingAsInteger == 9) RatingScore = "Great";
		if(ratingAsInteger == 8) RatingScore = "Very good";
		if(ratingAsInteger == 7) RatingScore = "Good";
		if(ratingAsInteger == 6) RatingScore = "Average";
		if(ratingAsInteger == 5) RatingScore = "Bad";
		if(ratingAsInteger == 4) RatingScore = "Very Bad";
		if(ratingAsInteger == 3) RatingScore = "Horrible";
		if(ratingAsInteger == 2) RatingScore = "Horrible";
		if(ratingAsInteger == 1) RatingScore = "Appaling";
		
		return RatingScore;
		
	}
	
	@Override
	public Permission permission() {
		// TODO Auto-generated method stub
		return Permission.MESSAGE_WRITE;
	}
	@Override
	public String usage() {
		// TODO Auto-generated method stub
		return this.prefix()[0] + " <search>";
	}

	@Override
	public int ID() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return true;
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
	
}
