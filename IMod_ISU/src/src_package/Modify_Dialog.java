package src_package;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.AbstractListModel;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Modify_Dialog extends JFrame {

	private JPanel contentPane;
	
	//declarations of data the user inputs using UI
	static String file = new String("");
	static String modification = new String("");
	static String directory = new String("");
	static String name = new String("");
	static String format = new String("");
	private JLabel lblNewLabel;
	private JTextField txtChooseAName;
	
	//---------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Create the frame.
	 */
	public Modify_Dialog() {
		setResizable(false);
		setTitle("Modify");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 128), 2);
		
		//---------------------------------------------------------------------------------------------------------------------
		//label that shows file selected
		lblNewLabel = new JLabel(file);
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 10));
		lblNewLabel.setBackground(new Color(0, 255, 255));
		lblNewLabel.setBounds(35, 106, 315, 23);
		lblNewLabel.setBorder(border);
		contentPane.add(lblNewLabel);
		//lblNewLabel.setText(file);
		
		//---------------------------------------------------------------------------------------------------------------------
		//allows user to choose file
		JButton btnNewButton = new JButton("Click here to Choose File");
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
						
						file = chooser.getSelectedFile().getAbsolutePath();
						//System.out.println(file);
						lblNewLabel.setText(file);
						
					} else {System.out.println("Valid file not chosen.");}
				} catch(NullPointerException e1) {
					
					System.out.println("File not chosen.");
					
				}
				
			}
		});
		btnNewButton.setBounds(35, 72, 315, 23);
		contentPane.add(btnNewButton);
		
		//---------------------------------------------------------------------------------------------------------------------
		//label prompting parameters for modification
		JLabel lblPickAFile = new JLabel("Select Parameters for Modification");
		lblPickAFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblPickAFile.setForeground(new Color(0, 0, 128));
		lblPickAFile.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblPickAFile.setBackground(new Color(0, 255, 255));
		lblPickAFile.setBounds(35, 11, 315, 30);
		lblPickAFile.setBorder(border);
		contentPane.add(lblPickAFile);
		
		//---------------------------------------------------------------------------------------------------------------------
		//separators
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 140, 315, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 59, 315, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(35, 229, 315, 2);
		contentPane.add(separator_2);
		
		//---------------------------------------------------------------------------------------------------------------------
		//combobox for modification options
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"No Modification", "Convert to Grayscale", "Simulate Protanopia", "Simulate Deuteranopia", "Simulate Tritanopia"}));
		comboBox.setFont(new Font("Courier New", Font.PLAIN, 12));
		comboBox.setBackground(new Color(0, 255, 255));
		comboBox.setBounds(35, 187, 315, 30);
		comboBox.setBorder(border);
		modification = (String)comboBox.getSelectedItem();
		contentPane.add(comboBox);
		
		//---------------------------------------------------------------------------------------------------------------------
		//text label
		JLabel lblPickAModification = new JLabel("Pick a modification");
		lblPickAModification.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblPickAModification.setForeground(new Color(0, 0, 128));
		lblPickAModification.setBackground(new Color(0, 255, 255));
		lblPickAModification.setHorizontalAlignment(SwingConstants.CENTER);
		lblPickAModification.setBounds(35, 153, 315, 23);
		lblPickAModification.setBorder(border);
		contentPane.add(lblPickAModification);
		
		//---------------------------------------------------------------------------------------------------------------------
		//label that shows user's option
		JLabel label = new JLabel("");
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Courier New", Font.PLAIN, 10));
		label.setBackground(Color.CYAN);
		label.setBounds(35, 276, 315, 23);
		label.setBorder(border);
		contentPane.add(label);
		
		//---------------------------------------------------------------------------------------------------------------------
		//prompts for a directory to save file
		JButton btnClickHereTo = new JButton("Click here to Choose Directory to Save");
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
			    		 
			    		 directory = chooser.getSelectedFile().getAbsolutePath();
					   	 //System.out.println(directory);
						 label.setText(directory);
			    	
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
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(35, 310, 315, 2);
		contentPane.add(separator_4);
		
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
		name = txtChooseAName.getSelectedText();
		
		//---------------------------------------------------------------------------------------------------------------------
		//asks for format
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"jpg", "png"}));
		comboBox_1.setForeground(new Color(0, 0, 128));
		comboBox_1.setFont(new Font("Courier New", Font.PLAIN, 12));
		comboBox_1.setBackground(Color.CYAN);
		comboBox_1.setBounds(35, 391, 315, 30);
		comboBox_1.setBorder(border);
		format = (String)comboBox_1.getSelectedItem();
		contentPane.add(comboBox_1);
		
		//---------------------------------------------------------------------------------------------------------------------
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(35, 432, 315, 2);
		contentPane.add(separator_5);
		
		//---------------------------------------------------------------------------------------------------------------------
		JLabel lblChooseAName = new JLabel("Choose a name and format for the new file");
		lblChooseAName.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAName.setForeground(new Color(0, 0, 128));
		lblChooseAName.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblChooseAName.setBackground(Color.CYAN);
		lblChooseAName.setBounds(35, 323, 315, 23);
		lblChooseAName.setBorder(border);
		contentPane.add(lblChooseAName);
		
		//--------------------------------------------------------------------------------------------------------------------- RUNS MODIFY WINDOW AFTER USER INPUT 
		JButton btnNewButton_1 = new JButton("Go!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//sets user options at end
				modification = (String)comboBox.getSelectedItem();
				format = (String)comboBox_1.getSelectedItem();
				name = txtChooseAName.getText();
				
				//System.out.println(name + "." + format);
				
				//makes sure that all necessary data was given
				if(!file.equals("") && !directory.equals("") && !name.equals("")){
			
					//runs window for modify mode
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Modify_Window frame = new Modify_Window(file, modification, directory, name, format);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
					dispose();
			
				}
				
				//displays error messages for data that was not given
				if(file.equals("")) {
					
					infoBox("Please select a file.", "Error");
					
				}
				if(directory.equals("")) {
					
					infoBox("Please select a directory.", "Error");
					
				}
				if(name.equals("")) {
					
					infoBox("Please select a name for the file.", "Error");
					
				}
				
			}
		});
		btnNewButton_1.setBackground(new Color(0, 255, 255));
		btnNewButton_1.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnNewButton_1.setForeground(new Color(0, 0, 128));
		btnNewButton_1.setBounds(35, 462, 315, 58);
		btnNewButton_1.setBorder(border);
		contentPane.add(btnNewButton_1);
		
	}
	
	/**
	 * Shows a message box with a title and message
	 * pre: none
	 * post: a message box is shown with the title and message
	 */
	public static void infoBox(String infoMessage, String titleBar){
		
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
        
    }
}
