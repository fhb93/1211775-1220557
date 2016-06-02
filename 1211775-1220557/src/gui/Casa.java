package gui;

public class Casa {
	private static int qtdPin;
	private double x;
	private double y;
	
	public Casa(double x, double y) {
		setQtdPin(0);
		setX(x);
		setY(y);
	}
	
	public static int getQtdPin() {
		return qtdPin;
	}
	public static void setQtdPin(int qtdPin) {
		Casa.qtdPin = qtdPin;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
