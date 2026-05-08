package model;

import java.awt.Color;
import java.awt.Graphics;

public class MyOval extends MyShape
{
	private boolean fill; // This variable checks rectangle is filled or not
	
	// Constructor for rectangle
	public MyOval(int x1, int x2, int y1, int y2, Color color, boolean fill)
	{
		super(x1,x2,y1,y2,color);
		this.fill=fill;
	}
	
	// Set method for fill
	public void setFill(boolean fill) 
	{
        this.fill = fill;
    }
	
	@Override
	public void draw(Graphics g)
	{
		g.setColor(getColor());
		
		int floorX = Math.min(getX1(), getX2());
		int floorY = Math.min(getY1(), getY2());
		
		int width = Math.abs(getX1()-getX2());
		int height = Math.abs(getY1()-getY2());
		
		if(fill)
		{
			g.fillOval(floorX, floorY, width, height);
		}
		else
		{
			g.drawOval(floorX, floorY, width, height);
		}
	}
	
	@Override
	public String getType()
	{
		return "oval";
	}
}
