/*
This class was developed by Michael Diamond (http://www.DigitalGemstones.com) �2010
and is released freely for personal and corporate use under the license which can be found at: 
http://digitalgemstones.com/licence.php 
and can be summarized as: 
You are free to use this software for any purpose as long as Digital Gemstones is credited, 
and may redistribute the software in its original form or modified as you see fit,  
as long as any credit comments in the code remain unchanged. 
*/
package adventure.stories;

import adventure.Choice;

public class SampleStory extends Story {
	Choice computerLab = new Choice("Go back into the lab.","You are in a computer lab.");
	Choice goOutside = new Choice("Go outside.","You are in a dark hallway, there are stairs to your left.");
	Choice turnOnComp = new Choice("Turn on a computer.","You turn on a computer.");
	Choice checkEmail = new Choice("Check your email.","You have an email from Michael warning you not to explore the hallway, it's very dangerous!");
	Choice surfWeb = new Choice("Surf the web.","You try to surf the web, but all the websites you try are blocked.");
	Choice powerOff = new Choice("Turn off the computer.","You powered off the computer.");
	Choice goDownStairs = new Choice("Go down the stairs.","You escaped the terrible terror, well done!");
	Choice explore = new Choice("Explore the hallway.","You wander around in the dark for a while, before you fall into a hole in the ground, and cannot get out.");
	
	public SampleStory(){
		title = "A Short Adventure";
		storyStart = computerLab;
		computerLab.setChoices(goOutside,turnOnComp);
		goOutside.setChoices(explore,goDownStairs,computerLab);
		turnOnComp.setChoices(checkEmail,surfWeb,powerOff);
		checkEmail.setChoices(checkEmail,surfWeb,powerOff);
		surfWeb.setChoices(checkEmail,surfWeb,powerOff);
		powerOff.setChoices(computerLab);
	}
}
