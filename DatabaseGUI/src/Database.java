
//@author Trevor Amburgey
// Java object that represents a database
// Requires the JConnector tool and MySQL server locally running


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
	// basic database objects
	private Connection con;
	private Statement stmt;
	private ResultSet result;
	private ResultSetMetaData meta;
	// secure database object
	private PreparedStatement ps;
	
	// class variables
	private String databaseURL;
	
	// class constants
	public static String LOCAL_URL = "jdbc:mysql://localhost:3306/project?";
	public static String DEFAULT_USER = "root";
	public static String DEFAULT_PASS = "admin";
	
	// constructors
	public Database()
	{
		init(LOCAL_URL, DEFAULT_USER, DEFAULT_PASS);
	}
	
	public Database(String user, String pass)
	{
		init(LOCAL_URL, user, pass);
	}
	
	public Database(String url, String user, String pass)
	{
		init(url, user, pass);
	}
	
	// generic intializer for all constructors
	private void init(String url, String user, String pass)
	{
		this.databaseURL = url;
		// try to load the MySQL driver: MySQL Connector/J
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("Driver for mysql connection not found, error code: " + e);
		}
		// try to connect to the database, make sure MySQL server is running!
		
		try
		{
			con = DriverManager.getConnection(databaseURL, user, pass);
			stmt = con.createStatement();
			System.out.println("Successfully connected to database: " + url);
		}
		catch (SQLException e)
		{
			System.err.println("Unable to connect to the database\nError Code: " + e);
		}
	}
	
	public List<String[]> querySelect(String query)
	{
		List<String[]> rows = new ArrayList<String[]>();
		try
		{
			result = stmt.executeQuery(query);
			while (result.next())
			{
				meta = result.getMetaData();
				String[] columns = new String[meta.getColumnCount()];
				for (int i = 1; i < columns.length+1; i++)
					columns[i-1] = result.getString(i);
				rows.add(columns);
			}
		}
		catch (SQLException e)
		{
			System.err.println("SQL error, code: " + e);
			rows.clear();
			String[] error = new String[1];
			error[0] = "SQL error, code: " + e;
			rows.add(error);
		}
		return rows;
	}
	
	public String queryInsert(String query)
	{
		String success = "false";
		try
		{
			stmt.execute(query);
			success = "true";
		}
		catch (SQLException e)
		{
			System.err.println("SQL error, code: " + e);
			success = "Error Inserting, code: ";
			success += e.toString();
		}
		return success;
	}
	
	public String getURL()
	{
		return this.databaseURL;
	}
}
