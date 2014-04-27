package core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class FastOntology implements Ontology {

	private Multimap<Resource,Triple> stmap;
	private Multimap<Resource,Triple> ptmap;
	private Multimap<Resource,Triple> otmap;

	private Set<Triple> sameSP;
	private Set<Triple> samePO;
	private Set<Triple> sameSO; 
	private Set<Triple> sameSPO;
	
	public FastOntology() {
		stmap   = HashMultimap.create();
		ptmap   = HashMultimap.create();
		otmap   = HashMultimap.create();
	
		sameSP  = new HashSet<>();
		samePO  = new HashSet<>();
		sameSO  = new HashSet<>();
		sameSPO = new HashSet<>();
	}
	
	public FastOntology(Collection<Triple> triples) {
		this();
		
		addAll(triples);
	}

	// stmap だけを使う
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stmap == null) ? 0 : stmap.hashCode());
		return result;
	}

	// stmap だけを使う
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
		FastOntology other = (FastOntology) obj;
		if (stmap == null) {
			if (other.stmap != null) {
				return false;
			}
		} else if (!stmap.equals(other.stmap)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for ( final Triple t : stmap.values() ) {
			sb.append(t).append(System.lineSeparator());
		}
		
		return sb.toString();
	}
	
	@Override
	public boolean add(Triple t) {
		if ( contains(t) ) {
			return false;
		}
		
		Resource s = t.getSubject();
		Resource p = t.getPredicate();
		Resource o = t.getObject();
		
		stmap.put(s, t);
		ptmap.put(p, t);
		otmap.put(o, t);
		
		if ( s.equals(p) ) sameSP.add(t); 
		if ( s.equals(o) ) sameSO.add(t); 
		if ( p.equals(o) ) samePO.add(t);
		
		if ( s.equals(p) && s.equals(o)) sameSPO.add(t);
		
		return true;
	}
	
	@Override
	public boolean add(Resource s, Resource p, Resource o) {
		return add( new Triple(s, p, o) );
	}
	
	@Override
	public boolean addAll(Collection<Triple> ts) {
		boolean modifies = false;

		for ( final Triple t : ts) {
			if ( add(t) ) {
				modifies = true;
			}
		}
		
		return modifies;
	}

	@Override
	public boolean contains(Triple t) {
		return contains( t.getSubject(), t.getPredicate(), t.getObject() );
	}
	
	
	@Override
	public boolean contains(Resource s, Resource p, Resource o) {
		return stmap.get(s).contains(new Triple(s, p, o));
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

	@Override
	public Collection<Triple> listXPO(Resource p, Resource o) {
		Collection<Triple> result = new HashSet<>();
		
		for ( final Triple t : otmap.get(o) ) {
			if ( t.getPredicate().equals(p) ) {
				result.add(t);
			}
		}
		
		return result;
	}

	@Override
	public Collection<Triple> listSXO(Resource s, Resource o) {
		Collection<Triple> result = new HashSet<>();
		
		for ( final Triple t : stmap.get(s)) {
			if ( t.getObject().equals(o) ) {
				result.add(t);
			}
		}
		
		return result;
	}

	@Override
	public Collection<Triple> listSPX(Resource s, Resource p) {
		Collection<Triple> result = new HashSet<>();
		
		for ( final Triple t : stmap.get(s) ) {
			if ( t.getPredicate().equals(p) ) {
				result.add(t);
			}
		}
		
		return result;
	}

	@Override
	public Collection<Triple> listSXY(Resource s) {
		return stmap.get(s);
	}

	@Override
	public Collection<Triple> listXPY(Resource p) {
		return ptmap.get(p);
	}

	@Override
	public Collection<Triple> listXYO(Resource o) {
		return otmap.get(o);
	}

	@Override
	public Collection<Triple> listSXX(Resource s) {
		Collection<Triple> result = new HashSet<>();
		
		for ( final Triple t : samePO ) {
			if ( t.equalsSubject(s) ) {
				result.add(t);
			}
		}

		return result;
	}

	@Override
	public Collection<Triple> listXPX(Resource p) {
		Collection<Triple> result = new HashSet<>();
		
		for ( final Triple t : sameSO ) {
			if ( t.equalsPredicate(p) ) {
				result.add(t);
			}
		}

		return result;
	}

	@Override
	public Collection<Triple> listXXO(Resource o) {
		Collection<Triple> result = new HashSet<>();
		
		for ( final Triple t : sameSP ) {
			if ( t.equalsObject(o) ) {
				result.add(t);
			}
		}

		return result;
	}

	@Override
	public Collection<Triple> listXYZ() {
		return stmap.values();
	}

	@Override
	public Collection<Triple> listXXY() {
		return sameSP;
	}

	@Override
	public Collection<Triple> listXYY() {
		return samePO;
	}

	@Override
	public Collection<Triple> listXYX() {
		return sameSO;
	}

	@Override
	public Collection<Triple> listXXX() {
		return sameSPO;
	}

}
