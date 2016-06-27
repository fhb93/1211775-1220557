package logic;

public class Vencedor {
	private int colocacao;
	private int cor;
	
	public Vencedor(){
		setColocacao(0);
	}
	
	public void setCor(int cor){
		this.cor = cor;
	}
	
	public int getCor(){
		return this.cor;
	}
	
	public void setColocacao(int posicao){
		this.colocacao = posicao;
	}
	
	public int getColocacao(){
		return this.colocacao;
	}
}
