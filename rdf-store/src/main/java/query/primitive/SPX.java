package query.primitive;

import java.util.HashSet;
import java.util.Set;

import query.Resolution;
import query.Substitution;
import query.Variable;
import core.Ontology;
import core.Resource;
import core.Triple;

public class SPX extends AbstractPrimitiveQuery<Resource,Resource,Variable> {
	
	public SPX(Resource s, Resource p, Variable o) {
		super(s, p, o);
	}
	
	@Override
	public Resolution solve(Ontology target) {
		Set<Substitution> res = new HashSet<>();

		for ( final Triple t : target.listSPX(s, p) ) {
			res.add( Substitution.singleton(o, t.getObject()) ); 
		}

		return Resolution.of(res);
	}

}
