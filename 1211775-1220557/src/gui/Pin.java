package gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Pin{
	final int L_TAB = 715;
	final int L_BASE = 285;
	final int L_CASA = (L_TAB - (2*L_BASE)) / 3;
	final int L_CENTRO = L_CASA * 3;
	final int D_PEAO = 40;




	public boolean voltaCompleta = false;
	private int x;
	private int y;
	private int player;
	private int id;
	private int idCasa;
	private int casaInicial;
	private int casaFinal;
	private int casasAndadas = 0;
	private int cor;
	public static boolean captured = false;
	public boolean isOnPath;


	public Pin(int x, int y, int player, int id, int cInicial, int cFinal, int cor, boolean onPath){
		this.x = x;
		this.y = y;
		this.player = player;
		this.id = id;
		this.casaInicial = cInicial;
		this.casaFinal = cFinal;
		this.setCor(cor);
		isOnPath = onPath;
		
	}

	public boolean pinSelected(Point p) {
		Rectangle bounds = new Rectangle(this.getX(), this.getY(), 40, 40);
		return bounds.contains(p);
	}



	public boolean testPinMovability(int valorDado, int casaInicial, ArrayList<Casa> casas, PinList pins, Pin selectedPin) {
			int valorDadoDepois;

			
			if(valorDado + casaInicial > 52){
				valorDadoDepois = (valorDado + casaInicial) - 52;
				//verifica se ha pino de outra cor na casa destino
				for(int i = 0; i < 16; i++){
					if(casas.get(valorDadoDepois - 1).getId() == pins.getList().get(i).getIdCasa() && pins.getList().get(i).getCor() != selectedPin.cor){
						System.out.println("Impossivel mover");
						return false;
					}
				}
				
				//verifica se ha barreira na casa de destino, independente da cor
				if(casas.get(valorDadoDepois - 1).getQtdPin() == 2){
					System.out.println("nao move");
					return false;
				}
				
				//verifica se ha barreira de cor diferente antes da virada da lista
				for(int i = casaInicial + 1 ; i < 51; i++){
					if(casas.get(i).getQtdPin() == 2) {
						for(int j = 0; j < 16; j++) {
							if(pins.getList().get(j).getIdCasa() == casas.get(i).getId()) {
								if(pins.getList().get(j).getCor() != selectedPin.cor) {
									System.out.println("barreira cor diferente");
									return false;
								}
							}
							
						}
					}
				}
				
				//verifica se ha barreira de cor diferente apos a virada da lista
				for(int i = 0; i < valorDadoDepois - 1; i++){
					if(casas.get(i).getQtdPin() == 2) {
						for(int j = 0; j < 16; j++) {
							if(pins.getList().get(j).getIdCasa() == casas.get(i).getId()) {
								if(pins.getList().get(j).getCor() != selectedPin.cor) {
									System.out.println("barreira cor diferente");
									return false;
								}
							}
						}
					}
				}
			}
			else{			
				for(int i = 0; i < pins.getList().size(); i++) {
					if(pins.getList().get(i).getIdCasa() == casaInicial + valorDado && pins.getList().get(i).getCor() != selectedPin.cor) {
						System.out.println("impossivel mover");
						return false;
					}
				}
				if(casas.get(valorDado + casaInicial).getQtdPin() == 2) {
					System.out.println("nao move");
					return false;
				}
				for(int i = casaInicial + 1; i < casaInicial + valorDado - 1; i++) {
					if(casas.get(i).getQtdPin() == 2) {
						for(int j = 0; j < 16; j++) {
							if(pins.getList().get(j).getIdCasa() == casas.get(i).getId()) {
								if(pins.getList().get(j).getCor() != selectedPin.cor) {
									System.out.println("barreira cor diferente");
									return false;
								}
							}
							
						}
					}
					
				}
			}
			captured = capturePin(casas, valorDado, casaInicial, selectedPin);
			
			return true;
	}
		

	public void handleSelectedPin(MouseEvent event, int valorDado, int valorAgregado, ArrayList<Casa> casas, ArrayList<Casa> coloridas, int player) {
		System.out.println(event.getX() + " "+ event.getY());
		Rectangle bounds = new Rectangle(this.getX(), this.getY(), 40, 40);
		Pin selectedPin = this;
		
		if(bounds.contains(event.getX(), event.getY(), 1, 1)) {
			System.out.println("Clique no retangulo!");
		
			if(casas.get(casaInicial).getQtdPin() == 2){
				for(int i = 0; i < 16; i++){
					if(Ludo.pinos.get(i).getCasaInicial() == casas.get(casaInicial).getId()){
						selectedPin = Ludo.pinos.get(i);
					}
				}
			}
			
			if(valorDado == 5 && !selectedPin.isOnPath) {
				if(player == 1) {
					selectedPin.setX(casas.get(0).getX());
					selectedPin.setY(casas.get(0).getY());
					casas.get(0).setQtdPin(casas.get(0).getQtdPin() + 1);
					selectedPin.isOnPath = true;
					selectedPin.setCasaInicial(0);
				}
				if(player == 2) {
					selectedPin.setX(casas.get(14).getX());
					selectedPin.setY(casas.get(14).getY());
					casas.get(14).setQtdPin(casas.get(14).getQtdPin() + 1);
					selectedPin.isOnPath = true;
					selectedPin.setCasaInicial(14);
				}
				if(player == 3) {
					selectedPin.setX(casas.get(27).getX());
					selectedPin.setY(casas.get(27).getY());
					casas.get(27).setQtdPin(casas.get(27).getQtdPin() + 1);
					selectedPin.isOnPath = true;
					selectedPin.setCasaInicial(27);
				}
				if(player == 4) {
					selectedPin.setX(casas.get(40).getX());
					selectedPin.setY(casas.get(40).getY());
					casas.get(40).setQtdPin(casas.get(40).getQtdPin() + 1);
					selectedPin.isOnPath = true;
					selectedPin.setCasaInicial(40);
				}
				return;
			}

			if(valorAgregado < 52 && !selectedPin.voltaCompleta && selectedPin.testPinMovability(valorDado, selectedPin.getCasaInicial(), casas, Ludo.pins, selectedPin)) {
				// da a volta na lista de casas, para nao estourar a lista
				if(player > 1 && selectedPin.idCasa + valorDado > 52){
					int newindex = (selectedPin.idCasa + valorDado) - 52;
					selectedPin.setX(casas.get(newindex - 1).getX());
					selectedPin.setY(casas.get(newindex - 1).getY());
					selectedPin.setIdCasa(casas.get(newindex - 1).getId());
					valorAgregado += valorDado;
					selectedPin.setCasasAndadas(selectedPin.getCasasAndadas() + valorDado);
					selectedPin.setCasaInicial(newindex - 1);
				}
				else{
					if(captured) {
						System.out.println("capturou!");
					}


					casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
					selectedPin.setX(casas.get(selectedPin.getCasaInicial() + valorDado).getX());
					selectedPin.setY(casas.get(selectedPin.getCasaInicial() + valorDado).getY());

					//nova casa inicial apos movimento
					selectedPin.casaInicial += valorDado;
					casas.get(selectedPin.casaInicial).setQtdPin(casas.get(selectedPin.casaInicial).getQtdPin() + 1);
					selectedPin.setIdCasa(casas.get(casaInicial).getId());
					selectedPin.setCasasAndadas(selectedPin.getCasasAndadas() + valorDado);
					System.out.println(valorDado);


				}
			}

			//reconhece volta completa e passa o pino para o caminho de sua cor
			else if(valorAgregado > 52 && selectedPin.voltaCompleta != true) { // se valorDado for maior e volta foi completa
				selectedPin.voltaCompleta = true; //definir uma volta completa

				casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
				int newindex = (selectedPin.casaInicial + valorDado) - selectedPin.casaFinal;
				selectedPin.setX(coloridas.get(newindex - 1).getX());
				selectedPin.setY(coloridas.get(newindex - 1).getY());
				selectedPin.setCasaInicial(newindex - 1);
				casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() + 1);
				selectedPin.setCasasAndadas(selectedPin.getCasasAndadas() + valorDado);

			}

			else if(valorAgregado > 52 && selectedPin.voltaCompleta == true){
				if(selectedPin.casaInicial + valorDado > coloridas.size() - 1){
					if(player == 1){
						selectedPin.setX(L_BASE);
						selectedPin.setY(L_BASE + L_CASA);
						casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
						
					}
					else if(player == 2){
						selectedPin.setX(L_BASE + L_CASA);
						selectedPin.setY(L_BASE);
						casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
					}
					else if(player == 3){
						selectedPin.setX(L_BASE + 2*L_CASA);
						selectedPin.setY(L_BASE + L_CASA);
						casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
					}
					else if(player == 4){
						selectedPin.setX(L_BASE + L_CASA);
						selectedPin.setY(L_BASE + 2*L_CASA);
						casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
					}
				}
				else{
					casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
					selectedPin.setX(coloridas.get(selectedPin.casaInicial + valorDado).getX());
					selectedPin.setY(coloridas.get(selectedPin.casaInicial + valorDado).getY());
					selectedPin.setCasaInicial(selectedPin.casaInicial + valorDado);
					casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() + 1);
					selectedPin.setCasasAndadas(selectedPin.casasAndadas+ valorDado);
				}
			}
		}
		else {
			System.out.println("fora do retangulo");
		}
		Ludo.diceClicked = false;
	}


	public boolean capturePin(ArrayList<Casa> casas, int valorDado, int casaInicial, Pin selectedPin) {
		int valorDadoDepois;

		if(valorDado + casaInicial > 52){
			valorDadoDepois = (valorDado + casaInicial) - 52;			

			//abrigo
			for(int i = casaInicial + 1; i < 51; i++){
				if(casas.get(i).getIsBlack() == true){
					for(int j = 0; j < 16; j++){
						if(Ludo.pinos.get(j).getCasaInicial() == casas.get(i).getId() && Ludo.pinos.get(j).getCor() != selectedPin.cor){
							return false;
						}
					}

				}
			}
			
			//captura antes da virada da lista
			for(int i = casaInicial + 1; i < 51; i++) {
				for(int j = 0; j < 16; j++) {
					if(casas.get(i).getId() == Ludo.pins.getList().get(j).getCasaInicial()) {
						if(Ludo.pins.getList().get(j).getPlayer() != selectedPin.player) {
							if(Ludo.pins.getList().get(j).getPlayer() == 1) {
								Ludo.pins.getList().get(j).setX(L_CASA);
								Ludo.pins.getList().get(j).setY(L_BASE);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(0).setQtdPin(casas.get(0).getQtdPin() + 1);

								return true;
							}
							if(Ludo.pins.getList().get(j).getPlayer() == 2) {
								Ludo.pins.getList().get(j).setX(L_BASE + 2 * L_CASA);
								Ludo.pins.getList().get(j).setY(L_CASA);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(14).setQtdPin(casas.get(14).getQtdPin() + 1);
								
								return true;
							} 
							if(Ludo.pins.getList().get(j).getPlayer() == 4) {
								Ludo.pins.getList().get(j).setX(L_TAB - 2 * L_CASA);
								Ludo.pins.getList().get(j).setY(L_BASE + 2 * L_CASA);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(27).setQtdPin(casas.get(27).getQtdPin() + 1);
								
								return true;
							}
							if(Ludo.pins.getList().get(j).getPlayer() == 3) {
								Ludo.pins.getList().get(j).setX(L_BASE);
								Ludo.pins.getList().get(j).setY(L_TAB - 2 * L_CASA);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(40).setQtdPin(casas.get(40).getQtdPin() + 1);
								return true;
							}
						}

					}
				}
			}
			
			//captura depois da virada da lista
			for(int i = 0; i < valorDadoDepois - 1; i++) {
				for(int j = 0; j < 16; j++) {
					if(casas.get(i).getId() == Ludo.pins.getList().get(j).getCasaInicial()) {
						if(Ludo.pins.getList().get(j).getPlayer() != selectedPin.player) {
							if(Ludo.pins.getList().get(j).getPlayer() == 1) {
								Ludo.pins.getList().get(j).setX(L_CASA);
								Ludo.pins.getList().get(j).setY(L_BASE);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(0).setQtdPin(casas.get(0).getQtdPin() + 1);

								return true;
							}
							if(Ludo.pins.getList().get(j).getPlayer() == 2) {
								Ludo.pins.getList().get(j).setX(L_BASE + 2 * L_CASA);
								Ludo.pins.getList().get(j).setY(L_CASA);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(14).setQtdPin(casas.get(14).getQtdPin() + 1);
								
								return true;
							} 
							if(Ludo.pins.getList().get(j).getPlayer() == 4) {
								Ludo.pins.getList().get(j).setX(L_TAB - 2 * L_CASA);
								Ludo.pins.getList().get(j).setY(L_BASE + 2 * L_CASA);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(27).setQtdPin(casas.get(27).getQtdPin() + 1);
								
								return true;
							}
							if(Ludo.pins.getList().get(j).getPlayer() == 3) {
								Ludo.pins.getList().get(j).setX(L_BASE);
								Ludo.pins.getList().get(j).setY(L_TAB - 2 * L_CASA);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(40).setQtdPin(casas.get(40).getQtdPin() + 1);
								
								return true;
							}
						}

					}
				}
			}
			
			
		}

		else{
			//abrigo
			for(int i = casaInicial + 1; i < valorDado + casaInicial - 1; i++){
				if(casas.get(i).getIsBlack() == true){
					for(int j = 0; j < 16; j++){
						if(Ludo.pinos.get(j).getCasaInicial() == casas.get(i).getId() && Ludo.pinos.get(j).getCor() != selectedPin.cor){
							return false;
						}
					}

				}
			}

			for(int i = casaInicial + 1; i < valorDado + casaInicial; i++) {
				for(int j = 0; j < 16; j++) {
					if(casas.get(i).getId() == Ludo.pins.getList().get(j).getCasaInicial()) {
						if(Ludo.pins.getList().get(j).getPlayer() != selectedPin.player) {
							if(Ludo.pins.getList().get(j).getPlayer() == 1) {
								Ludo.pins.getList().get(j).setX(L_CASA);
								Ludo.pins.getList().get(j).setY(L_BASE);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(0).setQtdPin(casas.get(0).getQtdPin() + 1);

								return true;
							}
							if(Ludo.pins.getList().get(j).getPlayer() == 2) {
								Ludo.pins.getList().get(j).setX(L_BASE + 2 * L_CASA);
								Ludo.pins.getList().get(j).setY(L_CASA);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(14).setQtdPin(casas.get(14).getQtdPin() + 1);
								
								return true;
							} 
							if(Ludo.pins.getList().get(j).getPlayer() == 4) {
								Ludo.pins.getList().get(j).setX(L_TAB - 2 * L_CASA);
								Ludo.pins.getList().get(j).setY(L_BASE + 2 * L_CASA);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(27).setQtdPin(casas.get(27).getQtdPin() + 1);
								
								return true;
							}
							if(Ludo.pins.getList().get(j).getPlayer() == 3) {
								Ludo.pins.getList().get(j).setX(L_BASE);
								Ludo.pins.getList().get(j).setY(L_TAB - 2 * L_CASA);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(40).setQtdPin(casas.get(40).getQtdPin() + 1);
								
								return true;
							}
						}

					}
				}
			}
		}
		return false;
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
		this.casasAndadas = value;

	}


	public int getCasasAndadas() {
		return this.casasAndadas;
	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}

}