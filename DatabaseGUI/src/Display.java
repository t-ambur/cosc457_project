

import java.awt.BorderLayout;
/*
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
*/
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

public class Display {
	
	// the window for the program
	private JFrame frame;
	// init variables for program
	private String title;
	private int width, height;
	// Panels that make up the interior of the window
	private JPanel buttonPanel;
	private JPanel inputPanel;
	// buttons that go in the button panel
	private JButton lookupButton;
	private JButton employeeButton;
	private JButton customerButton;
	// fields that go in the input panel
	private JLabel labelTop;
	private JLabel label2;
	
	//protected static Dimension mapSize;
	
	public Display(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init()
	{
		// init the main window
		frame = new JFrame(title);
		frame.setLayout(new BorderLayout());
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		
		// panel on the left side to go to a new screen on the right side
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		
		// panel on the right side to enter fields
		inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
		
		// init buttons, actionlisteners, and input panel
		addButtons();
		welcome();
		
		// add panels to the window
		frame.add(buttonPanel, "West");
		frame.add(new JPanel(), "Center");
		frame.add(inputPanel, "East");
		pack();
	}
	
	public void addButtons()
	{
		lookupButton = new JButton("Lookup");
		lookupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				lookupButton.setText("clicked");
			}
		});
		buttonPanel.add(lookupButton);
		
		employeeButton = new JButton("New Employee");
		employeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				employeeButton.setText("clicked");
			}
		});
		buttonPanel.add(employeeButton);
		
		customerButton = new JButton("New Customer");
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				customerButton.setText("clicked");
			}
		});
		buttonPanel.add(customerButton);
	}
	
	public void welcome()
	{
		JLabel labelTop = new JLabel();
		labelTop.setText("Welcome to the FedUps Employee shipping application.");
		inputPanel.add(labelTop);
		
		JLabel label2 = new JLabel();
		label2.setText("Choose an option on the left to begin.");
		inputPanel.add(label2);
	}
	
	public void pack()
	{
		frame.pack();
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
