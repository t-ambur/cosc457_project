

import java.awt.BorderLayout;

// TODO
// MAKE NEW EMPLOYEES RETURN NUMBER
// FINISH PACKAGE

import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Display {
	// the window for the program
	private JFrame frame;
	// init variables for program window
	private String title;
	private int width, height;
	// Panels that make up the interior of the window
	private JPanel buttonPanel;
	private JPanel inputPanel;
	private JPanel outputPanel;
	// persistent outputArea at bottom of screen
	private JTextArea outputArea;
	// buttons that need to remain
	JButton newCustomer;
	// a factory class for abstracting away construction
	JFactory factory;
	// a class to handle processing to the database
	Process process;
	
	//protected static Dimension mapSize;
	
	public Display(String title, int width, int height, Database db){
		this.title = title;
		this.width = width;
		this.height = height;
		factory = new JFactory();
		process = new Process(db);
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
	
	private void addButtons()
	{
		factory.createButton("SQL_Lookup", buttonPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				lookup();
			}
		});
		factory.createButton("New Employee", buttonPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				newEmployee();
			}
		});
		factory.createButton("New Customer", buttonPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				newCustomer();
			}
		});
		factory.createButton("New Package", buttonPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				newPackage();
			}
		});
	}
	
	private void welcome()
	{
		factory.createLabel("Welcome to the FedUps Employee shipping application.", inputPanel);
		factory.createLabel("Choose an option on the left to begin.", inputPanel);
	}
	
	private void lookup()
	{
		clearInput();
		JTextArea area1 = factory.createArea("Enter your select query:", inputPanel);
		
		factory.createButton("Submit", inputPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				outputArea.setText("");
				String[] r = process.sqlLookup(area1.getText());
				for (int i = 0; i < r.length; i++)
					outputArea.append(r[i]);
				pack();
			}
		});
		pack();
	}
	
	private void clearInput()
	{
		inputPanel.removeAll();
		inputPanel.revalidate();
		inputPanel.repaint();
		outputArea.setText("");
	}
	
	private void newEmployee()
	{
		clearInput();
		factory.createLabel("Please enter the employee's details.", inputPanel);
		
		JTextField box1 = factory.createTextField("SSN", inputPanel);
		JTextField box2 = factory.createTextField("First Name", inputPanel);
		JTextField box3 = factory.createTextField("Last Name", inputPanel);
		JTextField box4 = factory.createTextField("Middle Initial", inputPanel);
		JTextField box5 = factory.createTextField("DOB: YYYY-MM-DD", inputPanel);
		JTextField box6 = factory.createTextField("Address", inputPanel);
		JTextField box7 = factory.createTextField("Phone Number", inputPanel);
		
		String[] sexString = {"Male", "Female"};
		JComboBox<String> sexList = factory.createDropdown("Sex", inputPanel, sexString);
		
		JTextField box10 = factory.createTextField("Salary", inputPanel);
		JTextField box11 = factory.createTextField("Manager ID", inputPanel);
		JTextField box12 = factory.createTextField("Department Number", inputPanel);
		JTextField box13 = factory.createTextField("Building Number", inputPanel);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				outputArea.setText("Processing... Please Wait");
				String[] input = new String[12];
				input[0] = box1.getText() + ","; //ssn
				input[1] = "'" + box2.getText() + "', "; //first name
				input[2] = "'" + box3.getText() + "', "; // last name
				input[3] = "'" + box4.getText() + "', "; // m init
				input[4] = "'" + box5.getText() + "', "; // DOB
				input[5] = "'" + box6.getText() + "', "; // address
				input[6] = "'" + box7.getText() + "', "; // phone number
				String sex;
				if (sexList.getSelectedIndex() == 0)
					sex = "M";
				else
					sex = "F";
				input[7] = "'" + sex + "'"; // sex
				input[8] = box10.getText() + ", "; // salary
				input[9] = box11.getText() + ", "; // manager id
				input[10] = box12.getText() + ", "; // department
				input[11] = box13.getText(); // building
				String response = process.processNewEmployee(input);
				outputArea.setText(response);
				pack();
			}
		});
		inputPanel.add(submit);
		pack();
	}
	
	private void newCustomer()
	{
		clearInput();
		factory.createLabel("Please enter the customer's details.", inputPanel);
		
		JTextField box1 = factory.createTextField("SSN", inputPanel);
		JTextField box2 = factory.createTextField("First Name", inputPanel);
		JTextField box3 = factory.createTextField("Last Name", inputPanel);
		JTextField box4 = factory.createTextField("Middle Initial", inputPanel);
		JTextField box5 = factory.createTextField("DOB: YYYY-MM-DD", inputPanel);
		JTextField box6 = factory.createTextField("Address", inputPanel);
		JTextField box7 = factory.createTextField("Phone Number", inputPanel);
		
		String[] sexString = {"Male", "Female"};
		JComboBox<String> sexList = factory.createDropdown("Sex", inputPanel, sexString);
		String[] clientString = {"Individual", "Business"};
		JComboBox<String> clientList = factory.createDropdown("Client Type", inputPanel, clientString);
		String[] loyalArray = {"0", "1", "2", "3"};
		JComboBox<String> loyalList = factory.createDropdown("Loyality Level", inputPanel, loyalArray);
		
		JTextField box12 = factory.createTextField("Email", inputPanel);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				outputArea.setText("Processing... Please Wait");
				String[] input = new String[11];
				input[0] = box1.getText(); //ssn
				input[1] = ",' " + box2.getText() + "', "; //first name
				input[2] = "'" + box3.getText() + "', "; // last name
				input[3] = "'" + box4.getText() + "', "; // m init
				input[4] = "'" + box5.getText() + "', "; // DOB
				input[5] = "'" + box6.getText() + "', "; // address
				input[6] = "'" + box7.getText() + "', "; // phone number
				
				String selected;
				if (sexList.getSelectedIndex() == 0)
					selected = "M";
				else
					selected = "F";
				input[7] = "'" + selected + "'"; // sex
				
				selected = Integer.toString(clientList.getSelectedIndex());
				input[8] = selected + ", "; // Client Type
				
				selected = loyalArray[loyalList.getSelectedIndex()];
				input[9] = selected + ", "; // Loyality Level
				
				
				input[10] = "'" + box12.getText() + "', "; // Email, then add SSN again in next method
				String response = process.processNewCustomer(input);
				outputArea.setText(response);
				pack();
			}
		});
		inputPanel.add(submit);
		pack();
	}
	
	private void newPackage()
	{
		clearInput();
		factory.createLabel("Please enter the package's information.",inputPanel);
		
		JTextField box1 = factory.createTextField("Package Type", inputPanel);
		JTextField box2 = factory.createTextField("Weight", inputPanel);
		JTextField box3 = factory.createTextField("Insured", inputPanel);
		JTextField box4 = factory.createTextField("Accessorial", inputPanel);
		JTextField box5 = factory.createTextField("Client Number", inputPanel);
		JTextField box6 = factory.createTextField("Store Number", inputPanel);
		JTextField box7 = factory.createTextField("Origin", inputPanel);
		JTextField box8 = factory.createTextField("Destination", inputPanel);
		JTextField box9 = factory.createTextField("Priority", inputPanel);
		JTextField box10 = factory.createTextField("Currency Type", inputPanel);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				outputArea.setText("Processing... Please Wait");
				String[] input = new String[10];
				input[0] = box1.getText() + ", "; //package type
				input[1] = box2.getText() + ", "; // weight
				input[2] = box3.getText() + ", "; // insured
				input[3] = "'" + box4.getText() + "', "; // access
				input[4] = box5.getText() + ", "; // client num
				input[5] = box6.getText() + ", "; // store num --------
				input[6] = "'" + box7.getText() + "', "; // origin
				input[7] = "'" + box8.getText() + "'"; // destination
				input[8] = box9.getText() + ", "; // priority
				input[9] = box10.getText(); // currency type
				String response = process.processPackage(input);
				outputArea.setText(response);
				pack();
			}
		});
		inputPanel.add(submit);
		pack();
	}
	
	public void pack()
	{
		frame.pack();
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
