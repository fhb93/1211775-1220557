package gui;

import java.util.ArrayList;

public class ListCasas {
	public final ArrayList<Casa> casas =  new ArrayList<>();
	
	
	public ListCasas() {	
		casas.add(0, new Casa(0, 0));
		casas.add(1, new Casa(1, 1));
		casas.add(2, new Casa(2, 2));
		casas.add(3, new Casa(3, 3));
	}
	
	public ArrayList<Casa> getListCasa(){
		return this.casas;
	}
}
