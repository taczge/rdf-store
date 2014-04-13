package query.primitive;

import java.util.HashSet;
import java.util.Set;

import query.QueryTarget;
import query.Resolution;
import query.Substitution;
import query.Variable;
import core.Triple;
import core.TripleIter;

public class XYX extends AbstractPrimitiveQuery<Variable,Variable,Variable> {

	public XYX(Variable s, Variable p, Variable o) {
		super(s, p, o);
	}

	@Override
	public Resolution solve(QueryTarget target) {
		Set<Substitution> substitutions = new HashSet<>();
		
		for ( TripleIter it = target.listXYX(); it.hasNext(); ) {
			Triple t = it.next();

			Substitution subs = new Substitution();
			subs.put(s, t.getSubject());
			subs.put(p, t.getPredicate());

			substitutions.add(subs);
		}

		return new Resolution(substitutions);
	}

}