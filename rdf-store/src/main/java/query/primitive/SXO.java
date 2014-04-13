package query.primitive;

import java.util.HashSet;
import java.util.Set;

import query.Constant;
import query.QueryTarget;
import query.Resolution;
import query.Substitution;
import query.Variable;
import core.TripleIter;

public class SXO extends AbstractPrimitiveQuery<Constant,Variable,Constant> {
	
	public SXO(Constant s, Variable p, Constant o) {
		super(s, p, o);
	}

	@Override
	public Resolution solve(QueryTarget target) {
		Set<Substitution> res = new HashSet<>();

		for (TripleIter it = target.listSXO(s, o); it.hasNext(); ) {
			Constant constant = it.next().getPredicate();
			res.add( new Substitution(p, constant) ); 
		}

		return new Resolution(res);
	}

}
