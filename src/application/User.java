package application;

abstract class User
{
	private String name;
	private String surname;
	private String userId;

	
	
	
	
	public String getName()
	{
		return name;
	}
	public String getSurname()
	{
		return surname;
	}
	public String getUserId()
	{
		return userId;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
}
