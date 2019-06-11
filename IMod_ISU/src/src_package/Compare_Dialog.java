package src_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Compare_Dialog extends JFrame {

	private JPanel contentPane;

	//---------------------------------------------------------------------------------------------------------------------
	//declarations for all of the data that the user must input via the UI
	static String file1 = new String("");
	static String file2 = new String("");
	static String modification1 = new String("");
	static String modification2 = new String("");
	static String directory1 = new String("");
	static String directory2 = new String("");
	static String name1 = new String("");
	static String name2 = new String("");
	static String format1 = new String("");
	static String format2 = new String("");
	
	//other declarations
	private JLabel lblNewLabel;
	private JTextField txtChooseAName;
	private JTextField textField;
	
	//---------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Create the frame.
	 */
	public Compare_Dialog() {
		
		//---------------------------------------------------------------------------------------------------------------------
		//overall window
		setResizable(false);
		setTitle("Compare");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//---------------------------------------------------------------------------------------------------------------------
		//sets up border and layout
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 128), 2);
		contentPane.setLayout(null);
		
		//---------------------------------------------------------------------------------------------------------------------
		//label to show file chosen
		lblNewLabel = new JLabel(file1);
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 10));
		lblNewLabel.setBackground(new Color(0, 255, 255));
		lblNewLabel.setBounds(35, 106, 315, 23);
		lblNewLabel.setBorder(border);
		contentPane.add(lblNewLabel);
		//lblNewLabel.setText(file);
		
		//---------------------------------------------------------------------------------------------------------------------
		//button to choose file#1
		JButton btnNewButton = new JButton("Click here to Choose File #1");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnNewButton.setBorder(border);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				    "Images", "png", "jpg");
				chooser.setFileFilter(filter);
				chooser.setCurrentDirectory(new java.io.File("."));
				int returnVal = chooser.showOpenDialog(getParent());
				
				try {
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						
						file1 = chooser.getSelectedFile().getAbsolutePath();
						//System.out.println(file);
						lblNewLabel.setText(file1);
						
					} else {System.out.println("Valid file not chosen.");}
				} catch(NullPointerException e1) {
					
					System.out.println("File not chosen.");
					
				}
				
			}
		});
		btnNewButton.setBounds(35, 72, 315, 23);
		contentPane.add(btnNewButton);
		
		//---------------------------------------------------------------------------------------------------------------------
		//opening message label
		JLabel lblPickAFile = new JLabel("Select Parameters for Modification of File #1 and File #2");
		lblPickAFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblPickAFile.setForeground(new Color(0, 0, 128));
		lblPickAFile.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblPickAFile.setBackground(new Color(0, 255, 255));
		lblPickAFile.setBounds(35, 11, 660, 30);
		lblPickAFile.setBorder(border);
		contentPane.add(lblPickAFile);
		
		//---------------------------------------------------------------------------------------------------------------------
		//various separators
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 140, 660, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 59, 660, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(35, 228, 660, 3);
		contentPane.add(separator_2);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(35, 310, 660, 2);
		contentPane.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(35, 432, 660, 2);
		contentPane.add(separator_5);
		
		//---------------------------------------------------------------------------------------------------------------------
		//select modification for file#1
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"No Modification", "Convert to Grayscale", "Simulate Protanopia", "Simulate Deuteranopia", "Simulate Tritanopia"}));
		comboBox.setFont(new Font("Courier New", Font.PLAIN, 12));
		comboBox.setBackground(new Color(0, 255, 255));
		comboBox.setBounds(35, 187, 315, 30);
		comboBox.setBorder(border);
		modification1 = (String)comboBox.getSelectedItem();
		contentPane.add(comboBox);
		
		//---------------------------------------------------------------------------------------------------------------------
		//text field
		JLabel lblPickAModification = new JLabel("Pick a modification for File #1");
		lblPickAModification.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblPickAModification.setForeground(new Color(0, 0, 128));
		lblPickAModification.setBackground(new Color(0, 255, 255));
		lblPickAModification.setHorizontalAlignment(SwingConstants.CENTER);
		lblPickAModification.setBounds(35, 153, 315, 23);
		lblPickAModification.setBorder(border);
		contentPane.add(lblPickAModification);
		
		//---------------------------------------------------------------------------------------------------------------------
		//field which shows selection
		JLabel label = new JLabel("");
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Courier New", Font.PLAIN, 10));
		label.setBackground(Color.CYAN);
		label.setBounds(35, 276, 315, 23);
		label.setBorder(border);
		contentPane.add(label);
		
		//---------------------------------------------------------------------------------------------------------------------
		//button prompt to choose directory for file#1
		JButton btnClickHereTo = new JButton("Click here to Choose Directory to Save #1");
		btnClickHereTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        
				 JFileChooser chooser = new JFileChooser(); 
				 chooser.setCurrentDirectory(new java.io.File("."));
				 //chooser.setDialogTitle(choosertitle);
				 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				 chooser.setAcceptAllFileFilterUsed(false);
				 
				 int returnVal = chooser.showOpenDialog(getParent());
				 
			     try {
			    	 if(returnVal == JFileChooser.APPROVE_OPTION) {
			    		 
			    		 directory1 = chooser.getSelectedFile().getAbsolutePath();
					   	 //System.out.println(directory);
						 label.setText(directory1);
			    	
			    	 } else {System.out.println("Valid directory not chosen.");}
			    	 
				 } catch(NullPointerException e1) {
						
					 System.out.println("Directory not chosen.");
						
				 }
				
			}
		});
		btnClickHereTo.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnClickHereTo.setBackground(Color.CYAN);
		btnClickHereTo.setBounds(35, 242, 315, 23);
		btnClickHereTo.setBorder(border);
		contentPane.add(btnClickHereTo);
		
		//---------------------------------------------------------------------------------------------------------------------
		//input text field
		txtChooseAName = new JTextField();
		txtChooseAName.setToolTipText("");
		txtChooseAName.setHorizontalAlignment(SwingConstants.CENTER);
		txtChooseAName.setBackground(new Color(0, 255, 255));
		txtChooseAName.setForeground(new Color(0, 0, 128));
		txtChooseAName.setFont(new Font("Courier New", Font.PLAIN, 12));
		txtChooseAName.setBounds(35, 357, 315, 23);
		contentPane.add(txtChooseAName);
		txtChooseAName.setColumns(10);
		txtChooseAName.setBorder(border);
		name1 = txtChooseAName.getSelectedText();
		
		//---------------------------------------------------------------------------------------------------------------------
		//combo box to select file format
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"jpg", "png"}));
		comboBox_1.setForeground(new Color(0, 0, 128));
		comboBox_1.setFont(new Font("Courier New", Font.PLAIN, 12));
		comboBox_1.setBackground(Color.CYAN);
		comboBox_1.setBounds(35, 391, 315, 30);
		comboBox_1.setBorder(border);
		format1 = (String)comboBox_1.getSelectedItem();
		contentPane.add(comboBox_1);
		
		//---------------------------------------------------------------------------------------------------------------------
		JLabel lblChooseAName = new JLabel("Choose a name and format for File #1");
		lblChooseAName.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAName.setForeground(new Color(0, 0, 128));
		lblChooseAName.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblChooseAName.setBackground(Color.CYAN);
		lblChooseAName.setBounds(35, 323, 315, 23);
		lblChooseAName.setBorder(border);
		contentPane.add(lblChooseAName);
		
		//---------------------------------------------------------------------------------------------------------------------
		
		
		//start of selectors for file#2
		
		
		//---------------------------------------------------------------------------------------------------------------------
		JLabel label_1 = new JLabel("");
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("Courier New", Font.PLAIN, 10));
		label_1.setBackground(Color.CYAN);
		label_1.setBounds(380, 106, 315, 23);
		label_1.setBorder(border);
		contentPane.add(label_1);
		
		//---------------------------------------------------------------------------------------------------------------------
		JButton btnClickHereTo_2 = new JButton("Click here to Choose File #2");
		btnClickHereTo_2.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnClickHereTo_2.setBackground(Color.CYAN);
		btnClickHereTo_2.setBounds(380, 71, 315, 23);
		btnClickHereTo_2.setBorder(border);
		btnClickHereTo_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
				    "Images", "png", "jpg");
				chooser.setFileFilter(filter);
				chooser.setCurrentDirectory(new java.io.File("."));
				int returnVal = chooser.showOpenDialog(getParent());
				
				try {
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						
						file2 = chooser.getSelectedFile().getAbsolutePath();
						//System.out.println(file);
						label_1.setText(file2);
						
					} else {System.out.println("Valid file not chosen.");}
				} catch(NullPointerException e1) {
					
					System.out.println("File not chosen.");
					
				}
				
			}
		});
		contentPane.add(btnClickHereTo_2);
		
		//---------------------------------------------------------------------------------------------------------------------
		JLabel lblPickAModification_1 = new JLabel("Pick a modification for File #2");
		lblPickAModification_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPickAModification_1.setForeground(new Color(0, 0, 128));
		lblPickAModification_1.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblPickAModification_1.setBackground(Color.CYAN);
		lblPickAModification_1.setBounds(380, 153, 315, 23);
		lblPickAModification_1.setBorder(border);
		contentPane.add(lblPickAModification_1);
		
		//---------------------------------------------------------------------------------------------------------------------
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setForeground(new Color(0, 0, 128));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"No Modification", "Convert to Grayscale", "Simulate Protanopia", "Simulate Deuteranopia", "Simulate Tritanopia"}));
		comboBox_2.setFont(new Font("Courier New", Font.PLAIN, 12));
		comboBox_2.setBackground(Color.CYAN);
		comboBox_2.setBounds(380, 187, 315, 30);
		comboBox_2.setBorder(border);
		modification2 = (String)comboBox_2.getSelectedItem();
		contentPane.add(comboBox_2);
		
		//---------------------------------------------------------------------------------------------------------------------
		JLabel label_3 = new JLabel("");
		label_3.setForeground(new Color(0, 0, 128));
		label_3.setFont(new Font("Courier New", Font.PLAIN, 10));
		label_3.setBackground(Color.CYAN);
		label_3.setBounds(380, 276, 315, 23);
		label_3.setBorder(border);
		contentPane.add(label_3);
				
		//---------------------------------------------------------------------------------------------------------------------
		JButton btnClickHereTo_1 = new JButton("Click here to Choose Directory to Save #2");
		btnClickHereTo_1.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnClickHereTo_1.setBackground(Color.CYAN);
		btnClickHereTo_1.setBounds(380, 242, 315, 23);
		btnClickHereTo_1.setBorder(border);
		btnClickHereTo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        
				 JFileChooser chooser = new JFileChooser(); 
				 chooser.setCurrentDirectory(new java.io.File("."));
				 //chooser.setDialogTitle(choosertitle);
				 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				 chooser.setAcceptAllFileFilterUsed(false);
				 
				 int returnVal = chooser.showOpenDialog(getParent());
				 
			     try {
			    	 if(returnVal == JFileChooser.APPROVE_OPTION) {
			    		 
			    		 directory2 = chooser.getSelectedFile().getAbsolutePath();
					   	 //System.out.println(directory);
						 label_3.setText(directory2);
			    	
			    	 } else {System.out.println("Valid directory not chosen.");}
			    	 
				 } catch(NullPointerException e1) {
						
					 System.out.println("Directory not chosen.");
						
				 }
				
			}
		});
		contentPane.add(btnClickHereTo_1);
		
		//---------------------------------------------------------------------------------------------------------------------
		JLabel lblChooseAName_1 = new JLabel("Choose a name and format for File #2");
		lblChooseAName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAName_1.setForeground(new Color(0, 0, 128));
		lblChooseAName_1.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblChooseAName_1.setBackground(Color.CYAN);
		lblChooseAName_1.setBounds(380, 323, 315, 23);
		lblChooseAName_1.setBorder(border);
		contentPane.add(lblChooseAName_1);
		
		//---------------------------------------------------------------------------------------------------------------------
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(0, 0, 128));
		textField.setFont(new Font("Courier New", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBackground(Color.CYAN);
		textField.setBounds(380, 357, 315, 23);
		textField.setBorder(border);
		contentPane.add(textField);
		name2 = textField.getSelectedText();
		
		//---------------------------------------------------------------------------------------------------------------------
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setForeground(new Color(0, 0, 128));
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"jpg", "png"}));
		comboBox_3.setFont(new Font("Courier New", Font.PLAIN, 12));
		comboBox_3.setBackground(Color.CYAN);
		comboBox_3.setBounds(380, 391, 315, 30);
		comboBox_3.setBorder(border);
		format2 = (String)comboBox_3.getSelectedItem();
		contentPane.add(comboBox_3);
		
		//---------------------------------------------------------------------------------------------------------------------------------------- LAST ENTRY. EXITS WINDOW USING GIVEN SETTINGS
				JButton btnNewButton_1 = new JButton("Go!");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						//gets the data at the end
						modification1 = (String)comboBox.getSelectedItem();
						format1 = (String)comboBox_1.getSelectedItem();
						name1 = txtChooseAName.getText();
						
						modification2 = (String)comboBox_2.getSelectedItem();
						format2 = (String)comboBox_3.getSelectedItem();
						name2 = textField.getText();
						
						//testing println statemtents
						/*
						System.out.println(file1 + " " + modification1 + " " + directory1);
						System.out.println(name1 + "." + format1);
						System.out.println(file2 + " " + modification2 + " " + directory2);
						System.out.println(name2 + "." + format2);
						*/
						
						//if statement makes sure that all necessary parameters were given
						if(!file1.equals("") && !directory1.equals("") && !name1.equals("") && !file2.equals("") && !directory2.equals("") && !name2.equals("") && !name1.equals(name2)){
					
							//shows compare window using given data
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										Compare_Window frame = new Compare_Window(file1, modification1, directory1, name1, format1, file2, modification2, directory2, name2, format2);
										frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							
							dispose();
					
						}
						
						//series of if statements to show error messages for necessary data that may have been left out
						if(name1.equals(name2)) {
							
							infoBox("Please do not give the files the same name.", "Error");
							
						}
						if(file1.equals("")) {
							
							infoBox("Please select file #1.", "Error");
							
						}
						if(directory1.equals("")) {
							
							infoBox("Please select a directory to save file #1.", "Error");
							
						}
						if(name1.equals("")) {
							
							infoBox("Please select a name for file #1.", "Error");
							
						}
						if(file2.equals("")) {
							
							infoBox("Please select file #2.", "Error");
							
						}
						if(directory2.equals("")) {
							
							infoBox("Please select a directory to save file#2.", "Error");
							
						}
						if(name2.equals("")) {
							
							infoBox("Please select a name for file #2.", "Error");
							
						}
						
					}
				});
				btnNewButton_1.setBackground(new Color(0, 255, 255));
				btnNewButton_1.setFont(new Font("Courier New", Font.PLAIN, 20));
				btnNewButton_1.setForeground(new Color(0, 0, 128));
				btnNewButton_1.setBounds(35, 462, 660, 58);
				btnNewButton_1.setBorder(border);
				contentPane.add(btnNewButton_1);
				
		
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Shows a message box with a title and message
	 * pre: none
	 * post: a message box is shown with the title and message
	 */
	public static void infoBox(String infoMessage, String titleBar){
		
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
        
    }
}
