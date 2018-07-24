package myApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import GUI.Home;
import GUI.Login;

public enum UserDao 
{
	instance;
	public static String User;
	
	public static Connection getConnection()
	{				
		Connection connect = null;
		
		try
		{
			Class.forName("org.hsqldb.jdbcDriver");
			connect = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB","sa","");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return connect;
	}
	
	public static void checkUser(String username, String password) 
	{
		Connection connect = getConnection();
	
	    try 
	    {
	    	PreparedStatement psmt = connect.prepareStatement("SELECT * FROM USER WHERE name = ? and password = ?");
	    	psmt.setString(1, username);
	    	psmt.setString(2, password);
	    	ResultSet rs = psmt.executeQuery();
	    	
	    	User = username;
	    	
	        if(!rs.next()) 
	        {
	           JOptionPane.showMessageDialog(null,"Incorrect Username / Password");  
	        }
	        else
	        {
	        	Home home = new Home();
				home.setVisible(true);
				Login.getFrame().dispose();
	        }
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }

	}
	
	public static void createUser(String username, String password, String address)
	{
		Connection connect = getConnection();
		
		try
		{
			String insertSQL = "INSERT INTO USER VALUES(NULL,?,?,?)";
			PreparedStatement psmt = connect.prepareStatement(insertSQL);
			psmt.setString(1, username);
			psmt.setString(2, password);
			psmt.setString(3, address);
			
			int rowCount = psmt.executeUpdate();
			System.out.println("Executed Update - Row Count: "+rowCount);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
