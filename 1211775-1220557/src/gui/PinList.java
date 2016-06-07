package gui;

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
	
	public PinList() {	
				pinos.add(0, new Pin(L_BASE/2 - D_PEAO/2, D_PEAO,1,1, 0, 52)); 			// 1 e 52 são os ids da primeira e ultima casa
				pinos.add(1, new Pin(L_BASE/2 - D_PEAO/2, L_BASE - 2*D_PEAO,1,2, 0, 52));
				pinos.add(2, new Pin(D_PEAO, L_BASE/2 - D_PEAO/2,1,3, 0, 52));
				pinos.add(3, new Pin(L_BASE - D_PEAO*2, L_BASE/2 - D_PEAO/2,1,4, 0, 52));
				
				//azuis = player 2
				pinos.add(4, new Pin(L_TAB - L_BASE/2 - D_PEAO/2, D_PEAO,2,5, 14, 13));
				pinos.add(5, new Pin(L_TAB - L_BASE/2 - D_PEAO/2, 206,2,6, 14, 13));
				pinos.add(6, new Pin(L_TAB - L_BASE + D_PEAO, 128,2,7, 14, 13));
				pinos.add(7, new Pin(L_TAB - D_PEAO*2, 128,2,8, 14, 13));
				
				//verdes = player 3
				pinos.add(8, new Pin(L_BASE/2 - D_PEAO/2, L_TAB - L_BASE + D_PEAO,3,9, 40, 39));
				pinos.add(9, new Pin(L_BASE/2 - D_PEAO/2, L_TAB - D_PEAO*2,3,10, 40, 39));
				pinos.add(10, new Pin(D_PEAO, L_TAB - L_BASE/2 - D_PEAO/2,3,11, 40, 39));
				pinos.add(11, new Pin(L_BASE - D_PEAO*2, L_TAB - L_BASE/2 - D_PEAO/2,3,11, 40, 39));
				
				//vermelhos = player 4
				pinos.add(12, new Pin(L_TAB - L_BASE/2 - D_PEAO/2, L_TAB - L_BASE + D_PEAO,4,13, 27, 26 ));
				pinos.add(13, new Pin(L_TAB - L_BASE/2 - D_PEAO/2, L_TAB - D_PEAO*2,4,14, 27, 26 ));
				pinos.add(14, new Pin(L_TAB - L_BASE + D_PEAO, L_TAB - L_BASE/2 - D_PEAO/2,4,15, 27, 26 ));
				pinos.add(15, new Pin(L_TAB - D_PEAO*2, L_TAB - L_BASE/2 - D_PEAO/2,4,16, 27, 26 ));			
	}

	public ArrayList<Pin> getList(){
		return this.pinos;
	}
}
