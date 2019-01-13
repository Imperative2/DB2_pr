package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection
{

	private String jdbcUrl = "jdbc:mysql://localhost:3306/edukacja_cl?user=admin&password=admin";
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
			e.printStackTrace();
			return false;
		}
	}

	public boolean disconnect()
	{
		if(connection != null)
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
		return true;
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
