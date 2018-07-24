package myApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

public enum MovieDao 
{
	instance;
	
	public void insertKey(String id,String key)
	{
		Connection connection = getConnection();
	
		try
		{
			String insertSQL = "INSERT INTO SECURITY (CLIENTID, SECRETKEY) VALUES('"+id+"','"+key+"')";
			Statement mystatement = connection.createStatement();
			mystatement.executeQuery(insertSQL);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String getKey(String clientId)
	{
		Connection connection = getConnection();
		String sk = "";
		try
		{
			String getkey = "SELECT SECRETKEY FROM SECURITY WHERE CLIENTID='"+clientId+"'";
			Statement myStatement = connection.createStatement();
			ResultSet rs = myStatement.executeQuery(getkey);
			while(rs.next())
			{
				sk = rs.getString("secretkey");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sk;
	}
	
	public void insertLog(String clientid, String name)
	{
		Connection connection = getConnection();
	
		try
		{
			String insertSQL = "INSERT INTO LOG(clientid,name) values ('"+clientid+"','"+name+"')";
			Statement myStmt = connection.createStatement();
			
			myStmt.executeQuery(insertSQL);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
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
	
	public static List<Movie> getMovies()
	{
		
		Connection connect = getConnection();
		
		List<Movie> movieList = new ArrayList<Movie>();
		
		try
		{
			PreparedStatement psmt = connect.prepareStatement("SELECT * FROM MOVIE");
			ResultSet rs = psmt.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("MovieId");
				String title = rs.getString("MovieTitle");
				int age = rs.getInt("MovieAge");
				String director = rs.getString("MovieDirector");
				String cast = rs.getString("MovieCast");
				
				Movie movie = new Movie(id,title,age,director,cast);
				movieList.add(movie);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return movieList;
	}
	
	public static Movie getMovie(int t)

	{
		Connection connect = getConnection();
		
		Movie movie = null;
		
		try
		{
			PreparedStatement psmt = connect.prepareStatement("SELECT * FROM MOVIE WHERE MOVIEID = ?");
			psmt.setInt(1, t);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt("MovieId");
				String title = rs.getString("MovieTitle");
				int age = rs.getInt("MovieAge");
				String director = rs.getString("MovieDirector");
				String cast = rs.getString("MovieCast");
				
				movie = new Movie(id,title,age,director,cast);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return movie;
	}
	
	public static void create(Movie movie)
	{
		Connection connect = getConnection();
		
		try
		{
			String insertSQL = "INSERT INTO MOVIE VALUES(NULL,?,?,?,?)";
			PreparedStatement psmt = connect.prepareStatement(insertSQL);
			psmt.setString(1, movie.getTitle());
			psmt.setInt(2, movie.getAge());
			psmt.setString(3, movie.getDirector());
			psmt.setString(4, movie.getCast());
			int rowCount = psmt.executeUpdate();
			System.out.println("Executed Update - Row Count: "+rowCount);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/*
	public static void delete(int id)
	{
		Connection connection = getConnection();
		try
		{
			String deleteSQL = "DELETE FROM MOVIE WHERE MovieId = ?";
			PreparedStatement psmt = connection.prepareStatement(deleteSQL);
			psmt.setInt(1, id);
			
			int returned = psmt.executeUpdate();
			System.out.println("Executed update - Return: "+returned);
			JOptionPane.showMessageDialog(null, "Movie deleted");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	
	public static void update(int id, Movie movie)
	{
		Connection connect = getConnection();
		
		try
		{
			String insertSQL = "UPDATE MOVIE SET MOVIETITLE = ?, MOVIEAGE= ?, MOVIEDIRECTOR=?,MOVIECAST=? WHERE MOVIEID = ?";
			PreparedStatement psmt = connect.prepareStatement(insertSQL);
			psmt.setString(1, movie.getTitle());
			psmt.setInt(2, movie.getAge());
			psmt.setString(3, movie.getDirector());
			psmt.setString(4, movie.getCast());
			psmt.setString(5, Integer.toString(id));
			int rowCount = psmt.executeUpdate();
			System.out.println("Executed Update - Row Count: "+rowCount);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
