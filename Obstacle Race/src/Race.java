import java.util.ArrayList;

public class Race implements Game{
	Screen screen;
	Character character;
	ArrayList <Obstacle> obstacles;
	//associação de agregação
	
	Race() {
		character = new Character("/media/Luffy.gif");
		character.getLbImg().setBounds(100, 250, 200, 200);
		
		this.screen = new Screen();	
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
}
