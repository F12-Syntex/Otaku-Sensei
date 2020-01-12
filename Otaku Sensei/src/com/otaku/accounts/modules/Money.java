package com.otaku.accounts.modules;

import java.util.concurrent.ThreadLocalRandom;

public class Money {

	private int MAX = 999999999;
	private int MIN = 0;
	
	private final int MIN_MONEY = 25;
	private final int MAX_MONEY = 50;
	
	private int money;
	
	public Money(int money) {
		this.setAmount(money);
	}
	
	public Money() {
		this.setAmount(0);
	}

	public int getAmount() {
		return money;
	}

	public void setAmount(int money) {
		
		if(money > MAX) money = MAX;
		if(money < MIN) money = MIN;		
		
		this.money = money;
	}
	
	public void addAmount(int amount) {
		
		if(amount > MAX) amount = MAX;
		if(amount < MIN) amount = MIN;
		
		this.money += amount;
	}
	public void incrementAmount() {
		this.money += 1;
		
		if(money > MAX) money = MAX;
		if(money < MIN) money = MIN;
		
	}
	
	public void takeAmount(int amount) {
		
		if(amount > MAX) amount = MAX;
		if(amount < MIN) amount = MIN;
		
		this.money -= amount;
	}
	public void descrementAmount(int amount) {
		this.money -= 1;
		
		if(money > MAX) money = MAX;
		if(money < MIN) money = MIN;
		
	}	
	
	public void give() {
		this.money += ThreadLocalRandom.current().nextInt(MIN_MONEY, MAX_MONEY);
	}
	
}
