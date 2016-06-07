package gui;

public class Casa {
	private int qtdPin;
	private int x;
	private int y;
	private int id;
	
	
	public Casa(int x, int y, int id) {
		setQtdPin(0);
		setX(x);
		setY(y);
		setId(id);
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	
	public int getQtdPin() {
		return qtdPin;
	}
	public void setQtdPin(int qtdPin) {
		this.qtdPin = qtdPin;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
