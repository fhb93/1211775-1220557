package logic;

import java.util.ArrayList;

class ListCasas {
	
	private final int L_TAB = 715;
	private final int L_BASE = 285;
	private final int L_CASA = (L_TAB - (2*L_BASE)) / 3;
	private final int L_CENTRO = L_CASA * 3;
	private final ArrayList<Casa> casas =  new ArrayList<>();
	private final ArrayList<Casa> coloridas = new ArrayList<>();
	
	public ListCasas(){
		int id = 0;
		int x = L_CASA;
		int y = L_BASE;
		
		
		for (; x < L_BASE; x += L_CASA){ 		
			casas.add(new Casa(x, y, id));
			id++;
			
		}										
		
		y -= L_CASA;
		
		for(; y > 0 ; y -= L_CASA){ 			
			casas.add(new Casa(x, y, id));
			id++;
		}
		casas.add(new Casa(x, y, id));
		id++;
		x += L_CASA;
		casas.add(new Casa(x , y, id));
		id++;
		x += L_CASA;
		
		
		for(; y < L_BASE ; y += L_CASA){
			casas.add(new Casa(x , y, id));
			id++;
		}
		
		x += L_CASA;
	
		for(; x < L_BASE + L_CENTRO + 5*L_CASA; x+= L_CASA){
			casas.add(new Casa(x , y, id));
			id++;
		}
		
		casas.add(new Casa(x , y, id));
		id++;
		y += L_CASA;
		casas.add(new Casa(x , y, id));
		id++;
		y += L_CASA;
		casas.add(new Casa(x , y, id));
		id++;
		x-= L_CASA;
		
		for(; x > L_TAB - L_BASE ; x -= L_CASA){
			casas.add(new Casa(x , y, id));
			id++;
		}
		
		y += L_CASA;
		
		for(; y < L_TAB - L_CASA; y += L_CASA){
			casas.add(new Casa(x , y, id));
			id++;
		}
		
		casas.add(new Casa(x , y, id));
		id++;
		
		x -= L_CASA;
		casas.add(new Casa(x , y, id));
		id++;
		
		x -= L_CASA;

		for(; y > L_BASE + L_CENTRO; y -= L_CASA){
			casas.add(new Casa(x , y, id));
			id++;
		}
		
		casas.add(new Casa(x, y, id));
		id++;
		
		y -= L_CASA;
		x -= L_CASA;
		
		for(; x > 0; x -= L_CASA){
			casas.add(new Casa(x , y, id));
			id++;
		}
		
		casas.add(new Casa(x, y, id));
		id++;
		
		y -= L_CASA;
		casas.add(new Casa(x , y, id));
		id++;
		
		y -= L_CASA;
		casas.add(new Casa(x , y, id));
		id++;
		
		//limbo, posicao inicial dos pinos das bases
		casas.add(new Casa(1500, 1500, id));
				
		casas.get(0).setQtdPin(1);
		casas.get(13).setQtdPin(1);
		casas.get(26).setQtdPin(1);
		casas.get(39).setQtdPin(1);
		
		
		casas.get(9).setIsBlack(true);
		casas.get(22).setIsBlack(true);
		casas.get(35).setIsBlack(true);
		casas.get(48).setIsBlack(true);
	}
	
	public ListCasas(int cor){
		int id = 0;
		
		//amarelo
		if(cor == 1){
			int x = L_CASA;
			int y = L_BASE;
			
			coloridas.add(new Casa(x, y, id));
			id++;
			
			y += L_CASA;
		
			for(; x < L_BASE; x += L_CASA){
				coloridas.add(new Casa(x , y, id));
				id++;
			}
			coloridas.add(new Casa(x, y, id));
		}
		//azul
		else if(cor == 2){
		
			int x = L_BASE + 2*L_CASA;
			int y = L_CASA;
			
			coloridas.add(new Casa(x, y, id));
			id++;
			
			x -= L_CASA;
			
			for(; y < L_BASE; y += L_CASA){
				coloridas.add(new Casa(x , y, id));
				id++;
			}
			coloridas.add(new Casa(x, y, id));
		}
		//vermelho
		else if(cor == 3){
			
			int x = L_TAB - 2*L_CASA;
			int y = L_BASE + 2*L_CASA;
			
			coloridas.add(new Casa(x, y, id));
			id++;
			
			y -= L_CASA;
			
			for(; x > L_TAB - L_BASE; x -= L_CASA){
				coloridas.add(new Casa(x , y, id));
				id++;
			}
			coloridas.add(new Casa(x, y, id));
		}
	    //verde
		else if(cor == 4){
		
			int x = L_BASE;
			int y = L_TAB - 2*L_CASA;
		
			coloridas.add(new Casa(x, y, id));
			id++;
			
			x += L_CASA;
			
			for(; y > L_TAB - L_BASE; y -= L_CASA){
				coloridas.add(new Casa(x , y, id));
				id++;
			}
			coloridas.add(new Casa(x, y, id));
		}
	}
	
	public ArrayList<Casa> getListCasa(){
		return this.casas;
	}
	
	public ArrayList<Casa> getListColoridas(){
		return this.coloridas;
	}
}
