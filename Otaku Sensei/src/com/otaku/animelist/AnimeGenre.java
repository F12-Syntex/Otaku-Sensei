package com.otaku.animelist;

import java.util.ArrayList;
import java.util.List;

public class AnimeGenre {

	private List<String> names = new ArrayList<String>();
	
	public AnimeGenre() {
		
		this.names.add("Action Anime");
		this.names.add("Adventure Anime");
		this.names.add("Cars Anime");
		this.names.add("Comedy Anime");
		this.names.add("Dementia Anime");
		this.names.add("Demons Anime");
		this.names.add("Mystery Anime");
		this.names.add("Drama Anime");
		this.names.add("Ecchi Anime");
		this.names.add("Fantasy Anime");
		this.names.add("Game Anime");
		this.names.add("Hentai Anime");
		this.names.add("Historical Anime");
		this.names.add("Horror Anime");
		this.names.add("Kids Anime");
		this.names.add("Magic Anime");
		this.names.add("Martial Arts Anime");
		this.names.add("Mecha Anime");
		this.names.add("Music Anime");
		this.names.add("Parody Anime");
		this.names.add("Samurai Anime");
		this.names.add("Romance Anime");
		this.names.add("School Anime");
		this.names.add("Sci-Fi Anime");
		this.names.add("Shoujo Anime");
		this.names.add("Shoujo Ai Anime");
		this.names.add("Shounen Anime");
		this.names.add("Shounen Ai Anime");
		this.names.add("Space Anime");
		this.names.add("Sports Anime");
		this.names.add("Super Power Anime");
		this.names.add("Vampire Anime");
		this.names.add("Yaoi Anime");
		this.names.add("Yuri Anime");
		this.names.add("Harem Anime");
		this.names.add("Slice of Life Anime");
		this.names.add("Supernatural Anime");
		this.names.add("Military Anime");
		this.names.add("Police Anime");
		this.names.add("Psychological Anime");
		this.names.add("Thriller Anime");
		this.names.add("Seinen Anime");
		this.names.add("Josei Anime");
		
	}
	
	public String getById(int id) {
		return this.names.get(id+1);
	}
	
	public int getIdByName(String name) {
		for(int i = 0; i < this.names.size(); i++) {
			if(this.names.get(i).equalsIgnoreCase(name)) return (i+1);
			if(this.names.get(i).replace("Anime", "").replace(" ", "").equalsIgnoreCase(name)) return (i+1);
			if(this.names.get(i).replace("Anime", "").replace(" ", "").toUpperCase().startsWith(name.toUpperCase())) return (i+1);
		}
		return 0;
	}
	
	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
	
}
