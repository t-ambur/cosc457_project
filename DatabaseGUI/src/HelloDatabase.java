
//@author Trevor Amburgey
// main method for app

public class HelloDatabase {
    
    public static void main(String[] args)
    {
    	// create an object to abstract the database operations
    	Database db = new Database();
    	
    	// example of an insert statement
    	//String insertValues = "987654321, 'Created', 'Java', 'W', '2020-4-16', '987 West St', '4334330001', 'M'";
    	//db.queryInsert(Constants.getInsertStatement(Constants.PERSON_COLUMNS, insertValues));
    	
    	// Create the GUI and pass it the database object
    	Display display = new Display("FedUps", 700, 700, db);
    }
}
