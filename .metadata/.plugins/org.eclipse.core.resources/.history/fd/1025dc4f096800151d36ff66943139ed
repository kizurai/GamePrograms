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

public class Choice {
	public String choice;
	public String story;
	public Choice[] choices = new Choice[0];
	
	public Choice(String choi, String stor){
		choice = choi;
		story = stor;
	}
	
	public void setChoices(Choice ... chois){
		choices = chois;
	}
	
	public String toString(){
		return "Choice: "+choice+" - Plot: "+story+" - Num Choices: "+choices.length;
	}
}
