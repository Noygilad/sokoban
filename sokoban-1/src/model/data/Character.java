package model.data;

import java.io.Serializable;

//The class character
@SuppressWarnings("serial")
public class Character extends AbstractItems implements Moveable,Serializable {

	//Data members
	Position2D posch;

	//default C'tor
	public Character() {
	}

	//C'tor
	public Character(model.data.Position position) {
		super(position);
	}
	public Character(model.data.Position position,char represtive) {
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

	//Move
	@Override
	public Position2D Move(Position2D pos, char direction)
	{

		return null;
	}




}
