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

public class SXY extends AbstractPrimitiveQuery<Constant,Variable,Variable> {

	public SXY(Constant s, Variable p, Variable o) {
		super(s, p, o);
	}

	public Resolution solve(QueryTarget target) {
		Set<Substitution> substitutions = new HashSet<>();
		
		for ( TripleIter it = target.listSXY(s); it.hasNext(); ) {
			Triple t = it.next();

			Substitution subs = new Substitution();
			subs.put(p, t.getPredicate());
			subs.put(o, t.getObject());

			substitutions.add(subs);
		}

		return new Resolution(substitutions);
	}
	
}
