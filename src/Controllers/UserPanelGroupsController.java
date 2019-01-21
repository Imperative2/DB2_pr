package Controllers;

import application.DatabaseConnection;
import application.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class UserPanelGroupsController
{
	private Student mainUser;
	private DatabaseConnection dbConn;
	
	private Map<String,Integer> groupsComponents;
	
	public UserPanelGroupsController(DatabaseConnection dbConnection,Map<String,Integer> groupsComponents)
	{
		this.dbConn = dbConnection;
		this.groupsComponents = groupsComponents;
	}
	
	public void setMainUser(Student mainUser)
	{
		this.mainUser = mainUser;
	}
	
	public application.User getMainUser()
	{
		return mainUser;
	}
	
	
	public void updateSelectedGroup(JPanel userPanelGropus)
	{
		
	}
	
	public void updateUserECTS(JPanel userPanelGroups)
	{
		int ectsPoints = groupsComponents.get("labelUserECTS");
		JLabel labelName = (JLabel)userPanelGroups.getComponent(ectsPoints);
		labelName.setText(calculateECTS());
	}
	
	
	private String calculateECTS()
	{
		
		List<String[]> querryResult = new ArrayList<>();
		String querryString = "SELECT student.id_indeks,  SUM(kurs.ects)\r\n" + 
				"FROM student INNER JOIN zapis\r\n" + 
				"ON student.id_indeks = zapis.id_indeksu\r\n" + 
				"INNER JOIN grupa_zajeciowa\r\n" + 
				"ON zapis.id_grupy = grupa_zajeciowa.id_grupy\r\n" + 
				"INNER JOIN kurs\r\n" + 
				"ON grupa_zajeciowa.id_kursu = kurs.id_kursu\r\n" + 
				"GROUP BY student.id_indeks";
		
		dbConn.connect();
		querryResult = dbConn.querryDatabase(querryString, 2);
		String ects = "0";
		for(String[] row: querryResult)
		{
			if(row[0].equals(mainUser.getUserId()) )
			{
				ects = row[1];
			}
		}
		
		return ects;
		
	}
}
