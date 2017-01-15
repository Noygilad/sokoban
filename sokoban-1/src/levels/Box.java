package levels;

import java.io.Serializable;

//The class Box
@SuppressWarnings("serial")
public class Box extends AbstractItems implements Moveable,Serializable{
	
	//default C'tor
	public Box() {
	}
	
	//C'tor
		public Box(Position position) {
			super(position);
		}
	
	//C'tor
	public Box(levels.Position position,char represtive) {
		super(position,represtive);
	}
	
	private Position2D PosBox;

	//Getters andSetters
	public Position2D getPosBox() {
		return PosBox;
	}
	public void setPosBox(Position2D p) {
		this.PosBox = p;
	}
	
	//ToString- return the sign of box
	public String toString(){
		return "@";
	}

	//Move 
	@Override
	public Position2D Move(Position2D pos, char direction) {
		
		return null;
	}
	
	//Check if the box is in the position
	@Override
	public boolean equals(Object arg0) {
		if ((Position.getRow() == ((Box)arg0).getPosition().getRow()) && (Position.getColumn() == ((Box)arg0).getPosition().getColumn()))
		{
			return true;
		}
		return false;
	}
}
