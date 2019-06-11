package src_package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Main_Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Main_Menu() {
		
		//window declarations and setup
		setResizable(false);
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 128), 2);
		contentPane.setLayout(null);
		
		//---------------------------------------------------------------------------------------------------------------------
		//title
		JLabel lblImod = new JLabel("IMod");
		lblImod.setHorizontalAlignment(SwingConstants.CENTER);
		lblImod.setBounds(5, 6, 384, 93);
		lblImod.setForeground(new Color(0, 0, 128));
		lblImod.setFont(new Font("Courier Std", Font.PLAIN, 40));
		contentPane.add(lblImod);
		
		//---------------------------------------------------------------------------------------------------------------------
		//caption
		JLabel lblImageModificationSoftware = new JLabel("  Image Modification Software");
		lblImageModificationSoftware.setBackground(new Color(0, 255, 255));
		lblImageModificationSoftware.setBounds(5, 89, 384, 93);
		lblImageModificationSoftware.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblImageModificationSoftware.setBorder(border);
		contentPane.add(lblImageModificationSoftware);
		
		//---------------------------------------------------------------------------------------------------------------------
		//runs modify logic
		JButton btnNewButton = new JButton("Modify");
		btnNewButton.setBounds(5, 192, 384, 93);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Modify");
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Modify_Dialog frame = new Modify_Dialog();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				dispose();
				
			}
		});
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnNewButton.setBorder(border);
		contentPane.add(btnNewButton);
		
		//---------------------------------------------------------------------------------------------------------------------
		//runs compare logic
		JButton btnNewButton_1 = new JButton("Compare");
		btnNewButton_1.setBounds(5, 296, 384, 93);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Compare");
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Compare_Dialog frame = new Compare_Dialog();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				dispose();
				
			}
		});
		btnNewButton_1.setForeground(new Color(0, 0, 128));
		btnNewButton_1.setBackground(new Color(0, 255, 255));
		btnNewButton_1.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnNewButton_1.setBorder(border);
		contentPane.add(btnNewButton_1);
		
		//---------------------------------------------------------------------------------------------------------------------
		//displays infoBox for about
		JButton btnNewButton_3 = new JButton("About");
		btnNewButton_3.setBounds(5, 400, 384, 93);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("About");
				
				infoBox("IMod is an image modification program."
						+ "\nSelect Modify or Compare modes:"
						+ "\nIn Modify, pick an image and the program will display a modification."
						+ "\nIn Compare, do this for two images and they will be displayed side by side."
						+ "\nThe modifications include color blindness simulation and some others."
						+ "\n"
						+ "\nSoftware by Matthew Vidov"
						+ "\nMatrix class from Jama library"
						+ "\nTransformation matrix parameters from ixora.io Color Blindness Simulation Research", "About");
				
				
			}
		});
		btnNewButton_3.setForeground(new Color(0, 0, 128));
		btnNewButton_3.setBackground(new Color(0, 255, 255));
		btnNewButton_3.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnNewButton_3.setBorder(border);
		contentPane.add(btnNewButton_3);

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
