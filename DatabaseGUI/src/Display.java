

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
	
	private void addButtons()
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
				newEmployee();
			}
		});
		buttonPanel.add(employeeButton);
		
		customerButton = new JButton("New Customer");
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				newCustomer();
			}
		});
		buttonPanel.add(customerButton);
	}
	
	private void welcome()
	{
		JLabel labelTop = new JLabel();
		labelTop.setText("Welcome to the FedUps Employee shipping application.");
		inputPanel.add(labelTop);
		
		JLabel label2 = new JLabel();
		label2.setText("Choose an option on the left to begin.");
		inputPanel.add(label2);
	}
	
	private void lookup()
	{
		clearInput();
		JLabel labelTop = new JLabel();
		labelTop.setText("Enter your select query:");
		inputPanel.add(labelTop);
		
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
		JLabel title = new JLabel("Please enter the employee's details.");
		inputPanel.add(title);
		// ssn first_name last_name m_init DOB address phone sex
		// ssn employee_id salary manager_id department_num building_num
		JLabel l1 = new JLabel("SSN");
		JTextField box1 = new JTextField();
		inputPanel.add(l1);
		inputPanel.add(box1);
		
		JLabel l2 = new JLabel("First Name");
		JTextField box2 = new JTextField();
		inputPanel.add(l2);
		inputPanel.add(box2);
		
		JLabel l3 = new JLabel("Last Name");
		JTextField box3 = new JTextField();
		inputPanel.add(l3);
		inputPanel.add(box3);
		
		JLabel l4 = new JLabel("Middle Initial");
		JTextField box4 = new JTextField();
		inputPanel.add(l4);
		inputPanel.add(box4);
		
		JLabel l5 = new JLabel("DOB: YYYY-MM-DD");
		JTextField box5 = new JTextField();
		inputPanel.add(l5);
		inputPanel.add(box5);
		
		JLabel l6 = new JLabel("Address");
		JTextField box6 = new JTextField();
		inputPanel.add(l6);
		inputPanel.add(box6);
		
		JLabel l7 = new JLabel("Phone Number");
		JTextField box7 = new JTextField();
		inputPanel.add(l7);
		inputPanel.add(box7);
		
		JLabel l8 = new JLabel("Sex");
		JTextField box8 = new JTextField();
		inputPanel.add(l8);
		inputPanel.add(box8);
		
		JLabel l10 = new JLabel("Salary");
		JTextField box10 = new JTextField();
		inputPanel.add(l10);
		inputPanel.add(box10);
		
		JLabel l11 = new JLabel("manager ID");
		JTextField box11 = new JTextField();
		inputPanel.add(l11);
		inputPanel.add(box11);
		
		JLabel l12 = new JLabel("Department Number");
		JTextField box12 = new JTextField();
		inputPanel.add(l12);
		inputPanel.add(box12);
		
		JLabel l13 = new JLabel("Building Number");
		JTextField box13 = new JTextField();
		inputPanel.add(l13);
		inputPanel.add(box13);
		
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
				input[7] = "'" + box8.getText() + "'"; // sex
				input[8] = box10.getText() + ", "; // salary
				input[9] = box11.getText() + ", "; // manager id
				input[10] = box12.getText() + ", "; // department
				input[11] = box13.getText(); // building
				String response = processNewEmployee(input);
				outputArea.setText(response);
				pack();
			}
		});
		inputPanel.add(submit);
		pack();
	}
	
	private String processNewEmployee(String[] input)
	{
		// example of an insert statement
    	//String insertValues = "987654321, 'Created', 'Java', 'W', '2020-4-16', '987 West St', '4334330001', 'M'";
    	//db.queryInsert(Constants.getInsertStatement(Constants.PERSON_COLUMNS, insertValues));
		// ssn first_name last_name m_init DOB address phone sex
		// ssn employee_id salary manager_id department_num building_num
		String insertPerson = "";
		String insertEmployee = "";
		String status = "";
		String sql_resp;
		
		for (int i = 0; i <= 7; i++)
		{
			insertPerson += input[i];
		}
		String toInsert = Constants.getInsertStatement(Constants.PERSON_COLUMNS, insertPerson);
		sql_resp = db.queryInsert(toInsert);

		if (sql_resp.equals("true"))
		{
			status += "Person created.\n"; 
		}
		else
		{
			status += sql_resp + "\n";
		}
		
		insertEmployee += input[0];
		for (int i = 8; i < input.length; i++)
		{
			insertEmployee += input[i];
		}
		sql_resp = db.queryInsert(Constants.getInsertStatement(Constants.EMPLOYEE_COLUMNS, insertEmployee));
		
		if (sql_resp.equals("true"))
		{
			status += " -- Employee created.\n"; 
		}
		else
		{
			status += " Employee creation error: " + sql_resp + "\n";
		}
		
		return status;
	}
	
	private void newCustomer()
	{
		clearInput();
		// ssn first_name last_name m_init DOB address phone sex
		// account_num client_type loyality_level email ssn
		JLabel title = new JLabel("Please enter the customer's details.");
		inputPanel.add(title);
		
		JLabel l1 = new JLabel("SSN");
		JTextField box1 = new JTextField();
		inputPanel.add(l1);
		inputPanel.add(box1);
		
		JLabel l2 = new JLabel("First Name");
		JTextField box2 = new JTextField();
		inputPanel.add(l2);
		inputPanel.add(box2);
		
		JLabel l3 = new JLabel("Last Name");
		JTextField box3 = new JTextField();
		inputPanel.add(l3);
		inputPanel.add(box3);
		
		JLabel l4 = new JLabel("Middle Initial");
		JTextField box4 = new JTextField();
		inputPanel.add(l4);
		inputPanel.add(box4);
		
		JLabel l5 = new JLabel("DOB: YYYY-MM-DD");
		JTextField box5 = new JTextField();
		inputPanel.add(l5);
		inputPanel.add(box5);
		
		JLabel l6 = new JLabel("Address");
		JTextField box6 = new JTextField();
		inputPanel.add(l6);
		inputPanel.add(box6);
		
		JLabel l7 = new JLabel("Phone Number");
		JTextField box7 = new JTextField();
		inputPanel.add(l7);
		inputPanel.add(box7);
		
		JLabel l8 = new JLabel("Sex");
		JTextField box8 = new JTextField();
		inputPanel.add(l8);
		inputPanel.add(box8);
		
		JLabel l10 = new JLabel("Client Type");
		JTextField box10 = new JTextField();
		inputPanel.add(l10);
		inputPanel.add(box10);
		
		JLabel l11 = new JLabel("Loyality Level");
		JTextField box11 = new JTextField();
		inputPanel.add(l11);
		inputPanel.add(box11);
		
		JLabel l12 = new JLabel("Email");
		JTextField box12 = new JTextField();
		inputPanel.add(l12);
		inputPanel.add(box12);
		
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
				input[7] = "'" + box8.getText() + "'"; // sex
				input[8] = box10.getText() + ", "; // Client Type
				input[9] = box11.getText() + ", "; // Loyality Level
				input[10] = "'" + box12.getText() + "', "; // Email, then add SSN again in next method
				String response = processNewCustomer(input);
				outputArea.setText(response);
				pack();
			}
		});
		inputPanel.add(submit);
		pack();
	}
	
	private String processNewCustomer(String[] input)
	{
		String insertPerson = "";
		String insertCustomer = "";
		String status = "";
		String sql_resp;
		
		for (int i = 0; i <= 7; i++)
		{
			insertPerson += input[i];
		}
		String toInsert = Constants.getInsertStatement(Constants.PERSON_COLUMNS, insertPerson);
		System.out.println(toInsert);
		sql_resp = db.queryInsert(toInsert);

		if (sql_resp.equals("true"))
		{
			status += "Person created.\n"; 
		}
		else
		{
			status += sql_resp + "\n";
		}
		
		for (int i = 8; i < input.length; i++)
		{
			insertCustomer += input[i];
		}
		insertCustomer += input[0];
		toInsert = Constants.getInsertStatement(Constants.CUSTOMER_COLUMNS, insertCustomer);
		System.out.println(toInsert);
		sql_resp = db.queryInsert(toInsert);
		
		if (sql_resp.equals("true"))
		{
			status += " -- Employee created.\n"; 
		}
		else
		{
			status += " Employee creation error: " + sql_resp + "\n";
		}
		
		return status;
	}
	
	public void pack()
	{
		frame.pack();
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
