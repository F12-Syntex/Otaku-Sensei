package com.otaku.animelist;

import java.util.ArrayList;
import java.util.List;

public class MangaGenre {

	private List<String> names = new ArrayList<String>();
	
	public MangaGenre() {
		
		this.names.add("Action Manga");
		this.names.add("Adventure Manga");
		this.names.add("Cars Manga");
		this.names.add("Comedy Manga");
		this.names.add("Dementia Manga");
		this.names.add("Demons Manga");
		this.names.add("Mystery Manga");
		this.names.add("Drama Manga");
		this.names.add("Ecchi Manga");
		this.names.add("Fantasy Manga");
		this.names.add("Game Manga");
		this.names.add("Hentai Manga");
		this.names.add("Historical Manga");
		this.names.add("Horror Manga");
		this.names.add("Kids Manga");
		this.names.add("Magic Manga");
		this.names.add("Martial Arts Manga");
		this.names.add("Mecha Manga");
		this.names.add("Music Manga");
		this.names.add("Parody Manga");
		this.names.add("Samurai Manga");
		this.names.add("Romance Manga");
		this.names.add("School Manga");
		this.names.add("Sci-Fi Manga");
		this.names.add("Shoujo Manga");
		this.names.add("Shoujo Ai Manga");
		this.names.add("Shounen Manga");
		this.names.add("Shounen Ai Manga");
		this.names.add("Space Manga");
		this.names.add("Sports Manga");
		this.names.add("Super Power Manga");
		this.names.add("Vampire Manga");
		this.names.add("Yaoi Manga");
		this.names.add("Yuri Manga");
		this.names.add("Harem Manga");
		this.names.add("Slice of Life Manga");
		this.names.add("Supernatural Manga");
		this.names.add("Military Manga");
		this.names.add("Police Manga");
		this.names.add("Psychological Manga");
		this.names.add("Seinen Manga");
		this.names.add("Josei Manga");
		this.names.add("Doujinshi Manga");
		this.names.add("Gender Bender Manga");
		this.names.add("Thriller Manga");
		
	}
	
	public String getById(int id) {
		return this.names.get(id+1);
	}
	
	public int getIdByName(String name) {
		for(int i = 0; i < this.names.size(); i++) {
			if(this.names.get(i).equalsIgnoreCase(name)) return (i+1);
			if(this.names.get(i).replace("Manga", "").replace(" ", "").equalsIgnoreCase(name)) return (i+1);
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
