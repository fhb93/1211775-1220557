package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Tabuleiro{

	public void paintTabuleiro(Graphics g)
	{

		int x, y;
		//Define o lados dos elementos do tabuleiro
		final int L_TAB = 715;
		final int L_BASE = 285;
		final int L_CASA = (L_TAB - (2*L_BASE)) / 3;
		final int L_CENTRO = L_CASA * 3;
		final int D_PEAO = 40;


		int npoints = 4;
		int npoints_tri = 3;

		Graphics2D g2d = (Graphics2D) g;
		//Desenha as linhas exteriores do tabuleiro e as bases
		
		g2d.drawRect(0, 0, L_TAB, L_TAB);
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(0, 0, L_BASE, L_BASE);
		g2d.drawRect(0, L_BASE + L_CENTRO, L_BASE, L_BASE);
		g2d.setColor(Color.BLUE);
		g2d.fillRect(L_BASE + L_CENTRO, 0, L_BASE, L_BASE);
		g2d.drawRect(L_BASE + L_CENTRO, L_BASE + L_CENTRO, L_BASE, L_BASE);
		g2d.setColor(new Color(0, 153, 0));
		g2d.fillRect(0, L_BASE + L_CENTRO, L_BASE, L_BASE);
		g2d.drawRect(L_BASE + L_CENTRO, 0, L_BASE, L_BASE);
		g2d.setColor(Color.RED);
		g2d.fillRect(L_BASE + L_CENTRO, L_BASE + L_CENTRO, L_BASE, L_BASE);
		g2d.drawRect(0, 0, L_BASE, L_BASE);
		g2d.setColor(Color.BLACK);




		//Desenha quadrado do centro e suas diagonais, formando quatro triangulos
		g2d.drawRect(L_BASE, L_BASE, L_CENTRO, L_CENTRO);
		g2d.drawLine(L_BASE, L_BASE, L_BASE + L_CENTRO, L_BASE + L_CENTRO);
		g2d.drawLine(L_BASE, L_BASE + L_CENTRO, L_BASE + L_CENTRO, L_BASE);



		//Desenha as bases iniciais dos peoes
		g2d.setColor(Color.WHITE);
		g2d.drawPolygon(new int[]{0, L_BASE/2, L_BASE, L_BASE/2}, new int[]{L_BASE/2, 0, L_BASE/2, L_BASE}, npoints);
		g2d.fillPolygon(new int[]{0, L_BASE/2, L_BASE, L_BASE/2}, new int[]{L_BASE/2, 0, L_BASE/2, L_BASE}, npoints);

		g2d.setColor(Color.WHITE);
		g2d.drawPolygon(new int[]{L_BASE + L_CENTRO, L_TAB - L_BASE/2, L_TAB, L_TAB - L_BASE/2}, new int[]{L_BASE/2, 0, L_BASE/2, L_BASE}, npoints);
		g2d.fillPolygon(new int[]{L_BASE + L_CENTRO, L_TAB - L_BASE/2, L_TAB, L_TAB - L_BASE/2}, new int[]{L_BASE/2, 0, L_BASE/2, L_BASE}, npoints);

		g2d.setColor(Color.WHITE);
		g2d.drawPolygon(new int[]{0, L_BASE/2, L_BASE, L_BASE/2}, new int[]{L_TAB - L_BASE/2, L_BASE + L_CENTRO, L_TAB - L_BASE/2, L_TAB}, npoints);
		g2d.fillPolygon(new int[]{0, L_BASE/2, L_BASE, L_BASE/2}, new int[]{L_TAB - L_BASE/2, L_BASE + L_CENTRO, L_TAB - L_BASE/2, L_TAB}, npoints);

		g2d.setColor(Color.WHITE);
		g2d.drawPolygon(new int[]{L_BASE + L_CENTRO, L_TAB - L_BASE/2, L_TAB, L_TAB - L_BASE/2}, new int[]{L_TAB - L_BASE/2, L_BASE + L_CENTRO, L_TAB - L_BASE/2, L_TAB}, npoints);
		g2d.fillPolygon(new int[]{L_BASE + L_CENTRO, L_TAB - L_BASE/2, L_TAB, L_TAB - L_BASE/2}, new int[]{L_TAB - L_BASE/2, L_BASE + L_CENTRO, L_TAB - L_BASE/2, L_TAB}, npoints);		


		//Desenha as posicoes iniciais dos peoes nas bases
		g2d.setColor(Color.YELLOW);
		g2d.drawOval(L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO, D_PEAO);
		g2d.fillOval(L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO, D_PEAO);
		g2d.drawOval(L_BASE/2 - D_PEAO/2, L_BASE - 2*D_PEAO, D_PEAO, D_PEAO);
		g2d.fillOval(L_BASE/2 - D_PEAO/2, L_BASE - 2*D_PEAO, D_PEAO, D_PEAO);
		g2d.drawOval(D_PEAO, L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);
		g2d.fillOval(D_PEAO, L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);
		g2d.drawOval(L_BASE - D_PEAO*2, L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);
		g2d.fillOval(L_BASE - D_PEAO*2, L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);

		g2d.setColor(Color.BLUE);
		g2d.drawOval(L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO, D_PEAO);
		g2d.fillOval(L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO, D_PEAO);
		g2d.drawOval(L_TAB - L_BASE/2 - D_PEAO/2, 206, D_PEAO, D_PEAO);
		g2d.fillOval(L_TAB - L_BASE/2 - D_PEAO/2, 206, D_PEAO, D_PEAO);
		g2d.drawOval(L_TAB - L_BASE + D_PEAO, 128, D_PEAO, D_PEAO);
		g2d.fillOval(L_TAB - L_BASE + D_PEAO, 128, D_PEAO, D_PEAO);
		g2d.drawOval(L_TAB - D_PEAO*2, 128, D_PEAO, D_PEAO);
		g2d.fillOval(L_TAB - D_PEAO*2, 128, D_PEAO, D_PEAO);

		g2d.setColor(new Color(0, 153, 0)); //VERDE MAIS ESCURO
		g2d.drawOval(L_BASE/2 - D_PEAO/2, L_TAB - L_BASE + D_PEAO, D_PEAO, D_PEAO);
		g2d.fillOval(L_BASE/2 - D_PEAO/2, L_TAB - L_BASE + D_PEAO, D_PEAO, D_PEAO);
		g2d.drawOval(L_BASE/2 - D_PEAO/2, L_TAB - D_PEAO*2, D_PEAO, D_PEAO);
		g2d.fillOval(L_BASE/2 - D_PEAO/2, L_TAB - D_PEAO*2, D_PEAO, D_PEAO);
		g2d.drawOval(D_PEAO, L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);
		g2d.fillOval(D_PEAO, L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);
		g2d.drawOval(L_BASE - D_PEAO*2, L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);
		g2d.fillOval(L_BASE - D_PEAO*2, L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);

		g2d.setColor(Color.RED);
		g2d.drawOval(L_TAB - L_BASE/2 - D_PEAO/2, L_TAB - L_BASE + D_PEAO, D_PEAO, D_PEAO);
		g2d.fillOval(L_TAB - L_BASE/2 - D_PEAO/2, L_TAB - L_BASE + D_PEAO, D_PEAO, D_PEAO);
		g2d.drawOval(L_TAB - L_BASE/2 - D_PEAO/2, L_TAB - D_PEAO*2, D_PEAO, D_PEAO);
		g2d.fillOval(L_TAB - L_BASE/2 - D_PEAO/2, L_TAB - D_PEAO*2, D_PEAO, D_PEAO);
		g2d.drawOval(L_TAB - L_BASE + D_PEAO, L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);
		g2d.fillOval(L_TAB - L_BASE + D_PEAO, L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);
		g2d.drawOval(L_TAB - D_PEAO*2, L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);
		g2d.fillOval(L_TAB - D_PEAO*2, L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO, D_PEAO);

		// Pinta os caminhos seguros dos peoes

		// Pinta o caminho amarelo
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(L_CASA, L_BASE, L_CASA, L_CASA);

		for(x = L_CASA; x < L_BASE; x += L_CASA)
		{
			g2d.fillRect(x, L_BASE + L_CASA, L_CASA, L_CASA);
		}

		g2d.fillPolygon(new int[]{L_BASE,L_BASE,L_TAB/2}, new int[]{L_BASE,L_TAB - L_BASE,L_TAB/2} , npoints_tri);

		// Pinta o caminho azul
		g2d.setColor(Color.BLUE);
		g2d.fillRect(L_TAB - (L_BASE + L_CASA), L_CASA, L_CASA, L_CASA);

		for(y = L_CASA; y < L_BASE; y += L_CASA)
		{
			g2d.fillRect(L_BASE + L_CASA, y, L_CASA, L_CASA);
		}

		g2d.fillPolygon(new int[]{L_BASE, L_BASE + L_CENTRO, L_TAB/2}, new int[]{L_BASE, L_BASE, L_TAB/2}, npoints_tri);

		//Pinta o caminho vermelho
		g2d.setColor(Color.RED);
		g2d.fillRect(L_TAB - 2*L_CASA, L_BASE + 2*L_CASA, L_CASA, L_CASA);

		for(x = L_TAB - 2*L_CASA; x > L_TAB/2; x -= L_CASA)
		{
			g2d.fillRect(x, L_BASE + L_CASA, L_CASA, L_CASA);
		}

		g2d.fillPolygon(new int[]{L_TAB/2, L_BASE + L_CENTRO, L_BASE + L_CENTRO}, new int[]{L_TAB/2, L_BASE, L_BASE + L_CENTRO}, npoints_tri);

		//Pinta o caminho verde
		g2d.setColor(new Color(0, 153, 0));
		g2d.fillRect(L_BASE, L_TAB - 2*L_CASA, L_CASA, L_CASA);

		for(y = L_TAB - 2*L_CASA; y > L_TAB/2; y -= L_CASA)
		{
			g2d.fillRect(L_BASE + L_CASA, y, L_CASA, L_CASA);
		}

		g2d.fillPolygon(new int[]{L_BASE, L_TAB/2, L_BASE + L_CENTRO}, new int[]{L_BASE + L_CENTRO, L_TAB/2, L_BASE + L_CENTRO} , npoints_tri);


		//Desenha as casas em que os peoes podem andar
		g2d.setColor(Color.BLACK);
		for(x = 0; x < L_BASE; x += L_CASA)
		{
			g2d.drawLine(x, L_BASE, x, L_BASE + L_CENTRO);
			g2d.drawLine(L_TAB - x, L_BASE, L_TAB - x, L_BASE + L_CENTRO);
		}
		for(y = 0; y < L_BASE; y +=L_CASA)
		{
			g2d.drawLine(L_BASE, y, L_BASE + L_CENTRO, y);
			g2d.drawLine(L_BASE, L_TAB - y, L_BASE + L_CENTRO, L_TAB - y);
		}

		for(y = L_BASE + L_CASA; y < L_BASE + L_CENTRO ; y+=L_CASA)
		{
			g2d.drawLine(0, y, L_BASE, y);
			g2d.drawLine(L_BASE + L_CENTRO, y, L_TAB, y);
		}
		for(x = L_BASE + L_CASA; x < L_BASE + L_CENTRO; x+=L_CASA)
		{
			g2d.drawLine(x, 0, x, L_BASE);
			g2d.drawLine(x, L_BASE + L_CENTRO, x, L_TAB);
		}
		g2d.drawLine(783, 783, 761, 761);
		
		// pinta as 4 casas pretas
		g2d.fillRect(L_CASA, L_BASE + 2*L_CASA, L_CASA, L_CASA);
		g2d.fillRect(L_BASE, L_CASA, L_CASA, L_CASA);
		g2d.fillRect(L_TAB - 2*L_CASA, L_BASE, L_CASA, L_CASA);
		g2d.fillRect(L_BASE + 2*L_CASA, L_TAB - 2*L_CASA, L_CASA, L_CASA);
	}
}
