package race;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Element {
	private Icon img;
	private JLabel lbImg;
	
	Element(String imagePath) {
		img = new ImageIcon(getClass().getResource(imagePath));
		setLbImg(new JLabel("", img, JLabel.CENTER));
	}
	
	//retorna o y que um elemento deve ter para que fique alinhado na base de um elemento referencia
	public int yToBaseAlign(Element reference, int yRef) {
		int y = (yRef + reference.getLbImg().getHeight()) - this.getImg().getIconHeight();
		 
		return y;
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