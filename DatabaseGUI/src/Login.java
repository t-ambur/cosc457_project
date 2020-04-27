import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JDialog {
	
	private JFrame frame;
	private JPanel loginPanel;
	private JTextField userBox;
	private JTextField passBox;
	private JTextArea output;
	
	private Database db;
	private String user;
	private String pass;
	private String title;
	private int width;
	private int height;
	private JFactory factory;
	
	public Login(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		factory = new JFactory();
		db = null;
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
		
		loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
		
		factory.createLabel("Enter your login credentials for the database schema: project", loginPanel);
		userBox = factory.createTextField("Username", loginPanel);
		
		factory.createLabel("Password", loginPanel);
		passBox = new JPasswordField();
		loginPanel.add(passBox);
		
		output = new JTextArea();
		loginPanel.add(output);
		
		factory.createButton("Connect", loginPanel, new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				attemptConnection();
			}
		});
		
		// add panels to the window
		frame.add(loginPanel, "Center");
		frame.pack();
	}
	
	private void attemptConnection()
	{
		this.user = userBox.getText();
		this.pass = passBox.getText();
		this.db = new Database(user, pass);
		if (db.wasSuccessful())
		{
			output.setText("Login Successful, Starting employee system...");
			frame.setVisible(false);
			frame.dispose();
		}
		else
		{
			output.setText("Error connecting to database:\n");
			output.append(db.connectionCode());
		}
		frame.pack();
	}
	
	public Database getDB()
	{
		return this.db;
	}
	
	public String getUser()
	{
		return this.user;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}

}
