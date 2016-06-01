package gui;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Ludo {
	// final static int L_TAB = 762;
	// final static int L_BASE = 306;
	// final static int L_CASA = (L_TAB - (2*L_BASE)) / 3;
	// final static int D_PEAO = 40;

	// public enum Color {
	// RED,
	// BLUE,
	// GREEN,
	// YELLOW;
	// }

	public static void main(String[] args) {
		final int frameWidth = 1024;
		final int frameHeight = 762;

		JFrame myFrame = new JFrame("Ludo");
		Panel panel = new Panel();
		Dado d = new Dado();
		PinList pinos = new PinList();

		// pinos.inicializaListaPino();

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
		d.movement(pinos.getList().get(2), 1);
		
		
		
		// isso aqui nao ta funfando, lek
		panel.repaint();
		myFrame.add(panel);
		myFrame.setVisible(true);
		

	}
}
