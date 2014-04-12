package query;

import java.util.HashMap;
import java.util.Map;

public class Substitusion {
	
	private Map<Variable,Constant> substitutions;

	public Substitusion(Map<Variable, Constant> substitutions) {
		super();
		this.substitutions = substitutions;
	}
	
	public Substitusion() {
		this( new HashMap<Variable,Constant>() );
	}
	
	public Substitusion(Variable v, Constant c) {
		this();
		substitutions.put(v, c);		
	}

	public boolean contains(Variable var) {
		return substitutions.containsKey(var);
	}
	
	public Constant getAssignedValue(Variable var) {
		return substitutions.get(var);
	}
	
}
