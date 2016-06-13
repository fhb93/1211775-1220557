package gui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ludo  {

	public static boolean turn = false;
	public static PinList pins = new PinList();
	public static ListCasas casa = new ListCasas();
	public static ListCasas amarela = new ListCasas(1);
	public static ListCasas azul = new ListCasas(2);
	public static ListCasas verde = new ListCasas(3);
	public static ListCasas vermelha  = new ListCasas(4);


	public static ArrayList <Pin> pinos = pins.getList();		
	public static ArrayList <Casa> casas = casa.getListCasa();
	public static ArrayList <Casa> amarelas = amarela.getListColoridas();
	public static ArrayList <Casa> azuis = azul.getListColoridas();
	public static ArrayList <Casa> verdes = verde.getListColoridas();		
	public static ArrayList <Casa> vermelhas = vermelha.getListColoridas();
	public static boolean voltaCompleta = false;
	public static boolean played = false;
	public static boolean diceClicked = false;
	public static boolean[] playerTurnHasFinished;


	public static void main(String[] args) {

		final int frameWidth = 1024;
		final int frameHeight = 762;

		int player = 1;
		JButton buttonTurn = new JButton("Iniciar!"); 
		JFrame myFrame = new JFrame("Ludo");
		Panel panel = new Panel();
		Dado d = new Dado();
		JLabel board = new JLabel();
		board.setBounds(770, 450, 150, 75);
		board.setText("Jogador: ");
		board.setVisible(true);
		playerTurnHasFinished = new boolean[4];
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
		panel.add(board);
		myFrame.add(panel);
		myFrame.setVisible(true);

		turn(buttonTurn, d.getButton(), player );

		for(int i = 0; i < 4; i++)
			playerTurnHasFinished[i] = false;

		while(true) {

			if(turn) {

				turn = false;

				if(player == 1 &&  !playerTurnHasFinished[player - 1]) //amarelo  
					movements(d, d.getButton(), panel, player); 
				if(player == 2 && !playerTurnHasFinished[player - 1])	 //azul 
					movements(d, d.getButton(), panel, player);
				if(player == 3 &&  !playerTurnHasFinished[player - 1]) //vermelhas
					movements(d, d.getButton(), panel, player);
				if(player == 4 && !playerTurnHasFinished[player - 1]) //verdes
					movements(d, d.getButton(), panel, player);
				switch(player) {
				case 1:
					board.setText("Jogador: Amarelas");
					break;
				case 2:
					board.setText("Jogador: Azuis");
					break;
				case 3:
					board.setText("Jogador: Vermelhas");
					break;
				case 4:
					board.setText("Jogador: Verdes");
					break;
				}


				player++;

				if(player == 5)
					player = 1;
				for(int i = 0; i < 4; i++)
					playerTurnHasFinished[i] = false; //reseta os jogadas da rodada ao seu término


			}

			try {
				Thread.sleep(500);
				turn(buttonTurn, d.getButton(), player );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panel.repaint();

		}
	}

	public static void movements(Dado d, JButton button, JPanel panel,  int player) { // , Pin pin, int player
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				if(!playerTurnHasFinished[player - 1])
					handleMouseClick(event);
			}
			public void handleMouseClick(MouseEvent event) {
				if(diceClicked == false){
					d.setValorDado(d.rollDice());
					if(d.getValorDado() == 5) {
						System.out.println("Movendo novo pino");
					}
					diceClicked = true;
				}
			}
		});

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				int yellowPinsOnPath = 1; 
				int bluePinsOnPath = 5;
				int redPinsOnPath = 9;
				int greenPinsOnPath = 13;

				if(d.getValorDado() != 5) {
					if(!played && !playerTurnHasFinished[player - 1]) {
						if(player == 1){
							for(int i = 0; i < yellowPinsOnPath; i++) {
								if(pins.getList().get(i).pinSelected(event.getPoint())) {
									d.setValorDado(checkDice(d.getValorDado(), pins, player));
									pins.getList().get(i).handleSelectedPin(event, d.getValorDado(),d.getValorDado() + pins.getList().get(i).getCasasAndadas(), casas, amarelas, player); //pin.getValorDado

								}
							}
						}
						else if(player == 2){
							for(int i = 4; i < bluePinsOnPath; i++) {
								if(pins.getList().get(i).pinSelected(event.getPoint())) {// tava amarelas em vez de azuis
									d.setValorDado(checkDice(d.getValorDado(), pins, player));							
									pins.getList().get(i).handleSelectedPin(event, d.getValorDado(),d.getValorDado() + pins.getList().get(i).getCasasAndadas(), casas, azuis, player); //pin.getValorDado
								}
							}
						}
						else if(player == 3){
							for(int i = 8; i < redPinsOnPath; i++) {
								if(pins.getList().get(i).pinSelected(event.getPoint()))  {// tava amarelas em vez de verdes
									d.setValorDado(checkDice(d.getValorDado(), pins, player));
									pins.getList().get(i).handleSelectedPin(event, d.getValorDado(),d.getValorDado() + pins.getList().get(i).getCasasAndadas(), casas, verdes, player); //pin.getValorDado
								}
							}
						}

						else if(player == 4){
							for(int i = 12; i < greenPinsOnPath; i++) {
								if(pins.getList().get(i).pinSelected(event.getPoint())) { // tava amarela em vez de vermelhas
									d.setValorDado(checkDice(d.getValorDado(), pins, player));
									pins.getList().get(i).handleSelectedPin(event, d.getValorDado(),d.getValorDado() + pins.getList().get(i).getCasasAndadas(), casas, vermelhas, player); //pin.getValorDado
								}
							}
						}
					}
				}

				else if(d.getValorDado() == 5) {
					if(!played && !playerTurnHasFinished[player - 1]) {
						if(player == 1){
							for(int i = 0; i < 4; i++) {
								if(pins.getList().get(i).pinSelected(event.getPoint()))
									pins.getList().get(i).handleSelectedPin(event, d.getValorDado(),d.getValorDado() + pins.getList().get(i).getCasasAndadas(), casas, amarelas, player); //pin.getValorDado
								if(!pins.getList().get(i).isOnPath) {
									yellowPinsOnPath++;
								}
							}

						}
						else if(player == 2){
							for(int i = 4; i < 8; i++) {
								if(pins.getList().get(i).pinSelected(event.getPoint()))// tava amarelas em vez de azuis
									pins.getList().get(i).handleSelectedPin(event, d.getValorDado(),d.getValorDado() + pins.getList().get(i).getCasasAndadas(), casas, azuis, player); //pin.getValorDado
								if(!pins.getList().get(i).isOnPath) {
									bluePinsOnPath++;
								}
							}
						}
						else if(player == 3){
							for(int i = 8; i < 12; i++) {
								if(pins.getList().get(i).pinSelected(event.getPoint())) // tava amarelas em vez de verdes
									pins.getList().get(i).handleSelectedPin(event, d.getValorDado(),d.getValorDado() + pins.getList().get(i).getCasasAndadas(), casas, vermelhas, player); //pin.getValorDado
								if(!pins.getList().get(i).isOnPath) {
									redPinsOnPath++;
								}
							}
						}

						else if(player == 4){
							for(int i = 12; i < 16; i++) {
								if(pins.getList().get(i).pinSelected(event.getPoint())) // tava amarela em vez de vermelhas
									pins.getList().get(i).handleSelectedPin(event, d.getValorDado(),d.getValorDado() + pins.getList().get(i).getCasasAndadas(), casas, verdes, player); //pin.getValorDado
								if(!pins.getList().get(i).isOnPath) {
									greenPinsOnPath++;
								}

							}
						}

					}				
					if(!Pin.captured) 
						playerTurnHasFinished[player - 1] = true;
					panel.repaint();			
				}
			}
		});
	}
	public static void turn (JButton buttonTurn, JButton dadoButton, int cor) {

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
					jogador = "Conclui Amarelas";
					break;
				case 2:
					jogador = "Conclui Azuis";
					break;
				case 3:
					jogador = "Conclui Vermelhas";
					break;
				case 4:
					jogador = "Conclui Verdes";
					break;
				}
				buttonTurn.setText(jogador);
				dadoButton.setEnabled(true);
				System.out.println("Mudou!");
			}
		});
	}

	public static int checkDice(int valorDado, PinList pins, int player) {
		if(valorDado == 6) {
			if(player == 1) {
				for(int i = 0; i < 4; i++) {
					if(!pins.getList().get(i).isOnPath) {
						return valorDado;
					}
				}
				return 7;
			}
			if(player == 2) {
				for(int i = 4; i < 8; i++) {
					if(!pins.getList().get(i).isOnPath) {
						return valorDado;
					}

				}
				return 7;
			}
			if(player == 3) {
				for(int i = 8; i < 12; i++) {
					if(!pins.getList().get(i).isOnPath) {
						return valorDado;
					}

				}
				return 7;
			}
			if(player == 4) {
				for(int i = 12; i < 16; i++) {
					if(!pins.getList().get(i).isOnPath) {
						return valorDado;
					}
				}
				return 7;
			}
		}
		return valorDado;
	}
}
