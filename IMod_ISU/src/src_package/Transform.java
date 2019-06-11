package src_package;

import java.awt.image.BufferedImage;

public class Transform {

	/*
	 * static inner class, allowing these methods to be accessed without instantiating an object of Transform
	 */
	static class imageTrans {
		
		/*
		 * method to convert an image to grayscale
		 */
		public static BufferedImage grayscale(BufferedImage input) {
			
			//for loops iterate through every pixel of the image. same for all of these methods
			for(int x = 0; x < input.getWidth(); x++) {
				
				for(int y = 0; y < input.getHeight(); y++) {
					
					//int pixel value
					int p = 0;
					
					//get pixel argb values
					int a = 255;
					int r = (getPixel(input, x, y)>>16) & 0xFF;
					int g = (getPixel(input, x, y)>>8) & 0xFF;
					int b = (getPixel(input, x, y)) & 0xFF;
					
					//conversion for grayscale. takes the average value of all three colours in each pixel.
					int avg = (r + g + b)/3;
					
					//makes each colour the same as the average. this effectively makes the image grayscale. (or all black and white)
					r = avg;
					g = avg;
					b = avg;
					
					//assign values in RGB format to pixel int. undergo binary shift conversion to 'allow computer to understand' the values
					p = (a<<24) | (r<<16) | (g<<8) | b;	
						
					//sets the pixel
					input.setRGB(x, y, p);
					
				}
				
			}
			
			return input;
			
		}
		
