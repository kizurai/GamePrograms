package com.barghest.games.chooserpg;
import java.io.File;
import java.util.Scanner;

public class StoryGrabber {
	
	public static String loadStory(String race, int chapter) {
        File file = new File("ChooseRPG - " + race + ".txt");
        String pattern = "-Chapter " + chapter + "-";
        String endpattern = "-Chapter " + (chapter+1) + "-";
        StringBuilder story = new StringBuilder();
        try {
	        Scanner sc = new Scanner(file);
	        sc.reset();
	        while (sc.hasNext(pattern)) {}
	        while (!sc.hasNext(endpattern)) {
	        	String line = sc.nextLine();
	        	System.out.println(line);
	        	story.append(line);
	        }
	        sc.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return story.toString();
    }
}
