package org.cap.theatermgt.dto;

import java.util.List;

import org.cap.theatermgt.entities.Screen;

public class TheaterDetailsDto 
{

	private int theaterId;
	private String theaterName;
	private String theaterCity;
	private List<Screen> screenList;
	
	public int getTheaterId() 
	{
		return theaterId;
	}
	public void setTheaterId(int theaterId)
	{
		this.theaterId = theaterId;
	}
	public String getTheaterName()
	{
		return theaterName;
	}
	public void setTheaterName(String theaterName) 
	{
		this.theaterName = theaterName;
	}
	public String getTheaterCity() 
	{
		return theaterCity;
	}
	public void setTheaterCity(String theaterCity)
	{
		this.theaterCity = theaterCity;
	}
	public List<Screen> getScreenList() 
	{
		return screenList;
	}
	public void setScreenList(List<Screen> screenList) 
	{
		this.screenList = screenList;
	}
	
	
}
