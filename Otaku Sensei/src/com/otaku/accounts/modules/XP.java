package com.otaku.accounts.modules;

import java.util.concurrent.ThreadLocalRandom;

public class XP {

	private int xp;
	
	private final int MIN_XP = 10;
	private final int MAX_XP = 25;
	
	public XP(int xp) {
		this.setAmount(xp);
	}
	
	public XP() {
		this.setAmount(0);
	}

	public int getAmount() {
		return xp;
	}

	public void setAmount(int xp) {
		this.xp = xp;
	}
	
	public void addAmount(int amount) {
		this.xp += amount;
	}
	public void incrementAmount() {
		this.xp += 1;
	}
	
	public void takeAmount(int amount) {
		this.xp -= amount;
	}
	public void descrementAmount(int amount) {
		this.xp -= 1;
	}
	
	public void give() {
		this.xp += ThreadLocalRandom.current().nextInt(MIN_XP, MAX_XP);
	}
	
	
	
}
