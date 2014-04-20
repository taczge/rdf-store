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
	
	public SimpleOntology() {
		this( new HashSet<Triple>(DEFAULT_SIZE) );
	}

	@Override
	public void add(Triple t) {
		triples.add( t );
	}

	@Override
	public void add(Resource s, Resource p, Resource o) {
		triples.add( new Triple(s, p, o) );
	}

	@Override
	public void addAll(Collection<Triple> ts) {
		triples.addAll( ts );
	}

	@Override
	public boolean contains(Triple t) {
		return triples.contains( t );
	}

	@Override
	public boolean contains(Resource s, Resource p, Resource o) {
		return triples.contains( new Triple(s, p, o) );
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
