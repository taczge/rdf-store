package rule;

import java.util.HashSet;
import java.util.Set;

import core.Ontology;
import core.Triple;

public class Rules {

	private final Set<Rule> rules;

	public Rules(Set<Rule> rules) {
		super();
		this.rules = rules;
	}
	
	public Rules() {
		this( new HashSet<Rule>() );
	}
	
	public Rules(Rule...rs) {
		this();
		
		for ( final Rule r : rs) {
			rules.add( r );
		}
	}
	
	public Ontology apply(Ontology ontology) {
		while ( true ) {
			Set<Triple> triples = applyOnce(ontology);
			
			boolean modifies = ontology.addAll(triples);
			
			if ( !modifies ) {
				break;
			}
		}
		
		return ontology;
	}
	
	private Set<Triple> applyOnce(Ontology ontology) {
		Set<Triple> triples = new HashSet<>();
		
		for ( final Rule r : rules) { 
			triples.addAll( r.apply(ontology) );
		}

		return triples;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rules == null) ? 0 : rules.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Rules other = (Rules) obj;
		if (rules == null) {
			if (other.rules != null) {
				return false;
			}
		} else if (!rules.equals(other.rules)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final String LS = System.lineSeparator();
		StringBuilder sb = new StringBuilder();
		
		for ( final Rule r : rules ) {
			sb.append(r).append(LS);
		}
		
		return sb.toString();
	}
	
}
