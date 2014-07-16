package com.hexlan.handlers;

public class Input 
{
	private boolean[] keys;
	private boolean[] pkeys;
	
	public static final int NUM_KEYS = 7;
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int SPACE = 4;
	public static final int ENTER = 5;
	public static final int ESCAPE = 6;
	
	public Input() 
	{
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	public void update() 
	{
		for(int i = 0; i < NUM_KEYS; i++) 
		{
			pkeys[i] = keys[i];
		}
	}
	
	public void setKey(int i, boolean b) { keys[i] = b; }
	public boolean isDown(int i) { return keys[i]; }
	public boolean isPressed(int i) { return keys[i] && !pkeys[i]; }
	public boolean isReleased(int i) { return !keys[i] && pkeys[i]; }
}
