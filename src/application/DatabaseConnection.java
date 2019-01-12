package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection
{

	private String jdbcUrl = "jdbc:mysql://localhost/karol_bd2?user=admin&password=admin";
	private Connection connection;
	
	public DatabaseConnection()
	{
		
	}
	
	public boolean connect()
	{
		try 
		{
			 connection = DriverManager.getConnection(jdbcUrl);
			 return true;
		}
		catch (SQLException e)
		{
			System.out.println(e.toString() +"\n error durign connection!!!!");
			return false;
		}
	}
	
	public boolean disconnect()
	{
		try
		{
			connection.close();
			return true;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<String[]> querryDatabase(String querryString, int columnNumber)
	{
		List<String[]> databaseResult = new ArrayList<>();
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(querryString);
			while(resultSet.next())
			{
				String[] row = new String[columnNumber];
				for(int i=0; i<columnNumber; i++)
				{
					row[i] = resultSet.getString(i+1);
				}
				databaseResult.add(row);
			}
			statement.close();
			resultSet.close();
			return databaseResult;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
