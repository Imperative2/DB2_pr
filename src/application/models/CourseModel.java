package application.models;

public class CourseModel
{
	private String courseId;
	private String courseName;
	private String semester;
	private String form;
	private String ECTS;
	private String quantity;
	
	public CourseModel()
	{
		
	}
	
	public String toString()
	{
		return courseName + " "+form.charAt(0)+" "+semester;
	}
	
	
	public String getCourseId()
	{
		return courseId;
	}
	public void setCourseId(String courseId)
	{
		this.courseId = courseId;
	}
	public String getCourseName()
	{
		return courseName;
	}
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	public String getSemester()
	{
		return semester;
	}
	public void setSemester(String semester)
	{
		this.semester = semester;
	}
	public String getForm()
	{
		return form;
	}
	public void setForm(String form)
	{
		this.form = form;
	}
	public String getECTS()
	{
		return ECTS;
	}
	public void setECTS(String eCTS)
	{
		ECTS = eCTS;
	}
	public String getQuantity()
	{
		return quantity;
	}
	public void setQuantity(String quantity)
	{
		this.quantity = quantity;
	}
	
}
