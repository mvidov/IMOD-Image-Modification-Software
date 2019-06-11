package src_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Modify_Window extends JFrame {

	private JPanel contentPane;
	
	//window dimensions
	final static int winX = 1600;
	final static int winY = 900;
	
	//image dimensions
	static int imageX;
	static int imageY;
	
	static JLabel imageLabel = new JLabel("");
	
	/**
	 * Create the frame. contains code relating to the UI
	 */
	public Modify_Window(String file, String modification, String directory, String name, String format) {
		
		//setup window
		setResizable(false);
		setTitle("Modify");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, winX, winY);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 128), 2);
		
		//---------------------------------------------------------------------------------------------------------------------
		//shows modification choice of user
		JLabel label_1 = new JLabel("File Modified: " + modification);
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("Courier New", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 10, 1574, 24);
		label_1.setBorder(border);
		contentPane.add(label_1);
		
		//runs logic
		Modify_Logic(file, modification, directory, name, format);
		
	}
	
	/*
	 * runs the logic of the program. separate from window/UI elements
	 * takes user input as parameters
	 */
	private void Modify_Logic(String file, String modification, String directory, String name, String format) {
		
		//println for debug
		//System.out.println(file + " " + modification + " " + directory + " " + name + "." + format);
		
		try {
			
			//reads image file, at given path
			BufferedImage image = ImageIO.read(new File(file));
			
			//defines image size
			imageX = image.getWidth();
			imageY = image.getHeight();
			//System.out.println(imageX + " " + imageY + " | " + winX + " " + winY);
			
			//changes the pixel size of the image if it is too big
			while(imageX > (winX-10)|| imageY > (winY-80)) {
				
				//aspect ratio of image
				double ratio = imageX/imageY;
				
				//if image is too large, resizes it in accordance with aspect ratio
				if(imageX > (winX-10)) {
					imageX = winX-10;
					imageY = (int)(imageX/ratio);
				}
				if(imageY > (winY-80)) {
					imageY = winY-80;
					imageX = (int)(ratio*imageY);
				}
				
			}
			
			//resizes the image according to pixel definitions
			BufferedImage resized = resizeImage(image, imageX, imageY);
			
			//defines file path for the modified image
			String finalPath = directory + "\\" + name + "." + format;
			//System.out.println(finalPath);
			
			//modify image
			BufferedImage modified = resized;
			
			//using given modification, will perform transformation using the static Transform class
			if(modification.equals("Convert to Grayscale")) {
				
				modified = Transform.imageTrans.grayscale(resized);
				
			}
			
			if(modification.equals("Simulate Protanopia")) {
				
				modified = Transform.imageTrans.protanopia(resized);
				
			}
			
			if(modification.equals("Simulate Deuteranopia")) {
				
				modified = Transform.imageTrans.deuteranopia(resized);
				
			}

			if(modification.equals("Simulate Tritanopia")) {
	
				modified = Transform.imageTrans.tritanopia(resized);
	
			}
			
			//writes the modified image to computer memory
			ImageIO.write(modified, format, new File(finalPath));
			
			//creates the label that is used to display image
			initLabel(finalPath);
			
			
		}
		catch(IOException e) { //catches IOException
			
			e.printStackTrace();
			System.out.println("IOException occurred.");
			
		}
		
	}
	
	/*
	 * initializes the label that will display the image
	 * sizes it appropriately
	 */
	private void initLabel(String path) {
		
		//sets position of image
		int xPos = (int)(winX - imageX)/2;
		int yPos = (int)(winY - imageY)/2;
		
		//sets up label
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setVerticalAlignment(SwingConstants.CENTER);
		imageLabel.setBounds(xPos, yPos, imageX, imageY);
		contentPane.add(imageLabel);

		//creates ImageIcon object to display the image
		ImageIcon img = new ImageIcon(path);
		imageLabel.setIcon(img);
		
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
