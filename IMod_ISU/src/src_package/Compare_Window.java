package src_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JSeparator;
import java.awt.Font;

public class Compare_Window extends JFrame {

	private JPanel contentPane;
	
	//window dimensions with halves for easy reference
	final static int winX = 1600;
	final static int winY = 900;
	static int winXH = (int)winX/2;
	static int winYH = (int)winY/2;
	
	//dimensions of undecided input images
	static int image1X;
	static int image1Y;
	static int image2X;
	static int image2Y;
	
	//labels to display images. for use later
	static JLabel imageLabel1 = new JLabel("");
	static JLabel imageLabel2 = new JLabel("");
	
	/**
	 * Create the frame. contains code relating to the UI
	 */
	public Compare_Window(String file1, String modification1, String directory1, String name1, String format1, String file2, String modification2, String directory2, String name2, String format2) {
		setResizable(false);
		setTitle("Compare");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, winX, winY);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 128), 2);
		
		//---------------------------------------------------------------------------------------------------------------------
		//visual divider in window
		JLabel dividerLabel = new JLabel("");
		dividerLabel.setBounds(794, 11, 10, 849);
		dividerLabel.setBorder(border);
		contentPane.add(dividerLabel);
		
		//---------------------------------------------------------------------------------------------------------------------
		//label showing the modification of first file
		JLabel label_1 = new JLabel("File #1 Modified: " + modification1);
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("Courier New", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 10, 770, 24);
		label_1.setBorder(border);
		contentPane.add(label_1);
		
		//---------------------------------------------------------------------------------------------------------------------
		//label showing the modification of second file
		JLabel label_2 = new JLabel("File #2 Modified: " + modification2);
		label_2.setForeground(new Color(0, 0, 128));
		label_2.setFont(new Font("Courier New", Font.PLAIN, 12));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(814, 11, 770, 24);
		label_2.setBorder(border);
		contentPane.add(label_2);
		
		//runs the logic of this window
		Compare_Logic(file1, modification1, directory1, name1, format1, file2, modification2, directory2, name2, format2);
		
	}
	
	/*
	 * runs the logic of the program. separate from the UI code
	 * takes in all the data as input from the dialog
	 */
	private void Compare_Logic(String file1, String modification1, String directory1, String name1, String format1, String file2, String modification2, String directory2, String name2, String format2) {
		
		//println statements for debug
		/*
		System.out.println(file1 + " " + modification1 + " " + directory1 + " " + name1 + "." + format1);
		System.out.println(file2 + " " + modification2 + " " + directory2 + " " + name2 + "." + format2);
		*/
		
		try {
			
			//reads image file, at given path
			BufferedImage image1 = ImageIO.read(new File(file1));
			BufferedImage image2 = ImageIO.read(new File(file2));
			
			//defines image size
			image1X = image1.getWidth();
			image1Y = image1.getHeight();
			image2X = image2.getWidth();
			image2Y = image2.getHeight();
			//System.out.println(imageX + " " + imageY + " | " + winX + " " + winY);
			
			//changes the pixel size of the image. while image is larger than given bounds, will run
			while(image1X > (winXH-20)|| image1Y > (winY-30)) {
				
				//aspect ratio of image
				double ratio = image1X/image1Y;
				
				//changes the dimensions, in agreement with the aspect ratio of the image. 
				if(image1X > (winXH-20)) {
					image1X = winXH-20;
					image1Y = (int)(image1X/ratio);
				}
				if(image1Y > (winY-30)) {
					image1Y = winY-30;
					image1X = (int)(ratio*image1Y);
				}
				
			}
			
			//same logic for the second image
			while(image2X > (winXH-20)|| image2Y > (winY-30)) {
				
				//aspect ratio
				double ratio = image2X/image2Y;
				
				if(image2X > (winXH-20)) {
					image2X = winXH-20;
					image2Y = (int)(image2X/ratio);
				}
				if(image2Y > (winY-30)) {
					image2Y = winY-30;
					image2X = (int)(ratio*image2Y);
				}
				
			}
			
			//takes the modifications made and applies them, effectively resizing image
			BufferedImage resized1 = resizeImage(image1, image1X, image1Y);
			BufferedImage resized2 = resizeImage(image2, image2X, image2Y);
			
			//defines file path for the modified image, after it is saved in computer memory
			String finalPath1 = directory1 + "\\" + name1 + "." + format1;
			String finalPath2 = directory2 + "\\" + name2 + "." + format2;
			//System.out.println(finalPath);
			
			//declare objects of modified image, not modified yet
			BufferedImage modified1 = resized1;
			BufferedImage modified2 = resized2;
			
			//will transform the image based on the user's input. Uses the static Transform class.
			if(modification1.equals("Convert to Grayscale")) {
				
				modified1 = Transform.imageTrans.grayscale(resized1);
				
			}
			
			if(modification1.equals("Simulate Protanopia")) {
				
				modified1 = Transform.imageTrans.protanopia(resized1);
				
			}
			
			if(modification1.equals("Simulate Deuteranopia")) {
				
				modified1 = Transform.imageTrans.deuteranopia(resized1);
				
			}

			if(modification1.equals("Simulate Tritanopia")) {
	
				modified1 = Transform.imageTrans.tritanopia(resized1);
	
			}
			
			if(modification2.equals("Convert to Grayscale")) {
				
				modified2 = Transform.imageTrans.grayscale(resized2);
				
			}
			
			if(modification2.equals("Simulate Protanopia")) {
				
				modified2 = Transform.imageTrans.protanopia(resized2);
				
			}
			
			if(modification2.equals("Simulate Deuteranopia")) {
				
				modified2 = Transform.imageTrans.deuteranopia(resized2);
				
			}

			if(modification2.equals("Simulate Tritanopia")) {
	
				modified2 = Transform.imageTrans.tritanopia(resized2);
	
			}
			
			//writes the modified images into computer memory.
			ImageIO.write(modified1, format1, new File(finalPath1));
			ImageIO.write(modified2, format2, new File(finalPath2));
			
			//creates the label that is used to display image
			initLabel1(finalPath1);
			initLabel2(finalPath2);
			
			
		}
		catch(IOException e) { //catches an IOException
			
			e.printStackTrace();
			System.out.println("IOException occurred.");
			
		}
		
	}
	
	/*
	 * initializes the label that will display the first image
	 * sizes it appropriately
	 */
	private void initLabel1(String path1) {
		
		//determines position on window based on dimensions of window and image
		int xPos = (int)(winXH - image1X)/2;
		int yPos = (int)(winY - image1Y)/2;
		
		//statements to initialize the label
		imageLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel1.setVerticalAlignment(SwingConstants.CENTER);
		imageLabel1.setBounds(xPos, yPos, image1X, image1Y);
		contentPane.add(imageLabel1);

		//creates an ImageIcon object of the image and draws it
		ImageIcon img = new ImageIcon(path1);
		imageLabel1.setIcon(img);
		
	}
	
	/*
	 * initializes the label that will display the second image
	 * sizes it appropriately
	 */
	private void initLabel2(String path2) {
		
		//same logic as previous method
		int xPos = (int)(winXH - image2X)/2 + winXH;
		int yPos = (int)(winY - image2Y)/2;
		
		imageLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel2.setVerticalAlignment(SwingConstants.CENTER);
		imageLabel2.setBounds(xPos, yPos, image2X, image2Y);
		contentPane.add(imageLabel2);

		ImageIcon img = new ImageIcon(path2);
		imageLabel2.setIcon(img);
		
	}
	
	/*
	 * resizes the image to the given pixel counts
	 */
	private BufferedImage resizeImage(BufferedImage input, int width, int height) throws IOException {
		
		BufferedImage output = new BufferedImage(width, height, input.getType());
		
		// scales the input image to the output image using Graphics2D class
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(input, 0, 0, width, height, null);
        g2d.dispose();
        
        return output;
		
	}
}
