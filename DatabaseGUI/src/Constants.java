
public class Constants {

	public static final String PERSON_COLUMNS = "insert into person(ssn, first_name, last_name, m_init,"
			+ "DOB, address, phone, sex) values(";
	
	public static String getInsertStatement(String table, String values)
	{
		return table + values + ");";
	}
}
