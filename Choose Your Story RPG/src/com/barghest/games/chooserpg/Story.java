/*
This class was developed by Michael Diamond (http://www.DigitalGemstones.com) �2010
and is released freely for personal and corporate use under the license which can be found at: 
http://digitalgemstones.com/licence.php 
and can be summarized as: 
You are free to use this software for any purpose as long as Digital Gemstones is credited, 
and may redistribute the software in its original form or modified as you see fit,  
as long as any credit comments in the code remain unchanged. 
*/
package com.barghest.games.chooserpg;

import rpg.BadChoice;
import rpg.Choice;

public abstract class Story implements Comparable<Story> {
	protected String title;
	protected Choice storyStart;
	private Choice curChoice;
	
	public String getStory(){
		return curChoice.story;
	}
	
	public int numChoices(){
		return curChoice.choices.length;
	}
	
	public String[] getChoices(){
		String[] arr = new String[curChoice.choices.length];
		for(int i = 0; i < curChoice.choices.length; i++){
			arr[i] = curChoice.choices[i].choice;
		}
		return arr;
	}
	
	public void makeChoice(int i) throws BadChoice{
		try {
			curChoice = curChoice.choices[i];
		} catch (ArrayIndexOutOfBoundsException e){
			throw new BadChoice("Expected a number between 0 and "+(curChoice.choices.length-1));
		}
	}
	
	public void restart(){
		if(storyStart == null){
			throw new RuntimeException("You need to specify a choice to start your story called 'storyStart'");
		}
		curChoice = storyStart;
	}
	
	public String title(){
		return title;
	}
	
	public String toString(){
		return title;
	}
	
	public int compareTo(Story s){
		return title().compareTo(s.title());
	}
}
