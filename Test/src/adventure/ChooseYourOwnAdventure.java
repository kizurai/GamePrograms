/*
This class was developed by Michael Diamond (http://www.DigitalGemstones.com) �2010
and is released freely for personal and corporate use under the license which can be found at: 
http://digitalgemstones.com/licence.php 
and can be summarized as: 
You are free to use this software for any purpose as long as Digital Gemstones is credited, 
and may redistribute the software in its original form or modified as you see fit,  
as long as any credit comments in the code remain unchanged. 
*/
package adventure;

import java.util.Scanner;

import adventure.stories.SampleStory;
import adventure.stories.Story;

/**
 * This class is a command line interface to play through some CYOA stories.  Stories are Java classes that extend Story, 
 * and are hardcoded into the stories array.  This is the conceptually simpler way to work with CYOA, but is not
 * as user friendly or extendible (no CSV file loading, for instance) as the Swing GUI version (ChooseYourAdventureFrame).
 * 
 * @author Michael Diamond
 */
public class ChooseYourOwnAdventure {
	static Scanner in = new Scanner(System.in);
	
	// README
	// To add your own story, create a new class that extends Story, and add an instance of it to the array below
	//
	static Story[] stories = new Story[]{
		new SampleStory()
	};
	
	public static void main(String[] args){
		Story story = null;
		while(story == null)
			story = selectStory();
		
		while(true){
			followStory(story);
			System.out.print("Try the story again? (y/n): ");
			if(in.nextLine().trim().toLowerCase().charAt(0) == 'n')
				break;
		}
	}

	private static Story selectStory() {
		System.out.println("Which adventure would you like to follow?");
		for(int i = 0; i < stories.length; i++){
			System.out.println((i+1)+": "+stories[i].title());
		}
		System.out.print("Enter your selection (1-"+stories.length+") or 0 to exit: ");
		try{
			String ln = in.nextLine();
			int sel = Integer.parseInt(ln.trim());
			if(sel == 0)
				System.exit(0);
			if(sel > 0 && sel <= stories.length)
				return stories[sel-1];
			System.out.println("\nTry selecting an adventure's number.");
			return null;
		} catch (NumberFormatException e){
			System.out.println("\nThat's not a number silly!");
			return null;
		}
	}

	private static void followStory(Story story) {
		story.restart();
		
		System.out.println("\nStarting the adventure \""+story.title()+"\"!\n");
		
		while(true){
			System.out.println(story.getStory());
			
			if(story.numChoices() == 0){
				System.out.println("The end.");
				return;
			}
			
			choose(story);
		}
	}

	private static void choose(Story story) {
		if(story.numChoices() == 0)
			return;
		if(story.numChoices() == 1){
			try {
				story.makeChoice(0);
				return;
			} catch (BadChoice e) {
				throw new RuntimeException("Well this shouldn't have happened...");
			}
		}
		
		System.out.println("What do you do?");
		String[] arr = story.getChoices();
		for(int i = 0; i < arr.length; i++){
			System.out.println((i+1)+": "+arr[i]);
		}
		System.out.print("Enter your selection (1-"+arr.length+"): ");
		while(true){
			try{
				String ln = in.nextLine();
				int sel = Integer.parseInt(ln.trim());
				story.makeChoice(sel-1);
				System.out.println();
				return;
			} catch (NumberFormatException e){
				System.out.println("\nThat's not a number silly!");
			} catch (BadChoice e) {
				System.out.println("\nTry selecting a choice's number.");
			}
		}
	}
}
