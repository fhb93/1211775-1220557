package logic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogicFacade {

	public boolean voltaCompleta = false;
	public static boolean diceClicked = false;
	public boolean[] playerTurnHasFinished;
	public static int contColocacao = 1;
	public JLabel message;
	public boolean turn = false;

	public ArrayList<JLabel> positions = new ArrayList<JLabel>();
	private Colocacoes posicoes;
	private static PinList pins;
	private ListCasas casa;
	private ListCasas amarela;
	private ListCasas azul;
	private ListCasas verde;
	private ListCasas vermelha;
	
	private static ArrayList <Vencedor> colocacoes;
	private static ArrayList <Pin> pinos;
	private static ArrayList <Casa> casas;
	private static ArrayList <Casa> amarelas;
	private static ArrayList <Casa> azuis;
	private static ArrayList <Casa> verdes;
	private static ArrayList <Casa> vermelhas;
	private static SaveGame save;
	private static LoadGame load;
	
	private int yellowPinsOnPath = 1; 
	private int bluePinsOnPath = 5;
	private int redPinsOnPath = 9;
	private int greenPinsOnPath = 13;

	private Dado dado; 

	public LogicFacade() {
		this.posicoes = new Colocacoes();
		LogicFacade.setPins(new PinList());
		this.casa = new ListCasas();
		this.amarela = new ListCasas(1);
		this.azul = new ListCasas(2);
		this.vermelha = new ListCasas(3);
		this.verde = new ListCasas(4);
		this.dado = new Dado();
		save = new SaveGame();
		load = new LoadGame();




		LogicFacade.colocacoes = posicoes.getList();
		LogicFacade.setPinos(getPins().getList());		
		LogicFacade.setCasas(casa.getListCasa());
		LogicFacade.setAmarelas(amarela.getListColoridas());
		LogicFacade.setAzuis(azul.getListColoridas());
		LogicFacade.setVerdes(verde.getListColoridas());		
		LogicFacade.setVermelhas(vermelha.getListColoridas());

	}


	public void positionTable() {
		JLabel holder;
		int x = 770;
		int y = 390;

		for(int i = 0; i < 4; i++){
			holder = new JLabel();
			holder.setBounds(x, y, 180, 20);
			holder.setText("JOGADOR:" + LogicFacade.getColocacoes().get(i).getCor() + " POSICAO:" + LogicFacade.getColocacoes().get(i).getColocacao());
			positions.add(holder);
			y += 20;
		}
	}

	public void movements(JButton button, JPanel panel,  int player) { 
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				if(!playerTurnHasFinished[player - 1])
					handleMouseClick(event);
			}
			public void handleMouseClick(MouseEvent event) {
				if(diceClicked == false){
					dado.setValorDado(dado.rollDice());
					panel.repaint();

					diceClicked = true;
				}
			}
		});

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				

				if(dado.getValorDado() != 5) {
					if(!playerTurnHasFinished[player - 1]) {
						if(player == 1){
							for(int i = 0; i < yellowPinsOnPath; i++) {
								if(pinos.get(i).pinSelected(event.getPoint())) {
									dado.setValorDado(checkDice(dado.getValorDado(), 
											getPins(), player, yellowPinsOnPath));
									pinos.get(i).handleSelectedPin(event, 
											dado.getValorDado(), dado.getValorDado() + 
											pinos.get(i).getCasasAndadas(), casas, amarelas, player, 
											colocacoes); //pin.getValorDado

								}
							}
						}
						else if(player == 2){
							for(int i = 4; i < bluePinsOnPath; i++) {
								if(getPins().getList().get(i).pinSelected(event.getPoint())) {// tava amarelas em vez de azuis
									dado.setValorDado(checkDice(dado.getValorDado(), getPins(), player, bluePinsOnPath));							
									getPins().getList().get(i).handleSelectedPin(event, dado.getValorDado(),dado.getValorDado() + getPins().getList().get(i).getCasasAndadas(), casas, azuis, player, colocacoes); //pin.getValorDado
								}
							}
						}
						else if(player == 3){
							for(int i = 8; i < redPinsOnPath; i++) {
								if(getPins().getList().get(i).pinSelected(event.getPoint()))  {
									dado.setValorDado(checkDice(dado.getValorDado(), getPins(), player, redPinsOnPath));
									getPins().getList().get(i).handleSelectedPin(event, dado.getValorDado(),dado.getValorDado() + getPins().getList().get(i).getCasasAndadas(), casas, vermelhas, player, colocacoes); //pin.getValorDado
								}
							}
						}

						else if(player == 4){
							for(int i = 12; i < greenPinsOnPath; i++) {
								if(getPins().getList().get(i).pinSelected(event.getPoint())) { // tava amarela em vez de vermelhas
									dado.setValorDado(checkDice(dado.getValorDado(), getPins(), player, greenPinsOnPath));
									getPins().getList().get(i).handleSelectedPin(event, dado.getValorDado(), dado.getValorDado() + getPins().getList().get(i).getCasasAndadas(), casas, verdes, player, colocacoes); //pin.getValorDado
								}
							}
						}
					}
				}

				else if(dado.getValorDado() == 5) {
					if(!playerTurnHasFinished[player - 1]) {
						if(player == 1){
							for(int i = 0; i < 4; i++) {
								if(getPins().getList().get(i).pinSelected(event.getPoint()))
									getPins().getList().get(i).handleSelectedPin(event, dado.getValorDado(),dado.getValorDado() + getPins().getList().get(i).getCasasAndadas(), casas, amarelas, player, colocacoes); //pin.getValorDado
								if(!getPins().getList().get(i).isOnPath) {
									yellowPinsOnPath++;
								}
							}

						}
						else if(player == 2){
							for(int i = 4; i < 8; i++) {
								if(getPins().getList().get(i).pinSelected(event.getPoint()))
									getPins().getList().get(i).handleSelectedPin(event, dado.getValorDado(),dado.getValorDado() + getPins().getList().get(i).getCasasAndadas(), casas, azuis, player, colocacoes); //pin.getValorDado
								if(!getPins().getList().get(i).isOnPath) {
									bluePinsOnPath++;
								}
							}
						}
						else if(player == 3){
							for(int i = 8; i < 12; i++) {
								if(getPins().getList().get(i).pinSelected(event.getPoint()))
									getPins().getList().get(i).handleSelectedPin(event, dado.getValorDado(), dado.getValorDado() + getPins().getList().get(i).getCasasAndadas(), casas, vermelhas, player, colocacoes); //pin.getValorDado
								if(!getPins().getList().get(i).isOnPath) {
									redPinsOnPath++;
								}
							}
						}

						else if(player == 4){
							for(int i = 12; i < 16; i++) {
								if(getPins().getList().get(i).pinSelected(event.getPoint())) 
									getPins().getList().get(i).handleSelectedPin(event, dado.getValorDado(), dado.getValorDado() + getPins().getList().get(i).getCasasAndadas(), casas, verdes, player, colocacoes); //pin.getValorDado
								if(!getPins().getList().get(i).isOnPath) {
									greenPinsOnPath++;
								}

							}
						}

					}				
					if(!Pin.captured) 
						playerTurnHasFinished[player - 1] = true;
					panel.repaint();
					return;
				}

				if(event.getClickCount() == 1) {
					playerTurnHasFinished[player - 1] = true;
					return;
				}
			}
		});
	}
	public void turn (JButton buttonTurn, JButton dadoButton, int cor) {

		buttonTurn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				handleMouseClick(event, cor);
			}

			public void handleMouseClick(MouseEvent event, int cor) {
				String concluir = "Concluir";
				turn = true;
				buttonTurn.setText(concluir);
				dadoButton.setEnabled(true);
			}
		});
	}

	public static int checkDice(int valorDado, PinList pins, int player, int pinsOnPathIndex) {
		if(valorDado == 6) {
			if(player == 1) {
				for(int i = 1; i < 4; i++) {
					if(!pins.getList().get(i).isOnPath) {
						return valorDado;
					}
				}
				return 7;
			}
			if(player == 2) {
				for(int i = 5; i < 8; i++) {
					if(!pins.getList().get(i).isOnPath) {
						return valorDado;
					}

				}
				return 7;
			}
			if(player == 3) {
				for(int i = 9; i < 12; i++) {
					if(!pins.getList().get(i).isOnPath) {
						return valorDado;
					}

				}
				return 7;
			}
			if(player == 4) {
				for(int i = 13; i < 16; i++) {
					if(!pins.getList().get(i).isOnPath) {
						return valorDado;
					}
				}
				return 7;
			}
		}
		return valorDado;
	}


	public static SaveGame getSave(){
		return save;
	}
	
	public static LoadGame getLoad(){
		return load;
	}

	public Dado getDado() {
		return dado;
	}

	public static ArrayList<Vencedor> getColocacoes() {
		return colocacoes;
	}

	public static ArrayList <Pin> getPinos() {
		return pinos;
	}

	public static void setPinos(ArrayList <Pin> pinos) {
		LogicFacade.pinos = pinos;
	}

	public static ArrayList <Casa> getCasas() {
		return casas;
	}

	public static void setCasas(ArrayList <Casa> casas) {
		LogicFacade.casas = casas;
	}

	public static ArrayList <Casa> getAmarelas() {
		return amarelas;
	}

	public static void setAmarelas(ArrayList <Casa> amarelas) {
		LogicFacade.amarelas = amarelas;
	}

	public static ArrayList <Casa> getAzuis() {
		return azuis;
	}

	public static void setAzuis(ArrayList <Casa> azuis) {
		LogicFacade.azuis = azuis;
	}

	public static ArrayList <Casa> getVerdes() {
		return verdes;
	}

	public static void setVerdes(ArrayList <Casa> verdes) {
		LogicFacade.verdes = verdes;
	}

	public static ArrayList <Casa> getVermelhas() {
		return vermelhas;
	}

	public static void setVermelhas(ArrayList <Casa> vermelhas) {
		LogicFacade.vermelhas = vermelhas;
	}

	public static PinList getPins() {
		return pins;
	}

	public static void setPins(PinList pins) {
		LogicFacade.pins = pins;
	}


	public ArrayList<JLabel> getPositions() {
		return positions;
	}


	public void setPositions(ArrayList<JLabel> positions) {
		this.positions = positions;
	}



}
