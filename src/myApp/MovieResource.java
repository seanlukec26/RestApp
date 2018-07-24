package myApp;

import java.util.Base64;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/movies")
public class MovieResource 
{
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.TEXT_XML})
	public List<Movie> getMovies(@HeaderParam("authentication") String signature ) throws Exception
	{
		String clientId = signature.split(",")[0];
		
		String encodedKey = MovieDao.instance.getKey(clientId);
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		SecretKey sk = new SecretKeySpec(decodedKey, 0, decodedKey.length,"HmacSHA256");
		
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(sk);
		
		byte[] result = mac.doFinal(clientId.getBytes());
		String sign = Base64.getEncoder().encodeToString(result);
		
		if(sign.equals(signature.split(",")[1]))
		{
			return MovieDao.instance.getMovies();
		}
		return null;
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
		MediaType.TEXT_XML})
	@Path("{MovieId}")
	
	public Movie getMovie(@HeaderParam("authentication") String signature, 
			@PathParam("MovieId")String id) throws Exception
	{
		String clientId = signature.split(",")[0];
		
		String encodedKey = MovieDao.instance.getKey(clientId);
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		SecretKey sk = new SecretKeySpec(decodedKey, 0, decodedKey.length,"HmacSHA256");
		
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(sk);
		
		byte[] result = mac.doFinal(clientId.getBytes());
		String sign = Base64.getEncoder().encodeToString(result);
		
		if(sign.equals(signature.split(",")[1]))
		{
			return MovieDao.instance.getMovie(Integer.parseInt(id));
	
		}
		return null;
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postMovie(@HeaderParam("authentication") String signature,
			@FormParam("MovieId") String id,
			@FormParam("MovieTitle") String title,
			@FormParam("MovieAge") String age,
			@FormParam("MovieDirector") String director,
			@FormParam("MovieCast") String cast,
			@Context HttpServletResponse servletResponse) throws Exception
			{
				//System.out.println("Inside POST id = "+id);
				//System.out.println("title = "+title);
				String clientId = signature.split(",")[0];
				
				String encodedKey = MovieDao.instance.getKey(clientId);
				byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
				SecretKey sk = new SecretKeySpec(decodedKey, 0, decodedKey.length,"HmacSHA256");
				
				Mac mac = Mac.getInstance("HmacSHA256");
				mac.init(sk);
				
				byte[] result = mac.doFinal(clientId.getBytes());
				String sign = Base64.getEncoder().encodeToString(result);
		
		
				Movie Movie = new Movie();
				Movie.setId(Integer.parseInt(id));
				Movie.setTitle(title);
				Movie.setAge(Integer.parseInt(age));
				Movie.setDirector(director);
				Movie.setCast(cast);
				
				if(sign.equals(signature.split(",")[1]))
				{
					MovieDao.instance.create(Movie);
					MovieDao.instance.insertLog(clientId, title);
				
					servletResponse.sendRedirect("../createMovie.html");
			
				}
			}
	
	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{MovieId}")
	public void putMovie(@HeaderParam("authentication") String signature,
	@FormParam("MovieId") String id,
	@FormParam("MovieTitle") String title,
	@FormParam("MovieAge") String age,
	@FormParam("MovieDirector") String director,
	@FormParam("MovieCast") String cast,
			@Context HttpServletResponse servletResponse) throws Exception
			{
				Movie Movie = new Movie();
				//Movie.setId(Integer.parseInt(id));
				Movie.setTitle(title);
				Movie.setAge(Integer.parseInt(age));
				Movie.setDirector(director);
				Movie.setCast(cast);
				String clientId = signature.split(",")[0];
				
				String encodedKey = MovieDao.instance.getKey(clientId);
				byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
				SecretKey sk = new SecretKeySpec(decodedKey, 0, decodedKey.length,"HmacSHA256");
				
				Mac mac = Mac.getInstance("HmacSHA256");
				mac.init(sk);
				
				byte[] result = mac.doFinal(clientId.getBytes());
				String sign = Base64.getEncoder().encodeToString(result);
				
				if(sign.equals(signature.split(",")[1]))
				{
					MovieDao.instance.update(Integer.parseInt(id),Movie);
					MovieDao.instance.insertLog(clientId, title+" (Update)");
				}
			}
	/*
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("{MovieId}")
	public void deleteMovie(@PathParam("MovieId") String id) throws IOException
	{
		MovieDao.instance.delete(Integer.parseInt(id));
		System.out.println("hello again");
	}
	*/
}
