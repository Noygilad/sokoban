package plannable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import commons.Level;
import model.data.SokoElements.Target;

public class SokobanPlannable implements iPlannable {
	
	private Level level;
	
	
	public SokobanPlannable(Level level) {
		this.level=level;
		
	}

	@Override
	public Goal getGoal() {
		List<Predicate> predicates=new ArrayList<>();
		
		for(Target t:this.level.getListTarget()){
			predicates.add(new Predicate("BoxAt", t));
		}
		
		return new Goal(predicates);
	}

	@Override
	public State getInitialState() {
		
		Set<Predicate> predicates= new HashSet<>();
		
		for(Target t:this.level.getListTarget()){
			predicates.add(new Predicate("Clear", t));
		}
		
		return new State(predicates) ;
	}

	@Override
	public List<Action> getAllActions() {
		List<Action> sokoActions= new ArrayList<>();
		sokoActions.add(new Move(level));
		return sokoActions;
	}

	@Override
	public List<Object> getAllObjects() {
		
		//To try- bring only the boxes to strips
		
		List<Object>allObjects=new ArrayList<>();
		allObjects.addAll(level.getListBox());
		allObjects.addAll(level.getListTarget());
		
		return allObjects;
		
	}

	@Override
	public Set<Predicate> groundTruth() {
		// TODO Auto-generated method stub
		return null;
	}

}
