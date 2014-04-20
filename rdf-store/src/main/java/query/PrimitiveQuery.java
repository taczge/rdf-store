package query;

import core.Ontology;
import core.Triple;

public interface PrimitiveQuery {
	
	Resolution     solve(Ontology target);
	PrimitiveQuery apply(Substitution substitusion);
	Triple         toTriple();

}