		/*
		 * method to simulate protanopia
		 */
		public static BufferedImage protanopia(BufferedImage input) {
			
			for(int x = 0; x < input.getWidth(); x++) {
				
				for(int y = 0; y < input.getHeight(); y++) {
					
					int p = 0;
					
					//get pixel values
					int a = 255;
					int r = (getPixel(input, x, y)>>16) & 0xFF;
					int g = (getPixel(input, x, y)>>8) & 0xFF;
					int b = (getPixel(input, x, y)) & 0xFF;
					
					//w/o gamma correction
					double rG = 0;
					double gG = 0;
					double bG = 0;
					
					//when done converting back to gamma uncorrected rgb from lms
					double rGf = 0;
					double gGf = 0;
					double bGf = 0;
					
					//remove gamma correction
					if(r <= 0.04045) {
						
						rG = ((double)r/255)/12.92;
						//rG = Math.pow((((double)r/255 + 0.055)/1.055), 2.4);
						
					} else if(r > 0.04045) {
						
						rG = Math.pow((((double)r/255 + 0.055)/1.055), 2.4);
						
					}
					if(g <= 0.04045) {
						
						gG = ((double)g/255)/12.92;
						//gG = Math.pow((((double)g/255 + 0.055)/1.055), 2.4);
						
					} else if(g > 0.04045) {
						
						gG = Math.pow((((double)g/255 + 0.055)/1.055), 2.4);
						
					}
					if(b <= 0.04045) {
		
						bG = ((double)b/255)/12.92;
						//bG = Math.pow((((double)b/255 + 0.055)/1.055), 2.4);
		
					} else if(b > 0.04045) {
		
						bG = Math.pow((((double)b/255 + 0.055)/1.055), 2.4);
		
					}
					
					//creates a vector for gamma corrected rgb values
					double[][] rgbGvals = {{rG}, {gG}, {bG}};
					Matrix rgbG = new Matrix(rgbGvals);
					
					//initializes "T" matrix to convert the values to the lms space
					double[][] Tvals = {{0.31399022, 0.63951294, 0.04649755}, {0.15537241, 0.75789446, 0.08670142}, {0.01775239, 0.10944209, 0.87256922}};
					Matrix T = new Matrix(Tvals);
					
					//initializes the inverse of T, to convert back from lms after modification is done
					double[][] Tinvvals = {{5.47221206, -4.6419601, 0.16963708}, {-1.1252419, 2.29317094, -0.1678952}, {0.02980165, -0.19318073, 1.16364789}};
					Matrix Tinv = new Matrix(Tinvvals);
					
					//Matrix for values in lms space, done by matrix multiplication with T
					Matrix lms = T.times(rgbG);
					
					//initializes parametric matrix for converting rgb values in the lms space to colours that a protanope would see. data collected from an outside source, cited in "About" infobox
					//detail: for protanopes, who don't see red, the red row of this matrix has a 0 in it. the green and blue rows are unaffected. 
					//the extra values in the red row are there to properly preserve the matrix values, due to a possible erroneous assumption that may be made otherwise.
					double[][] protanopiavals = {{0, 1.05118294, -0.05116099}, {0, 1, 0}, {0, 0, 1}};
					Matrix protanopia = new Matrix(protanopiavals);
					
					//declares final matrices for the modified values. performs inverse operations to get the values out of lms space
					Matrix lmsf = protanopia.times(lms);
					Matrix rgbGf = Tinv.times(lmsf);
					
					//extracts a 2D array out of the final matrix
					double[][] rgbGfvals = rgbGf.getArray();
					
					//extracts individual values out of 2D array
					rGf = rgbGfvals[0][0];
					gGf = rgbGfvals[1][0];
					bGf = rgbGfvals[2][0];
					
					//add back gamma correction using reverse of formula used at beginning
					if(rGf <= 0.0031308) {
						
						r = roundDoub((255*(12.92*rGf)));
						
					} else if(rGf > 0.0031308) {
						
						r = roundDoub((255*(1.055*(Math.pow(rGf, 0.41666)) - 0.055)));
						
					}
					if(gGf <= 0.0031308) {
						
						g = roundDoub((255*(12.92*gGf)));
						
					} else if(gGf > 0.0031308) {
						
						g = roundDoub((255*(1.055*(Math.pow(gGf, 0.41666)) - 0.055)));
						
					}
					if(bGf <= 0.0031308) {
						
						b = roundDoub((255*(12.92*bGf)));
						
					} else if(bGf > 0.0031308) {
						
						b = roundDoub((255*(1.055*(Math.pow(bGf, 0.41666)) - 0.055)));
						
					}
					
					//checks for overflow in values. argb values must be bounded by [0, 255], so if the values exceed or undercut this they will be set to the max/min
					if(r < 0) { r=0; }
					if(g < 0) { g=0; }
					if(b < 0) { b=0; }
					
					if(r >255) { r=255; }
					if(g >255) { g=255; }
					if(b >255) { b=255; }
					
					//assign values in RGB format to pixel int
					p = (a<<24) | (r<<16) | (g<<8) | b;	
						
					//sets the pixel
					input.setRGB(x, y, p);
					
					//logic used here is same for other conversions, so extrapolate comments from here to the rest.
					
				}
				
			}
			
			return input;
			
		}
		
