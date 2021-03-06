package com.barghest.games.chooserpg;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Skip text until -Chapter- then read until you see -Choices-.
 * Take each line and put it through Choice function.
 * Output each choice to the player and depending on they answer
 * search for -Chapter- based on the end of the choice line
 * and repeat. 													*/
public class Game {
	private Choice choices;
	
	public static String loadStory(String race, String chapter) throws FileNotFoundException {
        File file = new File("data/ChooseRPG-" + race + ".txt");
        String pattern = "-" + chapter + "-";
        String choice = "-CHOICE-";
        String endpattern = "-END-";
        Boolean foundChap = false;
        Boolean foundChoice = false;
        Boolean foundEnd = false;
        
        StringBuilder story = new StringBuilder();
        Scanner sc = new Scanner(file);
        sc.reset();
        while(sc.hasNextLine()) {
        	String line = sc.nextLine();
        	if (line.matches(pattern)) { foundChap = true; }
        	else if (line.matches(choice)) { foundChap = false; foundChoice = true; }
        	else if (line.matches(endpattern)) { foundChap = false; foundChoice = false; foundEnd = true;}
        	else if (foundChap) {
        		System.out.println(line);
        		story.append(line + "\n");
        	} else if (foundChoice && !foundEnd) {
        		System.out.println(line);
        		story.append(line + "\n");
        	}
        }
        sc.close();
        return story.toString();
    }
}
