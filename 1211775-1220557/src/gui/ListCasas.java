package gui;

import java.util.ArrayList;

public class ListCasas {
	
	public final int QUANT_CASAS = 57; 
	final int L_TAB = 715;
	final int L_BASE = 285;
	final int L_CASA = (L_TAB - (2*L_BASE)) / 3;
	final int L_CENTRO = L_CASA * 3;
	
	public final ArrayList<Casa> casas =  new ArrayList<>();
	public final ArrayList<Casa> coloridas = new ArrayList<>();
	
	public ListCasas(){
		int x = L_CASA;
		int y = L_BASE;
		
		
		for (; x < L_BASE; x += L_CASA){
			 casas.add(new Casa(x, y));
		}
		for(; y == 0 ; y -= L_CASA){
			casas.add(new Casa(x, y));
		}
		
		x += L_CASA;
		casas.add(new Casa(x , y));
		x += L_CASA;
		casas.add(new Casa(x , y));
		
		for(; y < L_BASE ; y += L_CASA){
			casas.add(new Casa(x , y));
		}
		
		x += L_CASA;
	
		for(; x < L_TAB; x+= L_CASA){
			casas.add(new Casa(x , y));
		}
		
		y += L_CASA;
		casas.add(new Casa(x , y));
		y += L_CASA;
		casas.add(new Casa(x , y));
		
		for(; x == L_TAB - L_BASE ; x -= L_CASA){
			casas.add(new Casa(x , y));
		}
		
		x -= L_CASA;
		y -= L_CASA;
		
		for(; y == L_TAB - L_CASA; y += L_CASA){
			casas.add(new Casa(x , y));
		}
		x -= L_CASA;
		casas.add(new Casa(x , y));
		x -= L_CASA;
		casas.add(new Casa(x , y));
		
		for(; y == L_BASE + L_CENTRO; y -= L_CASA){
			casas.add(new Casa(x , y));
		}
		
		y -= L_CASA;
	
		for(; x == 0; x -= L_CASA){
			casas.add(new Casa(x , y));
		}
		
		y -= L_CASA;
		casas.add(new Casa(x , y));
		y -= L_CASA;
		casas.add(new Casa(x , y));
		
	}
	
	public ListCasas(int cor){
		
		if(cor == 1){
			int x = L_CASA;
			int y = L_BASE + L_CASA;
			
			for(; x < L_BASE; x += L_CASA){
				coloridas.add(new Casa(x , y));
			}
		}
		else if(cor == 2){
		
			int x = L_BASE + L_CASA;
			int y = L_CASA;
		
			for(; y < L_BASE; y += L_CASA){
				casas.add(new Casa(x , y));
			}
		}
		else if(cor == 3){
		
			int x = L_TAB - 2*L_CASA;
			int y = L_BASE + L_CASA;
		
			for(; x == L_TAB - L_BASE; x -= L_CASA){
				casas.add(new Casa(x , y));
			}
		}
		else if(cor == 4){
		
			int x = L_BASE + L_CASA;
			int y = L_TAB - 2*L_CASA;
		
			for(; y == L_TAB - L_BASE; y -= L_CASA){
				casas.add(new Casa(x , y));
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
