package logic;

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
	public boolean isOnColorPath;


	public Pin(int x, int y, int player, int id, int cInicial, int cFinal, int cor, boolean onPath){
		this.x = x;
		this.y = y;
		this.player = player;
		this.id = id;
		this.casaInicial = cInicial;
		this.casaFinal = cFinal;
		this.setCor(cor);
		isOnPath = onPath;
		isOnColorPath = false;
	}

	public boolean pinSelected(Point p) {
		Rectangle bounds = new Rectangle(this.getX(), this.getY(), 40, 40);
		return bounds.contains(p);
	}



	public boolean testPinMovability(int valorDado, int casaInicial, ArrayList<Casa> casas, PinList pins, Pin selectedPin) {
			int valorDadoDepois;

			
			if(valorDado + casaInicial > 51){
				valorDadoDepois = (valorDado + casaInicial) - 51;
				//verifica se ha pino de outra cor na casa destino
				for(int i = 0; i < 16; i++){
					if(casas.get(valorDadoDepois - 1).getId() == pins.getList().get(i).getIdCasa() && pins.getList().get(i).getCor() != selectedPin.cor){
						return false;
					}
				}
				
				//verifica se ha barreira na casa de destino, independente da cor
				if(casas.get(valorDadoDepois - 1).getQtdPin() == 2){
					return false;
				}
				
				//verifica se ha barreira de cor diferente antes da virada da lista
				for(int i = casaInicial + 1 ; i < 50; i++){
					if(casas.get(i).getQtdPin() == 2) {
						for(int j = 0; j < 16; j++) {
							if(pins.getList().get(j).getIdCasa() == casas.get(i).getId()) {
								if(pins.getList().get(j).getCor() != selectedPin.cor) {
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
						return false;
					}
				}
				if(casas.get(valorDado + casaInicial).getQtdPin() == 2) {
					return false;
				}
				for(int i = casaInicial + 1; i < casaInicial + valorDado - 1; i++) {
					if(casas.get(i).getQtdPin() == 2) {
						for(int j = 0; j < 16; j++) {
							if(pins.getList().get(j).getIdCasa() == casas.get(i).getId()) {
								if(pins.getList().get(j).getCor() != selectedPin.cor) {
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
		

	public void handleSelectedPin(MouseEvent event, int valorDado, int valorAgregado, ArrayList<Casa> casas, ArrayList<Casa> coloridas, int player, ArrayList<Vencedor> colocacoes) {
		Rectangle bounds = new Rectangle(this.getX(), this.getY(), 40, 40);
		Pin selectedPin = this;
		
		if(bounds.contains(event.getX(), event.getY(), 1, 1)) {
		
			if(casas.get(casaInicial).getQtdPin() == 2){
				for(int i = 0; i < 16; i++){
					if(LogicFacade.getPinos().get(i).getCasaInicial() == casas.get(casaInicial).getId()){
						selectedPin = LogicFacade.getPinos().get(i);
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
				else if(player == 2) {
					selectedPin.setX(casas.get(13).getX());
					selectedPin.setY(casas.get(13).getY());
					casas.get(14).setQtdPin(casas.get(13).getQtdPin() + 1);
					selectedPin.isOnPath = true;
					selectedPin.setCasaInicial(13);
				}
				else if(player == 3) {
					selectedPin.setX(casas.get(26).getX());
					selectedPin.setY(casas.get(26).getY());
					casas.get(27).setQtdPin(casas.get(26).getQtdPin() + 1);
					selectedPin.isOnPath = true;
					selectedPin.setCasaInicial(26);
				}
				else if(player == 4) { // era if apenas
					selectedPin.setX(casas.get(39).getX());
					selectedPin.setY(casas.get(39).getY());
					casas.get(40).setQtdPin(casas.get(39).getQtdPin() + 1);
					selectedPin.isOnPath = true;
					selectedPin.setCasaInicial(39);
				}
				return;
			}

			if(valorAgregado < 51 && !selectedPin.voltaCompleta && selectedPin.testPinMovability(valorDado, selectedPin.getCasaInicial(), casas, LogicFacade.getPins(), selectedPin)) {
				// da a volta na lista de casas, para nao estourar a lista
				if(player > 1 && selectedPin.idCasa + valorDado > 51){
					int newindex = (selectedPin.idCasa + valorDado) - 51;
					selectedPin.setX(casas.get(newindex -1).getX());
					selectedPin.setY(casas.get(newindex - 1).getY());
					selectedPin.setIdCasa(casas.get(newindex - 1).getId());
					valorAgregado += valorDado;
					selectedPin.setCasasAndadas(selectedPin.getCasasAndadas() + valorDado);
					selectedPin.setCasaInicial(newindex - 1);
				}
				else{
					
					casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
					selectedPin.setX(casas.get(selectedPin.getCasaInicial() + valorDado).getX());
					selectedPin.setY(casas.get(selectedPin.getCasaInicial() + valorDado).getY());

					//nova casa inicial apos movimento
					selectedPin.casaInicial += valorDado;
					casas.get(selectedPin.casaInicial).setQtdPin(casas.get(selectedPin.casaInicial).getQtdPin() + 1);
					selectedPin.setIdCasa(casas.get(casaInicial).getId());
					selectedPin.setCasasAndadas(selectedPin.getCasasAndadas() + valorDado);
					valorDado = 0;

				}
			}
			
			else if(valorAgregado > 52 && selectedPin.voltaCompleta == true){
				if(selectedPin.casaInicial + valorDado == coloridas.size()){
					
						selectedPin.setX(coloridas.get(6).getX());
						selectedPin.setY(coloridas.get(6).getY());
						coloridas.get(casaInicial).setQtdPin(coloridas.get(casaInicial).getQtdPin() - 1);
						coloridas.get(6).setQtdPin(coloridas.get(6).getQtdPin() + 1);
					
						if(coloridas.get(6).getQtdPin() == 4){
							for(int i = 0; i < 4; i++){
								if(colocacoes.get(i).getCor() == player){
									colocacoes.get(i).setColocacao(LogicFacade.contColocacao);
									LogicFacade.contColocacao++;
								}
							}
						}
				}
			
				else if(selectedPin.casaInicial + valorDado > coloridas.size() - 1){
					selectedPin.setX(coloridas.get(coloridas.size() - ((selectedPin.casaInicial + valorDado) - coloridas.size() + 2)).getX());
					selectedPin.setY(coloridas.get(coloridas.size() - ((selectedPin.casaInicial + valorDado) - coloridas.size() + 2)).getY());
					coloridas.get(selectedPin.casaInicial).setQtdPin(coloridas.get(selectedPin.casaInicial).getQtdPin() - 1);
					selectedPin.setCasaInicial(coloridas.size() - ((selectedPin.casaInicial + valorDado) - coloridas.size() + 2));
					coloridas.get(selectedPin.casaInicial).setQtdPin(coloridas.get(selectedPin.casaInicial).getQtdPin() + 1);
						
				}
				else{
					casas.get(casaInicial).setQtdPin(coloridas.get(casaInicial).getQtdPin() - 1);
					selectedPin.setX(coloridas.get(selectedPin.casaInicial + valorDado).getX());
					selectedPin.setY(coloridas.get(selectedPin.casaInicial + valorDado).getY());
					selectedPin.setCasaInicial(selectedPin.casaInicial + valorDado);
					casas.get(casaInicial).setQtdPin(coloridas.get(casaInicial).getQtdPin() + 1);
					selectedPin.setCasasAndadas(selectedPin.casasAndadas + valorDado);
				}
			}

			//reconhece volta completa e passa o pino para o caminho de sua cor
			else if(valorAgregado > 51 && selectedPin.voltaCompleta != true) { // se valorDado for maior e volta foi completa
				selectedPin.voltaCompleta = true; //definir uma volta completa

				casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
				int newindex = (selectedPin.casaInicial + valorDado) - selectedPin.casaFinal;
				selectedPin.setX(coloridas.get(newindex - 1).getX());
				selectedPin.setY(coloridas.get(newindex - 1).getY());
				selectedPin.setCasaInicial(newindex - 1);
				coloridas.get(selectedPin.casaInicial).setQtdPin(coloridas.get(selectedPin.casaInicial).getQtdPin() + 1);
				selectedPin.setCasasAndadas(selectedPin.getCasasAndadas() + valorDado);
				selectedPin.isOnColorPath = true;

			}
		}
	
		LogicFacade.diceClicked = false;
	}


	public boolean capturePin(ArrayList<Casa> casas, int valorDado, int casaInicial, Pin selectedPin) {
		int valorDadoDepois;

		if(valorDado + casaInicial > 51){
			valorDadoDepois = (valorDado + casaInicial) - 51;			

			//abrigo
			for(int i = casaInicial + 1; i < 51; i++){
				if(casas.get(i).getIsBlack() == true){
					for(int j = 0; j < 16; j++){
						if(LogicFacade.getPinos().get(j).getCasaInicial() == casas.get(i).getId() && LogicFacade.getPinos().get(j).getCor() != selectedPin.cor){
							return false;
						}
					}

				}
			}
			
			//captura antes da virada da lista
			for(int i = casaInicial + 1; i < 51; i++) {
				for(int j = 0; j < 16; j++) {
					if(casas.get(i).getId() == LogicFacade.getPins().getList().get(j).getCasaInicial()) {
						if(LogicFacade.getPins().getList().get(j).getPlayer() != selectedPin.player) {
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 1) {
								LogicFacade.getPins().getList().get(j).setX(L_CASA);
								LogicFacade.getPins().getList().get(j).setY(L_BASE);
								LogicFacade.getPins().getList().get(j).setCasaInicial(0);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(0).setQtdPin(casas.get(0).getQtdPin() + 1);

								return true;
							}
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 2) {
								LogicFacade.getPins().getList().get(j).setX(L_BASE + 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setY(L_CASA);
								LogicFacade.getPins().getList().get(j).setCasaInicial(13);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(13).setQtdPin(casas.get(13).getQtdPin() + 1);
								
								return true;
							}
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 3) {
								LogicFacade.getPins().getList().get(j).setX(L_TAB - 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setY(L_BASE + 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setCasaInicial(26);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(26).setQtdPin(casas.get(26).getQtdPin() + 1);
								
								return true;
							}
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 4) {
								LogicFacade.getPins().getList().get(j).setX(L_BASE);
								LogicFacade.getPins().getList().get(j).setY(L_TAB - 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setCasaInicial(39);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(39).setQtdPin(casas.get(39).getQtdPin() + 1);
								return true;
							}
						}

					}
				}
			}
			
			//captura depois da virada da lista
			for(int i = 0; i < valorDadoDepois - 1; i++) {
				for(int j = 0; j < 16; j++) {
					if(casas.get(i).getId() == LogicFacade.getPins().getList().get(j).getCasaInicial() && LogicFacade.getPins().getList().get(j).isOnColorPath != true) {
						if(LogicFacade.getPins().getList().get(j).getPlayer() != selectedPin.player) {
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 1) {
								LogicFacade.getPins().getList().get(j).setX(L_CASA);
								LogicFacade.getPins().getList().get(j).setY(L_BASE);
								LogicFacade.getPins().getList().get(j).setCasaInicial(0);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(0).setQtdPin(casas.get(0).getQtdPin() + 1);

								return true;
							}
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 2) {
								LogicFacade.getPins().getList().get(j).setX(L_BASE + 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setY(L_CASA);
								LogicFacade.getPins().getList().get(j).setCasaInicial(13);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(13).setQtdPin(casas.get(13).getQtdPin() + 1);
								
								return true;
							} 
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 4) {
								LogicFacade.getPins().getList().get(j).setX(L_TAB - 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setY(L_BASE + 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setCasaInicial(26);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(26).setQtdPin(casas.get(26).getQtdPin() + 1);
								
								return true;
							}
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 3) {
								LogicFacade.getPins().getList().get(j).setX(L_BASE);
								LogicFacade.getPins().getList().get(j).setY(L_TAB - 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setCasaInicial(39);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(39).setQtdPin(casas.get(39).getQtdPin() + 1);
								
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
						if(LogicFacade.getPinos().get(j).getCasaInicial() == casas.get(i).getId() && LogicFacade.getPinos().get(j).getCor() != selectedPin.cor){
							return false;
						}
					}
				}
			}

			for(int i = casaInicial + 1; i < valorDado + casaInicial; i++) {
				for(int j = 0; j < 16; j++) {
					if(casas.get(i).getId() == LogicFacade.getPins().getList().get(j).getCasaInicial() && LogicFacade.getPins().getList().get(j).isOnColorPath != true) {
						if(LogicFacade.getPins().getList().get(j).getPlayer() != selectedPin.player) {
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 1) {
								LogicFacade.getPins().getList().get(j).setX(L_CASA);
								LogicFacade.getPins().getList().get(j).setY(L_BASE);
								LogicFacade.getPins().getList().get(j).setCasaInicial(0);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(0).setQtdPin(casas.get(0).getQtdPin() + 1);

								return true;
							}
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 2) {
								LogicFacade.getPins().getList().get(j).setX(L_BASE + 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setY(L_CASA);
								LogicFacade.getPins().getList().get(j).setCasaInicial(13);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(13).setQtdPin(casas.get(13).getQtdPin() + 1);
								
								return true;
							} 
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 4) {
								LogicFacade.getPins().getList().get(j).setX(L_TAB - 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setY(L_BASE + 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setCasaInicial(26);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(26).setQtdPin(casas.get(26).getQtdPin() + 1);
								
								return true;
							}
							if(LogicFacade.getPins().getList().get(j).getPlayer() == 3) {
								LogicFacade.getPins().getList().get(j).setX(L_BASE);
								LogicFacade.getPins().getList().get(j).setY(L_TAB - 2 * L_CASA);
								LogicFacade.getPins().getList().get(j).setCasaInicial(39);
								casas.get(i).setQtdPin(casas.get(i).getQtdPin() - 1);
								casas.get(39).setQtdPin(casas.get(39).getQtdPin() + 1);
								
								return true;
							}
						}

					}
				}
			}
		}
		return false;
	}


	public void setX( int x){
		this.x = x;
	}

	public int getX(){
		return this.x;
	}

	public void setY(int y){
		this.y = y;
	}

	public int getY(){
		return this.y;
	}

	public void setPlayer(int player){
		this.player = player;
	}

	public int getPlayer(){
		return this.player;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
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