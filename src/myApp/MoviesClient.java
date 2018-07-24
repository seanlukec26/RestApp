package myApp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import Security.Client;

public class MoviesClient 
{
	static String signature;
	static String clientId;
	
	public MoviesClient() throws Exception
	{
		Client client = new Client();
		signature = client.getSignature();
		clientId = client.getClientId();
		//System.out.println("MCLIENT----"+clientId+":"+signature);
	}
	static String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException
	{
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while(n>0)
		{
			byte[] b = new byte[4096];
			n = in.read(b);
			if(n>0)
				out.append(new String(b,0,n));
		}
		return out.toString();
	}
	
	public static List<Movie> getMovies()
	{
		List<Movie> movieList = null;
		try
		{
			CloseableHttpResponse response = null;
			try
			{		
				URI uri = new URIBuilder().setScheme("http").setHost("localhost")
						.setPort(8080).setPath("/A00207283SeanCostin_Security/rest/movies").build();
				
				System.out.println(uri.toString());
				HttpGet httpGet = new HttpGet(uri);
				httpGet.setHeader("Accept","application/xml");
				//security
				httpGet.setHeader("Authentication",clientId+","+signature);
				
			
				CloseableHttpClient httpClient = HttpClients.createDefault();
				response = httpClient.execute(httpGet);
				
				HttpEntity entity = response.getEntity();
				String text = getASCIIContentFromEntity(entity);
				System.out.println(text);
				movieList = new ParseMovies().doParseMovies(text);
				
			}
			finally
			{
				response.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return movieList;
		
	}

	public static Movie getMovie(int id)
	{
		Movie movie = null;
		try
		{
			CloseableHttpResponse response = null;
			try
			{		
				URI uri = new URIBuilder().setScheme("http").setHost("localhost")
						.setPort(8080).setPath("/A00207283SeanCostin_Security/rest/movies/"+id).build();
				
				System.out.println(uri.toString());
				HttpGet httpGet = new HttpGet(uri);
				httpGet.setHeader("Accept","text/xml");
				//security
				httpGet.setHeader("Authentication",clientId+","+signature);
			
				CloseableHttpClient httpClient = HttpClients.createDefault();
				response = httpClient.execute(httpGet);
				
				HttpEntity entity = response.getEntity();
				String text = getASCIIContentFromEntity(entity);
				movie = new ParseMovie().doParseMovie(text);
			}
			finally
			{
				response.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return movie;
	}
	public static void addMovie(int id,String title,int age,String director,String cast) throws Exception
	{
		URI uri = new URIBuilder().setScheme("http").setHost("localhost")
				.setPort(8080).setPath("/A00207283SeanCostin_Security/rest/movies").build();
		
		System.out.println(uri.toString());
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setHeader("Accept","text/html");
		//security
		httpPost.setHeader("Authentication",clientId+","+signature);
	
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("MovieId",Integer.toString(id)));
		nameValuePairs.add(new BasicNameValuePair("MovieTitle", title));
		nameValuePairs.add(new BasicNameValuePair("MovieAge", Integer.toString(age)));
		nameValuePairs.add(new BasicNameValuePair("MovieDirector", director));
		nameValuePairs.add(new BasicNameValuePair("MovieCast", cast));
	
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		System.out.println("Sending Request");
		CloseableHttpResponse response = httpClient.execute(httpPost);
	}
	
	public static void updateMovie(int id,String title,int age,String director,String cast) throws Exception
	{
		URI uri = new URIBuilder().setScheme("http").setHost("localhost")
				.setPort(8080).setPath("/A00207283SeanCostin_Security/rest/movies/"+id).build();
		
		System.out.println(uri.toString());
		HttpPut httpPut = new HttpPut(uri);
		httpPut.setHeader("Accept","text/html");
		//security
		httpPut.setHeader("Authentication",clientId+","+signature);
	
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("MovieId",Integer.toString(id)));
		nameValuePairs.add(new BasicNameValuePair("MovieTitle", title));
		nameValuePairs.add(new BasicNameValuePair("MovieAge", Integer.toString(age)));
		nameValuePairs.add(new BasicNameValuePair("MovieDirector", director));
		nameValuePairs.add(new BasicNameValuePair("MovieCast", cast));
	
		httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		System.out.println("Sending update Request");
		CloseableHttpResponse response = httpClient.execute(httpPut);
		
	}
	/*
	public static void deleteMovie(int id) throws Exception
	{
		URI uri = new URIBuilder().setScheme("http").setHost("localhost")
				.setPort(8080).setPath("/A00207283SeanCostin_Security/rest/movies/"+id).build();
		
		System.out.println(uri.toString());
		HttpDelete httpDelete = new HttpDelete(uri);
		httpDelete.setHeader("Accept","text/html");
		httpDelete.setHeader("Authentication",clientId+","+signature);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		System.out.println("Sending DELETE Request");
		CloseableHttpResponse response = httpClient.execute(httpDelete);
		//System.out.println(response);
		
	}
	*/
}
