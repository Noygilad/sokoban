package model.data;

import java.io.Serializable;
import java.util.ArrayList;

//The class Level
public class Level implements Serializable {

	//Data members
	static final long serialVersionUID = 1L;
	Moveable[][] MoveableMap;
	Unmoveable[][] UnmoveableMap;
	ArrayList<Box> BoxList;
	ArrayList<Target> TargetList;
	ArrayList<Character> CharacterList;
	int Steps;
//	private Position2D Start;

	public Level() {
	}

	//C'tor
	public Level(int row,int column) {
		this.MoveableMap = new Moveable[row][column];
		this.UnmoveableMap = new Unmoveable[row][column];
		this.BoxList = new ArrayList<Box>();
		this.TargetList = new ArrayList<Target>();
		this.CharacterList = new ArrayList<Character>();
		this.Steps = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				UnmoveableMap[i][j] = new Floor(new Position2D(i, j),' ');
				MoveableMap[i][j] = null;
			}
		}
	}

	//Getters and Setters
	public Moveable[][] getMoveableMap() {
		return MoveableMap;
	}

	public void setMoveableMap(int row,int column, Item item) {
		if (item.getReprestive()=='A') {
			MoveableMap[row][column] = new Character(new Position2D(row, column),item.getReprestive());
			CharacterList.add(new Character(new Position2D(row, column),item.getReprestive()));
		}
		else if (item.getReprestive()=='@')
		{
			MoveableMap[row][column] = new Box(new Position2D(row, column),item.getReprestive());
			BoxList.add(new Box(new Position2D(row, column),item.getReprestive()));
		}
	}

	public Unmoveable[][] getUnmoveableMap() {
		return UnmoveableMap;
	}

	public void setUnmoveableMap(int row,int column, Item item) {

		if (item.getReprestive()=='#') {
			UnmoveableMap[row][column] = new Wall(new Position2D(row, column),item.getReprestive());
		}
		else if (item.getReprestive()==' ')
		{
			UnmoveableMap[row][column] = new Floor(new Position2D(row, column),item.getReprestive());
		}
		else if (item.getReprestive()=='o')
		{
			UnmoveableMap[row][column] = new Target(new Position2D(row, column),item.getReprestive());
			TargetList.add(new Target(new Position2D(row, column),item.getReprestive()));
		}
	}

	public ArrayList<Box> getBoxList() {
		return BoxList;
	}

	public void setBoxList(ArrayList<Box> boxList) {
		BoxList = boxList;
	}

	public ArrayList<Target> getTargetList() {
		return TargetList;
	}

	public void setTargetList(ArrayList<Target> targetList) {
		TargetList = targetList;
	}

	public ArrayList<Character> getCharacterList() {
		return CharacterList;
	}

	public void setCharacterList(ArrayList<Character> characterList) {
		CharacterList = characterList;
	}

	public int getSteps() {
		return Steps;
	}

	/*public Position2D getStart() {
		return Start;
	}

	public void setStart(Position2D start) {
		Start = start;
	}
*/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//Add to the specific ArrayList the item
	public void addToArray(int row,int column,Item item)
	{
		if (item.getReprestive()=='A') {
			CharacterList.add(new Character(new Position2D(row, column),item.getReprestive()));
		}
		else if (item.getReprestive()=='@')
		{
			BoxList.add(new Box(new Position2D(row, column),item.getReprestive()));
		}
	}

	//Create ArrayList
	public ArrayList<String> GetPossibleMove (Level level){

		ArrayList<String> PossibleDirection = null;

		return PossibleDirection;
	}

	//Return the possible move
	public ArrayList<Position> PossibleMoves(Position pos)
	{
		ArrayList<Position> ArrayPos=new ArrayList<>();
		//Right
		if((UnmoveableMap[pos.getRow()][pos.getColumn()+1].toString()==" ")||(UnmoveableMap[pos.getRow()][pos.getColumn()+1].toString()=="o"))
		{
			ArrayPos.add(new Position2D(pos.getRow(),pos.getColumn()+1));
		}
		//Left
		if((UnmoveableMap[pos.getRow()][pos.getColumn()-1].toString()==" ")||(UnmoveableMap[pos.getRow()][pos.getColumn()+1].toString()=="o"))
		{
			ArrayPos.add(new Position2D(pos.getRow(),pos.getColumn()-1));
		}
		//Up
		if((UnmoveableMap[pos.getRow()+1][pos.getColumn()].toString()==" ")||(UnmoveableMap[pos.getRow()][pos.getColumn()+1].toString()=="o"))
		{
			ArrayPos.add(new Position2D(pos.getRow()+1,pos.getColumn()));
		}
		//Down
		if((UnmoveableMap[pos.getRow()-1][pos.getColumn()].toString()==" ")||(UnmoveableMap[pos.getRow()][pos.getColumn()+1].toString()=="o"))
		{
			ArrayPos.add(new Position2D(pos.getRow()-1,pos.getColumn()));
		}


		return ArrayPos;
	}

	//get type if target and box in same position-return box
	public char getType(Position position)
	{
		if(MoveableMap[position.getRow()][position.getColumn()]instanceof Box)
		{
			return '@';
		}
		else
		{
			if(UnmoveableMap[position.getRow()][position.getColumn()]instanceof Wall)
			{
				return '#';
			}
			else if(UnmoveableMap[position.getRow()][position.getColumn()]instanceof Target)
			{
				return 'o';
			}
			else
			{
				return ' ';
			}
		}

	}
	// get the position after the box
	public Position getDirection(Position posCharacter , Position posBox)
	{

		if(posCharacter.getRow()==posBox.getRow())
		{
			if(posCharacter.getColumn()>posBox.getColumn())
			{
				return new Position2D(posBox.getRow(),posBox.getColumn()-1);
			}
			else
			{
				return new Position2D(posBox.getRow(),posBox.getColumn()+1);
			}
		}
		else
		{
			if(posCharacter.getRow()>posBox.getRow())
			{
				return new Position2D(posBox.getRow()-1,posBox.getColumn());
			}
			else
			{
				return new Position2D(posBox.getRow()+1,posBox.getColumn());
			}
		}
	}

	//Move the item to the specific position
	public void MoveItem(Item item,Position position)
	{
		if(item.toString()=="A")
		{
			for(int i=0;i<CharacterList.size();i++)
			{
				if(MoveableMap[position.getRow()][position.getColumn()]instanceof Box)
				{
					Position pos = getDirection(CharacterList.get(i).getPosition(), position);
					MoveableMap[pos.getRow()][pos.getColumn()]=new Box(pos);
					int x = BoxList.indexOf(MoveableMap[position.getRow()][position.getColumn()]);
					(BoxList.get(x)).setPosition(pos);
					MoveableMap[CharacterList.get(i).getPosition().getRow()][CharacterList.get(i).getPosition().getColumn()] = null;
					MoveableMap[position.getRow()][position.getColumn()]=new Character(position);
					CharacterList.get(i).setPosition(position);
				}
				else if(UnmoveableMap[position.getRow()][position.getColumn()]instanceof Floor||UnmoveableMap[position.getRow()][position.getColumn()]instanceof Target)
				{
					MoveableMap[CharacterList.get(i).getPosition().getRow()][CharacterList.get(i).getPosition().getColumn()] = null;
					MoveableMap[position.getRow()][position.getColumn()]=new Character(position);
					CharacterList.get(i).setPosition(position);

				}

			}
		}

	}

	//insert the array(moveable and unmoveable) to string
	public String toString(){
		String moveable = new String();
		for (int i = 0; i < UnmoveableMap.length; i++) {
			for (int j = 0; j < UnmoveableMap[0].length; j++) {
				if(MoveableMap[i][j]== null)
				{
					moveable += UnmoveableMap[i][j].toString();

				}
				else
				{
					moveable += MoveableMap[i][j].toString();
				}

			}
			moveable += '\n';
		}
		return moveable;
	}

	public void setMoveableMap(Moveable[][] moveableMap) {
		MoveableMap = moveableMap;
	}

	public void setUnmoveableMap(Unmoveable[][] unmoveableMap) {
		UnmoveableMap = unmoveableMap;
	}

	public void setSteps(int steps) {
		Steps = steps;
	}

}