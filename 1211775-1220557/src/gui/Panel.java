package gui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	final int L_TAB = 715;
	final int L_BASE = 285;
	final int L_CASA = (L_TAB - (2*L_BASE)) / 3;
	final int L_CENTRO = L_CASA * 3;
	final int D_PEAO = 40;


	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		Tabuleiro tabuleiro = new Tabuleiro();
		int x = 0;
		int y = 0;

		tabuleiro.paintTabuleiro(g);

		for(int i = 0; i < 16; i++)
		{
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(6));

			g2d.drawOval(Ludo.pinos.get(i).getX(), Ludo.pinos.get(i).getY(), 40, 40);
		}
		for(int i = 0; i < 16; i++) 
		{
			if(i < 4){	
				g2d.setColor(Color.YELLOW);
				g2d.fillOval(Ludo.pinos.get(i).getX(), Ludo.pinos.get(i).getY(), 40, 40);
			}
			else if(i < 8){
				g2d.setColor(Color.BLUE);
				g2d.fillOval(Ludo.pinos.get(i).getX(), Ludo.pinos.get(i).getY(), 40, 40);
			}
			else if(i < 12){
				g2d.setColor(Color.RED);
				g2d.fillOval(Ludo.pinos.get(i).getX(), Ludo.pinos.get(i).getY(), 40, 40);
			}
			else {
				g2d.setColor(new Color(0, 153, 0));
				g2d.fillOval(Ludo.pinos.get(i).getX(), Ludo.pinos.get(i).getY(), 40, 40);
			}
		}

		
		//verifica se há casa com dois pinos da mesma cor e desenha um quadrado de sua cor se houver
		for(int i = 0; i < Ludo.casas.size(); i++){
			if(Ludo.casas.get(i).getQtdPin() == 2){
				x = Ludo.casas.get(i).getX();
				y = Ludo.casas.get(i).getY();
				for(int j = 0; j < Ludo.pinos.size(); j++){
					if(Ludo.pinos.get(j).getX() == Ludo.casas.get(i).getX() && Ludo.pinos.get(j).getY() == Ludo.casas.get(i).getY()){
						if(Ludo.pinos.get(j).getPlayer() == 1){
							g2d.setColor(Color.BLACK);
							g2d.setStroke(new BasicStroke(4));
							g2d.drawRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
							g2d.setColor(Color.YELLOW);
							g2d.fillRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
						}
						else if(Ludo.pinos.get(j).getPlayer() == 2){
							g2d.setColor(Color.BLACK);
							g2d.setStroke(new BasicStroke(4));
							g2d.drawRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
							g2d.setColor(Color.BLUE);
							g2d.fillRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
						}
						else if(Ludo.pinos.get(j).getPlayer() == 3){
							g2d.setColor(Color.BLACK);
							g2d.setStroke(new BasicStroke(4));
							g2d.drawRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
							g2d.setColor(new Color(0, 153, 0));
							g2d.fillRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
						}
						else{
							g2d.setColor(Color.BLACK);
							g2d.setStroke(new BasicStroke(4));
							g2d.drawRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
							g2d.setColor(Color.RED);
							g2d.fillRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
						}
						
					}
				}
			}
		}


	}

}


