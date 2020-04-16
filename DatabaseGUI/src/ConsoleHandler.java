
//@author Trevor Amburgey
// class full of static methods for console interaction

import java.util.List;

public class ConsoleHandler {
	
	public static void printRows(List<String[]> rows)
	{
		for (int i = 0; i < rows.size(); i++)
		{
			System.out.print(i + ": ");
			String[] row = rows.get(i);
			for (int j = 0; j < row.length; j++)
				System.out.print(row[j] + " | ");
			System.out.println();
		}
	}
}
