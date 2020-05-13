package org.cap.theatermgt.dto;

public class ScreenRequestDto 
{

	private String screenName;
	private int row;
	private int column;
	
	public String getScreenName()
	{
		return screenName;
	}
	public void setScreenName(String screenName)
	{
		this.screenName = screenName;
	}
	public int getRow() 
	{
		return row;
	}
	public void setRow(int row)
	{
		this.row = row;
	}
	public int getColumn() 
	{
		return column;
	}
	public void setColumn(int column) 
	{
		this.column = column;
	}
	
	
}
