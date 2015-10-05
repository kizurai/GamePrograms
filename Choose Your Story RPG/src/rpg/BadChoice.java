package rpg;

public class BadChoice extends Exception {
	private static final long serialVersionUID = 1L;
	
	public BadChoice(String str){
		super(str);
	}
}
