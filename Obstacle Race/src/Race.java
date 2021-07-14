import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Predicate;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Race implements Game, KeyListener{
	private Screen screen;
	private Character character;

	Obstacle obstacleTemplates[] = {
			new Obstacle("/media/obstacles/vaccine_small.png"),
			new Obstacle("/media/obstacles/vaccine_medium.png"),
			new Obstacle("/media/obstacles/vaccine_large.png"),
			new Obstacle("/media/obstacles/vaccines_small.png"),
			new Obstacle("/media/obstacles/vaccines_medium.png"),
			new Obstacle("/media/obstacles/vaccines_wide.png"),
			//new Obstacle("/media/obstacles/vaccines_large.png")
	};
	
	ArrayBlockingQueue<Obstacle> obstacles;
	
	Race() {
		obstacles = new ArrayBlockingQueue<Obstacle>(10);
		
		character = new Character("/media/Luffy.gif", 80, 280);
			
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
        
    	
    	timer.schedule(
        		new TimerTask() {
        			
        			public void run() {
        				
        				moveFloor();
        				
        				if(!obstacles.isEmpty()) {
        					moveObstacle();
        					removeUnusedObstacles();
        				}
        				
        				if(obstacles.size() < 1) {
        					createObstacle();
        				}
        				
        			}
        		}, 0, 10);

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
		
		Obstacle obs = obstacleTemplates[rnd.nextInt(obstacleTemplates.length)];
		
		obs.getLbImg().setBounds(1200, obs.yToBaseAlign(character, character.getyInitial()), obs.getImg().getIconWidth(), obs.getImg().getIconHeight());
		Border blackline = BorderFactory.createLineBorder(Color.black);
		obs.getLbImg().setBorder(blackline);
		
		obstacles.add(obs);
		screen.obtainContainer().add(obs.getLbImg(), 0);
		
	}
	
	public void moveObstacle() {
		for(Obstacle obs : obstacles) {
			obs.getLbImg().setLocation(obs.getLbImg().getX() - 10, obs.getLbImg().getY());
		}
	}
	
	public void removeUnusedObstacles() {
		Obstacle obs = someObstacleCameOut();
		
		if(obs != null) {
			obstacles.remove(obs);
			screen.obtainContainer().remove(obs.getLbImg());
		}
	}
	
	public Obstacle someObstacleCameOut() {
		Predicate<Obstacle> condition = (obs) -> obs.getLbImg().getX() <= (0 - obs.getLbImg().getWidth());
		
		for(Obstacle obs : obstacles) {
			if(condition.test(obs)) {
				return obs;
			}
		}
		
		return null;
	}

	public void checkCollision() {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key ==  KeyEvent.VK_SPACE) {
			character.jump();
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Screen getScreen() {
		return screen;
	}
}
