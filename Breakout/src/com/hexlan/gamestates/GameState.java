package com.hexlan.gamestates;

import java.awt.Graphics2D;
import com.hexlan.handlers.GSM;
import com.hexlan.Game;

public abstract class GameState 
{
	protected GSM gsm;
	protected Game game;
	
	protected GameState(GSM gsm) 
	{
		this.gsm = gsm;
		game = gsm.getGame();
	}
	
	public abstract void handleInput();
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void dispose();
}
