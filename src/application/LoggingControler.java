package application;

import java.util.List;

public class LoggingControler
{
	
	public static void main(String[] args)
	{
		DatabaseConnection dbConn = new DatabaseConnection();
		dbConn.connect();
		List<String[]> result = dbConn.querryDatabase("Select * From Student", 10);
		for(String[] row : result)
		{
			for(String field : row)
			{
				System.out.print(field+" ");
			}
			System.out.println();
		}
		dbConn.disconnect();
		
		
	}

}
