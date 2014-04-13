package query;

import java.util.HashMap;
import java.util.Map;

public class Substitution {
	
	private Map<Variable, Constant> substitutions;

	public Substitution(Map<Variable, Constant> substitutions) {
		super();
		this.substitutions = substitutions;
	}
	
	public Substitution() {
		this( new HashMap<Variable, Constant>() );
	}
	
	public Substitution(Variable v, Constant c) {
		this();
		substitutions.put(v, c);		
	}
	
	public void put(Variable v, Constant c) {
		substitutions.put(v, c);
	}
	
	public boolean contains(Variable var) {
		return substitutions.containsKey(var);
	}
	
	public Constant getAssignedValue(Variable var) {
		return substitutions.get(var);
	}
	
}