		/*
		 * method to simulate deuteranopia
		 */
		public static BufferedImage deuteranopia(BufferedImage input) {
			
			for(int x = 0; x < input.getWidth(); x++) {
				
				for(int y = 0; y < input.getHeight(); y++) {
					
					int p = 0;
					
					//get pixel values
					int a = 255;
					int r = (getPixel(input, x, y)>>16) & 0xFF;
					int g = (getPixel(input, x, y)>>8) & 0xFF;
					int b = (getPixel(input, x, y)) & 0xFF;
					
					//w/o gamma correction
					double rG = 0;
					double gG = 0;
					double bG = 0;
					
					//when done converting back to gamma uncorrected rgb from lms
					double rGf = 0;
					double gGf = 0;
					double bGf = 0;
					
					//remove gamma correction
					if(r <= 0.04045) {
						
						rG = ((double)r/255)/12.92;
						//rG = Math.pow((((double)r/255 + 0.055)/1.055), 2.4);
						
					} else if(r > 0.04045) {
						
						rG = Math.pow((((double)r/255 + 0.055)/1.055), 2.4);
						
					}
					if(g <= 0.04045) {
						
						gG = ((double)g/255)/12.92;
						//gG = Math.pow((((double)g/255 + 0.055)/1.055), 2.4);
						
					} else if(g > 0.04045) {
						
						gG = Math.pow((((double)g/255 + 0.055)/1.055), 2.4);
						
					}
					if(b <= 0.04045) {
		
						bG = ((double)b/255)/12.92;
						//bG = Math.pow((((double)b/255 + 0.055)/1.055), 2.4);
		
					} else if(b > 0.04045) {
		
						bG = Math.pow((((double)b/255 + 0.055)/1.055), 2.4);
		
					}
					
					double[][] rgbGvals = {{rG}, {gG}, {bG}};
					Matrix rgbG = new Matrix(rgbGvals);
					
					double[][] Tvals = {{0.31399022, 0.63951294, 0.04649755}, {0.15537241, 0.75789446, 0.08670142}, {0.01775239, 0.10944209, 0.87256922}};
					Matrix T = new Matrix(Tvals);
					
					double[][] Tinvvals = {{5.47221206, -4.6419601, 0.16963708}, {-1.1252419, 2.29317094, -0.1678952}, {0.02980165, -0.19318073, 1.16364789}};
					Matrix Tinv = new Matrix(Tinvvals);
					
					Matrix lms = T.times(rgbG);
					
					double[][] deuteranopiavals = {{1, 0, 0}, {0.9513092, 0, 0.04866992}, {0, 0, 1}};
					Matrix deuteranopia = new Matrix(deuteranopiavals);
					
					Matrix lmsf = deuteranopia.times(lms);
					Matrix rgbGf = Tinv.times(lmsf);
					
					double[][] rgbGfvals = rgbGf.getArray();
					
					rGf = rgbGfvals[0][0];
					gGf = rgbGfvals[1][0];
					bGf = rgbGfvals[2][0];
					
					//add back gamma correction
					if(rGf <= 0.0031308) {
						
						r = roundDoub((255*(12.92*rGf)));
						
					} else if(rGf > 0.0031308) {
						
						r = roundDoub((255*(1.055*(Math.pow(rGf, 0.41666)) - 0.055)));
						
					}
					if(gGf <= 0.0031308) {
						
						g = roundDoub((255*(12.92*gGf)));
						
					} else if(gGf > 0.0031308) {
						
						g = roundDoub((255*(1.055*(Math.pow(gGf, 0.41666)) - 0.055)));
						
					}
					if(bGf <= 0.0031308) {
						
						b = roundDoub((255*(12.92*bGf)));
						
					} else if(bGf > 0.0031308) {
						
						b = roundDoub((255*(1.055*(Math.pow(bGf, 0.41666)) - 0.055)));
						
					}
					
					if(r < 0) { r=0; }
					if(g < 0) { g=0; }
					if(b < 0) { b=0; }
					
					if(r >255) { r=255; }
					if(g >255) { g=255; }
					if(b >255) { b=255; }
					
					//assign values in RGB format to pixel int
					p = (a<<24) | (r<<16) | (g<<8) | b;	
						
					//sets the pixel
					input.setRGB(x, y, p);
					
				}
				
			}
			
			return input;
			
		}
		
