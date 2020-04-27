//@author Trevor Amburgey
// main method for app

public class HelloDatabase {
    
    public static void main(String[] args)
    {
    	// currently connects to localhost:3306 looking for database called project
    	Login login = new Login("FedUps Employee Login", 400,400);
    	
    	// not the best solution, but it works
    	while (login.getFrame().isActive())
    	{
    		
    	}
    	
    	Database db = null;
    	
    	// create an object to abstract the database operations
    	if (login.getDB() == null || !login.getDB().wasSuccessful())
    	{
    		System.exit(1);
    	}
    	else
    	{
    		db = login.getDB();
    	}
    	
    	
    	// Create the GUI and pass it the database object
    	Display display = new Display("FedUps Employee System", 700, 700, db);
    }
}
