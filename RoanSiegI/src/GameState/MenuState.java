package GameState;

import TileMap.Background;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {
	
	private Background bg;
	private String[] options = {"Start", "Help", "Quit"};
	private Color titleColour;
	private Font titleFont;
	private Font font;
	
	public int currentChoice = 0;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/background.png", 1);
			bg.setVector(-0.1, 0);
			
			titleColour = new Color(128,0,0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() {}
	public void update() {
		bg.update();
	}
	
	public void draw(Graphics2D g) {
		/* Draw BG */
		bg.draw(g);
		
		/* Draw Title */
		g.setColor(titleColour);
		g.setFont(titleFont);
		g.drawString("Roan Sieg I", 80, 70);
		
		/* Draw Menu Options */
		g.setFont(font);
		for(int i = 0; i< options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 145, 140 + i * 15);
		}
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL1STATE);
		} else if(currentChoice == 1) {
			//help
		} else if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			select();
		} else if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice < 0) {
				currentChoice = options.length - 1;
			}
		} else if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice > options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
	
}
