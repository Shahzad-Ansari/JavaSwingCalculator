/*
 * file		: calculator.java
 * purpose	: to emulate windows calculator
 * author	: Shahzad Ansari
 * class	: 
 * project	: 
 * due date	: July 14, 2017
 */

//////////////////////////////////////////////////////////////////////////////////
// import needed classes for this project 
//////////////////////////////////////////////////////////////////////////////////
import java.lang.StringBuilder;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

//////////////////////////////////////////////////////////////////////////////////
// end of import statements
//////////////////////////////////////////////////////////////////////////////////

/*
 * class	: calculator
 * purpose	: extends calculate JFrame class to deliver the needed functionality
 */
@SuppressWarnings({ "unused", "serial" })
public class calculator extends JFrame {

	//////////////////////////////////////////////////////////////////////////////
	// constant declarations
	//////////////////////////////////////////////////////////////////////////////
	
	// constants used for setting number system value
	public static final int HEXMODE 				= 0;
	public static final int DECMODE 				= 1;
	public static final int OCTMODE 				= 2;
	public static final int BINMODE 				= 3;
	
	// constants used for setting the math operator selected
	public static final int OP_UNKNOWN 				= -1;
	public static final int OP_EQUAL 				= 0;
	public static final int OP_ADD	 				= 1;
	public static final int OP_SUBTRACT				= 2;
	public static final int OP_MULTIPLY				= 3;
	public static final int OP_DIVIDE				= 4;
	public static final int OP_MODULUS 				= 5;
	public static final int OP_QUOTIENT				= 6;
	
	public static final int APP_WIDTH 				= 1029;
	public static final int APP_HEIGHT 				= 860;
	public static final int MENU_PANEL_WIDTH 		= 1029;
	public static final int MENU_PANEL_HEIGHT 		= 40;
	public static final int CALC_PANEL_WIDTH		= 1029;
	public static final int CALC_PANEL_HEIGHT		= 860;
	
	// font sizes 
	public static final int FONTSIZE_DISPLAY 		= 30;
	public static final int	FONTSIZE_LABEL			= 24;
	public static final int FONTSIZE_OP1 			= 28;
	public static final int FONTSIZE_OP2			= 26;
	public static final int FONTSIZE_DIGITS			= 38;
	public static final int FONTSIZE_OPTIONS		= 30;
	
	public static final int FONTSIZE_ERROR			= 25;
	public static final int FONTSIZE_MENU			= 26;
	
	//////////////////////////////////////////////////////////////////////////////
	// declare all display elements as private variables of the calculator class
	//////////////////////////////////////////////////////////////////////////////
	// the content panels
	private JPanel panel_MainPanel;
	private JPanel panel_MenuPanel;
	private JPanel panel_CalculatorPanel;
	private JPanel panel_CalculatorDisplay;
	private JPanel panel_BinaryDigits;
	private JPanel panel_NumberSystem;
	private JPanel panel_StorageSize;
	
	// the main calculator display string
	private static JTextField text_CalculatorDisplay;
	// error display string
	private static JTextField text_ErrorDisplay;
	
	// the decimal keys/buttons from 0 .. 9
	private static JButton button_0;
	private static JButton button_1;
	private static JButton button_2;
	private static JButton button_3;
	private static JButton button_4;
	private static JButton button_5;
	private static JButton button_6;
	private static JButton button_7;
	private static JButton button_8;
	private static JButton button_9;

	// hexadecimal keys/buttons from A .. F
	private static JButton button_A;
	private static JButton button_B;
	private static JButton button_C;
	private static JButton button_D;
	private static JButton button_E;
	private static JButton button_F;

	// button erases the last character typed
	private JButton button_Back;
	// button erases the last number entered but keep the accumulator that eventually produces the result
	private JButton button_CE;
	// button clears any work done and start over
	private JButton button_Clear;

	// math operations keys/buttons
	private JButton button_Point;
	private JButton button_Equals;
	private JButton button_Plus;
	private JButton button_Modulo;
	private JButton button_Mod;
	private JButton button_Radical;
	private JButton button_Quot;
	private JButton button_1pX;
	private JButton button_Minus;
	private JButton button_Multiply;
	private JButton button_Divisor;
	private JButton button_PlusMinus;
	
	// option buttons for number systems selections
	private JRadioButton option_Hex;
	private JRadioButton option_Dec;
	private JRadioButton option_Oct;
	private JRadioButton option_Bin;
	
	// option buttons for storage size
	private JRadioButton option_QWord;
	private JRadioButton option_DWord;
	private JRadioButton option_Word;
	private JRadioButton option_Byte;
	
	// text fields to show the binary representation of the number in the main text field
	private JTextField 	text_Bin1;
	private JTextField 	text_Bin2;
	private JTextField 	text_Bin3;
	private JTextField 	text_Bin4;
	private JTextField 	text_Bin5;
	private JTextField 	text_Bin6;
	private JTextField 	text_Bin7;
	private JTextField 	text_Bin8;
	private JTextField 	text_Bin9;
	private JTextField 	text_Bin10;
	private JTextField 	text_Bin11;
	private JTextField 	text_Bin12;
	private JTextField 	text_Bin13;
	private JTextField 	text_Bin14;
	private JTextField 	text_Bin15;
	private JTextField 	text_Bin16;

	// labels for the binary digits areas
	private JTextField 	text_15;
	private JTextField 	text_31;
	private JTextField 	text_47;
	private JTextField 	text_63;
	
	// menu items 
	private JMenuBar	mbar_Main;
	private JMenu 		menu_View;
	private JMenu 		menu_Edit;
	private JMenu 		menu_Help;
	private JMenu		menu_Sep1;
	private JMenu		menu_Sep2;
	
	private JMenuItem	item_Show;
	private JMenuItem	item_Hide;
	
	private JMenuItem	item_Copy;
	private JMenuItem	item_ViewHelp;
	
	// flag to indicate whether we need to clear the text in the display or not
	private boolean 	flagClearText 		= false;
	// flag to indicate whether 'equal' button was pressed or not 
	private boolean 	flagEqualClicked	= false;
	// flag to indicate whether any of the math key/buttons were pressed or not
	private boolean 	flagMathKeyClicked 	= false;

	private int 		lastMathOp 			= OP_UNKNOWN;
	private long 		lastValue 		= 0;
	private long 		accumulator 	= 0;
	
	// indicates the number system option in play; hex, dec, oct, bin
	private int 		numberSystem 	= DECMODE;

	/**
	 * function		: main
	 * parameters	: args - list of arguments that can be passed in on execution
	 * purpose 		: launch the application
	 */

	public static void main(String[] args) 
	{

		// create the calculator object
		calculator frame = new calculator();
		// make it visible
		frame.setVisible(true);
	}

