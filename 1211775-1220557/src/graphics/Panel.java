package graphics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import logic.LogicFacade;

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
		int x = 0;
		int y = 0;

		Tabuleiro.getTabuleiro().paintTabuleiro(g2d);

		for(int i = 0; i < 16; i++)
		{
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(6));

			g2d.drawOval(LogicFacade.getPinos().get(i).getX(), LogicFacade.getPinos().get(i).getY(), 40, 40);
		}
		for(int i = 0; i < 16; i++) 
		{
			if(i < 4){	
				g2d.setColor(Color.YELLOW);
				g2d.fillOval(LogicFacade.getPinos().get(i).getX(), LogicFacade.getPinos().get(i).getY(), 40, 40);
			}
			else if(i < 8){
				g2d.setColor(Color.BLUE);
				g2d.fillOval(LogicFacade.getPinos().get(i).getX(), LogicFacade.getPinos().get(i).getY(), 40, 40);
			}
			else if(i < 12){
				g2d.setColor(Color.RED);
				g2d.fillOval(LogicFacade.getPinos().get(i).getX(), LogicFacade.getPinos().get(i).getY(), 40, 40);
			}
			else {
				g2d.setColor(new Color(0, 153, 0));
				g2d.fillOval(LogicFacade.getPinos().get(i).getX(), LogicFacade.getPinos().get(i).getY(), 40, 40);
			}
		}

		
		//verifica se há casa com dois pinos da mesma cor e desenha um quadrado de sua cor se houver
		for(int i = 0; i < LogicFacade.getCasas().size(); i++){
			if(LogicFacade.getCasas().get(i).getQtdPin() == 2){
				x = LogicFacade.getCasas().get(i).getX();
				y = LogicFacade.getCasas().get(i).getY();
				for(int j = 0; j < LogicFacade.getPinos().size(); j++){
					if(LogicFacade.getPinos().get(j).getX() == LogicFacade.getCasas().get(i).getX() && 
							LogicFacade.getCasas().get(j).getY() == LogicFacade.getCasas().get(i).getY()) {
						if(LogicFacade.getPinos().get(j).getPlayer() == 1){
							g2d.setColor(Color.BLACK);
							g2d.setStroke(new BasicStroke(4));
							g2d.drawRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
							g2d.setColor(Color.YELLOW);
							g2d.fillRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
						}
						else if(LogicFacade.getPinos().get(j).getPlayer() == 2){
							g2d.setColor(Color.BLACK);
							g2d.setStroke(new BasicStroke(4));
							g2d.drawRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
							g2d.setColor(Color.BLUE);
							g2d.fillRect(x + L_CASA/2, y + L_CASA/2, L_CASA/3,L_CASA/3);
						}
						else if(LogicFacade.getPinos().get(j).getPlayer() == 3){
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


