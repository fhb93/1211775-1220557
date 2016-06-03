package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Dado{
	
	private String path1 = "src/assets/Dice1.png";
	private String path2 = "src/assets/Dice2.png";
	private String path3 = "src/assets/Dice3.png";
	private String path4 = "src/assets/Dice4.png";
	private String path5 = "src/assets/Dice5.png";
	private String path6 = "src/assets/Dice6.png";
	private JLabel label;
	private File file[];
	private BufferedImage img;
	private JButton button;
	private int valorDado = 0;
	public int rolagem;

	public Dado() {	
		label = new JLabel();
		button = new JButton("Lan�ar Dado!");
		button.setLayout(null);
		button.setSize(150, 75);
		button.setBounds(770, 20, 150, 75);
		button.setVisible(true);
		label.setBounds(770, 100, 204, 202);

		
		
	
		file = new File[6];
		file[0] = new File(path1);
		file[1] = new File(path2);
		file[2] = new File(path3);
		file[3] = new File(path4);
		file[4] = new File(path5);
		file[5] = new File(path6);

		
	}

	public File[] getDadoImg() {
		return file;
	}

	public JButton getButton(){
		return button;
	}

	public JLabel getLabel() {
		return label;
	}


	public int rollDice() {
		System.out.println("Dado lan�ado!");
		Random rand = new Random();
		int val = 0;
		for(int i = 0; i < 10; i++) {

			try {
				val = rand.nextInt(6);
				img = ImageIO.read(new FileInputStream(file[val]));
				label.setIcon(new ImageIcon(img));

			} catch (IOException exc) {
				exc.printStackTrace();
			}

		}
		setValorDado(val + 1);
		System.out.println(getValorDado());
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
