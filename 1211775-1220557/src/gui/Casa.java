package gui;

public class Casa {
	private int qtdPin;
	private int x;
	private int y;
	private int id;
	private boolean isBlack;
	
	public Casa(int x, int y, int id) {
		setQtdPin(0);
		setX(x);
		setY(y);
		setId(id);
		setIsBlack(false);
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
	
	public boolean getIsBlack() {
		return isBlack;
	}
	
	public void setIsBlack(boolean b) {
		isBlack = b;
	}

	
}
