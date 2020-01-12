package com.otaku.accounts;

import org.json.JSONObject;

import com.otaku.accounts.modules.Money;
import com.otaku.accounts.modules.XP;

import net.dv8tion.jda.api.entities.Guild;

public class UserData {

	private String ID;
	private Money money;
	private String name;
	private XP xp;
	
	public UserData(String name, String ID, int money, int xp) {
		
		this.ID = ID;
		this.money = new Money(money);
		this.name = name;
		this.xp = new XP(xp);
		
	}
	
	public UserData(JSONObject configuration, String ID) {
		
		this.ID = ID;
		this.name = configuration.getString("name");
		this.money = new Money(configuration.getInt("currency"));
		this.xp = new XP(configuration.getInt("xp"));
		
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void save(Guild guild) {
		
		try {
			
			UserAccounts.verify(guild, this);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}
	public XP getXp() {
		return xp;
	}

	public void setXp(XP xp) {
		this.xp = xp;
	}
	
}
