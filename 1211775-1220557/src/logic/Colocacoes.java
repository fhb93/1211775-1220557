package logic;

import java.util.ArrayList;

class Colocacoes {
	private ArrayList<Vencedor> colocacoes = new ArrayList<Vencedor>();
	public Colocacoes(){
		
		colocacoes.add(new Vencedor());
		colocacoes.add(new Vencedor());
		colocacoes.add(new Vencedor());
		colocacoes.add(new Vencedor());
		
		colocacoes.get(0).setCor(1);
		colocacoes.get(1).setCor(2);
		colocacoes.get(2).setCor(3);
		colocacoes.get(3).setCor(4);
	}
	
	public ArrayList<Vencedor> getList(){
		return this.colocacoes;
	}
}
