package application;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Student extends User
{

	private String pesel;
	private String gender;
	private String fieldOfStudy;
	private String semester;
	private String admissionRight;
	private String admissionTime;
	
	public Student()
	{
		
	}
	
	
	public String getPesel()
	{
		return pesel;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public String getFieldOfStudy()
	{
		return fieldOfStudy;
	}
	
	public String getSemester()
	{
		return semester;
	}
	
	public String getAdmissionRight()
	{
		return admissionRight;
	}
	
	public String getAdmissionTime()
	{
		return admissionTime;
	}
	
	public GregorianCalendar  getGregTime()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try
		{
			date = df.parse(admissionTime);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			return cal;
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GregorianCalendar();
		
	}
	
	public void setPesel(String pesel)
	{
		this.pesel = pesel;
	}
	
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public void setFieldOfStudy(String fieldOfStudy)
	{
		this.fieldOfStudy = fieldOfStudy;
	}
	
	public void setSemester(String semester)
	{
		this.semester = semester;
	}
	
	public void setAdmissionRight(String admissionRight)
	{
		this.admissionRight = admissionRight;
	}
	
	public void setAdmissionTime(String admissionTime)
	{
		this.admissionTime = admissionTime;
	}
	
}
