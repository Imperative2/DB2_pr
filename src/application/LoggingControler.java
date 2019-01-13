package application;

import java.util.List;

public class LoggingControler
{
	
	private User loggedUser = null;
	private DatabaseConnection dbConn;
	
	public LoggingControler(DatabaseConnection dbConn)
	{
		this.dbConn = dbConn;
	}
	
	public int login(String login, String password)
	{
		if(login == null || login.length() <=0)
		{
			return -1;
		}
		if(password == null || password.length() <= 0 )
		{
			return -1;
		}
		
		if(login.matches("pwr\\d{6}")) 
		{
			if(dbConn.connect() == true)
			{
				List<String[]> result;
				String sqlQuerry = "SELECT * FROM STUDENT WHERE LOGIN='"+login+"' AND haslo='"+password+"'";
				result = dbConn.querryDatabase(sqlQuerry, 10);
				dbConn.disconnect();
				if(result.size()>3 || result.size() < 1)
				{
					return -1;
				}
				else
				{
					String userId = result.get(0)[0];
					String name = result.get(0)[1];
					String surname = result.get(0)[2];
//					String fieldOfStudy = result.get(0)[8];
//					String semester = result.get(0)[9];

					Student student = new Student();
					student.setName(name);
					student.setSurname(surname);
					student.setUserId(userId);
//					student.setFieldOfStudy(fieldOfStudy);
//					student.setSemester(semester);
					loggedUser = student;
					return 1;
				}
			}
			return -1;
		}
		else
		{
			if(dbConn.connect() == true)
			{
				List<String[]> result;
				String sqlQuerry = "SELECT * FROM pracownik WHERE LOGIN='"+login+"' AND haslo='"+password+"'";
				result = dbConn.querryDatabase(sqlQuerry, 4);
				dbConn.disconnect();
				if(result.size()>3 || result.size() < 1)
				{
					return -1;
				}
				else
				{
					String name = result.get(0)[1];
					String surname = result.get(0)[2];
					String userId = result.get(0)[0];
					Employee employee = new Employee();
					employee.setName(name);
					employee.setSurname(surname);
					employee.setUserId(userId);
					loggedUser = employee;
					return 2;
				}
			}
			
			return -1;
		}
		
	}

	public User getLoggedUser()
	{
		return loggedUser;
	}
	public static void main(String[] args)
	{
		DatabaseConnection dbConn = new DatabaseConnection();
		LoggingControler lCon = new LoggingControler(dbConn);
		System.out.println(lCon.login("karol", "admin"));

		
		
	}

}
