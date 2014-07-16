package com.hexlan.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import com.hexlan.Game;
import com.hexlan.gamestates.PlayState;

public class Ball extends Entity
{	
	public Ball()
	{
		x = Game.WIDTH/2 - 5;
		y = (Game.HEIGHT * 3)/4;
		
		width = 5;
		height = 5;
		
		dx = 1;
		dy = -1;
	}
	
	public void update(double speed)
	{
		boolean reverseYflag = false;
		boolean reverseXflag = false;
		
		x += dx*speed;
		
		if(x+width >= Game.WIDTH-10 || x <= 10)
		{
			reverseX();
		}
		
		for(int i = 0; i < PlayState.ROWS; i++)
		{
			for(int j = 0; j < PlayState.COLS; j++)
			{
				if(PlayState.bricks[i][j].getState())
				{
					if(intersects(PlayState.bricks[i][j]))
					{
						if(!reverseXflag)
						{
							reverseX();
						}
						reverseXflag = true;
						PlayState.bricks[i][j].kill();
					}
				}
			}
		}
		
		y += dy*speed;
		
		if(y <= 10)
		{
			reverseY();
		}
		
		for(int i = 0; i < PlayState.ROWS; i++)
		{
			for(int j = 0; j < PlayState.COLS; j++)
			{
				if(PlayState.bricks[i][j].getState())
				{
					if(intersects(PlayState.bricks[i][j]))
					{
						if(!reverseYflag)
						{
							reverseY();
						}
						reverseYflag = true;
						PlayState.bricks[i][j].kill();
					}
				}
			}
		}
		
		if(intersects(PlayState.player))
		{
			reverseY();
			y = PlayState.player.getY() - 10;
		}
	}
	
	public void reverseX()
	{
		dx *= -1;
	}
	
	public void reverseY()
	{
		dy *= -1;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(new Color(61, 64, 72));
		g.fillOval((int)x+1, (int)y+1, width, height);
		g.setColor(new Color(165, 197, 175));
		g.fillOval((int)x, (int)y, width, height);
	}
}
