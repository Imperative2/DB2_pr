package Controllers;

import java.util.ArrayList;
import java.util.List;

import application.AdminPanelRights;
import application.DatabaseConnection;
import application.models.StudentModel;

public class AdminPanelRightsController
{
	private List<StudentModel> students = new ArrayList<>();
	private DatabaseConnection dbConn;
	private AdminPanelRights panel;
	
	public AdminPanelRightsController(DatabaseConnection dbConn, AdminPanelRights panel)
	{
		this.dbConn = dbConn;
		this.panel = panel;
		
		loadStudents();
	}
	
	
	public void loadStudents()
	{
		students.clear();
		
		String querryString = "SELECT `id_indeks`, `imie`, `nazwisko`, `pesel`, `email`, `plec`, `id_kierunku`, `semestr`, `prawo_do_zapisow`, `termin_zapisow`\r\n" + 
				"FROM `student`\r\n" + 
				"ORDER BY id_indeks ASC;";
		
		dbConn.connect();
		
		List<String[]> querryResult = dbConn.querryDatabase(querryString, 10);
		
		for(String[] row : querryResult)
		{
			StudentModel student = new StudentModel();
			student.setIndexId(row[0]);
			student.setName(row[1]);
			student.setSurname(row[2]);
			student.setPesel(row[3]);
			student.setEmail(row[4]);
			student.setGender(row[5]);
			student.setFieldOfStudy(row[6]);
			student.setSemester(row[7]);
			student.setAdmissionRight(row[8]);
			student.setAdmissionTime(row[9]);
			
			students.add(student);
		}
		
		panel.loadList(students);
	}
	
}
