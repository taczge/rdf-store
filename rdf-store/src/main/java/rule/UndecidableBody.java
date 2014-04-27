package rule;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import query.Resolution;
import query.Substitution;
import core.DifferenceOntology;
import core.Ontology;
import core.SimpleOntology;
import core.UndecidableOntology;

public class UndecidableBody {
	
	private final List<Body> bodies;

	public UndecidableBody(List<Body> bodies) {
		super();
		
		if ( bodies.isEmpty() ) {
			throw new IllegalArgumentException( "There is not any body: " + bodies);
		}

		this.bodies = bodies;
	}
	
	public static UndecidableBody of(Body first, Body second, Body...rest) {
		List<Body> bs = new LinkedList<>();
		
		bs.add( first );
		bs.add( second );

		for ( final Body b : rest ) {
			bs.add( b );
		}
		
		return new UndecidableBody( bs );
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodies == null) ? 0 : bodies.hashCode());
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
		UndecidableBody other = (UndecidableBody) obj;
		if (bodies == null) {
			if (other.bodies != null) {
				return false;
			}
		} else if (!bodies.equals(other.bodies)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final String SEPARATOR = "|";
		StringBuilder sb = new StringBuilder();

		for ( int i = 0; i < bodies.size(); i++ ) {
			if ( i != 0 ) {
				sb.append( SEPARATOR );
			}
			sb.append( bodies.get(i) );
		}

		return sb.toString();
	}
	
	public UndecidableOntology apply(Ontology ontology, Resolution r) {
		if ( r.isEmpty() ) {
			return UndecidableOntology.of(ontology);
		}
		
		return apply( ontology, r.get() );
	}

	public UndecidableOntology apply(Ontology common, Substitution s) {
		Set<Ontology> differences = new HashSet<>( bodies.size() );
		
		for ( final Body b : bodies ) {
			SimpleOntology diff = new SimpleOntology( b.toTriple(s) );
			
			differences.add( diff );
		}
		
		return DifferenceOntology.distribute(common, differences); 
	}

}
