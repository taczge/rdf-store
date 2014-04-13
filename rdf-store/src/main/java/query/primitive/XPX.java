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

public class XPX extends AbstractPrimitiveQuery<Variable,Constant,Variable> {

	public XPX(Variable s, Constant p, Variable o) {
		super(s, p, o);
	}

	public Resolution solve(QueryTarget target) {
		Set<Substitution> substitutions = new HashSet<>();
		
		for ( TripleIter it = target.listXPX(p); it.hasNext(); ) {
			Triple t = it.next();

			substitutions.add( new Substitution(s, t.getSubject()) );
		}

		return new Resolution(substitutions);
	}
	
}
