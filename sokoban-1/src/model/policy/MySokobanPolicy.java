package levels;

import java.util.ArrayList;

//The class MySokobanPolicy
public class MySokobanPolicy {

	//Data members
	Level Level;
	
	//C'tor
	public MySokobanPolicy(Level Level2) {
		Level=Level2;
	}
	
	//The policy of the game-return ArrayList with the possible moves
	public ArrayList<Position> Policy()
	{
		ArrayList<Position> PossibleMovesList=new ArrayList<Position>();
		PossibleMovesList=Level.PossibleMoves(Level.getCharacterList().get(0).getPosition());
		ArrayList<Position> temp = new ArrayList<>(PossibleMovesList);
		for (Position position : PossibleMovesList) {
			if(Level.getType(position) == '@')
			{
				if(Level.getType(Level.getDirection(Level.getCharacterList().get(0).getPosition(), position))=='@')
				{
					temp.remove(position);
				}
				else if(Level.getType(Level.getDirection(Level.getCharacterList().get(0).getPosition(), position))=='#')
				{
					temp.remove(position);
				}
			}
			
		}
		return temp;
	}
	
	//Move the item by the policy
	public void MoveByPolicy (Position direction)
	{
		ArrayList<Position> PossibleMove=new ArrayList<Position>();
		PossibleMove=Policy();
		if(PossibleMove.contains(direction))
		{
		Level.MoveItem(Level.getCharacterList().get(0),direction);
		}
	}
	
}
