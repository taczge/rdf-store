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

public class XXO extends AbstractPrimitiveQuery<Variable,Variable,Constant> {
	
	public XXO(Variable s, Variable p, Constant o) {
		super(s, p, o);
	}

	@Override
	public Resolution solve(QueryTarget target) {
		Set<Substitution> substitutions = new HashSet<>();
		
		for ( TripleIter it = target.listXXO(o); it.hasNext(); ) {
			Triple t = it.next();

			substitutions.add( new Substitution(s, t.getSubject()) );
		}

		return new Resolution(substitutions);
	}

}