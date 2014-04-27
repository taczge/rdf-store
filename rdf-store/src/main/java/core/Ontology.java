package core;

import java.util.Collection;


public interface Ontology {

	boolean add(Triple t);
	boolean add(Resource s, Resource p, Resource o);
	boolean addAll(Collection<Triple> ts);
	
	boolean contains(Triple t);
	boolean contains(Resource s, Resource p, Resource o);
	
	Collection<Triple> listXPO(Resource p, Resource o);
	Collection<Triple> listSXO(Resource s, Resource o);
	Collection<Triple> listSPX(Resource s, Resource p);
	
	Collection<Triple> listSXY(Resource s);
	Collection<Triple> listXPY(Resource p);
	Collection<Triple> listXYO(Resource o);
	Collection<Triple> listSXX(Resource s);
	Collection<Triple> listXPX(Resource p);
	Collection<Triple> listXXO(Resource o);
	
	Collection<Triple> listXYZ();
	Collection<Triple> listXXY();
	Collection<Triple> listXYY();
	Collection<Triple> listXYX();
	Collection<Triple> listXXX();

}
