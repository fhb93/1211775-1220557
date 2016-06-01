package gui;

public class Pin {
	
	private int x;
	private int y;
	private int player;
	private int id;
	
//	public Pin(){
//		
//	}
	
	public Pin(int x, int y, int player, int id){
		this.x = x;
		this.y = y;
		this.player = player;
		this.id = id;
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
	
		
		
		
}
