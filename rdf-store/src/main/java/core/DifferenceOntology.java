package core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DifferenceOntology implements Ontology {
	
	private final Ontology parent;
	private final Ontology self;

	public DifferenceOntology(Ontology parent, Ontology self) {
		super();
		this.parent = parent;
		this.self   = self;
	}
	
	public static final UndecidableOntology
	distribute(Ontology parent, Iterable<Ontology> chiledren) {
		Set<Ontology> os = new HashSet<>();
		
		for ( final Ontology child : chiledren ) {
			os.add( new DifferenceOntology(parent, child) );
		}
		
		return new UndecidableOntology( os );
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((self == null) ? 0 : self.hashCode());
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
		DifferenceOntology other = (DifferenceOntology) obj;
		if (parent == null) {
			if (other.parent != null) {
				return false;
			}
		} else if (!parent.equals(other.parent)) {
			return false;
		}
		if (self == null) {
			if (other.self != null) {
				return false;
			}
		} else if (!self.equals(other.self)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(parent);
		sb.append(" + ").append(self);
		
		return sb.toString();
	}

	@Override
	public boolean add(Triple t) {
		return self.add(t);
	}

	@Override
	public boolean add(Resource s, Resource p, Resource o) {
		return self.add(s, p, o);
	}

	@Override
	public boolean addAll(Collection<Triple> ts) {
		return self.addAll(ts);
	}

	@Override
	public boolean contains(Triple t) {
		return self.contains(t) || parent.contains(t);
	}

	@Override
	public boolean contains(Resource s, Resource p, Resource o) {
		return self.contains(s, p, o) || parent.contains(s, p, o);
	}
	
	private <T> Collection<T> union(Collection<T> a, Collection<T> b) {
		Set<T> union = new HashSet<>( a.size() + b.size() );
		
		union.addAll( a );
		union.addAll( b );
		
		return union;
	}

	@Override
	public Collection<Triple> listXPO(Resource p, Resource o) {
		return union( self.listXPO(p, o), parent.listXPO(p, o) ); 
	}

	@Override
	public Collection<Triple> listSXO(Resource s, Resource o) {
		return union( self.listSXO(s, o), parent.listSXO(s, o) );
	}

	@Override
	public Collection<Triple> listSPX(Resource s, Resource p) {
		return union( self.listSPX(s, p), parent.listSPX(s, p) );
	}

	@Override
	public Collection<Triple> listSXY(Resource s) {
		return union( self.listSXY(s), parent.listSXY(s) );
	}

	@Override
	public Collection<Triple> listXPY(Resource p) {
		return union( self.listXPY(p), parent.listXPY(p) );
	}

	@Override
	public Collection<Triple> listXYO(Resource o) {
		return union( self.listXYO(o), parent.listXYO(o) );
	}

	@Override
	public Collection<Triple> listSXX(Resource s) {
		return union( self.listSXX(s), parent.listSXX(s) );
	}

	@Override
	public Collection<Triple> listXPX(Resource p) {
		return union( self.listXPX(p), parent.listXPX(p) );
	}

	@Override
	public Collection<Triple> listXXO(Resource o) {
		return union( self.listXXO(o), parent.listXXO(o) );
	}

	@Override
	public Collection<Triple> listXYZ() {
		return union( self.listXYZ(), parent.listXYZ() );
	}

	@Override
	public Collection<Triple> listXXY() {
		return union( self.listXXY(), parent.listXXY() );
	}

	@Override
	public Collection<Triple> listXYY() {
		return union( self.listXYY(), parent.listXYY() );
	}

	@Override
	public Collection<Triple> listXYX() {
		return union( self.listXYX(), parent.listXYX() );
	}

	@Override
	public Collection<Triple> listXXX() {
		return union( self.listXXX(), parent.listXXX() );
	}


}
