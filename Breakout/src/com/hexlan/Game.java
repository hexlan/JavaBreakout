package com.hexlan;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.hexlan.handlers.GSM;
import com.hexlan.handlers.Input;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
	public static int level = 4;
	public static int lives = 3;
	
	public static MouseEvent[] mouse = new MouseEvent[10];
	public static MouseEvent[] mouseMotion = new MouseEvent[10];
	
	// Dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	// Game Thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// Image
	private BufferedImage image;
	private Graphics2D g;
	
	// Gamestate Manager
	private GSM gsm;
	
	// Input
	public static Input input;
	
	public Game()
	{
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() 
	{
		super.addNotify();
		if(thread == null) 
		{
			thread = new Thread(this);
			addKeyListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
			thread.start();
		}
	}
	
	private void init()
	{
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		running = true;	
		gsm = new GSM(this);
		input = new Input();
	}
	
	public void run() 
	{
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// Game Loop
		while(running) 
		{
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			try 
			{
				Thread.sleep(wait);
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private void update()
	{
		gsm.update();
		input.update();
	}
	private void draw() 
	{
		gsm.render(g);
	}
	
	private void drawToScreen() 
	{
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) 
	{
		if(key.getKeyCode() == KeyEvent.VK_UP) Game.input.setKey(Input.UP, true);
		if(key.getKeyCode() == KeyEvent.VK_DOWN) Game.input.setKey(Input.DOWN, true);
		if(key.getKeyCode() == KeyEvent.VK_LEFT) Game.input.setKey(Input.LEFT, true);
		if(key.getKeyCode() == KeyEvent.VK_RIGHT) Game.input.setKey(Input.RIGHT, true);
		if(key.getKeyCode() == KeyEvent.VK_SPACE) Game.input.setKey(Input.SPACE, true);
		if(key.getKeyCode() == KeyEvent.VK_ENTER) Game.input.setKey(Input.ENTER, true);
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE) Game.input.setKey(Input.ESCAPE, true);
	}
	public void keyReleased(KeyEvent key) 
	{
		if(key.getKeyCode() == KeyEvent.VK_UP) Game.input.setKey(Input.UP, false);
		if(key.getKeyCode() == KeyEvent.VK_DOWN) Game.input.setKey(Input.DOWN, false);
		if(key.getKeyCode() == KeyEvent.VK_LEFT) Game.input.setKey(Input.LEFT, false);
		if(key.getKeyCode() == KeyEvent.VK_RIGHT) Game.input.setKey(Input.RIGHT, false);
		if(key.getKeyCode() == KeyEvent.VK_SPACE) Game.input.setKey(Input.SPACE, false);
		if(key.getKeyCode() == KeyEvent.VK_ENTER) Game.input.setKey(Input.ENTER, false);
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE) Game.input.setKey(Input.ESCAPE, false);
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//level = 10;
		for(int i = 0; i < 10; i++)
		{
			if(mouse[i] == null)
			{
				mouse[i] = e;
				break;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 10; i++)
		{
			if(mouseMotion[i] == null)
			{
				mouseMotion[i] = e;
				break;
			}
		}
	}
}
