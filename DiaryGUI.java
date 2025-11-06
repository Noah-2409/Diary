package diary;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.*;
/*----------------------------------------------------------------------------Problems------------
 * Fehler anzeige mir JDialog erstellen und Jtextfield mit anwendungs fehlerr!!
 *  
 */

public class DiaryGUI {
	private static JFrame frame = new JFrame();
	private static CardLayout cardManager = new CardLayout();
	private static JPanel cardContainer = new JPanel();
	
	public static void main(String[] args) {

		//-------------------------------------------------------------------BasicSetUp-----
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.add(cardContainer);
		cardContainer.setLayout(cardManager);
		//---------------------------------------------------------------------FrameBuild----
		buildHomeFrame();
		buildSearchFrame();
		buildEditFrame();
		//----------------------------------------------------------------------Begin--------
		cardManager.show(cardContainer,"Home");
		frame.setVisible(true);
	
	}
	private static void buildHomeFrame() {
		JPanel panelHome = new JPanel();
		panelHome.setLayout(new BorderLayout());
		cardContainer.add(panelHome,"Home");
		//----------------------------------------------------------------------Labels-------
		JLabel labelTextEingabe = new JLabel("Entry: ");
		JLabel labelTextEingabeTitel =new JLabel("Enter Title: ");
		JLabel labelButtonSave = new JLabel("Save");
		JLabel labelButtonSearch = new JLabel("Search");
		JLabel labelPanelHome = new JLabel("HomeScreen");
		
		//-----------------------------------------------------------------------Buttons------
		JButton buttonSave = new JButton();
		JButton buttonSearch = new JButton();
		buttonSave.add(labelButtonSave);
		buttonSearch.add(labelButtonSearch);
		//---------------------------------------------------------------------TextField-------
		JTextArea textEingabe = new JTextArea(5,40);
		JTextField textEingabeTitel = new JTextField(20);
		JScrollPane paneTextArea = new JScrollPane(textEingabe);
		textEingabe.setLineWrap(true);
		textEingabe.setWrapStyleWord(true);
		//----------------------------------------------------------------------panels--------
		JPanel panelNorth = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelSouth = new JPanel();
		JPanel panelNorthInCenter = new JPanel();
		JPanel panelCenterInCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout());
		panelNorth.setLayout(new BorderLayout());
		JPanel panelWestInNorth = new JPanel();
		
		panelHome.add(panelNorth,BorderLayout.NORTH);
		panelHome.add(panelCenter,BorderLayout.CENTER);
		panelHome.add(panelSouth,BorderLayout.SOUTH);
		panelCenter.add(panelNorthInCenter,BorderLayout.NORTH);
		
