package Controllers;

import java.util.ArrayList;
import java.util.List;

import application.panels.AdminPanelCourse;
import application.DatabaseConnection;
import application.models.CourseModel;

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
		
		panel.loadList(coursesList);
	}
	
	public void createNewCourse(CourseModel course)
	{
		dbConn.connect();
		String sql_query = "INSERT INTO `kurs`(`id_kursu`, `nazwa_kursu`, `semestr`, `forma_zajec`, `ects`, `ilosc`)"
				+ " VALUES ("+course.getCourseId()+",'"+course.getCourseId()+"','"+course.getSemester()+"','"+course.getForm()
				+"','"+course.getECTS()+"','"+20+"');" ;
		
		dbConn.deleteOrUpdateData(sql_query);
		
		loadCourses();
		panel.update(course);
	}
	
	public void modifyCourse(CourseModel course)
	{
		dbConn.connect();
		String sql_query = "UPDATE `kurs` SET `nazwa_kursu`='"+course.getCourseName()
		+"',`semestr`='"+course.getSemester()+"',`forma_zajec`='"+course.getForm()+"',`ects`='"+course.getECTS()
		+"',`ilosc`='20' WHERE id_kursu = "+course.getCourseId()+";";
		
		dbConn.deleteOrUpdateData(sql_query);
		
		loadCourses();
		panel.update(course);
	}
	
	public void deleteCourse(CourseModel course)
	{
		dbConn.connect();
		String sql_query = "DELETE FROM `kurs` WHERE id_kursu = "+course.getCourseId()+";" ;
		
		dbConn.deleteOrUpdateData(sql_query);
		
		loadCourses();
		panel.update(course);
	}
}
