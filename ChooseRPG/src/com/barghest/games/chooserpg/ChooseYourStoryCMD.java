package com.barghest.games.chooserpg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

@SuppressWarnings("serial")
public class ChooseYourStoryCMD extends JFrame implements ActionListener {
	
	private final int SCREEN_WIDTH = 640;
	private final int SCREEN_HEIGHT = 524;
	private Player player;
	private Game game;
	
	private JPanel main;
	private JTextArea storyTextArea;
	private JTextField typeField;
	
	private static final Set<String> COMMANDS = new HashSet<String>
	(Arrays.asList(new String[] {"STATS"}));
	final LinkedList<String> holder = new LinkedList<String>();
	
	public static void main(String[] args) {
		new ChooseYourStoryCMD();
	}
	
	public ChooseYourStoryCMD() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		while(true) {
			try {
				update(Game.loadStory(player.myRace(), "Main Intro"));
			} catch (FileNotFoundException e) {
				update("My apologies, the story for this race type has yet to finish\n" +
						"Please choose another race to begin the game.");
				intro();
			}
			waitForText();
			update(typeField.getText());
			typeField.setText("");
		}
	}
	
	public void intro() {
		int index = 0;
		String tempname = "";
		int tempgender = 0;
		int temprace = 0;
		String line = "";
		Boolean end = false;
		
		String[] introlines = {"Please answer the following questions by " +
				"typing the options in the brackets (choice):\n" +
				"Are you new to this game? (Yes / No)",
				"What is your name?",
				"Are you Female (1) or Male (2)?",
				"What is your race?\n" +
						"Human (1)\n" +
						"Saint (2)\n" +
						"Demon (3)\n" +
						"Half Saint (4)\n" +
						"Half Demon (5)"};
		while (!end) {
			update(introlines[index]);
			waitForText();
			line = typeField.getText();
			update(line);
			try {
		    	if (index == 0 && line.substring(0,1).equalsIgnoreCase("n")) {
		    		end = true;
		    	} else if (index == 0 && line.substring(0,1).equalsIgnoreCase("y")) {
		    		update(Game.loadStory("Saint", "Character Intro"));
		    		index++;
		    	} else if (index == 1) {
		    		tempname = line;
		    		index++;
		    	} else if (index == 2 && Integer.parseInt(line) < 3) {
		    		tempgender = Integer.parseInt(line);
		    		index++;
		    	} else if (index == 3 && Integer.parseInt(line) < 6) {
		    		temprace = Integer.parseInt(line);
		    		player = new Player(tempname, tempgender, temprace);
		    		update("Congratulations " + player.myName() +
		    				"\nYou have set yourself as a " + player.myGender() + " " +
		    				player.myRace() + "\nYou will be starting your story now.");
		    		end = true;
		    	} else {
		    		update("I'm sorry, that's the wrong input.");
		    	}
			} catch (Exception e) {
				update("I'm sorry, that's the wrong input.\nPlease try again.");
			}
			typeField.setText("");
		}
		start();
	}
	
	public void waitForText() {
        synchronized (holder) {
        	while (holder.isEmpty())
				try {
					holder.wait();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	while (!holder.isEmpty()) {
        		holder.remove(0);
        	}
        }
	}
	
	public void update(String text) {
		storyTextArea.append(text + "\n");
		//storyTextArea.setCaretPosition(storyTextArea.getDocument().getLength());
	}
	
	public Boolean checkCommand(String text) {
		if (COMMANDS.contains(text.toUpperCase())) {
			for (int x=0; x < COMMANDS.size(); x++) {
				
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void init() {
		game = new Game();
		
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setTitle("Choose Your Life RPG - Fantasy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		main = new JPanel(new BorderLayout());
		setContentPane(main);
		main.setBorder(new EmptyBorder(5, 5, 5, 5));
        
		storyTextArea = new JTextArea();
		storyTextArea.setEditable(false);
		storyTextArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
		storyTextArea.setLineWrap(true);
		storyTextArea.setWrapStyleWord(true);
		storyTextArea.setOpaque(false);
		storyTextArea.setVisible(true);
		DefaultCaret caret = (DefaultCaret)storyTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane storyScroll = new JScrollPane(storyTextArea);
		storyScroll.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT - 200));
		storyScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		typeField = new JTextField(16);
        typeField.setPreferredSize(new Dimension(SCREEN_WIDTH, 30));
        typeField.setEditable(true);
        typeField.addActionListener(this);
        typeField.setVisible(true);
        
        main.add(storyScroll, BorderLayout.NORTH);
        main.add(typeField, BorderLayout.SOUTH);
        setVisible(true);
        
        pack();
        intro();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		synchronized (holder) {
			holder.add(typeField.getText());
			holder.notify();
		}
		this.dispose();
	}

	public void dispose() {
		// TODO Auto-generated method stub
	}
}