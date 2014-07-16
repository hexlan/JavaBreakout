package com.hexlan.handlers;

import java.awt.Color;
import java.awt.Graphics2D;

import com.hexlan.Game;
import com.hexlan.gamestates.*;

public class GSM 
{
	private Game game;
	
	private GameState gameState;
	public static final int PLAY = 0;
	public static final int TITLE = 1;
	
	public GSM(Game game)
	{
		this.game = game;
		setState(TITLE);
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void setState(int i)
	{
		if(gameState != null)
		{
			gameState.dispose();
		}
		if(i == PLAY)
		{
			gameState = new PlayState(this);
		}
		if(i == TITLE)
		{
			gameState = new TitleState(this);
		}
	}
	
	public void update() 
	{
		gameState.update();
		gameState.handleInput();
	}
	
	public void render(Graphics2D g) 
	{
		g.setColor(Color.black);
		g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
		gameState.render(g);
	}
}
