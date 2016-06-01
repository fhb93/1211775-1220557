package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class PinList {
	
	//final static int L_TAB = 762;
//	final static int L_BASE = 306;
//	final static int L_CASA = (L_TAB - (2*L_BASE)) / 3;
//	final static int D_PEAO = 40;
	
	final int L_TAB = 715;
	final int L_BASE = 285;
	final int L_CASA = (L_TAB - (2*L_BASE)) / 3;
	final int L_CENTRO = L_CASA * 3;
	final int D_PEAO = 40;
	
	public final ArrayList<Pin> pinos =  new ArrayList<>();
	
	//void inicializaListaPino(){
		//amarelos = player 1
	public PinList() {	
				pinos.add(0, new Pin(L_BASE/2 - D_PEAO/2, D_PEAO,1,1));
				pinos.add(1, new Pin(L_BASE/2 - D_PEAO/2, L_BASE - 2*D_PEAO,1,2));
				pinos.add(2, new Pin(D_PEAO, L_BASE/2 - D_PEAO/2,1,3));
				pinos.add(3, new Pin(L_BASE - D_PEAO*2, L_BASE/2 - D_PEAO/2,1,4));
				
				//azuis = player 2
				pinos.add(4, new Pin(L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO,2,5));
				pinos.add(5, new Pin(L_TAB - L_BASE/2 - D_PEAO/2, 206,2,6));
				pinos.add(6, new Pin(L_TAB - L_BASE + D_PEAO, 128,2,7));
				pinos.add(7, new Pin(L_TAB - D_PEAO*2, 128,2,8));
				
				//verdes = player 3
				pinos.add(8, new Pin(L_BASE/2 - D_PEAO/2, L_TAB - L_BASE + D_PEAO,3,9));
				pinos.add(9, new Pin(L_BASE/2 - D_PEAO/2, L_TAB - D_PEAO*2,3,10));
				pinos.add(10, new Pin(D_PEAO, L_TAB - L_BASE/2 - D_PEAO/2,3,11));
				pinos.add(11, new Pin(L_BASE - D_PEAO*2, L_TAB - L_BASE/2 - D_PEAO/2,3,11));
				
				//vermelhos = player 4
				pinos.add(12, new Pin(L_TAB - L_BASE/2 - D_PEAO/2, L_TAB - L_BASE + D_PEAO,4,13));
				pinos.add(13, new Pin(L_TAB - L_BASE/2 - D_PEAO/2, L_TAB - D_PEAO*2,4,14));
				pinos.add(14, new Pin(L_TAB - L_BASE + D_PEAO, L_TAB - L_BASE/2 - D_PEAO/2,4,15));
				pinos.add(15, new Pin(L_TAB - D_PEAO*2, L_TAB - L_BASE/2 - D_PEAO/2,4,16));			
	}

	public ArrayList<Pin> getList(){
		return this.pinos;
	}
}
