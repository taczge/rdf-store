package query.primitive;

import java.util.HashSet;
import java.util.Set;

import query.QueryTarget;
import query.Resolution;
import query.Substitution;
import query.Variable;
import core.Triple;
import core.TripleIter;

public class XXY extends AbstractPrimitiveQuery<Variable,Variable,Variable> {

	public XXY(Variable s, Variable p, Variable o) {
		super(s, p, o);
	}

	@Override
	public Resolution solve(QueryTarget target) {
		Set<Substitution> substitutions = new HashSet<>();
		
		for ( TripleIter it = target.listXXY(); it.hasNext(); ) {
			Triple t = it.next();

			Substitution subs = new Substitution();
			subs.put(s, t.getSubject());
			subs.put(o, t.getObject());

			substitutions.add(subs);
		}

		return new Resolution(substitutions);
	}

}
