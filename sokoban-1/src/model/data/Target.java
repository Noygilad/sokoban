package levels;

import java.io.Serializable;

//The class Target
@SuppressWarnings("serial")
public class Target extends AbstractItems implements Unmoveable,Serializable {

	//default C'tor
	public Target() {
	}
	
	//C'tor
	public Target(levels.Position position) {
		super(position);
	}
	
	//C'tor
	public Target(levels.Position position,char represtive) {
		super(position,represtive);
	}

	//ToString- return the sign of target
	public String toString(){
		return "o";
	}

}
