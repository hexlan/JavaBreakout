package com.hexlan.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

import com.hexlan.handlers.GSM;
import com.hexlan.handlers.Input;
import com.hexlan.Game;
import com.hexlan.entities.*;

public class PlayState extends GameState
{
	public static final int ROWS = 11; //13
	public static final int COLS = 7; //7
	private double speed = 2;
	
	int mouseX = 0;
	int mouseY = 0;
	
	public static Brick[][] bricks = new Brick[ROWS][COLS];
	
	public static Paddle player;
	private Ball ball;
	
	boolean allowed = true;
	
	public PlayState(GSM gsm)
	{
		super(gsm);
		init();
	}
	
	private void init()
	{
		ball = new Ball();
		player = new Paddle();
		
		// Initialize Bricks
		for(int i = 0; i < ROWS; i++)
		{
			for(int j = 0; j < COLS; j++)
			{
				
				bricks[i][j] = new Brick(i*26+20, j*16+30);
				switch(Game.level % 5)
				{
					case 1:
						if(i%2 == j%2)
							bricks[i][j].kill();
						break;
					case 2:
						if(i%2 == 0)
							bricks[i][j].kill();
						break;
					case 3:
						if(j%2 == 0)
							bricks[i][j].kill();
						break;
					case 4:
						if(j != 0 && j != COLS-1 && i != 0 && i != ROWS-1)
							bricks[i][j].kill();
						break;
						default:
							break;
				}
			}
		}
	}
	
	public void handleInput()
	{
		for(int i = 0; i < 10; i++)
		{
			if(Game.mouse[i] != null)
			{
				
				for(int k = 0; k < ROWS; k++)
				{
					for(int l = 0; l < COLS; l++)
					{
						if(bricks[k][l].getRectangle().contains(Game.mouse[i].getX()/2, Game.mouse[i].getY()/2)) bricks[k][l].kill();
					}
				}
				
				Game.mouse[i] = null;
			}
		}
		
		for(int i = 0; i < 10; i++)
		{
			if(Game.mouseMotion[i] != null)
			{
				mouseX = Game.mouseMotion[i].getX();
				mouseY = Game.mouseMotion[i].getY();
				player.x = mouseX/2 - player.getWidth()/2;
				if(player.x < 10) player.x = 10;
				if(player.x + player.getWidth() > Game.WIDTH - 10) player.x = Game.WIDTH - (10 + player.getWidth());
				Game.mouseMotion[i] = null;
			}
		}
		
		if(game.input.isPressed(Input.ESCAPE))
		{
			System.exit(0);
		}
		if(game.input.isPressed(Input.ENTER))
		{
			gsm.setState(gsm.TITLE);
		}
		if(game.input.isPressed(Input.SPACE))
		{
			allowed = !allowed;
		}
		if(game.input.isPressed(Input.UP))
		{
			for(int i = 0; i < ROWS; i++)
			{
				for(int j = 0; j < COLS; j++)
				{
					bricks[i][j].kill();
				}
			}
		}
		if(game.input.isPressed(Input.DOWN))
		{
			Game.level--;
			gsm.setState(gsm.PLAY);
		}
		player.HandleInput();
	}
	public void update()
	{
		if(allowed)ball.update(speed);
		
		if(ball.getY()+ball.getHeight() >= (Game.HEIGHT))
		{
			Game.lives--;
			if(Game.lives < 0)
			{
				Game.lives = 3;
				gsm.setState(gsm.TITLE);
			}
			ball = new Ball();
		}
		
		player.update();
		
		boolean winFlag = true;
		
		for(int i = 0; i < ROWS; i++)
		{
			for(int j = 0; j < COLS; j++)
			{
				if(bricks[i][j].getState())
				{
					winFlag = false;
				}
			}
		}
		
		if(winFlag)
		{
			Game.level++;
			if(Game.level > 4)
			{
				Game.level = 0;
				gsm.setState(gsm.TITLE);
			}
			else
				gsm.setState(gsm.PLAY);
		}
	}
	
	public void render(Graphics2D g)
	{
		g.setColor(new Color(248, 233, 213));
		g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
		
		g.setColor(new Color(61, 64, 72));
		g.fillRect(0,0, Game.WIDTH, 11);
		g.fillRect(0,0, 11, Game.HEIGHT);
		g.fillRect(Game.WIDTH-11,0, 10, Game.HEIGHT);
		
		g.setColor(new Color(170, 117, 112));
		g.fillRect(0,0, Game.WIDTH, 10);
		g.fillRect(0,0, 10, Game.HEIGHT);
		g.fillRect(Game.WIDTH-10,0, 10, Game.HEIGHT);
		
		
		for(int i = 0; i < ROWS; i++)
		{
			for(int j = 0; j < COLS; j++)
			{
				if(bricks[i][j].isAlive)
				{
					bricks[i][j].draw(g);
				}
			}
		}
		
		ball.draw(g);
		for(int i = 0; i < Game.lives; i++)
		{
			g.setColor(new Color(61, 64, 72));
			g.fillOval(i*10+11, 2, 7, 7);
			g.setColor(new Color(165, 197, 175));
			g.fillOval(i*10+10, 1, 7, 7);
		}
		
		player.draw(g);
		
		g.setFont(new Font("Monospace", Font.PLAIN, 8));
		g.setColor(new Color(248, 233, 213));
		g.drawString("Level: "+Game.level, 280, 9);
		
//		g.setColor(new Color(60, 60, 60));
//		g.drawString("X: "+mouseX, 30, 200);
//		g.drawString("Y: "+mouseY, 30, 212);
	}
	public void dispose()
	{
		
	}
}
