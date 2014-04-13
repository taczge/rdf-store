package query;

import java.util.HashMap;
import java.util.Map;

import core.Resource;

public class Substitution {
	
	private Map<Variable, Resource> substitutions;

	public Substitution(Map<Variable, Resource> substitutions) {
		super();
		this.substitutions = substitutions;
	}
	
	public Substitution() {
		this( new HashMap<Variable, Resource>() );
	}
	
	public Substitution(Variable v, Resource c) {
		this();
		substitutions.put(v, c);		
	}
	
	public void put(Variable v, Resource c) {
		substitutions.put(v, c);
	}
	
	public boolean contains(Variable var) {
		return substitutions.containsKey(var);
	}
	
	public Resource getAssignedValue(Variable var) {
		return substitutions.get(var);
	}
	
}
