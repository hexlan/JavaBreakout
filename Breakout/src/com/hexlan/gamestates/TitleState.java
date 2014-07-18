package com.hexlan.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.hexlan.handlers.GSM;
import com.hexlan.handlers.Input;
import com.hexlan.Game;

public class TitleState extends GameState
{
	int counter = 0;
	int state = 0;
	public TitleState(GSM gsm)
	{
		super(gsm);
	}
	
	public void handleInput()
	{
		if(game.input.isPressed(Input.ENTER) || game.input.isPressed(Input.SPACE))
		{
			gsm.setState(GSM.PLAY);
		}
		if(game.input.isPressed(Input.ESCAPE))
		{
			System.exit(0); 
		}
		if(game.input.isPressed(Input.UP))
		{
			Game.level++; 
		}
		if(game.input.isPressed(Input.DOWN))
		{
			Game.level--; 
		}
	}
	public void update()
	{
		if(counter<30)
		{
			counter++;
		}
		else
		{
			counter = 0;
			state++;
			if(state > 2 )
			{
				state = 1;
			}
		}
	}
	public void render(Graphics2D g)
	{
		if(state == 0){g.setColor(Color.black);};
		if(state == 1){g.setColor(Color.blue);};
		if(state == 2){g.setColor(Color.white);};
		
		g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
		
		g.setFont(new Font("Monospace", Font.PLAIN, 26)); 
		
		g.setColor(new Color(170, 117, 112));
		g.drawString("Breakout", Game.WIDTH/2-41, Game.HEIGHT/2-5);
		g.drawString("Breakout", Game.WIDTH/2-41, Game.HEIGHT/2-6);
		g.drawString("Breakout", Game.WIDTH/2-40, Game.HEIGHT/2-6);
		
		g.setColor(new Color(248, 233, 213));
		g.drawString("Breakout", Game.WIDTH/2-40, Game.HEIGHT/2-5);
	}
	public void dispose()
	{
		
	}
}
