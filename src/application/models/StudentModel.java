package application.models;

public class StudentModel
{
	private String indexId;
	private String name;
	private String surname;
	private String pesel;
	private String email;
	private String gender;
	private String fieldOfStudy;
	private String semester;
	private String admissionRight;
	private String admissionTime;
	
	public StudentModel()
	{
		
	}
	
	public String toString()
	{
		return indexId+" "+name+" "+surname;
	}
	
	
	public String getIndexId()
	{
		return indexId;
	}
	public void setIndexId(String indexId)
	{
		this.indexId = indexId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSurname()
	{
		return surname;
	}
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	public String getPesel()
	{
		return pesel;
	}
	public void setPesel(String pesel)
	{
		this.pesel = pesel;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getFieldOfStudy()
	{
		return fieldOfStudy;
	}
	public void setFieldOfStudy(String fieldOfStudy)
	{
		this.fieldOfStudy = fieldOfStudy;
	}
	public String getSemester()
	{
		return semester;
	}
	public void setSemester(String semester)
	{
		this.semester = semester;
	}
	public String getAdmissionRight()
	{
		return admissionRight;
	}
	public void setAdmissionRight(String admissionRight)
	{
		this.admissionRight = admissionRight;
	}
	public String getAdmissionTime()
	{
		return admissionTime;
	}
	public void setAdmissionTime(String admissionTime)
	{
		this.admissionTime = admissionTime;
	}
	
	
}
