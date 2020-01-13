# i-mod-java
A modular Java application designed to make modifications to images.

Made as a final project for a grade 12 high school java course.

Note: See the demo images above for a visual of the project in action.

IMOD is an image modification program that allows the user to apply one of several given modifications to any image.
User can choose where this new file will be saved and name it, after which it is displayed on a window.
The modifications available are similar to filters, and they include:
  Convert to Grayscale: makes the image black and white
  Simulate Protanopia: simulates protanopia, a type of colour blindness in which a green vision cone is not present.
  Simulate Deuteranopia: simulates deuteranopia, a type of colour blindness in which a red vision cone is not present.
  Simulate Tritanopia: simulates tritanopia, a type of colour blindness in which a blue vision cone is not present.

There are two primary modes. 
The first is "Modify". This mode will take in one image and display it with a given modification.
The second is 'Compare". This mode takes in two images, and displays them both side by side. Each image can be saved to a separate location.


This project involved research and data taken from outside sources.

for the underlying math to simulate colour blindness, as well as the theory behind it:
https://ixora.io/projects/colorblindness/color-blindness-simulation-research/

JAMA 'Matrix' class is used in this project. retrieved from: https://math.nist.gov/javanumerics/jama/

Here is the main menu:

![Menu](/IMOD_MENU.PNG)

Here are some images of the application in action:

![Image1](/IMOD_DEMO_1.PNG)
