package core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class UndecidableOntology implements Iterable<Ontology> {
	
	private Set<Ontology> ontologies;
	
	public UndecidableOntology(Set<Ontology> ontologies) {
		super();
		this.ontologies = ontologies;
	}
	
	public static UndecidableOntology of(Iterable<Ontology> iter) {
		Set<Ontology> os = new HashSet<>();

		for ( final Ontology o : iter ) {
			os.add(o);
		}
		
		return new UndecidableOntology( os );
	}
	
	public static UndecidableOntology of(Ontology...array) {
		Set<Ontology> os = new HashSet<>();

		for ( final Ontology o : array ) {
			os.add(o);
		}
		
		return new UndecidableOntology( os );
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ontologies == null) ? 0 : ontologies.hashCode());
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
		UndecidableOntology other = (UndecidableOntology) obj;
		if (ontologies == null) {
			if (other.ontologies != null) {
				return false;
			}
		} else if (!ontologies.equals(other.ontologies)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[ ");
		for ( final Ontology o : ontologies ) {
			sb.append(o).append(" ");
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	@Override
	public Iterator<Ontology> iterator() {
		return ontologies.iterator();
	}

}
