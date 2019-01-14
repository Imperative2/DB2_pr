package application;

import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserGroupModel
{
	private DatabaseConnection dbConn;
	private Map<String, Integer> groupComponentsMap;
	
	private String courseName;
	private String ects;
	private String form;
	private String groupCode;
	private String teacher;
	private String week;
	private String day;
	private String room;
	private String time;
	private String zapisID;
	
	
	public UserGroupModel(DatabaseConnection dbConn)
	{
		this.dbConn = dbConn;
	}
	
	public UserGroupModel(String[] data)
	{
		this.courseName = data[0];
		this.ects = data[1];
		this.form = data[2];
		this.groupCode = data[3];
		this.teacher = data[4];
		this.week = data[5];
		this.day = data[6];
		this.room = data[7];
		this.time = data[8];
		time = time.replaceAll(":00 |:00$", " ");
		this.zapisID = data[9];
	}
	
	public String toString()
	{
		return courseName+" "+form.charAt(0);
	}
	
	public void addComponentsMap(Map<String, Integer> groupComponentsMap)
	{
		this.groupComponentsMap = groupComponentsMap;
	}
	
	public  List<String[]> getData()
	{
		List<String[]> querryResult;
		
		String querryString = "SELECT kurs.nazwa_kursu, kurs.ects, kurs.forma_zajec, grupa_zajeciowa.id_grupy, CONCAT(prowadzacy.tytul,' ',prowadzacy.imie,' ',prowadzacy.nazwisko) AS teacher, grupa_zajeciowa.parzystosc_tygodnia, grupa_zajeciowa.dzien_tygodnia, grupa_zajeciowa.sala_zajeciowa, CONCAT(godziny_zajec.godzina_rozpoczecia,' ',godziny_zajec.godzina_zakonczenia) AS time, zapis.id_zapisu, zapis.id_indeksu\r\n" + 
				"FROM kurs INNER JOIN grupa_zajeciowa\r\n" + 
				"ON kurs.id_kursu = grupa_zajeciowa.id_kursu\r\n" + 
				"INNER JOIN prowadzacy\r\n" + 
				"ON grupa_zajeciowa.id_prowadzacego = prowadzacy.id_prowadzacego\r\n" + 
				"INNER JOIN godziny_zajec\r\n" + 
				"ON grupa_zajeciowa.id_godziny_zajec = godziny_zajec.id_godziny_zajec\r\n" + 
				"INNER JOIN zapis\r\n" + 
				"ON grupa_zajeciowa.id_grupy = zapis.id_grupy";
		
		dbConn.connect();
		querryResult = dbConn.querryDatabase(querryString, 11);
		
		return querryResult;
	}
	
	public void updateGroupModel(JPanel groupPanel )
	{
		int courseName = groupComponentsMap.get("labelGroupCourseName");
		JLabel labelCourseName = (JLabel)groupPanel.getComponent(courseName);
		labelCourseName.setVisible(true);
		labelCourseName.setText(this.courseName);
		
		int courseForm = groupComponentsMap.get("labelGroupForm");
		JLabel labelCourseForm = (JLabel)groupPanel.getComponent(courseForm);
		labelCourseForm.setVisible(true);
		labelCourseForm.setText(this.form);

		int courseECTS = groupComponentsMap.get("labelGroupECTS");
		JLabel labelCourseECTS = (JLabel)groupPanel.getComponent(courseECTS);
		labelCourseECTS.setVisible(true);
		labelCourseECTS.setText(this.ects);
		
		int groupCode = groupComponentsMap.get("labelGroupCode");
		JLabel labelGroupCode = (JLabel)groupPanel.getComponent(groupCode);
		labelGroupCode.setVisible(true);
		labelGroupCode.setText(this.groupCode);
		
		int groupTeacher = groupComponentsMap.get("labelGroupTeacher");
		JLabel labelGroupTeacher = (JLabel)groupPanel.getComponent(groupTeacher);
		labelGroupTeacher.setVisible(true);
		labelGroupTeacher.setText(this.teacher);
		
		int groupDay = groupComponentsMap.get("labelGroupDay");
		JLabel labelGroupDay = (JLabel)groupPanel.getComponent(groupDay);
		labelGroupDay.setVisible(true);
		labelGroupDay.setText(this.day);
		
		int groupTime = groupComponentsMap.get("labelGroupTime");
		JLabel labelGroupTime = (JLabel)groupPanel.getComponent(groupTime);
		labelGroupTime.setVisible(true);
		labelGroupTime.setText(this.time);
		
		int groupWeek = groupComponentsMap.get("labelGroupWeek");
		JLabel labelGroupWeek = (JLabel)groupPanel.getComponent(groupWeek);
		labelGroupWeek.setVisible(true);
		labelGroupWeek.setText(this.week);
		
		int groupRoom = groupComponentsMap.get("labelGroupRoom");
		JLabel labelGroupRoom = (JLabel)groupPanel.getComponent(groupRoom);
		labelGroupRoom.setVisible(true);
		labelGroupRoom.setText(this.room);
		
		int unsub = groupComponentsMap.get("buttonGroupUnsub");
		JButton buttonGroupUnsub = (JButton)groupPanel.getComponent(unsub);
		buttonGroupUnsub.setVisible(true);
		
		
	}
	
	
	public String getCourseName()
	{
		return courseName;
	}
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	public String getEcts()
	{
		return ects;
	}
	public void setEcts(String ects)
	{
		this.ects = ects;
	}
	public String getForm()
	{
		return form;
	}
	public void setForm(String form)
	{
		this.form = form;
	}
	public String getGroupCode()
	{
		return groupCode;
	}
	public void setGroupCode(String groupCode)
	{
		this.groupCode = groupCode;
	}
	public String getTeacher()
	{
		return teacher;
	}
	public void setTeacher(String teacher)
	{
		this.teacher = teacher;
	}
	public String getWeek()
	{
		return week;
	}
	public void setWeek(String week)
	{
		this.week = week;
	}
	public String getDay()
	{
		return day;
	}
	public void setDay(String day)
	{
		this.day = day;
	}
	public String getRoom()
	{
		return room;
	}
	public void setRoom(String room)
	{
		this.room = room;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	
	public String getZapisId()
	{
		return zapisID;
	}
	
}
