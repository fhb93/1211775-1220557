package gui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Pin{
	public  boolean voltaCompleta = false;
	private int x;
	private int y;
	private int player;
	private int id;
	private int idCasa;
	//	public Pin(){
	//		
	//	}

	public Pin(int x, int y, int player, int id){
		this.x = x;
		this.y = y;
		this.player = player;
		this.id = id;

	}


	public void handleSelectedPin(MouseEvent event, int valorDado, ListCasas casa, ListCasas coloridas) {
		System.out.println(event.getX() + " "+ event.getY());
		Rectangle bounds = new Rectangle(this.getX(), this.getY(), 40, 40);
		//				Rectangle bounds = new Rectangle(0, 0, 40, 40);
		if(bounds.contains(event.getX(), event.getY(), 1, 1)) {
			System.out.println("Clique no retangulo!");
			//pin.movePin(1, pins.getList().get(2).getId(), valorDado);

			if(valorDado < 52 && voltaCompleta != true) {
				Casa casaCorr = casa.getListCasa().get(valorDado);
				this.setX(casaCorr.getX());
				this.setY(casaCorr.getY());
				System.out.println(valorDado);
			}
			else if(valorDado >= 52 && voltaCompleta != true) { // se valorDado for maior e volta foi completa
				voltaCompleta = true; //definir uma volta completa
				valorDado -= 52;	//ao somatorio do valor do dado é subtraido o numero de casas brancas
			}
			else if(valorDado < 52 && voltaCompleta){						

				System.out.println(valorDado);
				Casa casaCorr = coloridas.getListColoridas().get(valorDado);
				this.setX(casaCorr.getX());
				this.setY(casaCorr.getY());

			}

		}
		else {
			System.out.println("fora do retangulo");
		}

	}




	/*
	public void movePin(int valorDado, int idPin, int idCasa){

		int difCasa = Ludo.casas.size() - idCasa;
		int dado;

		// ESSE CASO NAO FUNFA
		//caso: pino se encontra em casa branca e vai para a de sua cor

		if(difCasa < valorDado){
			dado = valorDado - difCasa;

			for(int i = 0; i < Ludo.pinos.size(); i++){
				if(Ludo.pinos.get(i).getId() == idPin){
					if(Ludo.pinos.get(i).getPlayer() == 1){
						Ludo.pinos.get(i).setX(Ludo.amarelas.get(dado).getX());
						Ludo.pinos.get(i).setY(Ludo.amarelas.get(dado).getY());
					}
				}
			}
		}
		else{

			//ESSE CASO FUNFA PERFEITAMENTE
			//caso mais simples: Pino se encontra em casa branca e vai para casa branca

			for(int i = 0; i < Ludo.pinos.size(); i++){
				if(Ludo.pinos.get(i).getId() == idPin)
				{
					for(int j = 0; j < Ludo.casas.size(); j++){
						if(Ludo.casas.get(j).getId() == Ludo.pinos.get(i).getIdCasa()){
							j = j + valorDado;
							Ludo.pinos.get(i).setX(Ludo.casas.get(j).getX());
							Ludo.pinos.get(i).setY(Ludo.casas.get(j).getY());
						}
					}
				}
			}

		}	
	}*/


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



}
