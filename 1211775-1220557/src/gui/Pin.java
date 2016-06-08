package gui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Pin{
	public boolean voltaCompleta = false;
	private int x;
	private int y;
	private int player;
	private int id;
	private int idCasa;
	private int casaInicial;
	private int casaFinal;
	private int casasAndadas;

	public Pin(int x, int y, int player, int id, int cInicial, int cFinal){
		this.x = x;
		this.y = y;
		this.player = player;
		this.id = id;
		this.casaInicial = cInicial;
		this.casaFinal = cFinal;
	}

	public void handleSelectedPin(MouseEvent event, int valorDado, int valorAgregado, ArrayList<Casa> casas, ArrayList<Casa> coloridas) {
		System.out.println(event.getX() + " "+ event.getY());
		Rectangle bounds = new Rectangle(this.getX(), this.getY(), 40, 40);
		//				Rectangle bounds = new Rectangle(0, 0, 40, 40);
		if(bounds.contains(event.getX(), event.getY(), 1, 1)) {
			System.out.println("Clique no retangulo!");
			//pin.movePin(1, pins.getList().get(2).getId(), valorDado);

			if(valorAgregado < 52 && voltaCompleta != true) {
				//Casa casaDestino = casa.getListCasa().get(valorDado);
				//this.setX(casaDestino.getX());
				this.setX(casas.get(this.getCasaInicial() + valorDado).getX());
				this.setY(casas.get(this.getCasaInicial() + valorDado).getY());
				this.casaInicial += valorDado;
				
				System.out.println(valorDado);
			}
			else if(valorAgregado >= 52 && voltaCompleta != true) { // se valorDado for maior e volta foi completa
				voltaCompleta = true; //definir uma volta completa
				valorAgregado -= 52;	//ao somatorio do valor do dado Ã© subtraido o numero de casas brancas
			}
			else if(valorAgregado < 52 && voltaCompleta){						

				System.out.println(valorDado);
				Casa casaDestino = coloridas.get(valorDado);
				this.setX(casaDestino.getX());
				this.setY(casaDestino.getY());

			}

		}
		else {
			System.out.println("fora do retangulo");
		}
		Ludo.diceClicked = false;
	}
	


	void setX( int x){
		this.x = x;
	}

	int getX(){
		return this.x;
	}

	void setY(int y){
		this.y = y;
	}

	int getY(){
		return this.y;
	}

	void setPlayer(int player){
		this.player = player;
	}

	int getPlayer(){
		return this.player;
	}

	void setId(int id){
		this.id = id;
	}

	int getId(){
		return this.id;
	}
	public void setIdCasa(int idCasa){
		this.idCasa = idCasa;
	}

	public int getIdCasa(){
		return this.idCasa;
	}


	public int getCasaInicial() {
		return casaInicial;
	}


	public void setCasaInicial(int casaInicial) {
		this.casaInicial = casaInicial;
	}


	public int getCasaFinal() {
		return this.casaFinal;
	}


	public void setCasaFinal(int casaFinal) {
		this.casaFinal = casaFinal;
	}


	public void setCasasAndadas(int value) {
		this.casasAndadas += value;

	}


	public int getCasasAndadas() {
		return this.casasAndadas;
	}



}
