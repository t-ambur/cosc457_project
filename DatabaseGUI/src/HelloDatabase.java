
//@author Trevor Amburgey
// main method for app

public class HelloDatabase {
    
    public static void main(String[] args)
    {
    	// create an object to abstract the database operations
    	Database db = new Database();
    	
    	// Create the GUI and pass it the database object
    	Display display = new Display("FedUps", 700, 700, db);
    }
}
