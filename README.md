# Design Document #

Chase Cullen
============

This application allows the user to draw pictures and has the google api grade them based on how well they drew the required
thing. Includes a brief splash screen, a main menu with music, a series of rounds of drawings, and a score screen.


System Design
=============

Need a google account, an internet connection, and an android touch-screen device.
Application targeted version is Nougat 7.0 - 7.1.2
Any android system can run this application




Usage
=====
Open the application from your device. The splash screen will start the application and take you to the main menu.
Press the select account button in the bottom left and select a valid google account, and follow the required steps. Once you
have connected a valid google account, press the play button. The play button will not work unless a valid account has been
selected. If the select button has been pressed and nothing appears to happen, the app has already selected a valid
google account. Once the play button is pressed, new music will play and a canvas will appear with different colors and 
different utencil options. The canvas include 12 different colors, the ability to change switch back and forth between 
drawing and erasing, and a clear page button. The draw and erase buttons include a small, medium, and large sized utencils.
The clear page will prompt the user, asking if they are sure they want to erase the canvas and start from scratch. 
Additionally, text in the upper portion of the screen will indicate to the user what to draw in the canvas. A timer in the
upper right corner indicates how much time the user has left. When the time runs out, the current drawing will be saved and
will go to the next canvas view, giving the user another thing to draw. At the end of the drawing rounds, thumbnails of each
of the users drawings will appear with a text underneath that says "Calculating." At this point, the drawings have already
been sent to google for analysis. To see the scores of your pictures, click the "Calculate Scores" button in the bottom right.
If some or no scores display, the app is still analyzing the images. Give it a few seconds, and press the button again.
A score for each drawing will be displayed, and a total score will be added up at the bottom. To return to the main menu,
and play again, press the "Back to menu" button in the bottom left.
