package com.otaku.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class Connection {

	  public static JSONObject connect(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = read(rd);
		      
		      
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    } finally {
		      is.close();
		    }
		  }
	  
	  
	  
	  private static String read(Reader connection) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = connection.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
	  }
	  
	  
	
}
