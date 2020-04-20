

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Display {
	
	// An object that represents the database
	Database db;
	// the window for the program
	private JFrame frame;
	// init variables for program
	private String title;
	private int width, height;
	// Panels that make up the interior of the window
	private JPanel buttonPanel;
	private JPanel inputPanel;
	private JPanel outputPanel;
	// buttons that go in the button panel
	private JButton lookupButton;
	private JButton employeeButton;
	private JButton customerButton;
	// fields that go in the input panel
	private JLabel labelTop;
	private JLabel label2;
	private JTextArea outputArea;
	
	//protected static Dimension mapSize;
	
	public Display(String title, int width, int height, Database d){
		this.title = title;
		this.width = width;
		this.height = height;
		this.db = d;
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
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// panel on the bottom to see output
		outputPanel = new JPanel();
		outputPanel.setLayout(new FlowLayout());
		outputArea = new JTextArea();
		outputPanel.add(outputArea);
		
		// init buttons, actionlisteners, and input panel
		addButtons();
		welcome();
		
		// add panels to the window
		frame.add(buttonPanel, "West");
		frame.add(inputPanel, "Center");
		frame.add(outputPanel, "South");
		pack();
	}
	
	public void addButtons()
	{
		lookupButton = new JButton("Lookup");
		lookupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				lookup();
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
		labelTop = new JLabel();
		labelTop.setText("Welcome to the FedUps Employee shipping application.");
		inputPanel.add(labelTop);
		
		label2 = new JLabel();
		label2.setText("Choose an option on the left to begin.");
		inputPanel.add(label2);
	}
	
	public void lookup()
	{
		labelTop.setText("Enter your select query:");
		label2.setText("");
		
		JTextArea area1 = new JTextArea();
		inputPanel.add(area1);
		
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				outputArea.setText("");
				String input = area1.getText();
				List<String[]> rows = new ArrayList<String[]>();
				rows = db.querySelect(input);
				String[] r = ConsoleHandler.getRowStrings(rows);
				for (int i = 0; i < r.length; i++)
					outputArea.append(r[i]);
				pack();
			}
		});
		inputPanel.add(submit);
	}
	
	public void pack()
	{
		frame.pack();
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
