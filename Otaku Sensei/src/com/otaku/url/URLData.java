package com.otaku.url;

import java.util.ArrayList;
import java.util.List;

public class URLData {

	private List<String> URL;
	
	public URLData() {
	
		this.URL = new ArrayList<String>();	
	}
	
	public void registerURL(String URL) {
		this.URL.add(URL);
	}
	public void removeURL(String URL) {
		this.URL.remove(URL);
	}

	public List<String> getURL() {
		return URL;
	}
	
	public void setURL(List<String> uRL) {
		URL = uRL;
	}
	
	
}

