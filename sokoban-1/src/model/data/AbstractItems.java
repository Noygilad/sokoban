package model.data;
//check
import java.io.Serializable;

//The class AbstraceItem
@SuppressWarnings("serial")
public class AbstractItems implements Item,Serializable {

	Position Position;
	char Represtive;

	//default C'tor
	public AbstractItems() {
	}

	//C'tor create Position2D
	public AbstractItems(Position position) {
		Position = new Position2D(position);
	}

	//C'tor create  Position2D and represtive
	public AbstractItems(Position position,char represtive) {
		Position = new Position2D(position);
		Represtive = represtive;
	}

	//Getters and Setters
	public Position getPosition() {
		return Position;
	}
	public void setPosition(Position position) {
		this.Position = position;
	}
	public char getReprestive() {
		return Represtive;
	}
	public void setReprestive(char represtive) {
		this.Represtive = represtive;
	}
}
