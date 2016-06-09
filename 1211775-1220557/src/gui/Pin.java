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
	//	public Pin(){
	//		
	//	}

	public Pin(int x, int y, int player, int id, int cInicial, int cFinal){
		this.x = x;
		this.y = y;
		this.player = player;
		this.id = id;
		this.casaInicial = cInicial;
		this.casaFinal = cFinal;
	}

	public boolean pinSelected(Point p) {
		Rectangle bounds = new Rectangle(this.getX(), this.getY(), 40, 40);
		return bounds.contains(p);
	}
	
	public void handleSelectedPin(MouseEvent event, int valorDado, int valorAgregado, ArrayList<Casa> casas, ArrayList<Casa> coloridas, int player) {
		System.out.println(event.getX() + " "+ event.getY());
		Rectangle bounds = new Rectangle(this.getX(), this.getY(), 40, 40);
		//				Rectangle bounds = new Rectangle(0, 0, 40, 40);
		if(bounds.contains(event.getX(), event.getY(), 1, 1)) {
			System.out.println("Clique no retangulo!");
			//pin.movePin(1, pins.getList().get(2).getId(), valorDado);

			if(valorAgregado < 52 && !this.voltaCompleta) {

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
					casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() - 1);
					this.setX(casas.get(this.getCasaInicial() + valorDado).getX());
					this.setY(casas.get(this.getCasaInicial() + valorDado).getY());

					//nova casa inicial ap�s movimento
					this.casaInicial += valorDado;
					casas.get(casaInicial).setQtdPin(casas.get(casaInicial).getQtdPin() + 1);
					this.setIdCasa(casas.get(casaInicial).getId());
					this.setCasasAndadas(this.getCasasAndadas() + valorDado);
					System.out.println(valorDado);
					
				}
			}
			
			//reconhece volta completa e passa o pino para o caminho de sua cor
			else if(valorAgregado >= 52 && this.voltaCompleta != true) { // se valorDado for maior e volta foi completa
				this.voltaCompleta = true; //definir uma volta completa
				
				//if(this.getIdCasa() + valorDado > this.getCasaFinal()){
					int newindex = (this.casaInicial + valorDado) - this.casaFinal;
					this.setX(coloridas.get(newindex).getX());
					this.setY(coloridas.get(newindex).getY());
					this.setCasaInicial(newindex);
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



}
