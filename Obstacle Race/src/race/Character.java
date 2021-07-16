package race;
import java.util.Timer;
import java.util.TimerTask;

public class Character extends Element {
	private String imgPath;
	private int x;
	private int y;
	private int maxY, minY;
	private int velocityY;
	private int gravity;
	
	private int score;
	private int record;

	Character(String imgPath, int x, int y, int minY) {
		super(imgPath);
		
		this.getLbImg().setSize(this.getLbImg().getIcon().getIconWidth(), this.getLbImg().getIcon().getIconHeight());
		
		this.setImgPath(imgPath);
		this.x = x;
		this.y = y;
		this.minY = minY;
		this.maxY = y;
		this.velocityY = 0;
		this.gravity = 2;
		this.score = 0;
		this.record = 0;
	}
	
	public void jump_unused() {
	
		if(getLbImg().getY() == maxY) {
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
	    					
	    					if(getLbImg().getY() >= maxY) {
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
	
	public void jump() {
		if(this.y == this.maxY) {
			this.velocityY = -30;
		}
	}
	
	public void move() {
		if(this.y + this.velocityY <= this.maxY && this.y + this.velocityY >= this.minY) {
			this.y += this.velocityY;
			this.velocityY += this.gravity;
		}
		
		this.getLbImg().setLocation(this.x, this.y);
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

	public int getyInitial() {
		return maxY;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
