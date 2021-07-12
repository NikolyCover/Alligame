import java.util.Timer;
import java.util.TimerTask;

public class Character extends Element {
	private String name;
	private int score;
	private int record;
	
	Character(String imgPath) {
		super(imgPath);
		getLbImg().setBounds(20, 250, 200, 200);
	}
	
	public void jump() {
	
		Timer timer = new Timer();
        
    	timer.scheduleAtFixedRate(
    		new TimerTask() {
    			private boolean falling = false;
    			
    			public void run() {
    				if(!isFalling()) {
    					getLbImg().setLocation(getLbImg().getX(), getLbImg().getY() - 1);
    					
    					if(getLbImg().getY() <= 80) {
    						setFalling(true);
    					}
    				}
    				
    				else if(isFalling()) {
    					getLbImg().setLocation(getLbImg().getX(), getLbImg().getY() + 1);
    					
    					if(getLbImg().getY() >= 250) {
    						timer.cancel();
    					}
    				}
    			}
    			
				public boolean isFalling() {
					return falling;
				}
				
				public void setFalling(boolean falling) {
					this.falling = falling;
				}
    		}, 0, 2);
    	
    }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public int getRecord() {
		return record;
	}

	public void setRecord(int record) {
		this.record = record;
	}
	
}
