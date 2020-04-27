
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
	
	public static String[] getRowStrings(List<String[]> rows)
	{
		if (rows == null || rows.size() == 0)
			return new String[] {""};
		String[] array = new String[rows.size()];
		for (int i = 0; i < rows.size(); i++)
		{
			array[i] = getString(rows.get(i));
		}
		return array;
	}
	
	private static String getString(String[] array)
	{
		String temp = "";
		for (int i = 0; i < array.length; i++)
		{
			temp += array[i] + " | ";
		}
		temp += "\n";
		return temp;
	}
}
