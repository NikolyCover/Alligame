import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Race implements Game{
	Screen screen;
	Character character;
	ArrayList <Obstacle> obstacles;
	//associação de agregação
	
	Race() {
		character = new Character("/media/Luffy.gif");
		character.getLbImg().setBounds(20, 250, 200, 200);
		
		screen = new Screen();	
		screen.createVisualElements(character);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	public void createObstacles() {
		
	}
	
	public void checkCollision() {
		
	}
	
	public void run() {		
        Timer timer = new Timer();
        
    	timer.scheduleAtFixedRate(
    		new TimerTask() {
    			public void run() {
    				moveFloor();
    			}
    		}, 0, 20);
    }
	
	public void moveFloor() {
		int x1 = screen.getFloor1().getX();
		int x2 = screen.getFloor2().getX();
		
		screen.getFloor1().setBounds(x1 - 10, 400, 2400, 200);
		screen.getFloor2().setBounds(x2 - 10, 400, 2400, 200);
		
		if(screen.getFloor1().getX() == 0 || screen.getFloor2().getX() == 0) {
			if(screen.getFloor1().getX() == 0) {
				screen.getFloor2().setBounds(2400, 400, 2400, 2400);
			} else {
				screen.getFloor1().setBounds(2400, 400, 2400, 2400);
			}
		}
	}
}
