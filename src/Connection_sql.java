import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Connection_sql {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/dbms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String USER = "root";
	private	static final String PASSWORD = "27021797";
	private String id;
	private String password;
	private Connection conn;
	private User user;
	public Connection_sql()
	{
		try
		{
			conn=DriverManager.getConnection(DB_URL,USER,PASSWORD);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public Connection_sql(String id,String password)
	{
		this.id=id;
		this.password=password;
		try
		{
			conn=DriverManager.getConnection(DB_URL,USER,PASSWORD);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public boolean signUp(String id,String name,String email,String password)
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("INSERT INTO User VALUES(?,?,?,?,?,?)");
			stat.setString(1,id);
			stat.setString(2,name);
			stat.setString(3,"User");
			stat.setString(4,password);
			stat.setString(5,email);
			stat.setInt(6,1);
			int result=stat.executeUpdate();
			if(result==1)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public User verify()
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("SELECT * FROM user WHERE User_ID= ? AND Password= ? ");
			stat.setString(1,id);
			stat.setString(2,password);
			ResultSet result=stat.executeQuery();
			while(result.next())
			{
				user=new User(result.getString(1),result.getString(2),result.getString(3),result.getString(5),result.getInt(6));
				return user;
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public User getUser()
	{
		return user;
	}
	public boolean createEvent(String title,String time,String location,String content)
	{
		try
		{
			Random ran=new Random();
			int id=0;
			String eventID="";
			boolean valid=true;
			while(valid)
			{
				id=ran.nextInt(100000);
				eventID=id+"";
				PreparedStatement stat;
				stat = conn.prepareStatement("SELECT * FROM EVENT WHERE Event_ID= ?");
				stat.setString(1,eventID);
				ResultSet result=stat.executeQuery();
				if(!result.next())
				{
					valid=false;
				}
			}
			PreparedStatement stat;
			stat = conn.prepareStatement("INSERT INTO Event VALUES(?,?,?,?,?,?)");
			stat.setString(1,eventID);
			stat.setString(2,user.getID());
			stat.setString(3,title);
			stat.setString(4,time);
			stat.setString(5,location);
			stat.setString(6,content);
			int result=stat.executeUpdate();
			if(result==1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public ResultSet searchMyEvent()
	{
		PreparedStatement stat;
		try
		{
			stat=conn.prepareStatement("SELECT * FROM event WHERE User_ID= ?");
			stat.setString(1,id);
			ResultSet result=stat.executeQuery();
			return result;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteEvent(String eventID)
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("DELETE FROM dbms.Join WHERE event_ID=?");
			stat.setString(1,eventID);
			int result=stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement stat1;
		try
		{
			stat1 = conn.prepareStatement("DELETE FROM Event WHERE event_ID=?");
			stat1.setString(1,eventID);
			int result=stat1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void joinEvent(String eventID)
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("INSERT INTO dbms.Join VALUES(?,?,?)");
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date current = new Date();
			String time=sdFormat.format(current);
			stat.setString(1,time);
			stat.setString(2,id);
			stat.setString(3,eventID);
			int result=stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean checkJoin(String eventID)
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("SELECT * FROM dbms.join WHERE User_ID=? AND Event_ID=?");
			stat.setString(1,id);
			stat.setString(2,eventID);
			ResultSet result=stat.executeQuery();
			if(result.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void cancelEvent(String eventID)
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("DELETE FROM dbms.Join WHERE user_ID=? AND event_ID=?");
			stat.setString(1,id);
			stat.setString(2,eventID);
			int result=stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeComment(String eventID,int score,String command)
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("INSERT INTO dbms.feedback VALUES(?,?,?,?)");
			stat.setString(1,command);
			stat.setString(2,id);
			stat.setString(3,eventID);
			stat.setInt(4,score);
			int result=stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean eventHolder(String eventID)
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("SELECT * FROM dbms.user WHERE User_ID=? AND Event_ID=?");
			stat.setString(1,id);
			stat.setString(2,eventID);
			ResultSet result=stat.executeQuery();
			if(result.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public ResultSet seeFeedback(String eventID)
	{
		PreparedStatement stat;
		try
		{
			stat=conn.prepareStatement("SELECT * FROM dbms.feedback WHERE Event_ID= ?");
			stat.setString(1,eventID);
			ResultSet result=stat.executeQuery();
			return result;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	public boolean checkFeedback(String eventID)
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("SELECT * FROM dbms.feedback WHERE User_ID=? AND Event_ID=?");
			stat.setString(1,id);
			stat.setString(2,eventID);
			ResultSet result=stat.executeQuery();
			if(result.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public double getAvg(String eventID)
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("SELECT AVG(Score) FROM dbms.feedback WHERE Event_ID=?");
			stat.setString(1,eventID);
			ResultSet result=stat.executeQuery();
			if(result.next())
			{
				return result.getDouble(1);
			}
			else
			{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public User getSearchUser(String searchUserID)
	{
		PreparedStatement stat;
		User searchUser;
		try
		{
			stat = conn.prepareStatement("SELECT * FROM dbms.user WHERE User_ID=?");
			stat.setString(1,searchUserID);
			ResultSet result=stat.executeQuery();
			if(result.next())
			{
				searchUser=new User(result.getString("User_ID"),result.getString("User_Name"),"User",result.getString("Email"),result.getInt("Validity"));
				return searchUser;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public double getUserAvg(String searchUserID)
	{
		PreparedStatement stat;
		double sum=0;
		double count=0;
		try
		{
			stat = conn.prepareStatement("SELECT event_ID FROM dbms.event WHERE User_ID=?");
			stat.setString(1,searchUserID);
			ResultSet result=stat.executeQuery();
			while(result.next())
			{
				double score=getAvg(result.getString(1));
				if(score>0)
				{
					sum+=score;
					count++;
				}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum/count;
	}
	public void blockUser(String userID)
	{
		PreparedStatement stat;
		try
		{
			stat = conn.prepareStatement("UPDATE dbms.user SET Validity=0 WHERE User_ID=?");
			stat.setString(1,userID);
			int result=stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
