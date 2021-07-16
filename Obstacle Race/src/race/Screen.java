package race;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

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
	private JLabel record;
	private JLabel score;

	Font pressStart;

	Screen(String name) {
		super(name);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(1200, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		this.container = this.getContentPane();
		this.container.setLayout(null);
		
		font();
	}
	
	public void font() {
		try {
			pressStart = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("fonts/PressStart2P-Regular.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void createVisualElements(Character character) {
		floor1 = new JLabel(new ImageIcon(getClass().getResource("/media/land.png")), JLabel.CENTER);
		floor1.setBounds(0, 400, floor1.getIcon().getIconWidth(), floor1.getIcon().getIconHeight());
		
		floor2 = new JLabel(new ImageIcon(getClass().getResource("/media/land.png")), JLabel.CENTER);
		floor2.setBounds(2400, 400, floor1.getIcon().getIconWidth(), floor1.getIcon().getIconHeight());
		
		sky = new JLabel(new ImageIcon(getClass().getResource("/media/sky.png")), JLabel.CENTER);
		sky.setBounds(0, 0, sky.getIcon().getIconWidth(), sky.getIcon().getIconHeight());
		
		record = new JLabel("RC: 0000");
		record.setBounds(860, 0, 200, 100);
		record.setFont(new Font("Sans-serif", 1, 32));
		
		score = new JLabel("SC: 0000");
		score.setBounds(1030, 0, 200, 100);
		score.setFont(new Font("Sans-serif", 1, 32));
		
		addElements(character);
	}
	
	public void addElements(Character character) {
		container.add(character.getLbImg());
		container.add(record);
		container.add(score);
		container.add(floor1);
		container.add(floor2);
		container.add(sky);
	}
	
	public void addElement(Element element) {
		container.add(element.getLbImg());
	}

	public Container obtainContainer() {
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

	public JLabel getRecord() {
		return record;
	}

	public void setRecord(JLabel record) {
		this.record = record;
	}

	public JLabel getScore() {
		return score;
	}

	public void setScore(JLabel score) {
		this.score = score;
	}
}
