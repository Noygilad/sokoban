package common;

import java.io.Serializable;
import java.util.ArrayList;

import model.data.Box;
import model.data.Character;
import model.data.Floor;
import model.data.Item;
import model.data.Moveable;
import model.data.Position;
import model.data.Position2D;
import model.data.Target;
import model.data.Unmoveable;
import model.data.Wall;

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
	long Time;
	long startTime=0,finishTime=0;
	private String Name;


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
		this.startTime=System.currentTimeMillis();
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

	public void setSteps(int steps) {
		Steps = steps;
	}

	public long getTime() {

		return Time =(finishTime- startTime)/1000;
	}

	public void setTime(long time) {
		Time=time;
	}

	public void setMoveableMap(Moveable[][] moveableMap) {
		MoveableMap = moveableMap;
	}

	public void setUnmoveableMap(Unmoveable[][] unmoveableMap) {
		UnmoveableMap = unmoveableMap;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

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
		if((UnmoveableMap[pos.getRow()][pos.getColumn()-1].toString()==" ")||(UnmoveableMap[pos.getRow()][pos.getColumn()-1].toString()=="o"))
		{
			ArrayPos.add(new Position2D(pos.getRow(),pos.getColumn()-1));
		}
		//Up
		if((UnmoveableMap[pos.getRow()+1][pos.getColumn()].toString()==" ")||(UnmoveableMap[pos.getRow()+1][pos.getColumn()].toString()=="o"))
		{
			ArrayPos.add(new Position2D(pos.getRow()+1,pos.getColumn()));
		}
		//Down
		if((UnmoveableMap[pos.getRow()-1][pos.getColumn()].toString()==" ")||(UnmoveableMap[pos.getRow()-1][pos.getColumn()].toString()=="o"))
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

					if(this.UnmoveableMap[pos.getRow()][pos.getColumn()]instanceof Target)
						BoxList.get(x).setOnDest(true);
					else
						BoxList.get(x).setOnDest(false);
				}
				else if((UnmoveableMap[position.getRow()][position.getColumn()]instanceof Floor)||(UnmoveableMap[position.getRow()][position.getColumn()]instanceof Target))
				{
					MoveableMap[CharacterList.get(i).getPosition().getRow()][CharacterList.get(i).getPosition().getColumn()] = null;
					MoveableMap[position.getRow()][position.getColumn()]=new Character(position);
					CharacterList.get(i).setPosition(position);

				}

			}
			setSteps(Steps+1);

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

	//return the board of the game
	public char[][] getBoard()
	{
		char[][] board = new char [UnmoveableMap.length][UnmoveableMap[0].length];
		for(int i=0; i<UnmoveableMap.length ;i++)
		{
			for(int j=0; j< UnmoveableMap[0].length; j++)
			{
				if(MoveableMap[i][j]==null)
				{
					if(UnmoveableMap[i][j].toString()=="#")
						board[i][j]='#';
					else
						if(UnmoveableMap[i][j].toString()==" ")
							board[i][j]=' ';
						else
								board[i][j]='o';
				}
				else
				{
					if(MoveableMap[i][j].toString()=="A")
						board[i][j]='A';
					else
						board[i][j]='@';
				}
			}
		}
		return board;
	}

	//return the numbers of the boxes that are on targets
	public int numOfBoxesOnTargets() {
		int count=0;
 		for (int i = 0 ; i<BoxList.size(); i++)
 			if (getBoxList().get(i).isOnDest())
 				count++;
 		return count;
 	}

	//check if level complete
	public Boolean isEndOfLevel() {
		if(numOfBoxesOnTargets()==TargetList.size()){
			this.finishTime=System.currentTimeMillis();
		}
 		return numOfBoxesOnTargets()==TargetList.size();

	}



}