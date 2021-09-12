
public class User {
	private String id;
	private String name;
	private String identity;
	private String email;
	private int validity;
	public User(String id,String name,String identity,String email,int validity)
	{
		this.id=id;
		this.name=name;
		this.identity=identity;
		this.email=email;
		this.validity=validity;
	}
	public String getID()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getIdentity()
	{
		return identity;
	}
	public String getEmail()
	{
		return email;
	}
	public int getValidity()
	{
		return validity;
	}
}
