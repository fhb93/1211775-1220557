package gui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ludo  {

	public static boolean turn = false;
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
	public static boolean played = false;
	public static boolean diceClicked = false;




	public static void main(String[] args) {

		final int frameWidth = 1024;
		final int frameHeight = 762;

		//Thread turnBounce = new Thread();
		int player = 1;
		JButton buttonTurn = new JButton("Iniciar!"); 
		JFrame myFrame = new JFrame("Ludo");
		Panel panel = new Panel();
		Dado d = new Dado();
		// inicializa frame e painel
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(frameWidth, frameHeight);
		myFrame.setResizable(false);
		panel.setLayout(null);
		panel.setBounds(new Rectangle(762, 762));
		buttonTurn.setBounds(770, 550, 150, 75);
		buttonTurn.setFocusable(true);

		panel.add(d.getLabel());
		panel.add(d.getButton());
		panel.add(buttonTurn);
		myFrame.add(panel);
		myFrame.setVisible(true);

		turn(buttonTurn, d.getButton(), player );
		//		if() {
		//			buttonTurn.setText("Finalizar Turno");
		//			panel.add(buttonTurn);
		//			myFrame.add(panel);
		//			myFrame.setVisible(true);
		//		}
		while(true) {
			if(turn) {
				turn = false;

				if(player == 1 && !played) { //amarelo 
					movements(d, d.getButton(), panel, pins.getList().get(0), player);
				}
				if(player == 2 && !played)	 //azul 
					movements(d, d.getButton(), panel, pins.getList().get(4), player);
				if(player == 3 && !played) 
					movements(d, d.getButton(), panel, pins.getList().get(8), player);
				if(player == 4 && !played)
					movements(d, d.getButton(), panel, pins.getList().get(12), player);
				player++;
				played = true;
//				d.getButton().setEnabled(false);
//				panel.add(d.getButton());
//				myFrame.add(panel);
				
				if(player == 5)
					player = 1;
				
			}

			try {
				Thread.sleep(500);
				turn(buttonTurn, d.getButton(), player );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			played = false;
			panel.repaint();
		}
	}

	public static void movements(Dado d, JButton button, JPanel panel, Pin pin, int player) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				handleMouseClick(event);
			}
			public void handleMouseClick(MouseEvent event) {
				if(diceClicked == false){
					d.setValorDado(d.rollDice());						
					diceClicked = true;
				}
			}
		});
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				if(!played) {
					if(player == 1){
						pin.handleSelectedPin(event, d.getValorDado(),d.getValorDado() + pin.getCasasAndadas(), casas, amarelas); //pin.getValorDado
					}
					else if(player == 2){
						pin.handleSelectedPin(event, d.getValorDado(), d.getValorDado() + pin.getCasasAndadas(), casas, azuis);
					}
					else if(player == 3){
						pin.handleSelectedPin(event, d.getValorDado(), d.getValorDado() + pin.getCasasAndadas(), casas, verdes);
					}
					else if(player == 4){
						pin.handleSelectedPin(event, d.getValorDado(), d.getValorDado() + pin.getCasasAndadas(), casas, vermelhas);
					}
				//played = true;
				}				
				//pin.setCasasAndadas(d.getValorDado() + pin.getCasasAndadas());
				panel.repaint();
			}
		});
	}
	static void turn (JButton buttonTurn, JButton dadoButton, int cor) {

		buttonTurn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				handleMouseClick(event, cor);
			}

			public void handleMouseClick(MouseEvent event, int cor) {
				String jogador = "";
				turn = true;

				switch(cor) {

				case 1:
					jogador = "Conclui amarela";
					break;
				case 2:
					jogador = "Conclui azul";
					break;
				case 3:
					jogador = "Conclui verde";
					break;
				case 4:
					jogador = "Conclui vermelha";
					break;
				}
				buttonTurn.setText(jogador);
				dadoButton.setEnabled(true);
				System.out.println("Mudou!");
			}
		});
	}

}
