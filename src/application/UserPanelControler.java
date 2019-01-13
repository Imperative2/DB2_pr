package application;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserPanelControler
{
	private Student mainUser;
	private DatabaseConnection dbConn;
	
	private Map<String,Integer> userComponentsMap;
	
	UserPanelControler(DatabaseConnection dbConnection,Map<String,Integer> userComponentsMap)
	{
		this.dbConn = dbConnection;
		this.userComponentsMap = userComponentsMap;
	}
	
	public void setMainUser(Student mainUser)
	{
		this.mainUser = mainUser;
	}
	
	public User getMainUser()
	{
		return mainUser;
	}
	
	public void updateUserPanel(JPanel userPanel)
	{
		int name = userComponentsMap.get("labelName");
		JLabel labelName = (JLabel)userPanel.getComponent(name);
		labelName.setText(mainUser.getName());
		
		int surname = userComponentsMap.get("labelSurname");
		JLabel labelSurname = (JLabel)userPanel.getComponent(surname);
		labelSurname.setText(mainUser.getSurname());
		
		int index = userComponentsMap.get("labelStudentIndex");
		JLabel labelStudentIndex = (JLabel)userPanel.getComponent(index);
		labelStudentIndex.setText(mainUser.getUserId());
		
		int fieldStudy = userComponentsMap.get("labelFieldStudy");
		JLabel labelFieldStudy = (JLabel)userPanel.getComponent(fieldStudy);
		labelFieldStudy.setText(mainUser.getFieldOfStudy());
		
		int semester = userComponentsMap.get("labelSemester");
		JLabel labelSemester = (JLabel)userPanel.getComponent(semester);
		labelSemester.setText(mainUser.getSemester());
	}
}
