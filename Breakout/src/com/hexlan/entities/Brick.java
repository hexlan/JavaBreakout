package com.hexlan.entities;

import java.awt.Graphics2D;
import java.awt.Color;

public class Brick extends Entity
{
	public boolean isAlive;
	private boolean collidable;
	private boolean flash;
	private int counter;
	
	public Brick(double x, double y)
	{
		this.x = x;
		this.y = y;
		
		width = 20;
		height = 10;
		
		collidable = true;
		isAlive = true;
		flash = false;
		
		counter = 0;
	}
	
	public void kill()
	{
		collidable = false;
		flash = true;
	}
	
	public boolean getState() { return collidable; }
	
	public void draw(Graphics2D g)
	{
			
		if(flash)
		{
			counter++;
			
			if(counter < 2)
				g.setColor(new Color(0, 0, 0));
			else
			g.setColor(new Color(255, 255, 255));
			
			if(counter == 6)
			{
				flash = false;
				isAlive = false;
			}
			g.fillOval((int)x-counter, (int)y-counter, width+counter, height+counter);
		}
		else
		{
			g.setColor(new Color(61, 64, 72));
			g.fillRect((int)x+1, (int)y+1, width, height);
			g.setColor(new Color(170, 117, 112));
			g.fillRect((int)x, (int)y, width, height);
		}
	}
}
