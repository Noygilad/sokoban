package plannable;

import java.util.ArrayList;
import java.util.List;

import commons.Level;

public class Move extends Action {
	
	public Move(Level level) {
		
		this.name="Move";
		this.args= new String[]{"b", "t"};
		
		this.preconditions.add(new Predicate("Clear", "t"));
		//this.preconditions.add(new Predicate("Path", "b","t"));
		
		this.addList.add(new Predicate("BoxAt", "t"));
		this.deleteList.add(new Predicate("Clear", "t"));
		
		List<Object> boxes=new ArrayList<>();
		boxes.addAll(level.getListBox());
		this.illegalAssignments.put("t", boxes);
		
		List<Object> targets=new ArrayList<>();
		targets.addAll(level.getListTarget());
		this.illegalAssignments.put("b", targets);
		
		
		
	}

}
