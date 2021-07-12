import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Screen extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private Container container;
	private JLabel sky;
	private JLabel floor1;
	private JLabel floor2;
	
	Screen() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(1200, 600);
		//this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.container = this.getContentPane();
		this.container.setLayout(null);
	}

	public void createVisualElements(Character character) {
		floor1 = new JLabel("", new ImageIcon(getClass().getResource("/media/land.png")), JLabel.CENTER);
		floor1.setBounds(0, 400, 2400, 200);
		
		floor2 = new JLabel("", new ImageIcon(getClass().getResource("/media/land.png")), JLabel.CENTER);
		floor2.setBounds(2400, 400, 2400, 200);
		
		sky = new JLabel("", new ImageIcon(getClass().getResource("/media/sky.png")), JLabel.CENTER);
		sky.setBounds(0, 0, 1200, 514);
		
		addElements(character);
	}
	
	public void addElements(Character character) {
		container.add(character.getLbImg());
		container.add(floor1);
		container.add(floor2);
		container.add(sky);
	}
	
	public void addElement(Element element) {
		container.add(element.getLbImg());
	}

	public Container getContainer() {
		return container;
	}
	
	public void setContainer(Container container) {
		this.container = container;
	}

	public JLabel getSky() {
		return sky;
	}

	public void setSky(JLabel sky) {
		this.sky = sky;
	}

	public JLabel getFloor1() {
		return floor1;
	}

	public void setFloor1(JLabel floor) {
		this.floor1 = floor;
	}

	public JLabel getFloor2() {
		return floor2;
	}

	public void setFloor2(JLabel floor2) {
		this.floor2 = floor2;
	}
}
