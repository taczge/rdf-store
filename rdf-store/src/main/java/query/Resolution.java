package query;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Resolution {

	public static final Resolution SUCCESS = new Resolution();
	public static final Resolution FAILURE = new Resolution();
	
	public static Resolution of(Variable var, Iterator<? extends Constant> it) {
		Set<Substitution> subs = new HashSet<>();
		
		while ( it.hasNext() ) {
			subs.add( new Substitution(var, it.next()) );
		}
		
		return new Resolution(subs);
	}
	
	private Set<Substitution> substitusions;

	public Resolution(Set<Substitution> substitusions) {
		super();
		this.substitusions = substitusions;
	}
	
	public Resolution() {
		this(new HashSet<Substitution>());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((substitusions == null) ? 0 : substitusions.hashCode());
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
		Resolution other = (Resolution) obj;
		if (substitusions == null) {
			if (other.substitusions != null) {
				return false;
			}
		} else if (!substitusions.equals(other.substitusions)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Resolution [substitusions=" + substitusions + "]";
	}
	
}
