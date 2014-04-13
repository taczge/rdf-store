package query.primitive;

import java.util.HashSet;
import java.util.Set;

import query.Constant;
import query.QueryTarget;
import query.Resolution;
import query.Substitution;
import query.Variable;
import core.Triple;
import core.TripleIter;

public class SXX extends AbstractPrimitiveQuery<Constant,Variable,Variable> {

	public SXX(Constant s, Variable p, Variable o) {
		super(s, p, o);
	}

	public Resolution solve(QueryTarget target) {
		Set<Substitution> substitutions = new HashSet<>();
		
		for ( TripleIter it = target.listSXX(s); it.hasNext(); ) {
			Triple t = it.next();

			substitutions.add( new Substitution(p, t.getPredicate()) );
		}

		return new Resolution(substitutions);
	}
	
}