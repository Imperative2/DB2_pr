package application;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Student extends User
{

	private int indeks;
	private String name, surname;
	private String pesel;
	private String gender;
	private String fieldOfStudy;
	private String semester;
	private String admissionRight;
	private String admissionTime;
	
	public Student()
	{
		
	}

	public Student(int indeks, String name, String surname, String fieldOfStudy, String admissionRight){
		this.indeks = indeks;
		this.name = name;
		this.surname = surname;
		this.fieldOfStudy = fieldOfStudy;
		this.admissionRight = admissionRight;
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

	public int getIndeks() {
		return indeks;
	}

	public void setIndeks(int indeks) {
		this.indeks = indeks;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	@Override
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return this.indeks + " " + this.surname;
	}
}
