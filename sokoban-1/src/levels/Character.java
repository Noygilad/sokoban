package levels;

import java.io.Serializable;

//The class character
@SuppressWarnings("serial")
public class Character extends AbstractItems implements Moveable,Serializable {

	Position2D posch;
	
	//default C'tor
	public Character() {
	}
	
	//C'tor 
	public Character(levels.Position position) {
		super(position);
	}
	public Character(levels.Position position,char represtive) {
		super(position,represtive);
	}

	//Getters and Setters
	public Position2D getPosch() {
		return posch;
	}
	public void setPosch(Position2D posch) {
		this.posch =posch ;
	}
	
	public String toString(){
		return "A";
	}
/*
	@Override
	public levels.Position getP() {
		return null;
	}


	@Override
	public void setP(levels.Position p) {
		
	}
	*/
	//Move
	@Override
	public Position2D Move(Position2D pos, char direction)
	{
	
		return null;
	}
	

	

}
