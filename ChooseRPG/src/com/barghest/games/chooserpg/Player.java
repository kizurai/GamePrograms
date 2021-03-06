package com.barghest.games.chooserpg;

import java.util.HashMap;
import java.util.Map;

public class Player {
	
	private String name;
	private String gender;
	private String race;
	private String job;
	
	private Map<String, Integer> personality = new HashMap<String, Integer>();
	
	public Player(String name, int gender, int race) {
		this.name = name;
		String tempgender;
		String temprace = "";
		
		if (gender == 1) {
			tempgender = "Female";
		} else {
			tempgender = "Male";
		}
		this.gender = tempgender;
		
		if (race == 1) {
			temprace = "Human";
		} else if (race == 2) {
			temprace = "Saint";
		} else if (race == 3) {
			temprace = "Demon";
		} else if (race == 4) {
			temprace = "Half Saint";
		} else if (race == 5) {
			temprace = "Half Demon";
		}
		this.race = temprace;
		this.job = "None";
		
		this.personality.put("altruistic", 0);
		this.personality.put("benevolent", 0);
		this.personality.put("courage", 0);
		this.personality.put("empathy", 0);
		this.personality.put("tranquil", 0);
	}
	
	public void changeJob(String newjob) {
		this.job = newjob;
	}
	
	public void changePersonality(String key, int value) {
		this.personality.put(key, this.personality.get(key) + value);
	}
	public int calcPersonality() {
		int personality = this.personality.get("empathy") + 
				(this.personality.get("benevolent")*this.personality.get("tranquil")) + 
				(this.personality.get("altruistic")*(this.personality.get("courage")/2));
		return personality;
	}
	
	public String myPersonality() {
		int calculated = calcPersonality();
		String behaviour = "Neutral";
		if (calculated > 0) {
			behaviour = "Good";
		} else if (calculated < 0) {
			behaviour = "Evil";
		}
		return behaviour;
	}
	
	public String myName() {
		return this.name;
	}
	
	public String myGender() {
		return this.gender;
	}
	
	public String myRace() {
		return this.race;
	}
	
	public String myJob() {
		return this.job;
	}
}
