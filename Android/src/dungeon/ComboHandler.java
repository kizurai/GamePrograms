package dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComboHandler {
	
	private Map<String, ArrayList> combos;
	
		public ComboHandler()
		{
		    combos = new HashMap<String, ArrayList>();
		}
		 
		public boolean registerCombo(String comboName, ArrayList comboKeys)
		{
		    if (combos.containsKey(comboName)) { return false; }
		    else { combos.put(comboName, comboKeys); }
		    
		    return true;
		}
}
