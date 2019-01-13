package application;

public class Student extends User
{

	private String pesel;
	private String gender;
	private String fieldOfStudy;
	private String semester;
	
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
	
}
