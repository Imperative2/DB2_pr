package Controllers;

import application.DatabaseConnection;
import application.Employee;
import application.User;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmployeePanelControler
{
	private Employee mainUser;
	private DatabaseConnection dbConn;
	
	private Map<String,Integer> employeeComponentsMap;
	
	public EmployeePanelControler(DatabaseConnection dbConnection,Map<String,Integer> employeeComponentsMap)
	{
		this.dbConn = dbConnection;
		this.employeeComponentsMap = employeeComponentsMap;
	}
	
	public void setMainUser(Employee mainUser)
	{
		this.mainUser = mainUser;
	}
	
	public User getMainUser()
	{
		return mainUser;
	}
	
	public void updateEmployeePanel(JPanel employeePanel)
	{
		int name = employeeComponentsMap.get("labelName");
		JLabel labelName = (JLabel)employeePanel.getComponent(name);
		labelName.setText(mainUser.getName());
		
		int surname = employeeComponentsMap.get("labelSurname");
		JLabel labelSurname = (JLabel)employeePanel.getComponent(surname);
		labelSurname.setText(mainUser.getSurname());
	}
}
