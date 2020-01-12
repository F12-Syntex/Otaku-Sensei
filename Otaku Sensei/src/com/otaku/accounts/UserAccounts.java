package com.otaku.accounts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.otaku.utilities.JsonConfiguration;
import com.otaku.utilities.StringMinipulation;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public class UserAccounts {
	
	private static final String SOURCE_JSON = "C:\\Users\\synte\\Dropbox\\Discord_dev\\Otaku Sensei\\configuration\\UserData\\guilds";
	
	private static Map<User, List<Guild>> users = new HashMap<User, List<Guild>>();
	
	public static UserData getAccount(User user, Guild guild) {
		
		
		try {
			
		    String GuildFormattedName = guild.getName().replace(" ", "_") + "-" + guild.getId();
		    String parentpath = SOURCE_JSON + "\\" + GuildFormattedName;	    
		    String filePath = parentpath + "\\" + JsonConfiguration.getFileExtension(JsonConfiguration.Accounts) + ".json";
		
		    boolean verifiedUser = false;
		    
		    if(users.containsKey(user)){
		    	if(users.get(user).contains(guild)) {
		    		verifiedUser = true;
		    	}
		    }
		    
		if(!verifiedUser) {
		verify(guild, user);
		
		if(users.containsKey(user)){
			List<Guild> guilds = users.get(user);
			guilds.add(guild);
			users.put(user, guilds);
		}else {
			List<Guild> guilds = new ArrayList<Guild>();
			guilds.add(guild);
			users.put(user, guilds);
		}
	
		}
		
		
		JSONObject userData = new JSONObject(getJSON(filePath)).getJSONObject("data").getJSONObject(user.getId());
		
		String ID = user.getId();
		
		UserData data = new UserData(userData, ID);
		
		
		
		
		return data;
		
		
		}catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static List<UserData> getAccounts(Guild guild) {
		
		try {
			
		    String GuildFormattedName = guild.getName().replace(" ", "_") + "-" + guild.getId();
		    String parentpath = SOURCE_JSON + "\\" + GuildFormattedName;	    
		    String filePath = parentpath + "\\" + JsonConfiguration.getFileExtension(JsonConfiguration.Accounts) + ".json";
		
		
		    List<UserData> UserDatas = new ArrayList<UserData>();
		    
		    for(Member i : guild.getMembers()) {
		    
		    	User user = i.getUser();
		    	
		    verify(guild, user);
		    
		
		JSONObject userData = new JSONObject(getJSON(filePath)).getJSONObject("data").getJSONObject(user.getId());
		
		String ID = user.getId();
		
		UserData data = new UserData(userData, ID);
		
		UserDatas.add(data);
		
		    }
		
		return UserDatas;
		
		
		}catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static void verify(Guild guild, User member) throws Throwable {
	
	    String GuildFormattedName = guild.getName().replace(" ", "_") + "-" + guild.getId();
	    String parentpath = SOURCE_JSON + "\\" + GuildFormattedName;
	    
	    String file = JsonConfiguration.getFileExtension(JsonConfiguration.Accounts);
	    
	    String filePath = parentpath + "\\" + file + ".json";
	    
		File source = new File(filePath);
		

		if(!source.getParentFile().exists()) {
			source.getParentFile().mkdirs();
		}
		
		if(!source.exists()) {
			source.createNewFile();
			UserAccounts.writeMainData(source);
		}
		
			JSONObject userFile = new JSONObject(UserAccounts.getJSON(filePath)).getJSONObject("data"); //store userdata
			JSONObject defualtFile = new JSONObject(UserAccounts.getJSON(filePath)); //store mainData
		
			
			JSONObject thisUsersData = new JSONObject(); //userData	
			
			String userID = member.getId();

			UserData data = new UserData(member.getName(), userID, 0 , 0);
			
			
			if(userFile.has(member.getId())) {
				
				thisUsersData = userFile.getJSONObject(data.getID());
				
				if(!thisUsersData.has("name")) thisUsersData.put("name", data.getName());
				if(!thisUsersData.has("currency")) thisUsersData.put("currency", data.getMoney().getAmount());
				if(!thisUsersData.has("xp")) thisUsersData.put("xp", data.getXp().getAmount());

			}else {
			
			
			thisUsersData.put("name", data.getName()); //add Name
			thisUsersData.put("currency", data.getMoney().getAmount()); //add Money
			thisUsersData.put("xp", data.getXp().getAmount()); //add xp
			
			}
			
			
			userFile.put(data.getID(), thisUsersData); //add User to userData
			defualtFile.put("data", userFile); //addUserData
			
		    BufferedWriter writer = new BufferedWriter(new FileWriter(source, false));
		    
		    String convertedJson[] = StringMinipulation.prettyPrint(defualtFile.toString()).split("\n");
		    
		    for(String i : convertedJson) {
		        writer.write(i);
		        writer.newLine();
		    }
		    
		    writer.close();
			
		
	}
	
	public static void verify(Guild guild, UserData userDataFile) throws Throwable {
		
	    String GuildFormattedName = guild.getName().replace(" ", "_") + "-" + guild.getId();
	    String parentpath = SOURCE_JSON + "\\" + GuildFormattedName;
	    
	    String filePath = parentpath + "\\" + JsonConfiguration.getFileExtension(JsonConfiguration.Accounts) + ".json";
	    
		File source = new File(filePath);
		

		if(!source.getParentFile().exists()) {
			source.getParentFile().mkdirs();
		}
		
		if(!source.exists()) {
			source.createNewFile();
			UserAccounts.writeMainData(source);
		}
		
			JSONObject userFile = new JSONObject(UserAccounts.getJSON(filePath)).getJSONObject("data"); //store userdata
			JSONObject defualtFile = new JSONObject(UserAccounts.getJSON(filePath)); //store mainData
		
			
			JSONObject thisUsersData = new JSONObject(); //userData
			
			UserData data = userDataFile;
			
			
			//store Userdata to a json
			
			thisUsersData.put("name", data.getName()); //add Name
			thisUsersData.put("currency", data.getMoney().getAmount()); //add Money
			thisUsersData.put("xp", data.getXp().getAmount()); //add xp

			
			
			
			userFile.put(data.getID(), thisUsersData); //add User to userData
			
			defualtFile.put("data", userFile); //addUserData
			
		    BufferedWriter writer = new BufferedWriter(new FileWriter(source, false));
		    
		    String convertedJson[] = StringMinipulation.prettyPrint(defualtFile.toString()).split("\n");
		    
		    for(String i : convertedJson) {
		        writer.write(i);
		        writer.newLine();
		    }
		    
		        writer.close();
			
		
	}
	
	
	public static void writeMainData(File file) throws IOException {
		
		JSONObject main = new JSONObject();
		JSONObject userData = new JSONObject();
		
		main.put("data", userData);
		
		String Write = StringMinipulation.prettyPrint(main.toString());
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
	    writer.append(Write);
	    writer.close();
		
	}
	
	private static String getJSON(String guild) throws Throwable {
		
		BufferedReader br = new BufferedReader(new FileReader(new File(guild)));
		
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	    }
	    
	    br.close();
	    
	    return sb.toString();
		
	}
	
	public static String readFile(String path) {
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(path)));
		
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	    }
	    
	    br.close();
	    
	    return sb.toString();
		
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	
}
