package race;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Predicate;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Race implements Game, KeyListener{
	private int delay = 10;
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
	
	private final int characterX = 80;
	private final int characterY = 280;
	private final String characterImgPath = "/media/Luffy.gif";
	
	private boolean isRunning; //true if character is running
		
	Race() {
		obstacles = new ArrayBlockingQueue<Obstacle>(10);
		
		character = new Character(characterImgPath, characterX, characterY);
			
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
        			boolean alredyOver; //não deixa o final acontecer mais de uma vez
        			
        			@Override
        			public void run() {

        				if(!thereWasACollision()) {
        					isRunning = true;
        					
        					if(alredyOver) {
        						reset();
        					}
        					        					        					
        					alredyOver = false;
        					
        					moveFloor();
            				
            				if(!obstacles.isEmpty()) {
            					moveObstacle();
            					removeUnusedObstacles();
            					
            				}
            				
            				if(obstacles.size() < 1) {
            					createObstacle();
            				}
            				            				
        				} else if(thereWasACollision() && !alredyOver) {
        					isRunning = false;
        					updateRecord();
        					gameOver();
        					alredyOver = true;
        				}
        				
        			}
        		}, 0, delay);
        
        timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(isRunning) {
					updateScore();
				}
			}
		}, 0, 100);
    }
	
	public void updateScore() {
		character.setScore(character.getScore() + 1);
		
		String score_label = "";
		
		for(int i = 0 ; i < 4; i++) {
			score_label += screen.getScore().getText().charAt(i);
		}
		
		screen.getScore().setText(score_label + String.format("%04d", character.getScore()));
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
		
		obstacles.add(obs);
		screen.obtainContainer().add(obs.getLbImg(), 1);
		
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

	public boolean thereWasACollision() {
		int crt_x1 = character.getLbImg().getX();
		int crt_x2 = character.getLbImg().getX() + character.getLbImg().getWidth();
		//int crt_y1 = character.getLbImg().getY();
		int crt_y2 = character.getLbImg().getY() + character.getLbImg().getHeight();
		
		for(Obstacle obs : obstacles) {
			int obs_x1 = obs.getLbImg().getX();
			int obs_x2 = obs.getLbImg().getX() + obs.getLbImg().getWidth();
			int obs_y1 = obs.getLbImg().getY();
			int obs_y2 = obs.getLbImg().getY() + obs.getLbImg().getHeight();
			
			if((obs_x1 > crt_x1 && obs_x1 < crt_x2) && (crt_y2 <= obs_y2 && crt_y2 >= obs_y1)) {
				return true;
			}
			
			else if((crt_x1 > obs_x1 && crt_x1 < obs_x2) && (crt_y2 <= obs_y2 && crt_y2 >= obs_y1)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void gameOver() {
		
		JLabel explosion = new JLabel("", new ImageIcon(getClass().getResource("/media/explosion.gif")), JLabel.CENTER);
		explosion.setBounds(0, character.getLbImg().getY() - (character.getImg().getIconHeight() / 2), explosion.getIcon().getIconWidth(), explosion.getIcon().getIconHeight());
		
		screen.obtainContainer().add(explosion, 0);
		character.getLbImg().setIcon(new ImageIcon(getClass().getResource("/media/alligator.png")));
		character.getLbImg().setSize(character.getLbImg().getIcon().getIconWidth(), character.getLbImg().getIcon().getIconHeight());

		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		screen.obtainContainer().remove(explosion);
	}

	public void reset() {
		character.getLbImg().setIcon(new ImageIcon(getClass().getResource(characterImgPath)));
		character.getLbImg().setBounds(characterX, characterX, character.getLbImg().getIcon().getIconWidth(), character.getLbImg().getIcon().getIconHeight());
		character.setScore(0);
	}
	
	public void updateRecord() {
		if(character.getScore() > character.getRecord()) {
			character.setRecord(character.getScore());
		}
		
		String record_label = "";
		
		for(int i = 0 ; i < 4; i++) {
			record_label += screen.getRecord().getText().charAt(i);
		}
		
		screen.getRecord().setText(record_label + String.format("%04d", character.getRecord()));
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