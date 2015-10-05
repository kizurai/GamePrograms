package rpg.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import rpg.BadChoice;

import com.barghest.games.chooserpg.Story;

@SuppressWarnings("serial")
public class ChooseYourStoryFrame extends JFrame implements ActionListener, ListSelectionListener{

	private ArrayList<Story> storiesList;
	private Story story = null;
	private String plot = "";

	private JFileChooser fc = new JFileChooser();
	private JPanel main;
	private JList storiesJList;
	private JPanel storyPanel;
	private JTextArea storyTextArea;
	private JPanel controlPanel;
	private JPanel storiesPanel;
	private JPanel storiesControls;
	private JButton playButton;
	private JButton restartButton;
	private ArrayList<JRadioButton> choiceButtons;
	private ButtonGroup choiceButGrp;
	private JMenuItem loadFile;
	private JMenuItem loadDir;
	
	private static int MAX_CHOICES = 8;
	
	public static void main(String[] args) {
		new ChooseYourStoryFrame();
	}
	
	public ChooseYourStoryFrame(){
		init();
		getStories();
	}
	
	private void getStories() {
		//StoryGrabber.loadStory("Saint", 1);
	}

	private void init() {
		setSize(425,524);
		setTitle("Choose Your Life RPG - Fantasy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu loadMenu = new JMenu("Load");
		menuBar.add(loadMenu);
		
		loadFile = new JMenuItem("Load File");
		loadFile.addActionListener(this);
		loadMenu.add(loadFile);
		
		loadDir = new JMenuItem("Load Directory");
		loadDir.addActionListener(this);
		loadMenu.add(loadDir);
		
		this.setJMenuBar(menuBar);
		
		main = new JPanel(new BorderLayout());
		setContentPane(main);
		
		storiesList = new ArrayList<Story>();
		
		storiesPanel = new JPanel(new BorderLayout());
		main.add(storiesPanel,BorderLayout.WEST);
		
		//storiesJList = new JList();
		//storiesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//storiesJList.addListSelectionListener(this);
		//JScrollPane listScroll = new JScrollPane(storiesJList);
		//listScroll.setPreferredSize(new Dimension(170,0));
		//storiesPanel.add(listScroll,BorderLayout.CENTER);
		
		storiesControls = new JPanel();
		storiesPanel.add(storiesControls,BorderLayout.SOUTH);
		playButton = new JButton("Play Story");
		playButton.setEnabled(false);
		playButton.addActionListener(this);
		storiesControls.add(playButton);
		
		restartButton = new JButton("Restart Story");
		restartButton.setEnabled(false);
		restartButton.addActionListener(this);
		storiesControls.add(restartButton);
		
		storyPanel = new JPanel(new BorderLayout());
		main.add(storyPanel,BorderLayout.CENTER);
		
		storyTextArea = new JTextArea();
		storyTextArea.setEditable(false);
		storyTextArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
		storyTextArea.setLineWrap(true);
		storyTextArea.setWrapStyleWord(true);
		JScrollPane storyScroll = new JScrollPane(storyTextArea);
		storyPanel.add(storyScroll,BorderLayout.CENTER);
		
		controlPanel = new JPanel(new GridLayout(4,2));
		controlPanel.setPreferredSize(new Dimension(0,100));
		storyPanel.add(controlPanel,BorderLayout.SOUTH);
		
		choiceButtons = new ArrayList<JRadioButton>();
		choiceButGrp = new ButtonGroup();
		if(MAX_CHOICES % 2 == 1)
			throw new RuntimeException("MAX_CHOICES should be a multiple of 2.");
		for(int i = 0; i < MAX_CHOICES; i++){
			JRadioButton b = new JRadioButton();
			b.addActionListener(this);
			b.setVisible(false);
			controlPanel.add(b);
			choiceButtons.add(b);
			choiceButGrp.add(b);
		}	
		setVisible(true);
	}

	private void start(){
		plot = story.title()+"\n";
		storyTextArea.setText(plot);
		story.restart();
		updatePlot();
	}
	private void updatePlot(){
		while(story.numChoices() >= 0){
			plot += "\n"+story.getStory();
			if(story.numChoices() == 1){
				try {
					story.makeChoice(0);
				} catch (BadChoice e) {
					throw new RuntimeException("Well that's not supposed to happen!");
				}
			} else {
				break;
			}
		}

		if(story.numChoices() == 0){
			plot += "\n\nThe End!";
		}
		
		storyTextArea.setText(plot);
		updateChoices();
	}
	
	private void updateChoices(){
		String[] choices = story.getChoices();
		int i;
		for(i = 0; i < choices.length; i++){
			choiceButtons.get(i).setText(choices[i]);
			choiceButtons.get(i).setVisible(true);
		}
		for(; i < MAX_CHOICES; i++){
			choiceButtons.get(i).setText("");
			choiceButtons.get(i).setVisible(false);
		}
	}
	
	private void makeChoice(int choice){
		try {
			story.makeChoice(choice);
		} catch (BadChoice e) {
			throw new RuntimeException("This shouldn't happen either!");
		}
		updatePlot();
	}
	@Override
	public void valueChanged(ListSelectionEvent evt) {
		if (evt.getValueIsAdjusting() == false) {
	        if (storiesJList.getSelectedIndex() == -1) {
	        //No selection, disable play button.
	        	playButton.setEnabled(false);
	        } else {
	        //Selection, enable the play button.
	        	playButton.setEnabled(true);
	        }
	    }
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		if (src == loadFile){
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setMultiSelectionEnabled(true);
			int ret = fc.showOpenDialog(this);
			if(ret == JFileChooser.APPROVE_OPTION){
				loadFiles(fc.getSelectedFiles());
			}
		} if(src == loadDir) {
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setMultiSelectionEnabled(false);
			int ret = fc.showOpenDialog(this);
			if(ret == JFileChooser.APPROVE_OPTION){
				loadDir(fc.getSelectedFile());
			}
		} else if(src == playButton){
			story = storiesList.get(storiesJList.getSelectedIndex());
        	restartButton.setEnabled(true);
        	start();
		} else if(src == restartButton){
			start();
		} else{
			int i = choiceButtons.indexOf(src);
			if(i == -1)
				return;
			choiceButGrp.clearSelection();
			makeChoice(i);
		}
	}
	
	private void loadFiles(File[] files){
	}
	
	private void loadDir(File dir){
		if(!dir.isDirectory()){
			JOptionPane.showMessageDialog(this, "Invalid or Nonexistant Directory.", "File Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		File[] ls = dir.listFiles();
		if(ls == null){
			JOptionPane.showMessageDialog(this, "Unable To Access Directory.", "File Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		loadFiles(ls);
	}
}