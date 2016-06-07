package gui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	//private static int flag = 0;

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		Tabuleiro tabuleiro = new Tabuleiro();
		
		tabuleiro.paintTabuleiro(g);
		
		for(int i = 0; i < 16; i++)
		{
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(3));
			
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
				g2d.setColor(new Color(0, 153, 0));
				g2d.fillOval(Ludo.pinos.get(i).getX(), Ludo.pinos.get(i).getY(), 40, 40);
			}
			else{
				g2d.setColor(Color.RED);
				g2d.fillOval(Ludo.pinos.get(i).getX(), Ludo.pinos.get(i).getY(), 40, 40);
			}
		}
		
	}

}
