package gui;

import java.util.ArrayList;

public class ListCasas {
	
	public final int QUANT_CASAS = 59; 
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
		casas.add(new Casa(x , y, id));
		id++;
		
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
		
		System.out.println(id == QUANT_CASAS - 6);
		System.out.println(id);
		
		casas.get(0).setQtdPin(1);
		casas.get(14).setQtdPin(1);
		casas.get(27).setQtdPin(1);
		casas.get(40).setQtdPin(1);
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
		}
	    //verde
		else if(cor == 3){
		
			int x = L_BASE;
			int y = L_TAB - 2*L_CASA;
		
			coloridas.add(new Casa(x, y, id));
			id++;
			
			x += L_CASA;
			
			for(; y > L_TAB - L_BASE; y -= L_CASA){
				coloridas.add(new Casa(x , y, id));
				id++;
			}
		}
		//vermelho
		else if(cor == 4){
			
			int x = L_TAB - 2*L_CASA;
			int y = L_BASE + 2*L_CASA;
			
			coloridas.add(new Casa(x, y, id));
			id++;
			
			y -= L_CASA;
			
			for(; x > L_TAB - L_BASE; x -= L_CASA){
				coloridas.add(new Casa(x , y, id));
				id++;
			}
		}
	}
	
	public ArrayList<Casa> getListCasa(){
		return this.casas;
	}
	
	public ArrayList<Casa> getListColoridas(){
		return this.coloridas;
	}
}