		/*
		 * method to simulate tritanopia
		 */
		public static BufferedImage tritanopia(BufferedImage input) {
			
			for(int x = 0; x < input.getWidth(); x++) {
				
				for(int y = 0; y < input.getHeight(); y++) {
					
					int p = 0;
					
					//get pixel values
					int a = 255;
					int r = (getPixel(input, x, y)>>16) & 0xFF;
					int g = (getPixel(input, x, y)>>8) & 0xFF;
					int b = (getPixel(input, x, y)) & 0xFF;
					
					//w/o gamma correction
					double rG = 0;
					double gG = 0;
					double bG = 0;
					
					//when done converting back to gamma uncorrected rgb from lms
					double rGf = 0;
					double gGf = 0;
					double bGf = 0;
					
					//remove gamma correction
					if(r <= 0.04045) {
						
						rG = ((double)r/255)/12.92;
						//rG = Math.pow((((double)r/255 + 0.055)/1.055), 2.4);
						
					} else if(r > 0.04045) {
						
						rG = Math.pow((((double)r/255 + 0.055)/1.055), 2.4);
						
					}
					if(g <= 0.04045) {
						
						gG = ((double)g/255)/12.92;
						//gG = Math.pow((((double)g/255 + 0.055)/1.055), 2.4);
						
					} else if(g > 0.04045) {
						
						gG = Math.pow((((double)g/255 + 0.055)/1.055), 2.4);
						
					}
					if(b <= 0.04045) {
		
						bG = ((double)b/255)/12.92;
						//bG = Math.pow((((double)b/255 + 0.055)/1.055), 2.4);
		
					} else if(b > 0.04045) {
		
						bG = Math.pow((((double)b/255 + 0.055)/1.055), 2.4);
		
					}
					
					double[][] rgbGvals = {{rG}, {gG}, {bG}};
					Matrix rgbG = new Matrix(rgbGvals);
					
					double[][] Tvals = {{0.31399022, 0.63951294, 0.04649755}, {0.15537241, 0.75789446, 0.08670142}, {0.01775239, 0.10944209, 0.87256922}};
					Matrix T = new Matrix(Tvals);
					
					double[][] Tinvvals = {{5.47221206, -4.6419601, 0.16963708}, {-1.1252419, 2.29317094, -0.1678952}, {0.02980165, -0.19318073, 1.16364789}};
					Matrix Tinv = new Matrix(Tinvvals);
					
					Matrix lms = T.times(rgbG);
					
					double[][] tritanopiavals = {{1, 0, 0}, {0, 1, 0}, {-0.86744736, 1.86727089, 0}};
					Matrix tritanopia = new Matrix(tritanopiavals);
					
					Matrix lmsf = tritanopia.times(lms);
					Matrix rgbGf = Tinv.times(lmsf);
					
					double[][] rgbGfvals = rgbGf.getArray();
					
					rGf = rgbGfvals[0][0];
					gGf = rgbGfvals[1][0];
					bGf = rgbGfvals[2][0];
					
					//add back gamma correction
					if(rGf <= 0.0031308) {
						
						r = roundDoub((255*(12.92*rGf)));
						
					} else if(rGf > 0.0031308) {
						
						r = roundDoub((255*(1.055*(Math.pow(rGf, 0.41666)) - 0.055)));
						
					}
					if(gGf <= 0.0031308) {
						
						g = roundDoub((255*(12.92*gGf)));
						
					} else if(gGf > 0.0031308) {
						
						g = roundDoub((255*(1.055*(Math.pow(gGf, 0.41666)) - 0.055)));
						
					}
					if(bGf <= 0.0031308) {
						
						b = roundDoub((255*(12.92*bGf)));
						
					} else if(bGf > 0.0031308) {
						
						b = roundDoub((255*(1.055*(Math.pow(bGf, 0.41666)) - 0.055)));
						
					}
					
					if(r < 0) { r=0; }
					if(g < 0) { g=0; }
					if(b < 0) { b=0; }
					
					if(r >255) { r=255; }
					if(g >255) { g=255; }
					if(b >255) { b=255; }
					
					//assign values in RGB format to pixel int
					p = (a<<24) | (r<<16) | (g<<8) | b;	
						
					//sets the pixel
					input.setRGB(x, y, p);
					
				}
				
			}
			
			return input;
			
		}
		
		/*
		 * gets value for a given pixel of an image. helper method
		 */
		private static int getPixel(BufferedImage input, int x, int y) {
			
			int p = input.getRGB(x, y);
			return p;
			
		}
		
		/*
		 * simple method to round a double. helper method
		 */
		private static int roundDoub(double num) {
			
			int rounded;
			
			if(num - (int)num >= 0.50) {
				
				rounded = (int)num + 1;
				
			} else {
				
				rounded = (int)num;
				
			}
			
			return rounded;
			
		}
		
	}
	
}
