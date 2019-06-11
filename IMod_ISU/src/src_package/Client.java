package src_package;

import java.awt.EventQueue;

/**
 * Beginning of the program.
 * By: Matthew Vidov
 * 
 */

public class Client {

	/*
	 * Main method, runs the main menu window
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Menu frame = new Main_Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
