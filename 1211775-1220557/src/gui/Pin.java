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
	//	public Pin(){
	//		
	//	}

	public Pin(int x, int y, int player, int id, int cInicial, int cFinal, int cor){
		this.x = x;
		this.y = y;
		this.player = player;
		this.id = id;
		this.casaInicial = cInicial;
		this.casaFinal = cFinal;
		//MUDANDO A PARTIR DAQUI
		this.setCor(cor);
	}

	public boolean pinSelected(Point p) {
		Rectangle bounds = new Rectangle(this.getX(), this.getY(), 40, 40);
		return bounds.contains(p);
	}



	public boolean testPinMovability(int valorDado, int casaInicial, ArrayList<Casa> casas, PinList pins) {
	
		for(int i = 0; i < pins.getList().size(); i++) {
			if(pins.getList().get(i).getIdCasa() == casaInicial + valorDado && pins.getList().get(i).getCor() != this.cor) {
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
						if(pins.getList().get(j).getCor() != this.cor) {
							System.out.println("barreira cor diferente");
							return false;
						}
					}
					
				}
			}
			
		}
		captured = capturePin(casas, valorDado, casaInicial);
		
		return true;
		
		/*	int casaInicialTemp = casaInicial;


		if(valorDado + casaInicial >= casas.size())
		{
			valorDado = (casaInicial + valorDado) - casas.size();
		}
		else {

			//verifica se o pino vai exatamente para uma barreira, independente da cor
			if(casas.get(valorDado + casaInicial).getQtdPin() == 2) {

				return false;
			}


			//verifica se ha uma barreira de 2 pinos, entre a casa inicial e a destino e impede o movimento
			for(int i = casaInicialTemp + 1; i < valorDado + casaInicialTemp + 1; i++) {

				//			if(casaInicial + valorDado > casas.size()) {
				//				casaInicialTemp = casas.get(0).getId();
				//			}
				if(casas.get(i).getQtdPin() == 2) {
					for(int j = 0; j < 16; j++) {
						if(pins.getList().get(j).getId() == i )
							if(pins.getList().get(j).getCor() != this.cor)
								return false;
					}
				}
			}

			// checa se ha um pino em alguma casa entre a casa corrente e a destino
			if(casas.get(valorDado + casaInicial).getQtdPin() == 0) {
				for(int i = casaInicial + 1; i < valorDado + casaInicial; i++) {
					if(casas.get(i).getQtdPin() != 0) {
						for(int j = 0; j < 16; j++) {
							if(pins.getList().get(j).getIdCasa() == casas.get(i).getId()) {
								if(pins.getList().get(j).getCor() != this.cor) {
									captured = capturePin(casas, valorDado, casaInicial);
									if(captured)
										System.out.println("capturou");
									return true;
								}
							}
						}
						//return true;
					}
				}
			}


			// checa se ha um pino de cor diferente na casa dstino
			if(casas.get(valorDado + casaInicial).getQtdPin() == 1 ) {
				for(int i = 0; i < 16; i++) {
					if(pins.getList().get(i).getIdCasa() == this.casaInicial + valorDado  ) {
						if(pins.getList().get(i).player != this.player) {
							System.out.println("Impossivel mover nesse caso!");
							return false;
						}
					}
				}
			}
		}
		return true;
		*/
	}

	public void handleSelectedPin(MouseEvent event, int valorDado, int valorAgregado, ArrayList<Casa> casas, ArrayList<Casa> coloridas, int player) {
		System.out.println(event.getX() + " "+ event.getY());
		Rectangle bounds = new Rectangle(this.getX(), this.getY(), 40, 40);
		//				Rectangle bounds = new Rectangle(0, 0, 40, 40);
		if(bounds.contains(event.getX(), event.getY(), 1, 1)) {
			System.out.println("Clique no retangulo!");
			//pin.movePin(1, pins.getList().get(2).getId(), valorDado);


			if(valorAgregado < 52 && !this.voltaCompleta && this.testPinMovability(valorDado, this.getCasaInicial(), casas, Ludo.pins)) {
				// da a volta na lista de casas, para nao estourar a lista
				if(player > 1 && this.idCasa + valorDado > 52){
					int newindex = (this.idCasa + valorDado) - 52;
					this.setX(casas.get(newindex - 1).getX());
					this.setY(casas.get(newindex - 1).getY());
					this.setIdCasa(casas.get(newindex - 1).getId());
					valorAgregado += valorDado;
					this.setCasasAndadas(this.getCasasAndadas() + valorDado);
					this.setCasaInicial(newindex - 1);
				}
				else{

					//efetua o movimento

					//					captured = capturePin(casas, valorDado, player);
					//					if(captured) {
					//						boolean inutil = capturePin(casas, valorDado, player);
					//						System.out.println("capturou!");
					//					}
					if(captured) {
						System.out.println("capturou!");
					}


					casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
					this.setX(casas.get(this.getCasaInicial() + valorDado).getX());
					this.setY(casas.get(this.getCasaInicial() + valorDado).getY());

					//nova casa inicial apos movimento
					this.casaInicial += valorDado;
					casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() + 1);
					this.setIdCasa(casas.get(casaInicial).getId());
					this.setCasasAndadas(this.getCasasAndadas() + valorDado);
					System.out.println(valorDado);


				}
			}

			//reconhece volta completa e passa o pino para o caminho de sua cor
			else if(valorAgregado > 52 && this.voltaCompleta != true) { // se valorDado for maior e volta foi completa
				this.voltaCompleta = true; //definir uma volta completa

				//if(this.getIdCasa() + valorDado > this.getCasaFinal()){
				int newindex = (this.casaInicial + valorDado) - this.casaFinal;
				this.setX(coloridas.get(newindex - 1).getX());
				this.setY(coloridas.get(newindex - 1).getY());
				this.setCasaInicial(newindex - 1);
				this.setCasasAndadas(this.getCasasAndadas() + valorDado);
				//}

			}

			else if(valorAgregado > 52 && this.voltaCompleta == true){
				if(this.casaInicial + valorDado > coloridas.size() - 1){
					if(player == 1){
						this.setX(L_BASE);
						this.setY(L_BASE + L_CASA);
					}
					else if(player == 2){
						this.setX(L_BASE + L_CASA);
						this.setY(L_BASE);
					}
					else if(player == 3){
						this.setX(L_BASE + 2*L_CASA);
						this.setY(L_BASE + L_CASA);
					}
					else if(player == 4){
						this.setX(L_BASE + L_CASA);
						this.setY(L_BASE + 2*L_CASA);
					}
				}
				else{
					this.setX(coloridas.get(this.casaInicial + valorDado).getX());
					this.setY(coloridas.get(this.casaInicial + valorDado).getY());
					this.setCasaInicial(this.casaInicial + valorDado);
					this.setCasasAndadas(this.casasAndadas+ valorDado);
				}
			}
		}
		else {
			System.out.println("fora do retangulo");
		}
		Ludo.diceClicked = false;
	}


	public boolean capturePin(ArrayList<Casa> casas, int valorDado, int casaInicial) {
		
		for(int i = casaInicial + 1; i < valorDado + casaInicial - 1; i++) {
			for(int j = 0; j < 16; j++) {
				if(casas.get(i).getId() == Ludo.pins.getList().get(j).getCasaInicial()) {
					if(Ludo.pins.getList().get(j).getPlayer() != this.player) {
						if(Ludo.pins.getList().get(j).getPlayer() == 1) {
							Ludo.pins.getList().get(j).setX(L_CASA);
							Ludo.pins.getList().get(j).setY(L_BASE);

							return true;
						}
						if(Ludo.pins.getList().get(j).getPlayer() == 2) {
							Ludo.pins.getList().get(j).setX(L_BASE + 2 * L_CASA);
							Ludo.pins.getList().get(j).setY(L_CASA);
							return true;
						} 
						if(Ludo.pins.getList().get(j).getPlayer() == 3) {
							Ludo.pins.getList().get(j).setX(L_TAB - 2 * L_CASA);
							Ludo.pins.getList().get(j).setY(L_BASE + 2 * L_CASA);
							return true;
						}
						if(Ludo.pins.getList().get(j).getPlayer() == 4) {
							Ludo.pins.getList().get(j).setX(L_BASE);
							Ludo.pins.getList().get(j).setY(L_TAB - 2 * L_CASA);
							return true;
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
