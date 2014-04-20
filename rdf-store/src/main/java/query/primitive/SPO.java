package query.primitive;

import query.Resolution;
import core.Ontology;
import core.Resource;
import core.Triple;

public class SPO extends AbstractPrimitiveQuery<Resource,Resource,Resource> {
	
	public SPO(Resource s, Resource p, Resource o) {
		super(s, p, o);
	}

	@Override
	public Resolution solve(Ontology target) {
		return target.contains(s, p, o) ? Resolution.SUCCESS : Resolution.FAILURE;
	}
	
	@Override
	public Triple toTriple() {
		// TODO: そもそもSPO がトリプル?
		return new Triple(s, p, o);
	}

}
