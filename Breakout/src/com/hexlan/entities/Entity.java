package com.hexlan.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Entity 
{
	public double x;
	public double y;
	protected double dx;
	protected double dy;
	protected int width;
	protected int height;
	
	public Entity() { }
	
	public int getX() { return (int)x; }
	public int getY() { return (int)y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public boolean intersects(Entity e)
	{
		Rectangle r1 = getRectangle();
		Rectangle r2 = e.getRectangle();
		
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle()
	{
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public void setPosition(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public void setVector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public abstract void draw(Graphics2D g);
}
