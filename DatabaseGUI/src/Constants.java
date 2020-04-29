
public class Constants {

	public static final String PERSON_COLUMNS = "insert into person(ssn, first_name, last_name, m_init,"
			+ "DOB, address, phone, sex) values(";
	
	public static final String EMPLOYEE_COLUMNS = "insert into employee(ssn, salary, manager_id, department_num,"
			+ "building_num) values(";
	
	public static final String CUSTOMER_COLUMNS = "insert into customer(client_type, loyality_level, email,"
			+ "ssn) values(";
	
	public static final String RATE_COLUMNS = "insert into rate(origin, destination, priority, percentage, tax_code,"
			+ "weight, rate, currency_type) values(";
	
	public static final String PACKAGE_COLUMNS = "insert into package(package_type, weight, insured, accessorial, shipment_id, is_shipped,"
			+ "is_complete, client_number, price_code, store_processed) values(";
	
	public static final String SHIPMENT_COLUMNS = "insert into shipment(shipment_id, priority, is_complete,"
			+ "completion_time,  last_warehouse, last_route, is_in_transit, requester) values(";
	
	/////////////
	
	public static final String[] TABLES = {"accessorial", "Building", "Country", "Currency", "Customer", "Department", "Employee",
			"Insurance", "Package", "Person", "Rate", "Route", "Shipment", "State", "Store", "Tax_Custom",
			"Vehicle", "Warehouse", "Zone"};
	
	public static final String[] CURRENCY_ABBR = {"AED", "AFA", "USD", "AUD", "BDT", "EUR", "BOB", "BRL", "CAD", "CHF", "RMD",
			"INR", "JPY", "MXN", "NPR", "PHP", "GBP", "RUB", "CLP"}; // id is index+1
	
	
	//////////////
	
	public static final int MAX_COLUMNS = 30;
	public static final int LIMIT_ALL = 35;
	
	////////////
	
	
	public static String getInsertStatement(String table, String values)
	{
		return table + values + ");";
	}
}
