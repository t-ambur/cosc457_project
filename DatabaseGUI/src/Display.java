

import java.awt.BorderLayout;

// TODO
// EMPLOYEE LOOKUP
// FINISH PACKAGE

import java.awt.FlowLayout;
import java.awt.event.*;

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
	// a factory class for abstracting away construction of UI components
	JFactory factory;
	// a class to handle processing to the database
	Process process;
	
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
		factory.createButton("Lookup", buttonPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				lookup();
			}
		});
		factory.createButton("SQL_Lookup", buttonPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				sqlLookup();
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
		factory.createButton("New Shipment", buttonPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				newShipment();
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
		clear();
		
		String[] options = Constants.TABLES;
		JComboBox<String> table = factory.createDropdown("Search Location:", inputPanel, options);
		
		String[] attributes = process.getColumns(0);
		JComboBox<String> attributesList = factory.createDropdown("Available Attributes", inputPanel, attributes);
		
		JTextField searchCriteria = factory.createTextField("What are you searching for?", inputPanel);
		
		factory.createLabel("You can select a blank value for attribute to search all (limit " +
				Constants.LIMIT_ALL + ")", inputPanel);
		
		table.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearOutput();
				outputArea.setText("Finding search fields...");
				String[] attributes = process.getColumns(table.getSelectedIndex());
				if (attributes != null)
				{
					clearOutput();
					DefaultComboBoxModel<String> available = new DefaultComboBoxModel<String>(attributes);
					attributesList.setModel(available);
					pack();
				}
				else
				{
					clearOutput();
					outputArea.setText("An error occured while retrieving attribute names.");
					pack();
				}
			}
		});
		
		factory.createButton("Submit", inputPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearOutput();
				Object selectedAttribute = attributesList.getSelectedItem();
				String selectedString = "";
				if (selectedAttribute != null)
					selectedString = selectedAttribute.toString();
				String[] r = process.sqlLookup(table.getSelectedItem().toString(),
						selectedString, searchCriteria.getText());
				ComboBoxModel<String> c = attributesList.getModel();
				for (int i = 0; i < c.getSize(); i++)
				{
					String temp = c.getElementAt(i);
					if (temp == null)
						break;
					temp = temp.replaceAll("\\n|\\r", "");
					outputArea.append(temp + " | ");
				}
				outputArea.append("\n");
				for (int i = 0; i < r.length; i++)
					outputArea.append(r[i]);
				pack();
			}
		});
		
		pack();
	}
	
	private void sqlLookup()
	{
		clear();
		JTextArea area1 = factory.createArea("Enter your select query:", inputPanel);
		
		factory.createButton("Submit", inputPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearOutput();
				String[] r = process.sqlLookup(area1.getText());
				for (int i = 0; i < r.length; i++)
					outputArea.append(r[i]);
				pack();
			}
		});
		pack();
	}
	
	private void newEmployee()
	{
		clear();
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
		
		String[] depString = {"1", "2", "3", "4", "5"};
		JComboBox<String> departmentList = factory.createDropdown("Department Number", inputPanel, depString);
		
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
				String dep = depString[departmentList.getSelectedIndex()];
				input[10] = dep + ", "; // department
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
		clear();
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
				
				if (clientList.getSelectedIndex() == 0)
					selected = "I";
				else
					selected = "B";
				input[8] = "'" + selected + "', "; // Client Type
				
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
		clear();
		factory.createLabel("Please enter the package's information.",inputPanel);
		
		String[] packageString = {"Cust", "UBox", "Letr", "10kg", "5kg", "Tube", "Pack"};
		JComboBox<String> packageList = factory.createDropdown("Package Type", inputPanel, packageString);
		
		// JTextField box2 = factory.createTextField("Weight", inputPanel);
		String[] weightString = {"0.5", "1.0", "2.0", "3.0", "4.0", "5.0", "10.0"};
		JComboBox<String> weightList = factory.createDropdown("Weight (Approx)", inputPanel, weightString);
		
		String[] insString = {"True", "False"};
		JComboBox<String> insList = factory.createDropdown("Insured", inputPanel, insString);
		
		
		// JTextField box4 = factory.createTextField("Accessorial", inputPanel);
		String[] accString = {"DDO", "RPS", "RES", "SLP", "FSC"};
		JComboBox<String> accList = factory.createDropdown("Accessorial", inputPanel, accString);
		
		JTextField box5 = factory.createTextField("Client Number", inputPanel);
		JTextField box6 = factory.createTextField("Store Number", inputPanel);
		
		//JTextField box7 = factory.createTextField("Origin", inputPanel);
		String[] originString = {"AE", "AR", "AU", "BE", "BR", "CN", "CA", "GE", "JP", "NP", "MX", "PH", "GB", "US"};
		JComboBox<String> originList = factory.createDropdown("Origin", inputPanel, originString);
		
		JTextField box8 = factory.createTextField("Destination", inputPanel);
		
		
		String[] prString = {"1DA", "2DA", "3DA", "4DA", "5DA"};
		JComboBox<String> prList = factory.createDropdown("Priority", inputPanel, prString);
		
		String[] curString = Constants.CURRENCY_ABBR;
		JComboBox<String> curList = factory.createDropdown("Currency Type", inputPanel, curString);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				outputArea.setText("Processing... Please Wait");
				String[] input = new String[10];
				input[0] = "'" + packageList.getSelectedItem().toString() + "', "; //package type
				double w = Double.parseDouble(weightList.getSelectedItem().toString());
				input[1] = w + ", "; // weight
				input[2] = insList.getSelectedItem().toString() + ", "; // insured
				input[3] = "'" + accList.getSelectedItem().toString() + "', "; // access
				input[4] = box5.getText() + ", "; // client num
				input[5] = box6.getText(); // store num --------
				input[6] = "'" + originList.getSelectedItem().toString() + "', "; // origin
				input[7] = "'" + box8.getText() + "'"; // destination
				input[8] = prList.getSelectedItem().toString() + ", "; // priority
				input[9] = curList.getSelectedItem().toString(); // currency type
				String response = process.processPackage(input);
				outputArea.setText(response);
				pack();
			}
		});
		inputPanel.add(submit);
		pack();
	}
	
	private void newShipment() {
		clear();
		factory.createLabel("Please enter the shipment information:",inputPanel);
		
		JTextField box1 = factory.createTextField("Shipment ID number", inputPanel);
		
		String[] prString = {"1", "2", "3"};
		JComboBox<String> prList = factory.createDropdown("Priority", inputPanel, prString);
		
		JTextField box2 = factory.createTextField("Shipment requestor", inputPanel);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String[] input = new String[8];
				outputArea.setText("Processing... Please Wait");
				input[0] = box1.getText() + ", ";
				input[1] = prList.getSelectedItem().toString() + ", ";
				input[2] = "false, ";  // is_complete
				input[3] = "NULL, "; //completion_time: determined later
				input[4] = "1, "; //TODO: get current warehouse
				input[5] = "4, "; //TODO: get route
				input[6] = "false, "; //is_in_transit
				input[7] = "'" + box2.getText() + "'";//requester
				String response = process.processShipment(input);
				outputArea.setText(response);
				pack();
			}
		});
		inputPanel.add(submit);
		
		pack();
	}
	
	private void clear()
	{
		inputPanel.removeAll();
		inputPanel.revalidate();
		inputPanel.repaint();
	}
	
	private void clearOutput()
	{
		outputArea.setText("");
	}
	
	public void pack()
	{
		frame.pack();
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
