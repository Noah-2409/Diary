package diary;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.*;
/*--------------------------------Probleme--------------------------
 * 
 */

public class DiaryGUI {
	private static JFrame frame = new JFrame();
	private static CardLayout cardManager = new CardLayout();
	private static JPanel cardContainer = new JPanel();
	public static void main(String[] args) {

		//------------------------------------BasicSetUp-----
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.add(cardContainer);
		cardContainer.setLayout(cardManager);
		//--------------------------------Build--------------
		buildHomeFrame();
		buildSearchFrame();
		buildEditFrame();
		//-----------------------------Begin-----------------
		cardManager.show(cardContainer,"Home");
		frame.setVisible(true);
	
	}
	private static void buildHomeFrame() {
		JPanel panelHome = new JPanel();
		panelHome.setLayout(new BorderLayout());
		cardContainer.add(panelHome,"Home");
		//----------------Labels-----------------------------
		JLabel labelTextEingabe = new JLabel("Entry: ");
		JLabel labelTextEingabeTitel =new JLabel("Enter Title: ");
		JLabel labelButtonSave = new JLabel("Save");
		JLabel labelButtonSearch = new JLabel("Search");
		JLabel labelPanelHome = new JLabel("HomeScreen");
		
		//---------------Buttons-----------------------
		JButton buttonSave = new JButton();
		JButton buttonSearch = new JButton();
		buttonSave.add(labelButtonSave);
		buttonSearch.add(labelButtonSearch);
		//-----------------TextField-------------------------
		JTextArea textEingabe = new JTextArea(5,40);
		JTextField textEingabeTitel = new JTextField(20);
		JScrollPane paneTextArea = new JScrollPane(textEingabe);
		textEingabe.setLineWrap(true);
		textEingabe.setWrapStyleWord(true);
		//------------------panels------------------------
		JPanel panelNorth = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelSouth = new JPanel();
		JPanel panelNorthInCenter = new JPanel();
		JPanel panelCenterInCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout());
		
		panelHome.add(panelNorth,BorderLayout.NORTH);
		panelHome.add(panelCenter,BorderLayout.CENTER);
		panelHome.add(panelSouth,BorderLayout.SOUTH);
		panelCenter.add(panelNorthInCenter,BorderLayout.NORTH);
		panelCenter.add(panelCenterInCenter,BorderLayout.CENTER);
		
		
		panelNorth.add(labelPanelHome);
		panelNorthInCenter.add(labelTextEingabeTitel);
		panelNorthInCenter.add(textEingabeTitel);
		panelCenterInCenter.add(labelTextEingabe);
		panelCenterInCenter.add(paneTextArea);
		panelSouth.add(buttonSave);
		panelSouth.add(buttonSearch);
		//-------------------------Action-------------------------
		buttonSearch.addActionListener(e->{
			cardManager.show(cardContainer,"Search");
		});
		buttonSave.addActionListener(e->{
			if(!textEingabe.getText().trim().isEmpty()) {
				TaskDo.insertTask(textEingabe.getText(),textEingabeTitel.getText());
			}
		});
		
	}
	private static void buildSearchFrame() {

		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(new BorderLayout());
		cardContainer.add(panelSearch,"Search");
		//----------------Labels-----------------------------
		JLabel labelDay = new JLabel("Day: ");
		JLabel labelMonth = new JLabel("Month: ");
		JLabel labelYear = new JLabel("Year: ");
		JLabel labelButtonEnter = new JLabel("Enter");
		JLabel labelButtonBack = new JLabel("Back");
		JLabel labelPanelSearch = new JLabel("SearchScreen");
		
		//---------------Buttons-----------------------
		JButton buttonBack = new JButton();
		JButton buttonEnter = new JButton();
		buttonBack.add(labelButtonBack);
		buttonEnter.add(labelButtonEnter);
		//-----------------TextField/Area/List-------------------------
		JTextField fieldDay = new JTextField(2);
		JTextField fieldMonth = new JTextField(2);
		JTextField fieldYear = new JTextField(3);
		JTextArea outputEntries = new JTextArea(5,40);
		outputEntries.setLineWrap(true);
		outputEntries.setWrapStyleWord(true);
		
		DefaultListModel<String> entryListModel = new DefaultListModel<>();
		JList<String> entryList = new JList<>(entryListModel);
		JScrollPane paneList = new JScrollPane(entryList);
		paneList.setPreferredSize(new Dimension(250,150));
		
		
		//------------------panels------------------------
		JPanel panelNorth = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelSouth = new JPanel();
		panelSearch.add(panelNorth,BorderLayout.NORTH);
		panelSearch.add(panelCenter,BorderLayout.CENTER);
		panelSearch.add(panelSouth,BorderLayout.SOUTH);
		
		panelNorth.add(labelPanelSearch);
		panelSouth.add(buttonBack);
		panelCenter.add(labelDay);
		panelCenter.add(fieldDay);
		panelCenter.add(labelMonth);
		panelCenter.add(fieldMonth);
		panelCenter.add(labelYear);
		panelCenter.add(fieldYear);
		panelCenter.add(buttonEnter);
		panelCenter.add(paneList);
		//---------------------Action----------------------------
		buttonBack.addActionListener(e->{
			entryListModel.clear();
			cardManager.show(cardContainer,"Home");
		});
		buttonEnter.addActionListener(e->{
			entryListModel.clear();
			int i=0;
			while (true) {
				try{
				 String storyEntry = TaskDo.getEntryStoryOrTitle(Integer.parseInt(fieldYear.getText()),
						Integer.parseInt(fieldMonth.getText()),
						Integer.parseInt(fieldDay.getText()), 
						"title", i);
				 
				if(!storyEntry.trim().equals("Keine weitere Ergebnisse")) {
					entryListModel.addElement(storyEntry);
					i++;
				}else break;
				
				}catch(SQLException E) {
					System.out.println("Fehler: "+E.getMessage());
				}
				
				}
	
		});
		
	
	}
	private static void buildEditFrame() {
		
	}
}



