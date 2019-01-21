package application.models;

public class TimeModel
{
	private String id;
	private String time;
	
	public TimeModel()
	{
		
	}
	
	public String toString()
	{
		return time;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}
	
}