		panelCenter.add(panelCenterInCenter,BorderLayout.CENTER);
		panelNorth.add(panelWestInNorth,BorderLayout.WEST);
		
		
		panelWestInNorth.add(labelPanelHome);
		panelNorthInCenter.add(labelTextEingabeTitel);
		panelNorthInCenter.add(textEingabeTitel);
		panelCenterInCenter.add(labelTextEingabe);
		panelCenterInCenter.add(paneTextArea);
		panelSouth.add(buttonSave);
		panelSouth.add(buttonSearch);
		//----------------------------------------------------------------------Button-Action-----
		buttonSearch.addActionListener(e->{
			cardManager.show(cardContainer,"Search");
		});
		buttonSave.addActionListener(e->{
			if(!textEingabe.getText().trim().isEmpty()) {
				TaskDo.insertTask(textEingabe.getText(),textEingabeTitel.getText());
			}
			textEingabe.setText(null);
			textEingabeTitel.setText(null);
		});
		
	}
	private static void buildSearchFrame() {

		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(new BorderLayout());
		cardContainer.add(panelSearch,"Search");
		//--------------------------------------------------------------------------Labels-------
		JLabel labelDay = new JLabel("Day: ");
		JLabel labelMonth = new JLabel("Month: ");
		JLabel labelYear = new JLabel("Year: ");
		JLabel labelButtonEnter = new JLabel("Enter");
		JLabel labelButtonBack = new JLabel("Back");
		JLabel labelPanelSearch = new JLabel("SearchScreen");
		//------------------------------------------------------------------------JDialog---------
		
		//-------------------------------------------------------------------------Buttons-------
		JButton buttonBack = new JButton();
		JButton buttonEnter = new JButton();
		buttonBack.add(labelButtonBack);
		buttonEnter.add(labelButtonEnter);
		//-----------------------------------------------------------TextField/Area/List----------
		JTextField fieldDay = new JTextField(2);
		JTextField fieldMonth = new JTextField(2);
		JTextField fieldYear = new JTextField(3);
		JTextArea inputEntries = new JTextArea(5,40);
		JTextArea outputStory = new JTextArea(10,40);
		JScrollPane paneOutputStory = new JScrollPane(outputStory);
		
		outputStory.setLineWrap(true);
		outputStory.setWrapStyleWord(true);
		
		outputStory.setEditable(false);
		paneOutputStory.setVisible(false);
		
		inputEntries.setLineWrap(true);
		inputEntries.setWrapStyleWord(true);
		
		DefaultListModel<String> entryListModel = new DefaultListModel<>();
		JList<String> entryList = new JList<>(entryListModel);
		JScrollPane paneList = new JScrollPane(entryList);
		paneList.setPreferredSize(new Dimension(250,100));
		paneList.setVisible(false);
		
		
		//-----------------------------------------------------------------------panels---------
		JPanel panelNorth = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelSouth = new JPanel();
		JPanel panelNorthInCenter = new JPanel();
		JPanel panelCenterInCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout());
		JPanel panelWestInNorth = new JPanel();
		panelNorth.setLayout(new BorderLayout());
		
		
		panelSearch.add(panelNorth,BorderLayout.NORTH);
		panelSearch.add(panelCenter,BorderLayout.CENTER);
		panelSearch.add(panelSouth,BorderLayout.SOUTH);
		panelCenter.add(panelNorthInCenter,BorderLayout.NORTH);
		panelCenter.add(panelCenterInCenter,BorderLayout.CENTER);
		panelNorth.add(panelWestInNorth,BorderLayout.WEST);
		
		panelWestInNorth.add(labelPanelSearch);
		panelSouth.add(buttonBack);
		panelNorthInCenter.add(labelDay);
		panelNorthInCenter.add(fieldDay);
		panelNorthInCenter.add(labelMonth);
		panelNorthInCenter.add(fieldMonth);
		panelNorthInCenter.add(labelYear);
		panelNorthInCenter.add(fieldYear);
		panelNorthInCenter.add(buttonEnter);
		panelCenterInCenter.add(paneList);
		panelCenterInCenter.add(paneOutputStory);
		//---------------------------------------------------------------Button-Action-----------
		buttonBack.addActionListener(e->{
			entryList.clearSelection();
			outputStory.setText(null);
			paneOutputStory.setVisible(false);
			paneList.setVisible(false);
			panelCenterInCenter.revalidate();
			cardManager.show(cardContainer,"Home");
			
		});
		
		buttonEnter.addActionListener(e->{
			
			if(TaskDo.isValidDate(fieldDay,fieldMonth,fieldYear)) {
				entryListModel.clear();
				int i=0;
				
				while (true) {
					try{
					 String storyEntry = TaskDo.getTitleFromDate(Integer.parseInt(fieldYear.getText()),
							Integer.parseInt(fieldMonth.getText()),
							Integer.parseInt(fieldDay.getText()), i);
					 
					if(!storyEntry.trim().equals("Keine weitere Ergebnisse")) {
						entryListModel.addElement(storyEntry);
						i++;
					}else break;
					
					}catch(SQLException E) {
						System.out.println("Fehler: "+E.getMessage());
					}
					
					}
				
			}
			paneList.setVisible(true);
			panelCenterInCenter.revalidate();
			
		});
		entryList.addListSelectionListener(e->{
			if(entryList.getSelectedValue()!=null) {
				
				int day =Integer.parseInt(fieldDay.getText());
			int month = Integer.parseInt(fieldMonth.getText());
			int year = Integer.parseInt(fieldYear.getText());
			
			String title = entryList.getSelectedValue();
			
			if(!e.getValueIsAdjusting()) {
				paneList.setVisible(false);
				
				try{
					outputStory.setText(TaskDo.getStoryFromDate(year, month, day,title));
				}catch(SQLException E) {
					outputStory.setText("Fehler: "+E.getMessage());
				}
				paneOutputStory.setVisible(true);
				panelCenterInCenter.revalidate();
			}
			}
			
			
			
			
		});
		
	
	}
	private static void buildEditFrame() {
		
	}
}



