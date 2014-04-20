package core;

import java.util.Collection;

import util.Util;

public class CompositeOntology implements Ontology {
	
	private final Ontology parent;
	private final Ontology self;

	public CompositeOntology(Ontology parent, Ontology self) {
		super();
		this.parent = parent;
		this.self = self;
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
		CompositeOntology other = (CompositeOntology) obj;
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
		return "CompositeOntology [parent=" + parent + ", self=" + self + "]";
	}

	@Override
	public void add(Triple t) {
		self.add(t);
	}

	@Override
	public void add(Resource s, Resource p, Resource o) {
		self.add(s, p, o);
	}

	@Override
	public void addAll(Collection<Triple> ts) {
		self.addAll( ts );
	}

	@Override
	public boolean contains(Triple t) {
		return self.contains(t) || parent.contains(t);
	}

	@Override
	public boolean contains(Resource s, Resource p, Resource o) {
		return self.contains(s, p, o) || parent.contains(s, p, o);
	}

	@Override
	public Collection<Triple> listXPO(Resource p, Resource o) {
		return Util.union( parent.listXPO(p, o), self.listXPO(p, o) );
	}

	@Override
	public Collection<Triple> listSXO(Resource s, Resource o) {
		return Util.union( parent.listSXO(s, o), self.listSXO(s, o) );
	}

	@Override
	public Collection<Triple> listSPX(Resource s, Resource p) {
		return Util.union( parent.listSPX(s, p), self.listSPX(s, p) );
	}

	@Override
	public Collection<Triple> listSXY(Resource s) {
		return Util.union( parent.listSXY(s), self.listSXY(s) );
	}

	@Override
	public Collection<Triple> listXPY(Resource p) {
		return Util.union( parent.listXPY(p), self.listXPY(p) );
	}

	@Override
	public Collection<Triple> listXYO(Resource o) {
		return Util.union( parent.listXYO(o), self.listXYO(o) );
	}

	@Override
	public Collection<Triple> listSXX(Resource s) {
		return Util.union( parent.listSXX(s), self.listSXX(s) );
	}

	@Override
	public Collection<Triple> listXPX(Resource p) {
		return Util.union( parent.listXPX(p), self.listXPX(p) );
	}

	@Override
	public Collection<Triple> listXXO(Resource o) {
		return Util.union( parent.listXXO(o), self.listXXO(o) );
	}

	@Override
	public Collection<Triple> listXYZ() {
		return Util.union( parent.listXYZ(), self.listXYZ() );
	}

	@Override
	public Collection<Triple> listXXY() {
		return Util.union( parent.listXXY(), self.listXXY() );
	}

	@Override
	public Collection<Triple> listXYY() {
		return Util.union( parent.listXYY(), self.listXYY() );
	}

	@Override
	public Collection<Triple> listXYX() {
		return Util.union( parent.listXYX(), self.listXYX() );
	}

	@Override
	public Collection<Triple> listXXX() {
		return Util.union( parent.listXXX(), self.listXXX() );
	}

}
