package myApp;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ParseMovie 
{
	boolean inMovies = false;
	boolean inMovie = false;
	boolean inId = false;
	boolean inAge = false;
	boolean inTitle = false;
	boolean inDirector = false;
	boolean inCast = false;
	
	Movie currentMovie;
	
	public Movie doParseMovie(String s)
	{
		try
		{
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser pullParser = factory.newPullParser();
			pullParser.setInput(new StringReader(s));
			processDocument(pullParser);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return currentMovie;
	}
	
	public void processDocument(XmlPullParser pullParser) throws XmlPullParserException, IOException
	{
		int eventType = pullParser.getEventType();
		do
		{
			if (eventType == XmlPullParser.START_DOCUMENT)
			{
				System.out.println("Start Document");
			}
			else if (eventType == XmlPullParser.END_DOCUMENT)
			{
				System.out.println("End Document");
			}
			else if (eventType == XmlPullParser.START_TAG)
			{
				processStartElement(pullParser);
			}
			else if (eventType == XmlPullParser.END_TAG)
			{
				processEndElement(pullParser);
			}
			else if (eventType == XmlPullParser.TEXT)
			{
				processText(pullParser);
			}
			eventType = pullParser.next();
		}
		while(eventType != XmlPullParser.END_DOCUMENT);
	}

	private void processStartElement(XmlPullParser event) 
	{
		String name = event.getName();
		if(name.equals("movie"))
		{
			inMovie = true;
			currentMovie = new Movie();
		}
		else if(name.equals("id"))
		{
			inId = true;
		}
		else if(name.equals("age"))
		{
			inAge = true;
		}
		else if(name.equals("title"))
		{
			inTitle = true;
		}
		else if(name.equals("director"))
		{
			inDirector = true;
		}
		else if(name.equals("cast"))
		{
			inCast = true;
		}
		
	}

	private void processEndElement(XmlPullParser event) 
	{
		String name = event.getName();
		if(name.equals("movie"))
		{
			inMovie = false;
		}
		else if(name.equals("id"))
		{
			inId = false;
		}
		else if(name.equals("age"))
		{
			inAge = false;
		}
		else if(name.equals("title"))
		{
			inTitle = false;
		}
		else if(name.equals("director"))
		{
			inDirector = false;
		}
		else if(name.equals("cast"))
		{
			inCast = false;
		}
		
	}

	private void processText(XmlPullParser event) throws XmlPullParserException
	{
		if(inId)
		{
			String s = event.getText();
			currentMovie.setId(Integer.parseInt(s));
		}
		if(inAge)
		{
			String s = event.getText();
			currentMovie.setAge(Integer.parseInt(s));
		}
		if(inTitle)
		{
			String s = event.getText();
			currentMovie.setTitle(s);
		}
		if(inDirector)
		{
			String s = event.getText();
			currentMovie.setDirector(s);
		}
		if(inCast)
		{
			String s = event.getText();
			currentMovie.setCast(s);
		}
		
	}
	
	

}