	/*
	 * function		: Calculator 
	 * parameters	: none
	 * purpose		: constructor for the calculator class
	 */
	public calculator() 
	{
		// application exits on close event
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set the size of the application window
		setBounds(0, 0, APP_WIDTH, APP_HEIGHT);
		// center the application in the window
		setLocationRelativeTo(null);
		// app cannot be resized
		setResizable(false);
		
		// set the application title
		setTitle("Calculator Project");
		
		// add the windows state listener to monitor restore event
		addWindowStateListener(new WindowStateListener() {
			   public void windowStateChanged(WindowEvent arg0) 
			   {
				   // if the frame is not minimized ... then it must be restored ... 
				   if ((arg0.getNewState() & Frame.ICONIFIED) != Frame.ICONIFIED)
				   {
					   // repaint the display text
					   SetDisplayTextByValue(Long.parseLong(text_CalculatorDisplay.getText()));			
				   }
			   }
			});
		
		//////////////////////////////////////////////////////////////////////////////
		// need a main panel
		//////////////////////////////////////////////////////////////////////////////
		panel_MainPanel = new JPanel();
		panel_MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel_MainPanel);
		panel_MainPanel.setLayout(null);

		//////////////////////////////////////////////////////////////////////////////
		// menu panel
		//////////////////////////////////////////////////////////////////////////////
		panel_MenuPanel = new JPanel();
		panel_MenuPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_MenuPanel.setBounds(0, 0, MENU_PANEL_WIDTH, MENU_PANEL_HEIGHT);
		panel_MenuPanel.setLayout(null);
		panel_MainPanel.add(panel_MenuPanel);
		
		//////////////////////////////////////////////////////////////////////////////
		// menu
		//////////////////////////////////////////////////////////////////////////////
		
		// create the menu bar for the application
		mbar_Main = new JMenuBar();
		// set the boundaries for the bar 
		mbar_Main.setBounds(0, 0, MENU_PANEL_WIDTH, MENU_PANEL_HEIGHT);

		panel_MenuPanel.add(mbar_Main);
		
