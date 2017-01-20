package levels;

import java.io.Serializable;

//The class Wall
@SuppressWarnings("serial")
public class Wall extends AbstractItems implements Unmoveable,Serializable {
	
	//default C'tor
	public Wall() {
	}
	
	//C'tor
	public Wall(levels.Position position) {
		super(position);
	}
	
	//C'tor
	public Wall(levels.Position position,char represtive) {
		super(position,represtive);
	}

	//ToString-return the sign of wall
	public String toString(){
		return "#";
	}

}
