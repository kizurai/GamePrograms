package com.barghest.games.chooserpg;

import java.util.HashMap;

public class Choice {
	private HashMap<String, Integer> attr;
	
	public Choice() {
		this.attr.put("altruistic", 0);
		this.attr.put("benevolent", 1);
		this.attr.put("courage", 2);
		this.attr.put("empathy", 3);
		this.attr.put("tranquil", 4);
	}
}