		// add the 'view' menu item
		menu_View = new JMenu("View");
		menu_View.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_MENU));
		mbar_Main.add(menu_View);
		
		item_Hide = new JMenuItem("Hide");
		item_Hide.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_MENU));
		menu_View.add(item_Hide);
		
		item_Hide.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				// hide the calculator panel and its contents
				panel_CalculatorPanel.setVisible(false);
			}
		});
		
		item_Show = new JMenuItem("Show");
		item_Show.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_MENU));
		menu_View.add(item_Show);

		item_Show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				// show the calculator panel and contents
				panel_CalculatorPanel.setVisible(true);
				SetDisplayTextByValue(Long.parseLong(text_CalculatorDisplay.getText()));			
			}
		});
	
		// add a separator to give some space between top level menu items
		menu_Sep1 = new JMenu(" ");
		menu_Sep1.setEnabled(false);
		mbar_Main.add(menu_Sep1);

		menu_Edit = new JMenu("Edit");
		menu_Edit.setToolTipText("Edit");
		menu_Edit.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_MENU));
		mbar_Main.add(menu_Edit);
		
		item_Copy = new JMenuItem("Copy");
		item_Copy.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_MENU));
		menu_Edit.add(item_Copy);
		
		item_Copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				java.awt.datatransfer.Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection selection = new StringSelection(text_CalculatorDisplay.getText());
				clipboard.setContents(selection, null);
			}
		});

		// add a separator to give some space between top level menu items
		menu_Sep2 = new JMenu(" ");
		menu_Sep2.setEnabled(false);
		mbar_Main.add(menu_Sep2);
		
		menu_Help = new JMenu("Help");
		menu_Help.setToolTipText("Help");
		menu_Help.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_MENU));
		mbar_Main.add(menu_Help);
		
		item_ViewHelp = new JMenuItem("View Help");
		item_ViewHelp.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_MENU));
		item_ViewHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					Desktop.getDesktop().browse(new URL( "https://support.microsoft.com/en-us/products/windows?os=windows-10") .toURI());
				} 
				catch (IOException | URISyntaxException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		menu_Help.add(item_ViewHelp);
		
		//////////////////////////////////////////////////////////////////////////////
		// need a main calculator panel so that we  position everything relative to it
		//////////////////////////////////////////////////////////////////////////////
		panel_CalculatorPanel = new JPanel();
		panel_CalculatorPanel.setLayout(null);
		panel_CalculatorPanel.setBounds(0, 0, CALC_PANEL_WIDTH, CALC_PANEL_HEIGHT);
		panel_MainPanel.add(panel_CalculatorPanel);

		//////////////////////////////////////////////////////////////////////////////
		// calculator main display text area
		//////////////////////////////////////////////////////////////////////////////
		
		// panel for the calculator display text
		panel_CalculatorDisplay = new JPanel();
		panel_CalculatorDisplay.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_CalculatorDisplay.setBounds(20, 60, 980, 114);
		panel_CalculatorDisplay.setBackground(Color.WHITE);
		panel_CalculatorDisplay.setLayout(null);
		panel_CalculatorPanel.add(panel_CalculatorDisplay);
		
		// text for the calculator display
		text_CalculatorDisplay = new JTextField();
		// cannot be editable
		text_CalculatorDisplay.setEditable(false);
		text_CalculatorDisplay.setForeground(Color.BLACK);
		text_CalculatorDisplay.setBackground(Color.WHITE);
		// no borders
		text_CalculatorDisplay.setBorder(null);
		// right justify
		text_CalculatorDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		text_CalculatorDisplay.setBounds(30, 96, 960, 50);
		text_CalculatorDisplay.setFont(new Font("Consolas", Font.PLAIN, 55));
		panel_CalculatorPanel.add(text_CalculatorDisplay);
		// set the number of columns to 20
		text_CalculatorDisplay.setColumns(20);
		// default text to "0"
		text_CalculatorDisplay.setText("0");

		// text for the calculator display
		text_ErrorDisplay = new JTextField();
		// cannot be editable
		text_ErrorDisplay.setEditable(false);
		text_ErrorDisplay.setForeground(Color.RED);
		text_ErrorDisplay.setBackground(Color.WHITE);
		// no borders
		text_ErrorDisplay.setBorder(null);
		// right justify
		text_ErrorDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		text_ErrorDisplay.setBounds(30, 145, 960, 24);
		text_ErrorDisplay.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_ERROR));
		panel_CalculatorPanel.add(text_ErrorDisplay);
		
		// clear the error string
		text_ErrorDisplay.setText("");

		//////////////////////////////////////////////////////////////////////////////
		// binary digits display text area
		//////////////////////////////////////////////////////////////////////////////
		int topYDisplay 	= 60;
		int displayHeight 	= 114;
		int displayWidth 	= 980;
		
		// position markers for the binary area
		int topYBinary 		= topYDisplay+displayHeight+10;
		int leftXBinary 	= 50;
		int topYGap 		= 10;
		int rowYGap 		= 65;
		int boxX 			= 122;
		int boxY 			= 36;
		
		// panel for the binary digit display text
		panel_BinaryDigits = new JPanel();
		panel_BinaryDigits.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_BinaryDigits.setBounds(20, topYBinary, 980, 140);
		panel_BinaryDigits.setLayout(null);
		panel_CalculatorPanel.add(panel_BinaryDigits);

		text_Bin1 = new JTextField();
		text_Bin1.setText("0000");
		text_Bin1.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin1.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin1.setEditable(false);
		text_Bin1.setBorder(null);
		text_Bin1.setBounds(leftXBinary+(boxX*7), 10+topYBinary+rowYGap, boxX-40, boxY);
		panel_CalculatorPanel.add(text_Bin1);
	
		text_Bin2 = new JTextField();
		text_Bin2.setText("0000");
		text_Bin2.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin2.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin2.setEditable(false);
		text_Bin2.setBorder(null);
		text_Bin2.setBounds(leftXBinary+(boxX*6), 10+topYBinary+rowYGap, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin2);
		
		text_Bin3 = new JTextField();
		text_Bin3.setText("0000");
		text_Bin3.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin3.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin3.setEditable(false);
		text_Bin3.setBorder(null);
		text_Bin3.setBounds(leftXBinary+(boxX*5), 10+topYBinary+rowYGap, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin3);
		
		text_Bin4 = new JTextField();
		text_Bin4.setText("0000");
		text_Bin4.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin4.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin4.setEditable(false);
		text_Bin4.setBorder(null);
		text_Bin4.setBounds(leftXBinary+(boxX*4), 10+topYBinary+rowYGap, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin4);
		
		text_Bin5 = new JTextField();
		text_Bin5.setText("0000");
		text_Bin5.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin5.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin5.setEditable(false);
		text_Bin5.setBorder(null);
		text_Bin5.setBounds(leftXBinary+(boxX*3), 10+topYBinary+rowYGap, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin5);
		
		text_Bin6 = new JTextField();
		text_Bin6.setText("0000");
		text_Bin6.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin6.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin6.setEditable(false);
		text_Bin6.setBorder(null);
		text_Bin6.setBounds(leftXBinary+(boxX*2), 10+topYBinary+rowYGap, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin6);
		
		text_Bin7 = new JTextField();
		text_Bin7.setText("0000");
		text_Bin7.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin7.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin7.setEditable(false);
		text_Bin7.setBorder(null);
		text_Bin7.setBounds(leftXBinary+boxX, 10+topYBinary+rowYGap, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin7);
		
		text_Bin8 = new JTextField();
		text_Bin8.setText("0000");
		text_Bin8.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin8.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin8.setEditable(false);
		text_Bin8.setBorder(null);
		text_Bin8.setBounds(leftXBinary, 10+topYBinary+rowYGap, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin8);
	
		text_Bin9 = new JTextField();
		text_Bin9.setText("0000");
		text_Bin9.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin9.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin9.setEditable(false);
		text_Bin9.setBorder(null);
		text_Bin9.setBounds(leftXBinary+(boxX*7), 10+topYBinary, boxX-40, boxY);
		panel_CalculatorPanel.add(text_Bin9);

		text_Bin10 = new JTextField();
		text_Bin10.setText("0000");
		text_Bin10.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin10.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin10.setEditable(false);
		text_Bin10.setBorder(null);
		text_Bin10.setBounds(leftXBinary+(boxX*6), 10+topYBinary, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin10);
		
		text_Bin11 = new JTextField();
		text_Bin11.setText("0000");
		text_Bin11.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin11.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin11.setEditable(false);
		text_Bin11.setBorder(null);
		text_Bin11.setBounds(leftXBinary+(boxX*5), 10+topYBinary, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin11);
		
		text_Bin12 = new JTextField();
		text_Bin12.setText("0000");
		text_Bin12.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin12.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin12.setEditable(false);
		text_Bin12.setBorder(null);
		text_Bin12.setBounds(leftXBinary+(boxX*4), 10+topYBinary, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin12);
		
		text_Bin13 = new JTextField();
		text_Bin13.setText("0000");
		text_Bin13.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin13.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin13.setEditable(false);
		text_Bin13.setBorder(null);
		text_Bin13.setBounds(leftXBinary+(boxX*3), 10+topYBinary, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin13);
		
		text_Bin14 = new JTextField();
		text_Bin14.setText("0000");
		text_Bin14.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin14.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin14.setEditable(false);
		text_Bin14.setBorder(null);
		text_Bin14.setBounds(leftXBinary+(boxX*2), 10+topYBinary, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin14);
	
		text_Bin15 = new JTextField();
		text_Bin15.setText("0000");
		text_Bin15.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin15.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin15.setEditable(false);
		text_Bin15.setBorder(null);
		text_Bin15.setBounds(leftXBinary+boxX, 10+topYBinary, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin15);
		
		text_Bin16 = new JTextField();
		text_Bin16.setText("0000");
		text_Bin16.setHorizontalAlignment(SwingConstants.LEFT);
		text_Bin16.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DISPLAY));
		text_Bin16.setEditable(false);
		text_Bin16.setBorder(null);
		text_Bin16.setBounds(leftXBinary, 10+topYBinary, boxX, boxY);
		panel_CalculatorPanel.add(text_Bin16);

		text_63 = new JTextField();
		text_63.setText("63");
		text_63.setHorizontalAlignment(SwingConstants.LEFT);
		text_63.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_LABEL));
		text_63.setForeground(Color.GRAY);
		text_63.setEditable(false);
		text_63.setBorder(null);
		text_63.setBounds(leftXBinary, 10+topYBinary+33, boxX, boxY-6);
		panel_CalculatorPanel.add(text_63);

		text_47 = new JTextField();
		text_47.setText("47");
		text_47.setHorizontalAlignment(SwingConstants.LEFT);
		text_47.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_LABEL));
		text_47.setForeground(Color.GRAY);
		text_47.setEditable(false);
		text_47.setBorder(null);
		text_47.setBounds(leftXBinary+(boxX*4), 10+topYBinary+33, boxX, boxY-6);
		panel_CalculatorPanel.add(text_47);		
		
		text_31 = new JTextField();
		text_31.setText("31");
		text_31.setHorizontalAlignment(SwingConstants.LEFT);
		text_31.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_LABEL));
		text_31.setForeground(Color.GRAY);
		text_31.setEditable(false);
		text_31.setBorder(null);
		text_31.setBounds(leftXBinary, 10+topYBinary+rowYGap+33, boxX, boxY-6);
		panel_CalculatorPanel.add(text_31);

		text_15 = new JTextField();
		text_15.setText("15");
		text_15.setHorizontalAlignment(SwingConstants.LEFT);
		text_15.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_LABEL));
		text_15.setForeground(Color.GRAY);
		text_15.setEditable(false);
		text_15.setBorder(null);
		text_15.setBounds(leftXBinary+(boxX*4), 10+topYBinary+rowYGap+33, boxX, boxY-6);
		panel_CalculatorPanel.add(text_15);		
		
		//////////////////////////////////////////////////////////////////////////////
		// number system selection area
		//////////////////////////////////////////////////////////////////////////////
		
		// position number system panel relative to binary digits panel
		int topYNumSys = topYBinary + 150;
				
		panel_NumberSystem = new JPanel();
		panel_NumberSystem.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_NumberSystem.setBounds(20, topYNumSys, 180, 220);
		panel_NumberSystem.setLayout(null);
		panel_CalculatorPanel.add(panel_NumberSystem);
		
		option_Hex = new JRadioButton("Hex");
		option_Hex.setHorizontalAlignment(SwingConstants.LEFT);
		option_Hex.setVerticalAlignment(SwingConstants.TOP);
		option_Hex.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OPTIONS));
		option_Hex.setBounds(20, 20, 80, FONTSIZE_OPTIONS);
		option_Hex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				HexMode ();
			}
		});
		panel_NumberSystem.add(option_Hex);
		
		option_Dec = new JRadioButton("Dec");
		option_Dec.setSelected(true);
		option_Dec.setHorizontalAlignment(SwingConstants.LEFT);
		option_Dec.setVerticalAlignment(SwingConstants.TOP);
		option_Dec.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OPTIONS));
		option_Dec.setBounds(20, 20+FONTSIZE_OPTIONS+15, 80, FONTSIZE_OPTIONS);
		option_Dec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DecMode();
			}
		});		
		panel_NumberSystem.add(option_Dec);
		
		option_Oct = new JRadioButton("Oct");
		option_Oct.setHorizontalAlignment(SwingConstants.LEFT);
		option_Oct.setVerticalAlignment(SwingConstants.TOP);
		option_Oct.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OPTIONS));
		option_Oct.setBounds(20, 20+(FONTSIZE_OPTIONS*2)+30, 80, FONTSIZE_OPTIONS);
		option_Oct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				OctMode();
			}
		});		
		panel_NumberSystem.add(option_Oct);
		
		option_Bin = new JRadioButton("Bin");
		option_Bin.setHorizontalAlignment(SwingConstants.LEFT);
		option_Bin.setVerticalAlignment(SwingConstants.TOP);
		option_Bin.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OPTIONS));
		option_Bin.setBounds(20, 20+(FONTSIZE_OPTIONS*3)+45, 80, FONTSIZE_OPTIONS);
		option_Bin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				BinMode();
			}
		});		
		panel_NumberSystem.add(option_Bin);
	
		//////////////////////////////////////////////////////////////////////////////
		// storage size selection area. will remain disabled
		//////////////////////////////////////////////////////////////////////////////
		
		int topYStorage = topYNumSys + 230;
				
		panel_StorageSize = new JPanel();
		panel_StorageSize.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_StorageSize.setBounds(20, topYStorage, 181, 220);
		panel_StorageSize.setLayout(null);
		panel_CalculatorPanel.add(panel_StorageSize);
		
		option_QWord = new JRadioButton("qWord");
		option_QWord.setHorizontalAlignment(SwingConstants.LEFT);
		option_QWord.setVerticalAlignment(SwingConstants.TOP);
		option_QWord.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OPTIONS));
		option_QWord.setBounds(20, 20, 120, FONTSIZE_OPTIONS);
		option_QWord.setEnabled(false);
		panel_StorageSize.add(option_QWord);
			
		option_DWord = new JRadioButton("dWord");
		option_DWord.setHorizontalAlignment(SwingConstants.LEFT);
		option_DWord.setVerticalAlignment(SwingConstants.TOP);
		option_DWord.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OPTIONS));
		option_DWord.setBounds(20, 20+FONTSIZE_OPTIONS+15, 120, FONTSIZE_OPTIONS);
		option_DWord.setEnabled(false);
		panel_StorageSize.add(option_DWord);
		
		option_Word = new JRadioButton("Word");
		option_Word.setHorizontalAlignment(SwingConstants.LEFT);
		option_Word.setVerticalAlignment(SwingConstants.TOP);
		option_Word.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OPTIONS));
		option_Word.setBounds(20, 20+(FONTSIZE_OPTIONS*2)+30, 120, FONTSIZE_OPTIONS);
		option_Word.setEnabled(false);
		panel_StorageSize.add(option_Word);
		
		option_Byte = new JRadioButton("Byte");
		option_Byte.setHorizontalAlignment(SwingConstants.LEFT);
		option_Byte.setVerticalAlignment(SwingConstants.TOP);
		option_Byte.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OPTIONS));
		option_Byte.setBounds(20, 20+(FONTSIZE_OPTIONS*3)+45, 120, FONTSIZE_OPTIONS);
		option_Byte.setEnabled(false);
		panel_StorageSize.add(option_Byte);

		//////////////////////////////////////////////////////////////////////////////
		// digits and math keys area
		//////////////////////////////////////////////////////////////////////////////

		// position markers
		int topYDigit  	= topYNumSys;
		int leftXDigit  = 210;
		int keyHeight 	= 67;
		int keyWidth  	= 90;
		
		// decimal digit pads
		button_0 = new JButton("0");
		button_0.setFocusable(false);
		button_0.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("0");
			}
		});
		button_0.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_0.setBounds(leftXDigit+(keyWidth+10)*3, topYDigit+(keyHeight+10)*5, (keyWidth*2)+10, keyHeight);
		panel_CalculatorPanel.add(button_0);
	 
		
		button_1 = new JButton("1");
		button_1.setFocusable(false);
		button_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("1");
			}
		});
		button_1.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_1.setBounds(leftXDigit+(keyWidth+10)*3, topYDigit+(keyHeight+10)*4, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_1);
	 
		
		button_2 = new JButton("2");
		button_2.setFocusable(false);

		button_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("2");
			}
		});
		button_2.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_2.setBounds(leftXDigit+(keyWidth+10)*4, topYDigit+(keyHeight+10)*4, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_2);
 
		
		button_3 = new JButton("3");
		button_3.setFocusable(false);
		button_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("3");
			}
		});
		button_3.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_3.setBounds(leftXDigit+(keyWidth+10)*5, topYDigit+(keyHeight+10)*4, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_3);
 
		
		
		button_4 = new JButton("4");
		button_4.setFocusable(false);
		button_4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("4");
			}
		});
		button_4.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_4.setBounds(leftXDigit+(keyWidth+10)*3, topYDigit+(keyHeight+10)*3, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_4);
 
		button_5 = new JButton("5");
		button_5.setFocusable(false);
		button_5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("5");
			}
		});
		button_5.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_5.setBounds(leftXDigit+(keyWidth+10)*4, topYDigit+(keyHeight+10)*3, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_5);
 
				
		button_6 = new JButton("6");
		button_6.setFocusable(false);
		button_6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("6");
			}
		});
		button_6.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_6.setBounds(leftXDigit+(keyWidth+10)*5, topYDigit+(keyHeight+10)*3, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_6);
	 
		
		button_7 = new JButton("7");
		button_7.setFocusable(false);
		button_7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("7");
			}
		});
		button_7.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_7.setBounds(leftXDigit+(keyWidth+10)*3, topYDigit+(keyHeight+10)*2, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_7);
 
		
		
		button_8 = new JButton("8");
		button_8.setFocusable(false);
		button_8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("8");
			}
		});
		button_8.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_8.setBounds(leftXDigit+(keyWidth+10)*4, topYDigit+(keyHeight+10)*2, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_8);
 
		
		
		button_9 = new JButton("9");
		button_9.setFocusable(false);
		button_9.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("9");
			}
		});
		button_9.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_9.setBounds(leftXDigit+(keyWidth+10)*5, topYDigit+(keyHeight+10)*2, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_9);
 
		
		//////////////////////////////////////////////////////////////////////////////
		// hexadecimal digit keys
		//////////////////////////////////////////////////////////////////////////////

		button_A = new JButton("A");
		button_A.setFocusable(false);
		button_A.setEnabled(false);
		button_A.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("A");
			}
		});
		button_A.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_A.setBounds(leftXDigit+(keyWidth+10)*2, topYDigit, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_A);
 
		
		button_B = new JButton("B");
		button_B.setFocusable(false);
		button_B.setEnabled(false);
		button_B.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("B");
			}
		});
		button_B.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_B.setBounds(leftXDigit+(keyWidth+10)*2, topYDigit+(keyHeight+10), keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_B);
 
				
		button_C = new JButton("C");
		button_C.setFocusable(false);
		button_C.setEnabled(false);
		button_C.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("C");
			}
		});
		button_C.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_C.setBounds(leftXDigit+(keyWidth+10)*2, topYDigit+(keyHeight+10)*2, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_C);
 
		
		button_D = new JButton("D");
		button_D.setFocusable(false);
		button_D.setEnabled(false);
		button_D.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("D");
			}
		});
		button_D.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_D.setBounds(leftXDigit+(keyWidth+10)*2, topYDigit+(keyHeight+10)*3, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_D);
 	
		
		button_E = new JButton("E");
		button_E.setFocusable(false);
		button_E.setEnabled(false);
		button_E.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("E");
			}
		});
		button_E.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_E.setBounds(leftXDigit+(keyWidth+10)*2, topYDigit+(keyHeight+10)*4, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_E);
	
		button_F = new JButton("F");
		button_F.setFocusable(false);
		button_F.setEnabled(false);
		button_F.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)  
			{
				DigitOperation("F");
			}
		});
		button_F.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_F.setBounds(leftXDigit+(keyWidth+10)*2, topYDigit+(keyHeight+10)*5, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_F);

		//////////////////////////////////////////////////////////////////////////////
		// navigation pads
		//////////////////////////////////////////////////////////////////////////////
	
		button_Back = new JButton("\u2190");
		button_Back.setFocusable(false);
		button_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int lenght = text_CalculatorDisplay.getText().length();
				int number = text_CalculatorDisplay.getText().length()-1;
				String store;
			
				if (lenght == 1 ){
					text_CalculatorDisplay.setText("0");
				}
				else if(lenght > 1)
				{
					StringBuilder back = new StringBuilder(text_CalculatorDisplay.getText());
					back.deleteCharAt(number);
					store = back.toString();
					text_CalculatorDisplay.setText(store);
					
				}
				flagMathKeyClicked = false;
				text_ErrorDisplay.setText("");
			}
		});
		button_Back.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_Back.setBounds(leftXDigit+(keyWidth+10)*3, topYDigit+(keyHeight+10), keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Back);

		//////////////////////////////////////////////////////////////////////////////
		// clear content keys
		//////////////////////////////////////////////////////////////////////////////

		button_CE = new JButton("CE");
		button_CE.setFocusable(false);
		button_CE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_CalculatorDisplay.setText("0");
				flagMathKeyClicked = false;
				text_ErrorDisplay.setText("");
				}
		});
		button_CE.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_CE.setBounds(leftXDigit+(keyWidth+10)*4, topYDigit+(keyHeight+10), keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_CE);
		
		button_Clear = new JButton("C");
		button_Clear.setFocusable(false);
		button_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ResetOperation();
			}
		});
		button_Clear.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_Clear.setBounds(leftXDigit+(keyWidth+10)*5, topYDigit+(keyHeight+10), keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Clear);
		
		//////////////////////////////////////////////////////////////////////////////
		// math keys
		//////////////////////////////////////////////////////////////////////////////

		// decimal digit is disabled 
		button_Point = new JButton(".");
		button_Point.setEnabled(false);
		button_Point.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_Point.setEnabled(false);
				flagMathKeyClicked = false;
			}
		});
		button_Point.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_Point.setBounds(leftXDigit+(keyWidth+10)*5, topYDigit+(keyHeight+10)*5, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Point);
		
		// equal key
		button_Equals = new JButton("=");
		button_Equals.setFocusable(false);
		button_Equals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MathOperation(OP_EQUAL);
				
			}
		});
		button_Equals.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_Equals.setBounds(leftXDigit+(keyWidth+10)*7, topYDigit+(keyHeight+10)*4, keyWidth, (keyHeight*2)+10);
		panel_CalculatorPanel.add(button_Equals);

		// plus operator
		button_Plus = new JButton("+");
		button_Plus.setFocusable(false);
		button_Plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MathOperation(OP_ADD);
				
			}
		});
		button_Plus.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_Plus.setBounds(leftXDigit+(keyWidth+10)*6, topYDigit+(keyHeight+10)*5, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Plus);
		
		// minus operator
		button_Minus = new JButton("-");
		button_Minus.setFocusable(false);
		button_Minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MathOperation(OP_SUBTRACT);
			}
		});
		button_Minus.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_Minus.setBounds(leftXDigit+(keyWidth+10)*6, topYDigit+(keyHeight+10)*4, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Minus);
		
		// multiplication operator
		button_Multiply = new JButton("*");
		button_Multiply.setFocusable(false);
		button_Multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MathOperation(OP_MULTIPLY);
			}
		});
		button_Multiply.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_Multiply.setBounds(leftXDigit+(keyWidth+10)*6, topYDigit+(keyHeight+10)*2, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Multiply);
		
		// division operator
		button_Divisor = new JButton("/");
		button_Divisor.setFocusable(false);
		button_Divisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MathOperation(OP_DIVIDE);
			}
		});
		button_Divisor.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_Divisor.setBounds(leftXDigit+(keyWidth+10)*6, topYDigit+(keyHeight+10)*3, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Divisor);
		
		// mod operator
		button_Mod = new JButton("Mod");
		button_Mod.setFocusable(false);
		button_Mod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MathOperation(OP_MODULUS);
			}
		});
		button_Mod.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OP2));
		button_Mod.setBounds(leftXDigit+keyWidth+10, topYDigit, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Mod);
		
		// quotient operator
		button_Quot = new JButton("Quot");
		button_Quot.setFocusable(false);
		button_Quot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MathOperation(OP_QUOTIENT);
			}
		});
		button_Quot.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OP2));
		button_Quot.setBounds(leftXDigit, topYDigit, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Quot);

		// 1/X operator
		button_1pX = new JButton("1/x");
		button_1pX.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_1pX.setEnabled(false);
		button_1pX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1pX.setEnabled(false);
				MathOperation(7);
			}
		});
		button_1pX.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OP1));
		button_1pX.setBounds(leftXDigit+(keyWidth+10)*7, topYDigit+(keyHeight+10)*3, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_1pX);

		// modulo operator
		button_Modulo = new JButton("%");
		button_Modulo.setEnabled(false);
		button_Modulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_Modulo.setEnabled(false);
				MathOperation(8);
			}
		});
		button_Modulo.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OP1));
		button_Modulo.setBounds(leftXDigit+(keyWidth+10)*7, topYDigit+(keyHeight+10)*2, keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Modulo);

		// square root operator
		button_Radical = new JButton(" \u221A ");
		button_Radical.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_Radical.setEnabled(false);
		button_Radical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_Radical.setEnabled(false);
				MathOperation(9);
			}
		});
		button_Radical.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_OP1));
		button_Radical.setBounds(leftXDigit+(keyWidth+10)*7, topYDigit+(keyHeight+10), keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_Radical);
	
		// ckey reverses the sign of the number in play
		button_PlusMinus = new JButton("\u00B1");
		button_PlusMinus.setFocusable(false);
		button_PlusMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				// get the display text value
				long tempVal = GetDisplayValue();
				// if there is a non-zero value in the display field
				if (tempVal != 0)
				{
					// reverse the sign and display the text
					SetDisplayTextByValue(tempVal*-1);
				}
			}
		});
		button_PlusMinus.setFont(new Font("Consolas", Font.PLAIN, FONTSIZE_DIGITS));
		button_PlusMinus.setBounds(leftXDigit+(keyWidth+10)*6, topYDigit+(keyHeight+10), keyWidth, keyHeight);
		panel_CalculatorPanel.add(button_PlusMinus);
	
	}

    /*
     * function 	: ResetOperation
     * parameters	: none
     * purpose		: reset any stored values and other indicators to a reset state
     */
    private void ResetOperation()
    {
 		text_CalculatorDisplay.setText("0");
		lastMathOp = -1;
		accumulator = 0;
		lastValue = 0;
		flagMathKeyClicked = false;
		text_ErrorDisplay.setText("");
    }
	//////////////////////////////////////////////////////////////////////////////
    // methods to set the right buttons state depending upon number system used
    //////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Function 	: HexMode
	 * Parameters	: none
	 * purpose		: set the button states to hexadecimal mode
	 */
	private void HexMode()
	{
		button_1.setEnabled(true);
		button_2.setEnabled(true);
		button_3.setEnabled(true);
		button_4.setEnabled(true);
		button_5.setEnabled(true);
		button_6.setEnabled(true);
		button_7.setEnabled(true);
		button_8.setEnabled(true);
		button_9.setEnabled(true);
		button_0.setEnabled(true);

		button_A.setEnabled(true);
		button_B.setEnabled(true);
		button_C.setEnabled(true);
		button_D.setEnabled(true);
		button_E.setEnabled(true);
		button_F.setEnabled(true);

		// get the current display value in the calculator
		long tempVal = GetDisplayValue();
		// set the hexadecimal mode value
		numberSystem = HEXMODE;
		// set the display text appropriately with the new number system
		SetDisplayTextByValue(tempVal);
	}
	
	/*
	 * Function 	: DecMode
	 * Parameters	: none
	 * purpose		: set the button states to decimal mode
	 */	
	private void DecMode()
	{
		button_1.setEnabled(true);
		button_2.setEnabled(true);
		button_3.setEnabled(true);
		button_4.setEnabled(true);
		button_5.setEnabled(true);
		button_6.setEnabled(true);
		button_7.setEnabled(true);
		button_8.setEnabled(true);
		button_9.setEnabled(true);
		button_0.setEnabled(true);

		button_A.setEnabled(false);
		button_B.setEnabled(false);
		button_C.setEnabled(false);
		button_D.setEnabled(false);
		button_E.setEnabled(false);
		button_F.setEnabled(false);

		// get the current display value in the calculator
		long tempVal = GetDisplayValue();
		// set the decimal mode value
		numberSystem = DECMODE;
		// set the display text appropriately with the new number system
		SetDisplayTextByValue(tempVal);
	}
	
	/*
	 * Function 	: OctMode
	 * Parameters	: none
	 * purpose		: set the button states to oct mode
	 */	
	private void OctMode()
	{
		button_1.setEnabled(true);
		button_2.setEnabled(true);
		button_3.setEnabled(true);
		button_4.setEnabled(true);
		button_5.setEnabled(true);
		button_6.setEnabled(true);
		button_7.setEnabled(false);
		button_8.setEnabled(false);
		button_9.setEnabled(true);
		button_0.setEnabled(true);
	
		button_A.setEnabled(false);
		button_B.setEnabled(false);
		button_C.setEnabled(false);
		button_D.setEnabled(false);
		button_E.setEnabled(false);
		button_F.setEnabled(false);

		// get the current display value in the calculator
		long tempVal = GetDisplayValue();
		// set the oct mode value
		numberSystem = OCTMODE;
		// set the display text appropriately with the new number system
		SetDisplayTextByValue(tempVal);
	}

	/*
	 * Function 	: BinMode
	 * Parameters	: none
	 * purpose		: set the button states to binary mode
	 */
	private void BinMode()
	{
		button_1.setEnabled(true);
		button_2.setEnabled(false);
		button_3.setEnabled(false);
		button_4.setEnabled(false);
		button_5.setEnabled(false);
		button_6.setEnabled(false);
		button_7.setEnabled(false);
		button_8.setEnabled(false);
		button_9.setEnabled(false);
		button_0.setEnabled(true);

		button_A.setEnabled(false);
		button_B.setEnabled(false);
		button_C.setEnabled(false);
		button_D.setEnabled(false);
		button_E.setEnabled(false);
		button_F.setEnabled(false);

		// get the current display value in the calculator
		long tempVal = GetDisplayValue();
		// set the binary mode value
		numberSystem = BINMODE; 
		// set the display text appropriately with the new number system
		SetDisplayTextByValue(tempVal);
	}
	
	//////////////////////////////////////////////////////////////////////////////
	// calculator display text methods below
	//////////////////////////////////////////////////////////////////////////////

	/*
	 * Function 	: GetDisplayValue 
	 * Parameters	: none
	 * returns		: the long value displayed in the calculator display text
	 * purpose		: get the display text value as a long
	 */
	private long GetDisplayValue()
	{
		// temporary storage for current value in the display
		long tempVal = 0;
		
		// depending upon the number system .. convert the value into decimal
		switch (numberSystem)
		{
			case HEXMODE: // hex
				tempVal = Long.parseLong(hexToDec(text_CalculatorDisplay.getText()));
				break;
			case DECMODE: // dec
				tempVal = Long.parseLong(text_CalculatorDisplay.getText());
				break;
			case OCTMODE: // oct
				tempVal = Long.parseLong(octToDec(text_CalculatorDisplay.getText()));
				break;
			case BINMODE: // bin
				tempVal = Long.parseLong(binToDec(text_CalculatorDisplay.getText()));
				break;
		}
		
		return tempVal;
	}
	
	/*
	 * Function 	: DisplayBinaryDigits 
	 * Parameters	: displayVal - value showing up in the main calculator display
	 * purpose		: display text to be rendered at binary
	 */
	private void DisplayBinaryDigits (long displayVal)
	{
		// temporary storage for binary display string
		String binaryText = decToBin(Long.toString(displayVal));
		
		// need temporary storage to build a zero padding in front 
		// in case the binary string is less than 64 digits
		String zeroPadding = "";
		
		// build the zero padding string
		for (int i = 0; i < 64-binaryText.length(); i++)
		{
			zeroPadding += "0";
		}
		
		// not add zero padding infront of the binary text
		binaryText = zeroPadding + binaryText;
		
		// display the right digits in the appropriate spots
		text_Bin1.setText(binaryText.substring(60, 64));
		text_Bin2.setText(binaryText.substring(56, 60));
		text_Bin3.setText(binaryText.substring(52, 56));
		text_Bin4.setText(binaryText.substring(48, 52));
		text_Bin5.setText(binaryText.substring(44, 48));
		text_Bin6.setText(binaryText.substring(40, 44));
		text_Bin7.setText(binaryText.substring(36, 40));
		text_Bin8.setText(binaryText.substring(32, 36));
		text_Bin9.setText(binaryText.substring(28, 32));
		text_Bin10.setText(binaryText.substring(24, 28));
		text_Bin11.setText(binaryText.substring(20, 24));
		text_Bin12.setText(binaryText.substring(16, 20));
		text_Bin13.setText(binaryText.substring(12, 16));
		text_Bin14.setText(binaryText.substring(8, 12));
		text_Bin15.setText(binaryText.substring(4, 8));
		text_Bin16.setText(binaryText.substring(0, 4));
		
		// write out the labels again. need for hide/show
		text_63.setText("63");
		text_47.setText("47");
		text_31.setText("31");
		text_15.setText("15");
	}
	
	/*
	 * Function 	: SetDisplayTextByValue
	 * parameters	: displayValue - the long value we need to display 
	 * purpose		: set the display text by the passed in long value but display
	 * 				  based upon the number system used
	 */
	private void SetDisplayTextByValue(long displayValue)
	{
		// temp display string storage
		String	displayString = "";

		// based upon the number setting, display the result in the right format
		switch (numberSystem)
		{
			case HEXMODE : // hex
				// convert the long to a hex string and then display
				displayString  = decToHex(Long.toString(displayValue));
				break;
			case DECMODE : // dec
				displayString  = Long.toString(displayValue);
				break;
			case OCTMODE : // oct
				// convert the long to a oct string and then display
				displayString  = decToOct(Long.toString(displayValue));
				break;
			case BINMODE : // bin
				// convert the long to a bin string and then display
				displayString  = decToBin(Long.toString(displayValue));
				break;
		}

		// check for the length of display string, if greater than 19 .. set error condition
		if (displayString.length() < 19)
		{
			// set the display string on the calculator
			text_CalculatorDisplay.setText(displayString);
			
			// display the binary string too
			DisplayBinaryDigits(displayValue);
			
			// clear the error string
			text_ErrorDisplay.setText("");
		}
		else
		{
			// reset operations
			ResetOperation();
			// display appropriate error message
			text_ErrorDisplay.setText("Cannot display more than 18 digits");
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////
	// conversion methods below
	//////////////////////////////////////////////////////////////////////////////
	
	/*
	 * function		: decToHex
	 * parameters	: textString - passed in value represented in hexadecimal fashion
	 * purpose		: convert a decimal string into a hexadecimal string
	 */
	public static String decToHex(String textString)
	{
		// convert the integer text string into a long variable value
		long hexVal = Long.parseLong(textString);
		
		// use the long class method to convert into a hexadecimal value string
		String hexString = Long.toHexString(hexVal);
		
		// make sure all characters are returned in upper case
		return hexString.toUpperCase();
	}
	
	/*
	 * function		: hexToDec
	 * parameters	: textString - passed in value represented in hexadecimal fashion
	 * purpose		: convert a hexadecimal string into a decimal string
	 */
	public static String hexToDec(String textString)
	{		
		// prepend the "0x" the passed in hex string and then convert it into a long value 
		long hexVal = Long.decode("0x"+textString);
	
		// return the string that represents the decimal system value
		return Long.toString(hexVal);		
	}

	/*
	 * function		: decToOct
	 * parameters	: textString - passed in value represented in decimal fashion
	 * purpose		: convert a decimal string into a oct string
	 */
	public static String decToOct(String textString)
	{
		// extract the long value from the passed in decimal system string
		long octVal = Long.parseLong(textString);
		
		// return the string the represents the same value in oct form
		return Long.toOctalString(octVal);
	}

	/*
	 * function		: octToDec
	 * parameters	: textString - the oct string passed in
	 * purpose		: convert a oct string into a decimal
	 */
	public static String octToDec(String textString)
	{
		// convert the value represented by the passed in oct string into a long variable
		long octVal = Long.parseLong(textString, 8);
		
		// use the long class method to produce the string that represents the decimal value
		return Long.toString(octVal);
	}

	/*
	 * function		: decToBin
	 * parameters	: textString - the decimal string passed in
	 * purpose		: convert a decimal string into a binary string
	 */
	public static String decToBin(String textString)
	{
		// extract the value held in the decimal system string
		long binVal = Long.parseLong(textString);
		
		// return the string the represents the same value in binary form
		return Long.toBinaryString(binVal);
	}
	
	/*
	 * function		: binToDec
	 * parameters	: textString - the binary string passed in
	 * purpose		: convert a binary string into a decimal string
	 */
	public static String binToDec(String textString)
	{
		// extract the value held in the binary system string
		long binVal = Long.parseLong(textString,2);
		// use the long class method to produce the string that represents the decimal value
		return Long.toString(binVal);
	}

	//////////////////////////////////////////////////////////////////////////////
	// the digit and math operations methods below
	//////////////////////////////////////////////////////////////////////////////
	/*
	 * Function 	: DigitOperation
	 * parameters	: strKey - the digit (hex/oct/dec/bin) pressed 
	 * purpose		: process a digit press event
	 */
	private void DigitOperation(String strKey)
	{
		// make sure the total number of digits is less than 18 before accepting the key stroke
		if (text_CalculatorDisplay.getText().length() < 18)
		{
			// if the first character in the display string is zero, it is as good as having 
			// the flagClearText set to true
			flagClearText = flagClearText || (text_CalculatorDisplay.getText().charAt(0) == '0');
			
			// set the display text appropriately. if flag clear text is set to true, 
			// then .. just show the typed in digit
			// else .. append the digit to the text that was already there and show the combined string
			text_CalculatorDisplay.setText((flagClearText)? strKey : text_CalculatorDisplay.getText() + strKey);
			
			// display the binary string too
			DisplayBinaryDigits(GetDisplayValue());
	
			// since a digit has been typed, set the clear text flag to false
			flagClearText = false;
			// and .. this was not a calculation key
			flagMathKeyClicked = false;
			
			// clear error text
			text_ErrorDisplay.setText("");
		}
		else
		{
			// display the error message
			text_ErrorDisplay.setText("Cannot display more than 18 digits");
		}
	}
	
	/*
	 * function 	: MathOperation
	 * parameters	: mathOp - the math operations key pressed
	 * purpose  	: perform the required math operation based upon the  
	 * 				  identifier of the operation requested
	 */
	private void MathOperation(int mathOp)
	{
		
		// default the current value to zero
		long 	currentValue  = 0;
		// boolean to indicate a divide by zero error
		boolean flagDivByZero = false;
		
		// based upon the number system being used ...
		// if one of the calculation buttons was pressed, set the current value to the last value stored
		// otherwise, read it from the display ... 
		// and convert from source number system to decimal for math operations
		switch (numberSystem)
		{
			case HEXMODE: // hex
				currentValue = (flagMathKeyClicked)? lastValue : Long.parseLong(hexToDec(text_CalculatorDisplay.getText()));
				break;
			case DECMODE: // dec
				currentValue = (flagMathKeyClicked)? lastValue : Long.parseLong(text_CalculatorDisplay.getText());
				break;
			case OCTMODE: // oct
				currentValue = (flagMathKeyClicked)? lastValue : Long.parseLong(octToDec(text_CalculatorDisplay.getText()));
				break;
			case BINMODE: // bin
				currentValue = (flagMathKeyClicked)? lastValue : Long.parseLong(binToDec(text_CalculatorDisplay.getText()));
				break;
		}
				
		// first do the calculation based upon the stored calculation key
		switch (lastMathOp)
		{
		// there is no calculation direction saved, set the accumulator to the current value 
		case OP_UNKNOWN :
			accumulator = (long) currentValue;
			break;
		// if the last calculation key was the 'equal' button, there is no math operation to do
		case OP_EQUAL :
			break;
		// if the last calculation key was 'add', perform the required math operation
		case OP_ADD : 
			accumulator += currentValue;
			break;
		// if the last calculation key was 'subtract', perform the required math operation
		case OP_SUBTRACT : 
			accumulator -= currentValue;
			break;
		// if the last calculation key was 'multiply', perform the required math operation
		case OP_MULTIPLY :
			accumulator *= currentValue;
			break;
		// if the last calculation key was 'divide', perform the required math operation
		case OP_DIVIDE : 
			// set the right value of divide by zero flag
			flagDivByZero = (currentValue == 0);
			// calculate only if current value is non-zero
			if (!flagDivByZero)
			{
				// set the result of the division. it will automatically round up
				accumulator /= currentValue;
			}
			break;
		// if the last calculation key was 'modulus', perform the required math operation
		case OP_MODULUS :
			// set the right value of divide by zero flag
			flagDivByZero = (currentValue == 0);
			// calculate only if current value is non-zero
			if (!flagDivByZero)
			{
				// calculate the remainder 
				accumulator %= currentValue;
			}
			break;
		// find the quotient if the divisor is non zero
		case OP_QUOTIENT :
			// set the right value of divide by zero flag
			flagDivByZero = (currentValue == 0);
			// calculate only if current value is non-zero
			if (!flagDivByZero)
			{
				// calculate the quotient
				accumulator = Math.floorDiv(accumulator, currentValue);
			}
			break;
		// unknown key ... do nothing.
		default :
			break;
		}
	
		// set the flag to indicate that the equal button was pressed
		flagEqualClicked 	= (mathOp == OP_EQUAL) ;
		// one of the calculation buttons was pressed 
		flagMathKeyClicked 	= true;
		// if equal was pressed, then clear the last calc operation
		lastMathOp 			= (flagEqualClicked)? OP_UNKNOWN : mathOp;
		// if equal was pressed, then set the last value to be the resultant value after calculation
		lastValue 			= (flagEqualClicked)? accumulator : currentValue;
			
		// check if divide by zero has been triggered
		if (flagDivByZero)
		{
			// reset operation
			ResetOperation();
			
			// convert the long to a hex string and then display
			text_ErrorDisplay.setText("Cannot Divide By Zero");
		}
		else
		{
			// based upon the number setting, display the result in the right format
			SetDisplayTextByValue(accumulator);
		}
		// set the flag to clear text 
		flagClearText = true;
	}
}