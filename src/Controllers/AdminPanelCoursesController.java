package Controllers;

import java.util.ArrayList;
import java.util.List;

import application.AdminPanelCourse;
import application.AdminPanelRights;
import application.DatabaseConnection;
import application.models.CourseModel;
import application.models.StudentModel;

public class AdminPanelCoursesController
{

	private List<CourseModel> coursesList = new ArrayList<>();
	private DatabaseConnection dbConn;
	private AdminPanelCourse panel;
	
	public AdminPanelCoursesController(DatabaseConnection dbConn, AdminPanelCourse panel)
	{
		this.dbConn = dbConn;
		this.panel = panel;
		
		loadCourses();
	}
	
	public void loadCourses()
	{
		coursesList.clear();
		
		String querryString = "SELECT * FROM `kurs`";
		
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
		
		panel.loadList(coursesList);
	}
}
