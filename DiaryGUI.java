package diary;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.*;


public class DiaryGUI {
	private static JFrame frame = new JFrame();
	private static CardLayout cardManager = new CardLayout();
	private static JPanel cardContainer = new JPanel();
	public static void main(String[] args) {

		//------------------------------------BasicSetUp-------------------------------------------
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.add(cardContainer);
		cardContainer.setLayout(cardManager);
		//--------------------------------Build---------------------------------------------------
		buildHomeFrame();
		buildSearchFrame();
		buildEditFrame();
		//-----------------------------Begin---------------------------------
		cardManager.show(cardContainer,"Home");
		frame.setVisible(true);
	
	}
	private static void buildHomeFrame() {
		JPanel panelHome = new JPanel();
		panelHome.setLayout(new BorderLayout());
		cardContainer.add(panelHome,"Home");
		//----------------Labels-----------------------------
		JLabel labeltextEingabe = new JLabel("Entry :");
		JLabel labelButtonSafe = new JLabel("Safe");
		JLabel labelButtonSearch = new JLabel("Search");
		JLabel labelPanelHome = new JLabel("HomeScreen");
		
		//---------------Buttons-----------------------
		JButton buttonSafe = new JButton();
		JButton buttonSearch = new JButton();
		buttonSafe.add(labelButtonSafe);
		buttonSearch.add(labelButtonSearch);
		//-----------------TextField-------------------------
		JTextArea textEingabe = new JTextArea(5,40);
		JScrollPane pane = new JScrollPane(textEingabe);
		textEingabe.setLineWrap(true);
		textEingabe.setWrapStyleWord(true);
		//------------------panels------------------------
		JPanel panelNorth = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelSouth = new JPanel();
		
		panelHome.add(panelNorth,BorderLayout.NORTH);
		panelHome.add(panelCenter,BorderLayout.CENTER);
		panelHome.add(panelSouth,BorderLayout.SOUTH);
		
		panelNorth.add(labelPanelHome);
		panelCenter.add(labeltextEingabe);
		panelCenter.add(pane);
		panelSouth.add(buttonSafe);
		panelSouth.add(buttonSearch);
		//-------------------------Action-------------------------
		buttonSearch.addActionListener(e->{
			cardManager.show(cardContainer,"Search");
		});
		buttonSafe.addActionListener(e->{
			if(!textEingabe.getText().trim().isEmpty()) {
				TaskDo.insertTask(textEingabe.getText());
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
		//-----------------TextField/Area-------------------------
		JTextField fieldDay = new JTextField(2);
		JTextField fieldMonth = new JTextField(2);
		JTextField fieldYear = new JTextField(2);
		JTextArea outputEntries = new JTextArea(5,40);
		JScrollPane pane = new JScrollPane(outputEntries);
		outputEntries.setLineWrap(true);
		outputEntries.setWrapStyleWord(true);
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
		//---------------------Action----------------------------
		buttonBack.addActionListener(e->{
			cardManager.show(cardContainer,"Home");
		});
		
	
	}
	private static void buildEditFrame() {
		
	}
}



