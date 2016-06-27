package logic;

import java.util.ArrayList;

class PinList {

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
		pinos.add(new Pin(L_CASA, L_BASE, 1, 1, 0, 51, 1, true)); 			// 1 e 52 são os ids da primeira e ultima casa
		pinos.add(new Pin(L_BASE/2 - D_PEAO/2, L_BASE - 2*D_PEAO,1,2, 52, 51, 1, false));
		pinos.add( new Pin(D_PEAO, L_BASE/2 - D_PEAO/2,1,3, 52, 51, 1, false));
		pinos.add( new Pin(L_BASE - D_PEAO*2, L_BASE/2 - D_PEAO/2,1,4, 52, 51, 1, false));

		//azuis = player 2
		pinos.add( new Pin(L_BASE + 2 * L_CASA, L_CASA ,2,5, 13, 12, 2, true));
		pinos.add(new Pin(L_TAB - L_BASE/2 - D_PEAO/2, 206,2,6, 52, 12, 2, false));
		pinos.add( new Pin(L_TAB - L_BASE + D_PEAO, 128,2,7, 52, 12, 2, false));
		pinos.add( new Pin(L_TAB - D_PEAO*2, 128,2,8, 52, 12, 2, false));

		//vermelhos = player 3
		pinos.add(new Pin(L_TAB - 2 * L_CASA, L_BASE + 2 * L_CASA,4,13, 26, 25, 3, true));
		pinos.add(new Pin(L_TAB - L_BASE/2 - D_PEAO/2, L_TAB - D_PEAO*2,4,14, 52, 25, 3, false));
		pinos.add( new Pin(L_TAB - L_BASE + D_PEAO, L_TAB - L_BASE/2 - D_PEAO/2,4,15, 52, 25, 3, false));
		pinos.add(new Pin(L_TAB - D_PEAO*2, L_TAB - L_BASE/2 - D_PEAO/2,4,16, 52, 25, 3, false));			

		//verdes = player 4
		pinos.add(new Pin(L_BASE, L_TAB - 2 * L_CASA,3,9, 39, 38, 4, true));
		pinos.add(new Pin(L_BASE/2 - D_PEAO/2, L_TAB - D_PEAO*2,3,10, 52, 38, 4, false));
		pinos.add(new Pin(D_PEAO, L_TAB - L_BASE/2 - D_PEAO/2,3,11, 52, 38, 4, false));
		pinos.add( new Pin(L_BASE - D_PEAO*2, L_TAB - L_BASE/2 - D_PEAO/2,3,11, 52, 38,4, false));

	}

	ArrayList<Pin> getList(){
		return this.pinos;
	}
}
