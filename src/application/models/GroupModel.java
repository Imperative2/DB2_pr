package application.models;

public class GroupModel
{
	private String groupId;
	private String courseId;
	private String teacherId;
	private String hours;
	private String dayOfTheWeek;
	private String parity;
	private String room;
	private String freeSpace;
	
	public GroupModel()
	{
		
	}
	
	public String toString()
	{
		return groupId;
	}

	public String getGroupId()
	{
		return groupId;
	}

	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

	public String getCourseId()
	{
		return courseId;
	}

	public void setCourseId(String courseId)
	{
		this.courseId = courseId;
	}

	public String getTeacherId()
	{
		return teacherId;
	}

	public void setTeacherId(String teacherId)
	{
		this.teacherId = teacherId;
	}

	public String getHours()
	{
		return hours;
	}

	public void setHours(String hours)
	{
		this.hours = hours;
	}

	public String getDayOfTheWeek()
	{
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(String dayOfTheWeek)
	{
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public String getParity()
	{
		return parity;
	}

	public void setParity(String parity)
	{
		this.parity = parity;
	}

	public String getRoom()
	{
		return room;
	}

	public void setRoom(String room)
	{
		this.room = room;
	}

	public String getFreeSpace()
	{
		return freeSpace;
	}

	public void setFreeSpace(String freeSpace)
	{
		this.freeSpace = freeSpace;
	}
	
	
}
