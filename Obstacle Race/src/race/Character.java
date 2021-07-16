package race;

public class Character extends Element {
	private String imgPath;
	private int x;
	private int y;
	private int maxY, minY;
	private int velocityY;
	private int gravity;
	private int width, height;
	
	private int score;
	private int record;

	Character(String imgPath, int x, int y, int minY) {
		super(imgPath);
				
		this.setImgPath(imgPath);
		this.x = x;
		this.y = y;
		this.minY = minY;
		this.maxY = y;
		this.velocityY = 0;
		this.gravity = 2;
		this.score = 0;
		this.record = 0;
		this.width = this.getLbImg().getIcon().getIconWidth();
		this.height = this.getLbImg().getIcon().getIconHeight();
		
		this.getLbImg().setSize(this.width, this.height);
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

	public int getHeight() {
		return height;
	}
	
}
