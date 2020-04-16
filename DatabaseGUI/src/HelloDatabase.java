
import java.util.ArrayList;
import java.util.List;

public class HelloDatabase {
    
    public static void main(String[] args)
    {
    	// create an object to abstract the database operations
    	Database db = new Database();
    	// create a List that represents rows, it contains an array containing every column
    	List<String[]> rows = new ArrayList<String[]>();
    	// example of a select statement using the database object
    	rows = db.querySelect("SELECT * FROM PERSON");
    	ConsoleHandler.printRows(rows);
    }
}
