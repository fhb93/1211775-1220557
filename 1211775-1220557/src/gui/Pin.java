package gui;

public class Pin{

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

	public void movePin(int valorDado, int idPin, int idCasa){

		int dado = Ludo.casas.size() - idCasa;

		//caso: pino se encontra em casa branca e vai para a de sua cor

		if(dado < valorDado){
			for(int i = 0; i < Ludo.pinos.size(); i++)
				if(Ludo.pinos.get(i).getId() == idPin){
					if(Ludo.pinos.get(i).getPlayer() == 1){
						Ludo.pinos.get(i).setX(Ludo.amarelas.get(dado).getX());
						Ludo.pinos.get(i).setY(Ludo.amarelas.get(dado).getY());
					}
					else if(Ludo.pinos.get(i).getPlayer() == 2){
						Ludo.pinos.get(i).setX(Ludo.azuis.get(dado).getX());
						Ludo.pinos.get(i).setY(Ludo.azuis.get(dado).getY());
					}
					else if(Ludo.pinos.get(i).getPlayer() == 3){
						Ludo.pinos.get(i).setX(Ludo.verdes.get(dado).getX());
						Ludo.pinos.get(i).setY(Ludo.verdes.get(dado).getY());
					}
					else {
						Ludo.pinos.get(i).setX(Ludo.vermelhas.get(dado).getX());
						Ludo.pinos.get(i).setY(Ludo.vermelhas.get(dado).getY());
					}
				}
		}

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
