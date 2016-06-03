package gui;

public class Casa {
	private static int qtdPin;
	private int x;
	private int y;
	private int id;
	
	
	public Casa(int x, int y) {
		setQtdPin(0);
		setX(x);
		setY(y);
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public static int getQtdPin() {
		return qtdPin;
	}
	public static void setQtdPin(int qtdPin) {
		Casa.qtdPin = qtdPin;
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
