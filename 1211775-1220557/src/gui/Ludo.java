package gui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ludo {

	public static int valorDado;

	public static PinList pins = new PinList();
	public static ListCasas casa = new ListCasas();
	public static ListCasas amarela = new ListCasas(1);
	public static ListCasas azul = new ListCasas(2);
	public static ListCasas verde = new ListCasas(3);
	public static ListCasas vermelha  = new ListCasas(4);


	public static ArrayList<Pin> pinos = pins.getList();		
	public static ArrayList<Casa> casas = casa.getListCasa();
	public static ArrayList <Casa> amarelas = amarela.getListColoridas();
	public static ArrayList <Casa> azuis = azul.getListColoridas();
	public static ArrayList <Casa> verdes = verde.getListColoridas();		
	public static ArrayList <Casa> vermelhas = vermelha.getListColoridas();
	public static boolean voltaCompleta = false;
	
	public static void main(String[] args) {

		final int frameWidth = 1024;
		final int frameHeight = 762;

		//		pins = new PinList();
		//		casa = new ListCasas();
		//		amarela = new ListCasas(1);
		//		azul = new ListCasas(2);
		//		vermelha = new ListCasas(3);
		//		verde  = new ListCasas(4);
		//
		//		
		//		pinos = pins.getList();		
		//		casas = casa.getListCasa();
		//		amarelas = amarela.getListColoridas();
		//		azuis = azul.getListColoridas();
		//		vermelhas = vermelha.getListColoridas();
		//		verdes = verde.getListColoridas();		
		


		JFrame myFrame = new JFrame("Ludo");
		Panel panel = new Panel();
		Dado d = new Dado();
		// inicializa frame e painel
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(frameWidth, frameHeight);
		myFrame.setResizable(false);
		panel.setLayout(null);
		panel.setBounds(new Rectangle(762, 762));

		panel.add(d.getLabel());
		panel.add(d.getButton());
		myFrame.add(panel);
		myFrame.setVisible(true);

		movements(d, d.getButton(), panel, pins.getList().get(4), 1);
		//movements(d, d.getButton(), panel, pins.getList().get(4), 2);
		System.out.println("valor = " + valorDado);

	}

	public static void movements(Dado d, JButton button, JPanel panel, Pin pin, int player) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				handleMouseClick(event);
			}
			public void handleMouseClick(MouseEvent event) {
				//				if(event.getLocationOnScreen() == new Point(pin.getX(), pin.getY())) {
				//					
				//				}
				valorDado += d.rollDice();	
				//pin.movePin(valorDado, 1, 1);


			}
		});

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				pin.handleSelectedPin(event, valorDado, casa, amarela);
				panel.repaint();
			}
			

		});

	}
}
