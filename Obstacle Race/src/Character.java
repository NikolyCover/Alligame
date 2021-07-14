import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Character extends Element {
	private String name;
	private int score;
	private int record;
	private int x = 80, y = 280;

	Character(String imgPath) {
		super(imgPath);
		getLbImg().setBounds(x, y, getLbImg().getIcon().getIconWidth(), getLbImg().getIcon().getIconHeight());
		Border blackline = BorderFactory.createLineBorder(Color.black);
		getLbImg().setBorder(blackline);
	}
	
	public void jump() {
	
		if(getLbImg().getY() == y) {
			Timer timer = new Timer();
	        
	    	timer.scheduleAtFixedRate(
	    		new TimerTask() {
	    			private boolean falling = false;
	    			
	    			public void run() {
	    				if(!isFalling()) {
	    					getLbImg().setLocation(getLbImg().getX(), getLbImg().getY() - 1);
	    					
	    					if(getLbImg().getY() <= 25) {
	    						setFalling(true);
	    					}
	    				}
	    				
	    				else if(isFalling()) {
	    					getLbImg().setLocation(getLbImg().getX(), getLbImg().getY() + 1);
	    					
	    					if(getLbImg().getY() >= y) {
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
	
	public int getY() {
		return y;
	}
	
}
