package core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SimpleOntology implements Ontology {
	
	private static final int DEFAULT_SIZE = 16;
	
	private final Set<Triple> triples;

	public SimpleOntology(Set<Triple> triples) {
		super();
		this.triples = triples;
	}
	
	public static SimpleOntology singleton(Triple t) {
		SimpleOntology ontology = new SimpleOntology();
		
		ontology.add(t);
		
		return ontology;
	}
	
	public static SimpleOntology singleton(Resource s, Resource p, Resource o) {
		return singleton( new Triple(s, p, o) );
	}
		
	public SimpleOntology() {
		this( new HashSet<Triple>(DEFAULT_SIZE) );
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
		SimpleOntology other = (SimpleOntology) obj;
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
		return triples.toString();
	}

	@Override
	public boolean add(Triple t) {
		return triples.add( t );
	}

	@Override
	public boolean add(Resource s, Resource p, Resource o) {
		return triples.add( new Triple(s, p, o) );
	}

	@Override
	public boolean addAll(Collection<Triple> ts) {
		return triples.addAll( ts );
	}

	@Override
	public boolean contains(Triple t) {
		return triples.contains( t );
	}

	@Override
	public boolean contains(Resource s, Resource p, Resource o) {
		return triples.contains( new Triple(s, p, o) );
	}
	
	@Override
	public boolean containsAll(Iterable<Triple> ts) {
		for ( final Triple t : ts ) {
			if ( !contains(t) ) {
				return false;
			}
		}
		
		return true;
	}
	
	private interface Predicate {
		boolean apply(Triple t);
	}
	
	private Collection<Triple> filter(Predicate predicate) {
		Set<Triple> ts = new HashSet<>();
		
		for ( final Triple t : triples ) {
			if ( predicate.apply(t) ) {
				ts.add(t);
			}
		}
		
		return ts;
	}

	@Override
	public Collection<Triple> listXPO(final Resource p, final Resource o) {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsPredicate(p) && t.equalsObject(o);
			}
		});
	}

	@Override
	public Collection<Triple> listSXO(final Resource s, final Resource o) {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsSubject(s) && t.equalsObject(o);
			}
		});
	}

	@Override
	public Collection<Triple> listSPX(final Resource s, final Resource p) {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsSubject(s) && t.equalsPredicate(p);
			}
		});
	}

	@Override
	public Collection<Triple> listSXY(final Resource s) {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsSubject(s);
			}
		});
	}

	@Override
	public Collection<Triple> listXPY(final Resource p) {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsPredicate(p);
			}
		});
	}

	@Override
	public Collection<Triple> listXYO(final Resource o) {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsObject(o);
			}
		});
	}

	@Override
	public Collection<Triple> listSXX(final Resource s) {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsSubject(s) && t.equalsPO();
			}
		});
	}

	@Override
	public Collection<Triple> listXPX(final Resource p) {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsPredicate(p) && t.equalsSO();
			}
		});
	}

	@Override
	public Collection<Triple> listXXO(final Resource o) {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsObject(o) && t.equalsSP();
			}
		});
	}

	@Override
	public Collection<Triple> listXYZ() {
		return triples;
	}

	@Override
	public Collection<Triple> listXXY() {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsSP();
			}
		});
	}

	@Override
	public Collection<Triple> listXYY() {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsPO();
			}
		});
	}

	@Override
	public Collection<Triple> listXYX() {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsSO();
			}
		});
	}

	@Override
	public Collection<Triple> listXXX() {
		return filter( new Predicate() {
			@Override
			public boolean apply(Triple t) {
				return t.equalsSPO();
			}
		});
	}

}
