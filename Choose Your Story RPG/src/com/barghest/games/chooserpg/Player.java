package com.barghest.games.chooserpg;

public class Player {
	
	private String name;
	private int gender;
	private int race;
	
	private int empathy;
	private int courage;
	private int benevolent;
	private int altruistic;
	private int tranquil;
	
	public Player(String name, int gender, int race) {
		this.name = name;
		this.gender = gender;
		this.race = race;
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
}