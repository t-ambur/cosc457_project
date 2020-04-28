import java.util.ArrayList;
import java.util.List;

// TODO
// UPDATE PRICE CODE IN processPackage

public class Process {
	
	Database db;
	List<String[]> rows;
	
	public Process(Database d)
	{
		this.db = d;
		rows = new ArrayList<String[]>(); 
	}
	
	public String processNewEmployee(String[] input)
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
			status += " Employee created.\n"; 
			rows = new ArrayList<String[]>();
			rows = db.querySelect("select employee_id from employee where ssn=" + input[0]);
			String[] r = ConsoleHandler.getRowStrings(rows);
			status += "Employee Number: " + r[0];
		}
		else
		{
			status += " Employee creation error: " + sql_resp + "\n";
		}
		
		return status;
	}
	
	public String processNewCustomer(String[] input)
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
		sql_resp = db.queryInsert(toInsert);
		
		if (sql_resp.equals("true"))
		{
			status += " Customer created.\n";
			rows = new ArrayList<String[]>();
			rows = db.querySelect("select account_num from customer where ssn=" + input[0]);
			String[] r = ConsoleHandler.getRowStrings(rows);
			status += "Account Number: " + r[0];
		}
		else
		{
			status += " Customer creation error: " + sql_resp + "\n";
		}
		
		return status;
	}
	
	public String processPackage(String[] input)
	{
		String insertPackage = "";
		String status = "";
		String sql_resp;
		
		// insert into package
		for (int i = 0; i < 3; i++)
			insertPackage += input[i]; // package_type, weight, insured
		if (input[3].equals("'', "))//access
			insertPackage += " null, ";
		insertPackage += " 1, "; // shipment id
		insertPackage += "False, False, "; // is shipped, is complete
		insertPackage += input[4]; // client number
		// TODO
		// update price code, reflect price code in output to user
		insertPackage += " 1, "; /////////////////////////////////// UPDATE PRICE CODE
		insertPackage += input[5]; // store num
		
		String toInsert = Constants.getInsertStatement(Constants.PACKAGE_COLUMNS, insertPackage);
		System.out.println(toInsert);
		sql_resp = db.queryInsert(toInsert);
		if (sql_resp.equals("true"))
		{
			status += "Success. Package created.\n";
			status += "Total Cost: 0";
		}
		else
		{
			status += "Package creation error: " + sql_resp + "\n";
		}
		
		return status;
	}
	public String processShipment(String[] input) {
		String insertShipment = "";
		String status = "";
		String sql_resp;
		
		for(int i = 0; i < 8; i++) {
			insertShipment += input[i]; //insert 
		}
		String toInsert = Constants.getInsertStatement(Constants.SHIPMENT_COLUMNS, insertShipment);
		System.out.println(toInsert);
		sql_resp = db.queryInsert(toInsert);
		if (sql_resp.equals("true"))
		{
			status += "Success. Shipment created.\n";
			status += "Total Cost: 0";
		}
		else
		{
			status += "Shipment creation error: " + sql_resp + "\n";
		}
		return status;
		
	}
	
	public String[] sqlLookup(String text)
	{
		rows = db.querySelect(text);
		String[] r = ConsoleHandler.getRowStrings(rows);
		return r;
	}
	
	public String[] sqlLookup(String table, String attribute, String search)
	{
		if (attribute.equals("") && search.equals(""))
		{
			rows = db.queryLimit(table);
		}
		else if (attribute.equals("") || search.equals(""))
			return new String[]{"Invalid Search"};
		else
			rows = db.querySelect(table, attribute, search);
		String[] r = ConsoleHandler.getRowStrings(rows);
		return r;
	}
	
	public String[] getColumns(int index)
	{
		if (index > -1 && index < Constants.TABLES.length)
		{
			String table = Constants.TABLES[index];
			return db.tableDescribe(table);
		}
		return null;
	}
}
