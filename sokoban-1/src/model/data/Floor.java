package model.data;

import java.io.Serializable;

//The class Floor
@SuppressWarnings("serial")
public class Floor extends AbstractItems implements Unmoveable,Serializable {
	
	//default C'tor
	public Floor() {
	}
	
	//C'tor
	public Floor(model.data.Position position) {
		super(position);
	}
	
	//C'tor
	public Floor(model.data.Position position,char represtive) {
		super(position,represtive);
	}

	//Return the sign of floor
	public String toString(){
		return " ";
	}
	/*
	//Move
	@Override
	public Position2D Move(Position2D pos, char direction) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Getters and Setters
	@Override
	public levels.Position getP() {
		return null;
	}
	@Override
	public void setP(levels.Position p) {	
	}
*/
}
