package com.otaku.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Numbers {

	public static Integer[] getRandomIndexs(int length) {
		
		List<Object> items = new ArrayList<Object>();
		
		for(int i = 0; i < length; i++) {
			items.add(i);
		}
		
		ShuffleObject object = new ShuffleObject(items);
		
		Collection<Object> shuffle = object.getShuffledObjects();
		
		List<Integer> indexes = new ArrayList<Integer>();
		
		for(Object i : shuffle) {
			indexes.add(Integer.parseInt(i.toString()));
		}
		
		return indexes.toArray(new Integer[length]);
		
	}
	
}
