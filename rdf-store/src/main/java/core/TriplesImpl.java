package core;

import java.util.HashSet;
import java.util.Set;

public class TriplesImpl implements Triples {

	private Set<Triple> triples;

	public TriplesImpl(Set<Triple> triples) {
		super();
		this.triples = triples;
	}
	
	public TriplesImpl() {
		this( new HashSet<Triple>() );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((triples == null) ? 0 : triples.hashCode());
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
		TriplesImpl other = (TriplesImpl) obj;
		if (triples == null) {
			if (other.triples != null) {
				return false;
			}
		} else if (!triples.equals(other.triples)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TriplesImpl [triples=" + triples + "]";
	}

}
