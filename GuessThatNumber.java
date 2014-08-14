/**
	The Guess That number program has the user input
   a guess between 1 and 10, then compares it to the 
   number giving the user hints along the way.

	@author William O'Sullivan
	@version 4/6/2014
	CIS 2151
*/

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class GuessThatNumber extends JFrame
{
	JPanel panel = new JPanel();
	JTextField left = new JTextField(5);
	JLabel middleLabel = new JLabel("Your guesses so far: ");
	JButton button = new JButton("Next");
	JLabel rightLabel = new JLabel("Status: New Game");
	private static final int WINDOW_WIDTH = 750;
	private static final int WINDOW_HEIGHT = 100;
	private int guess;
	
	Random rand = new Random();
	private static final int MAX_NUMBER = 10;
	private int winner = rand.nextInt(MAX_NUMBER) + 1;
	
   /*
   The Guess That Number constructor builds the GUI panel
   and creates all the buttons and handlers associated with.
   
   @return Sends the handler input to the handler classes,
   as well as the data inputted to the text fields.
   */
   
	public GuessThatNumber()
	{
	
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setTitle("Guess That Number! by William O'Sullivan");
		
		panel.add(left);
		panel.add(middleLabel);
		panel.add(button);
		button.setEnabled(false);
		panel.add(rightLabel);
		
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button.addActionListener( new ButtonHandler() );
		left.addActionListener( new TextHandler() );

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
		
	}
	/*
   The main class simply opesn and runs the new window
   and starts the game.
   
   @return Sends data command to create the window.
   */
   
	public static void main(String [] args)
	{
		GuessThatNumber window = new GuessThatNumber();
	}
	
   /*
   The DuttonHandler monitors clicking of the button for
   Next Game.
   
   @return Creates new game resetting the layout.
   */
   
	class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			left.setEditable(true);
			left.setText("");
			middleLabel.setText("Your guesses so far: ");
			winner = rand.nextInt(MAX_NUMBER) + 1;
			rightLabel.setText("Status: New Game");
			button.setEnabled(false);	
		}
	}
	
   /*
   The TextHandler monitors the text input and compares it
   to a random number generated to see if the user guessed the
   correct number. It also throws exceptions if they input a number
   not in range or a non number.
   
   @param NumberFormatException if the number does not match.
   */
   
	class TextHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
		
			boolean isNumber = left.getText().matches("\\d+");
			
         try
         {
         
			   if (isNumber == true)
			   {
			
				   guess = Integer.parseInt(left.getText());
			
				   if (guess > MAX_NUMBER)
				   {
				   	JOptionPane.showMessageDialog(null, "Guess must be between 1 and 10!");
				   	left.setText("");	
				   }
			
				   else if (guess != winner)
				   {
				   	middleLabel.setText(middleLabel.getText() + " " + guess + ", ");
				   	left.setText("");
				
				   	if (guess < winner)
				   		rightLabel.setText("Status: Guess higher!");
				   	else
				   		rightLabel.setText("Status: Guess lower!");	
				   }
			
				   else
				   {
				   	rightLabel.setText("You win!");
				   	left.setEditable(false);
				   	button.setEnabled(true);
				   }
			   }
            
            else 
            {
               throw new NumberFormatException();   
            }         	
		   }
         catch (NumberFormatException nfe)
         {
            JOptionPane.showMessageDialog(null, "Number must be between 1 and 10!");
            left.setText("");
         }
	   }
   }	
}	