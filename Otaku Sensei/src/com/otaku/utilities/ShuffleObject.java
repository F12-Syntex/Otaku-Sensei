package com.otaku.utilities;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ShuffleObject {

	private Map<Integer, Object> objectShuffleMap;
	private List<Integer> usedIdexes;
	private List<Object> object;
	private int size = 0;
	
	public ShuffleObject(List<Object> object) {
		this.setObject(object);
	}
	
	public Collection<Object> getShuffledObjects(){
		
		List<Object> intialObjects = this.object;
		this.usedIdexes = new ArrayList<Integer>();
		this.objectShuffleMap = new HashMap<Integer, Object>();
		
		for(Object object : intialObjects) {
			addValueToObject(object);
		}
		
		List<Object> shuffledObjectsList = new ArrayList<Object>();
		
		for(int i = 0; i < size; i++) {
			shuffledObjectsList.add(objectShuffleMap.get(i));
		}
		
		return objectShuffleMap.values();
		
	}
	private void addValueToObject(Object object) {
		int ValueToAppend = getRandomNumber(this.object.size(), usedIdexes);
		usedIdexes.add(ValueToAppend);
		objectShuffleMap.put(ValueToAppend, object);
	}

	public List<Object> getObject() {
		return object;
	}

	public void setObject(List<Object> object) {
		this.object = object;
	}
	
	public int getRandomNumber(int range, List<Integer> exclude) {
	    int random = ThreadLocalRandom.current().nextInt(range) + 1;
	    while(exclude.contains(random)) {
	    	//System.out.println("exclude had " + random);
	        random = ThreadLocalRandom.current().nextInt(range) + 1;
	    }
	    return random;
	}
	
	
	
	
}
