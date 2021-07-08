import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Element {
	private Icon img;
	private JLabel lbImg;
	
	Element() {
		
	}
	
	Element(String imagePath) {
		img = new ImageIcon(getClass().getResource(imagePath));
		setLbImg(new JLabel("", img, JLabel.CENTER));
	}
	
	public JLabel getLbImg() {
		return this.lbImg;
	}

	public void setLbImg(JLabel lbImg) {
		this.lbImg = lbImg;
	}
	
	public Icon getImg() {
		return this.img;
	}
	
	public void setImg(Icon img) {
		this.img = img;
	}
}