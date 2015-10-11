package com.barghest.games.chooserpg;

public class Player {
	
	private String name;
	private String gender;
	private String race;
	
	private int altruistic;
	private int benevolent;
	private int courage;
	private int empathy;
	private int tranquil;
	
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
	}
	
	public void changePersonality(String a, String b, String c, String e, String t) {
		this.empathy += Integer.parseInt(e);
		this.courage += Integer.parseInt(c);
		this.benevolent += Integer.parseInt(b);
		this.altruistic += Integer.parseInt(a);
		this.tranquil += Integer.parseInt(t);
	}
	public int calcPersonality() {
		int personality = empathy + (benevolent*tranquil) + (altruistic*(courage/2));
		return personality;
	}
	
	public String myPersonality() {
		String behaviour = "Neutral";
		if (calcPersonality() > 0) {
			behaviour = "Good";
		} else if (calcPersonality() < 0) {
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
}