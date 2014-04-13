package query.primitive;

import java.util.HashSet;
import java.util.Set;

import query.Constant;
import query.QueryTarget;
import query.Resolution;
import query.Substitution;
import query.Variable;
import core.TripleIter;

public class SPX extends AbstractPrimitiveQuery<Constant,Constant,Variable> {
	
	public SPX(Constant s, Constant p, Variable o) {
		super(s, p, o);
	}
	
	@Override
	public Resolution solve(QueryTarget target) {
		Set<Substitution> res = new HashSet<>();

		for (TripleIter it = target.listSPX(s, p); it.hasNext(); ) {
			Constant constant = it.next().getObject();
			res.add( new Substitution(o, constant) ); 
		}

		return new Resolution(res);
	}

}
