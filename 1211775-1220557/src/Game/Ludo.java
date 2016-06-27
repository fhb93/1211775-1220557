package Game;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import graphics.Panel;
import logic.LoadGame;
import logic.LogicFacade;
import logic.SaveGame;


public class Ludo  {

	public static JLabel message;

	public static void main(String[] args) {

		final int frameWidth = 1024;
		final int frameHeight = 762;

		int player = 1;
		JButton buttonTurn = new JButton("Iniciar!"); 
		JButton buttonSave = new JButton("Salvar");
		JButton buttonLoad = new JButton("Carregar");
		JFrame myFrame = new JFrame("Ludo");
		Panel panel = new Panel();


		message = new JLabel();
		message.setBounds(770, 480, 200, 75);

		LogicFacade lf = new LogicFacade();

		// inicializa frame e painel
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(frameWidth, frameHeight);
		myFrame.setResizable(false);
		panel.setLayout(null);
		panel.setBounds(new Rectangle(762, 762));
		buttonTurn.setBounds(770, 550, 100, 55);
		buttonTurn.setFocusable(true);

		buttonSave.setBounds(770, 610, 100, 55);
		buttonLoad.setBounds(770, 660, 100, 55);

		lf.playerTurnHasFinished = new boolean[4];

		panel.add(lf.getDado().getLabel());
		panel.add(lf.getDado().getLabel());
		panel.add(lf.getDado().getButton());
		panel.add(buttonSave);
		panel.add(buttonLoad);
		panel.add(message);
		panel.add(buttonTurn);
		lf.positionTable();
		myFrame.add(panel);
		myFrame.setVisible(true);

		lf.turn(buttonTurn, lf.getDado().getButton(), player );

		for(int i = 0; i < 4; i++)
			lf.playerTurnHasFinished[i] = false;

		while(true) {

			if(lf.turn) {

				for(int i = 0; i < 4; i++)
					lf.playerTurnHasFinished[i] = false; //reseta os jogadas da rodada ao seu termino

				lf.turn = false;

				if(player == 1 && !lf.playerTurnHasFinished[player - 1]) //amarelo  
					lf.movements(lf.getDado().getButton(), panel, player); 
				else if(player == 2 && ! lf.playerTurnHasFinished[player - 1])	 //azul 
					lf.movements(lf.getDado().getButton(), panel, player);
				else if(player == 3 && ! lf.playerTurnHasFinished[player - 1]) //vermelhas
					lf.movements(lf.getDado().getButton(), panel, player);
				else if(player == 4 && ! lf.playerTurnHasFinished[player - 1]) //verdes
					lf.movements(lf.getDado().getButton(), panel, player);



				try {

					lf.turn(buttonTurn, lf.getDado().getButton(), player );
					Thread.sleep(1000);
					if(LogicFacade.diceClicked && lf.getDado().getValorDado() > 0) {
						message.setText("Mova um peao");
					}
					if(lf.getDado().getValorDado() == 5) {
						message.setText("Pode mover um novo peao");
					}
					if(LogicFacade.diceClicked && lf.playerTurnHasFinished[player - 1]) {
						message.setText("Clique para concluir");
					}
					
					// checa se houve um vencedor na partida e imprime a mensagem de fim de jogo
					for(int i = 0; i < 4; i++)
						if(LogicFacade.getColocacoes().get(i).getColocacao() == 1) {
							message.setText("Fim de jogo. Vencedor: " + LogicFacade.getColocacoes().get(i).getColocacao() + "!");
							panel.repaint();
							break;
						}
							
					for(int i = 0; i < 4; i++){
						panel.add(lf.positions.get(i));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				player++;
				if(player == 5)
					player = 1;

				gameSaves(buttonSave, lf);
				gameLoads(buttonLoad, lf);
			}
			panel.repaint();
		}

	}
	private static void gameSaves(JButton button, LogicFacade logic) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				handleMouseClick(logic);
			}

			public void handleMouseClick(LogicFacade logic) {
				if(LogicFacade.getSave().saveOperation(logic))
					message.setText("Jogo Salvo!");
			}
		});
	}
	private static void gameLoads(JButton button, LogicFacade logic) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				super.mouseClicked(event);
				handleMouseClick(logic);
			}

			public void handleMouseClick(LogicFacade logic) {
				if(LogicFacade.getLoad().loadOperation(logic))
					message.setText("Jogo Carregado!");
					
			}
		});
	}

}