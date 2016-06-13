package gui;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Dado {
	
	
	private String path1 = "/Dice1.png";
	private String path2 = "/Dice2.png";
	private String path3 = "/Dice3.png";
	private String path4 = "/Dice4.png";
	private String path5 = "/Dice5.png";
	private String path6 = "/Dice6.png";
	
	private URL file[];
	
	private JLabel label;
	private BufferedImage img;
	private JButton button;
	private int valorDado = 0;
	public int rolagem;

	public Dado() {	
		label = new JLabel();
		button = new JButton("Jogar Dado!");
		button.setLayout(null);
		button.setSize(150, 75);
		button.setBounds(770, 20, 150, 75);
		button.setVisible(true);
		label.setBounds(770, 100, 204, 202);
		button.setEnabled(false);

		file = new URL[6];

		file[0] = this.getClass().getResource(path1);
		file[1] = this.getClass().getResource(path2);
		file[2] = this.getClass().getResource(path3);
		file[3] = this.getClass().getResource(path4);
		file[4] = this.getClass().getResource(path5);
		file[5] = this.getClass().getResource(path6);

		
	}
	public URL[] getDadoImg() {
		return file;
	}

	public JButton getButton(){
		return button;
	}

	public JLabel getLabel() {
		return label;
	}


	public int rollDice() {
		System.out.println("The Dice are Rolling!");
		Random rand = new Random();
		int val = 0;
		for(int i = 0; i < 10; i++) {

			try {
				val = rand.nextInt(6);
				val = rand.nextInt(6); // para aumentar o randomismo
				img = ImageIO.read(file[val]);
				label.setIcon(new ImageIcon(img));

				
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}

		}
		setValorDado(val + 1);
		return getValorDado();
	}

	

	public int getValorDado() {
		return valorDado;
	}

	public void setValorDado(int valorDado) {
		this.valorDado = valorDado;
	}

	public void setRolagem(int rolagem, int flag){
		rolagem = flag;
	}

	public int getRolagem(){
		return rolagem;
	}
	
}
