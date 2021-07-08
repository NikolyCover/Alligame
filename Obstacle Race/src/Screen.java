import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Screen extends JFrame{
	private Container container;
	JLabel sky;
	JLabel floor;
	
	Screen() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(1200, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		container = this.getContentPane();
		container.setLayout(null);
	}
	
	public Container getContainer() {
		return this.container;
	}
	
	public void createVisualElements(Character character) {
		floor = new JLabel("", new ImageIcon(getClass().getResource("/media/land.png")), JLabel.CENTER);
		floor.setBounds(0, 400, 2400, 200);
		
		sky = new JLabel("", new ImageIcon(getClass().getResource("/media/sky.png")), JLabel.CENTER);
		sky.setBounds(0, 0, 1200, 514);
		
		this.container.add(character.getLbImg());
		this.container.add(floor);
		this.container.add(sky);
	}
	
	public void addElement(Element element) {
		container.add(element.getLbImg());
	}
}
