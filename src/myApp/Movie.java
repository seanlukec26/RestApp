package myApp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "movie")
@XmlType(propOrder = {"id","title","age","director","cast"})

public class Movie 
{
	private int id;
	private String title;
	private int age;
	private String director;
	private String cast;
	
	public Movie(){}
	
	public Movie(int id, String title, int age, String director, String cast)
	{
		this.id = id;
		this.title = title;
		this.age = age;
		this.director = director;
		this.cast = cast;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	
	
	
	
}
