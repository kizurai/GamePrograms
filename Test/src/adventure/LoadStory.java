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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import util.csv.CSVReader;
import adventure.stories.Story;

public class LoadStory {
	
	public static Story load(File f) throws MalformedStoryException, IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String title = br.readLine();
		
		if(title == null){
			throw new MalformedStoryException("Expected a story title.");
		}
		
		CSVReader rd = new CSVReader(br);
		List<String[]> story = rd.readAll();
		
		String start = null;
		HashMap<String,Choice> choices = new HashMap<String,Choice>();
		HashMap<String,ArrayList<String>> choiceMapping = new HashMap<String,ArrayList<String>>();
				
		for(String[] arr : story){
			if(arr.length == 0)
				continue; // this shouldn't happen considering the edit to CSVReader, but here just in case
			if(arr.length < 3)
				throw new MalformedStoryException("Line labeled '"+arr[0]+"' is missing information.");
			if(arr.length > 4)
				throw new MalformedStoryException("Line labeled '"+arr[0]+"' has extra, unwanted content.  Perhaps you should double check you don't have extra commas not in quotes.");
		
			if(choices.get(arr[0]) != null){
				throw new MalformedStoryException("More than one choice is labeled '"+arr[0]+"'.");
			}
			
			for(int i = 0; i < arr.length; i++){
				arr[i] = arr[i].trim();
			}
			
			if(start == null)
				start = arr[0];
			
			choices.put(arr[0],new Choice(arr[1],arr[2]));
			ArrayList<String> al = new ArrayList<String>();
			choiceMapping.put(arr[0],al);
			
			if(arr.length == 4){
				String[] posChoices = arr[3].split("\\s+");
				for(String s : posChoices){
					if(s.trim().length() > 0)
						al.add(s);
				}
			}
		}
		
		return constructStory(title, start, choices, choiceMapping);
	}

	
	
	
	private static Story constructStory(final String t, String start, HashMap<String, Choice> choices, HashMap<String, ArrayList<String>> choiceMapping) throws MalformedStoryException {
		for(Entry<String, ArrayList<String>> e : choiceMapping.entrySet()){
			Choice c = choices.get(e.getKey());
			if(c == null)
				throw new RuntimeException("ChoiceMapping references a nonexistant Choice.");
			
			Choice[] choArr = new Choice[e.getValue().size()];
			int i = 0;
			for(String s : e.getValue()){
				Choice cm = choices.get(s);
				if(cm == null){
					throw new MalformedStoryException("Choice '"+e.getKey()+"' references a nonexistant choice '"+s+"'.");
				}
				choArr[i++] = cm;
			}
			c.setChoices(choArr);
		}
		
		final Choice c = choices.get(start);
		return new Story(){
			{
				title = t;
				storyStart = c;
			}
		};
	}
}
