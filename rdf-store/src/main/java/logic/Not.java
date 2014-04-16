package logic;

import java.util.HashSet;
import java.util.Set;

import core.Resource;
import core.Triple;

public class Not implements Proposition {

	private final Proposition p;

	public Not(Proposition p) {
		super();
		this.p = p;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
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
		Not other = (Not) obj;
		if (p == null) {
			if (other.p != null) {
				return false;
			}
		} else if (!p.equals(other.p)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format( "(not %s)", p );
	}
	
	private static final Resource not = Resource.of("NOT");
	@Override
	public Set<Triple> toTriples(ResourceIssuer issuer, Resource previous) {
		Set<Triple> triples = new HashSet<>();
		
		Resource fresh = issuer.createFresh(); 
		triples.add( new Triple(previous, not, fresh) );
		triples.addAll( p.toTriples(issuer, fresh) );
		
		return triples;
	}

}
