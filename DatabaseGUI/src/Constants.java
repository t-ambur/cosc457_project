
public class Constants {

	public static final String PERSON_COLUMNS = "insert into person(ssn, first_name, last_name, m_init,"
			+ "DOB, address, phone, sex) values(";
	
	public static final String EMPLOYEE_COLUMNS = "insert into employee(ssn, salary, manager_id, department_num,"
			+ "building_num) values(";
	
	public static final String CUSTOMER_COLUMNS = "insert into customer(client_type, loyality_level, email,"
			+ "ssn) values(";
	
	public static final String[] TABLES = {"accessorial", "Building", "Country", "Currency", "Customer", "Department", "Employee",
			"Insurance", "Package", "Person", "Rate", "Route", "Shipment", "State", "Store", "Tax_Custom",
			"Vehicle", "Warehouse", "Zone"};
	
	public static final int MAX_COLUMNS = 30;
	
	public static String getInsertStatement(String table, String values)
	{
		return table + values + ");";
	}
}
