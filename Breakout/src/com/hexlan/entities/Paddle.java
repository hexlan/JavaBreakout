package com.hexlan.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import com.hexlan.Game;
import com.hexlan.handlers.Input;

public class Paddle extends Entity
{
	private boolean left;
	private boolean right;
	
	public Paddle()
	{
		left = false;
		right = false;
		
		x = Game.WIDTH/2 - 22;
		y = Game.HEIGHT - 20;
		
		width = 44; //44
		height = 7;
	}
	
	public void HandleInput()
	{
		if(Game.input.isDown(Input.LEFT))
		{
			left = true;
		}
		if(Game.input.isDown(Input.RIGHT))
		{
			right = true;
		}
		
		if(Game.input.isReleased(Input.LEFT))
		{
			left = false;
		}
		if(Game.input.isReleased(Input.RIGHT))
		{
			right = false;
		}
	}
	
	public void update()
	{
		if(left)
		{
			x -= 4;
			if(x < 10)
			{
				x = 10;
			}
		}
		if(right)
		{
			x += 4;
			
			if(x + width > Game.WIDTH - 10)
			{
				x = Game.WIDTH - 10 - width;
			}
		}
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(new Color(61, 64, 72));
		g.fillRect((int)x+1, (int)y+1, width, height);
		g.setColor(new Color(170, 117, 112));
		g.fillRect((int)x, (int)y, width, height);
	}
}
