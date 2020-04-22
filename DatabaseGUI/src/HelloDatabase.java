//@author Trevor Amburgey
// main method for app

public class HelloDatabase {
    
    public static void main(String[] args)
    {
    	//TODO create login screen
    	// currently connects to localhost:3306 looking for database called project
    	String user = "root";
    	String pass = "admin";
    	
    	// create an object to abstract the database operations
    	Database db = new Database(user, pass);
    	
    	// Create the GUI and pass it the database object
    	Display display = new Display("FedUps Employee System", 700, 700, db);
    }
}
