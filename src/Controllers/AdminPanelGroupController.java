package Controllers;

import java.util.ArrayList;
import java.util.List;

import application.DatabaseConnection;
import application.models.CourseModel;
import application.models.Group;
import application.models.GroupModel;
import application.panels.AdminPanelGroup;



public class AdminPanelGroupController
{
	private List<CourseModel> coursesList = new ArrayList<>();
	private DatabaseConnection dbConn;
	private AdminPanelGroup panel;
	
	private List<GroupModel> groupList = new ArrayList<>();
	
	public AdminPanelGroupController(DatabaseConnection dbConn, AdminPanelGroup panel)
	{
		this.dbConn = dbConn;
		this.panel = panel;
		
		loadCourses();
	}
	
	public void loadCourses()
	{
		coursesList.clear();
		
		String querryString = "SELECT * FROM `kurs` ORDER BY semestr,nazwa_kursu ASC;";
		
		dbConn.connect();
		
		List<String[]> querryResult = dbConn.querryDatabase(querryString, 6);
		
		for(String[] row : querryResult)
		{
			CourseModel course = new CourseModel();
			course.setCourseId(row[0]);
			course.setCourseName(row[1]);
			course.setSemester(row[2]);
			course.setForm(row[3]);
			course.setECTS(row[4]);
			course.setQuantity(row[5]);
			
			coursesList.add(course);
		}
		
		panel.loadListCourses(coursesList);
	}
	
	public void loadGroups(CourseModel course)
	{
		groupList.clear();
		
		String querryString = "SELECT * FROM `grupa_zajeciowa` WHERE id_kursu = '"+course.getCourseId()+"';";
		
		dbConn.connect();
		
		List<String[]> querryResult = dbConn.querryDatabase(querryString, 8);
		
		for(String[] row : querryResult)
		{
			GroupModel group = new GroupModel();
			group.setGroupId(row[0]);
			group.setCourseId(row[1]);
			group.setTeacherId(row[2]);
			group.setHours(row[3]);
			group.setDayOfTheWeek(row[4]);
			group.setParity(row[5]);
			group.setFreeSpace(row[6]);
			group.setRoom(row[7]);
			
			
			groupList.add(group);
		}
		
		panel.loadListGroups(groupList);
	}

}
