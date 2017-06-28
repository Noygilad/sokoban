package boot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import commons.Level;
import commons.searcable.SearchableBoxToTarget;
import commons.searcable.SearchablePlayerToBox;
import model.data.SokoElements.Box;
import model.data.SokoElements.Position;
import model.data.SokoElements.Target;
import model.data.handle.LevelLoader;
import model.data.handle.MyTextLevelLoader;
import plannable.Action;
import plannable.SokobanPlannable;
import plannable.iPlannable;
import planner.Plan;
import planner.Strips;
import planner.iPlanner;
import search.BFS;
import search.Searchable;
import search.Searcher;
import search.Solution;

public class Run {
	
	public Position getPlayerPos(search.Action action, Position currPos) {

		if(action!=null){
			String act=action.getAction();
			if(act.equals("up"))
				return new Position(currPos.getX() + 1, currPos.getY());
			if(act.equals("down"))
				return new Position(currPos.getX() - 1, currPos.getY());
			if(act.equals("left"))
				return new Position(currPos.getX(), currPos.getY() + 1);
			if(act.equals("right"))
				return new Position(currPos.getX(), currPos.getY() - 1);
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LevelLoader loader=new MyTextLevelLoader();
		try {
			Level level=loader.loadLevel(new FileInputStream(new File("level1.txt")));
			
			iPlannable sokoAble=new SokobanPlannable(level);
			iPlanner planner=new Strips();
			Plan plan= planner.computePlan(sokoAble, null);
			
			System.out.println(plan);
			
			List<Action>ac=plan.getActions();
			for(Action a:ac){
				
				Object[]obj=a.getArgs();
				System.out.println("Move Box from: "+((Box)obj[0]).getPosition()+" to "+((Target)obj[1]).getPosition());
				
				char[][] arr=level.getLevelBored();
				SearchableBoxToTarget s= new SearchableBoxToTarget(((Box)obj[0]).getPosition(),((Target)obj[1]).getPosition(), arr,new BFS<Position>() , new SearchablePlayerToBox(new Position(0, 0), new Position(0, 0), arr));
				BFS<Position> bfs = new BFS<>();
				Solution sol = bfs.search(s);
				if (sol != null)
					System.out.println(sol);
				else
					System.out.println("NULL");
				
			}
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	


}
