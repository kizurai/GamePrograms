package rpg;

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