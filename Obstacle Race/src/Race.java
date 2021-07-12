import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Race implements Game, KeyListener{
	Screen screen;
	Character character;
	Obstacle obstacleTemplates[] = {
			new Obstacle("/media/obstacles/vaccine_small.png"),
			new Obstacle("/media/obstacles/vaccine_medium.png"),
			new Obstacle("/media/obstacles/vaccine_large.png"),
			new Obstacle("/media/obstacles/vaccines_small.png"),
			new Obstacle("/media/obstacles/vaccines_medium.png"),
			new Obstacle("/media/obstacles/vaccines_wide.png"),
			new Obstacle("/media/obstacles/vaccines_large.png")
	};
	ArrayList <Obstacle> obstacles;
	//associação de agregação
	
	Race() {
		character = new Character("/media/Luffy.gif");
		
		obstacles = new ArrayList<Obstacle>();
		
		screen = new Screen();	
		screen.createVisualElements(character);
		screen.addKeyListener(this);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	public void run() {		
        Timer timer = new Timer();
    	        
    	timer.scheduleAtFixedRate(
    		new TimerTask() {
    			public void run() {
    				createObstacle();
    			}
    		}, 0, 1200);
    	
    	timer.scheduleAtFixedRate(
        		new TimerTask() {
        			public void run() {
        				moveFloor();
        				moveObstacles();
        				DiscardObstacleUnused();
        			}
        		}, 0, 20);
    }
	
	public void moveFloor() {
		int x1 = screen.getFloor1().getX();
		int x2 = screen.getFloor2().getX();
		
		screen.getFloor1().setLocation(x1 - 10, screen.getFloor1().getY());
		screen.getFloor2().setLocation(x2 - 10, screen.getFloor2().getY());
		
		if(screen.getFloor1().getX() == 0 || screen.getFloor2().getX() == 0) {
			if(screen.getFloor1().getX() == 0) {
				screen.getFloor2().setLocation(screen.getFloor1().getWidth(), screen.getFloor2().getY());
			} else {
				screen.getFloor1().setLocation(screen.getFloor2().getWidth(), screen.getFloor1().getY());
			}
		}
	}
	

	public void createObstacle() {
		Random rnd = new Random();
		
		obstacles.add(obstacleTemplates[rnd.nextInt(obstacleTemplates.length)]);
		
		JLabel obstacle = obstacles.get(obstacles.size() - 1).getLbImg();
		obstacle.setBounds(1200, 280, obstacle.getIcon().getIconWidth(), obstacle.getIcon().getIconHeight());
		//deixar altura não estática

		screen.getContainer().add(obstacle);
		screen.getContainer().setComponentZOrder(obstacle, 1);
	}
	
	public void moveObstacles() {
		for(Obstacle obs : obstacles) {
			obs.getLbImg().setLocation(obs.getLbImg().getX() - 10, obs.getLbImg().getY());
		}
	}
	
	public void DiscardObstacleUnused() {
		for(Obstacle obs : obstacles) {
			if(obs.getLbImg().getX() <=  0) {
				obstacles.remove(obs);
			}		
		}
	}
	
	public void checkCollision() {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key ==  KeyEvent.VK_SPACE) {
			character.getLbImg().setIcon(new ImageIcon(getClass().getResource("/media/Luffy.png")));
			character.jump();
			character.getLbImg().setIcon(new ImageIcon(getClass().getResource("/media/Luffy.gif")));
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}}
