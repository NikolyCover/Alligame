import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main{
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1200, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		Container container;
		container = frame.getContentPane();
		container.setLayout(null);
		
		Character luffy = new Character("/media/luffy_direita_menor.gif");
		luffy.getLbImg().setBounds(0, 0, 200, 200);
		
		Obstacle vaccine = new Obstacle("/media/vaccine.png");
		vaccine.getLbImg().setBounds(430, 0, 30, 133);
		
		container.add(luffy.getLbImg());
		container.add(vaccine.getLbImg());
		
		frame.setVisible(true);
	}
}