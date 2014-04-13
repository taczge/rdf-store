package query.primitive;

import query.QueryTarget;
import query.Resolution;
import core.Resource;

public class SPO extends AbstractPrimitiveQuery<Resource,Resource,Resource> {
	
	public SPO(Resource s, Resource p, Resource o) {
		super(s, p, o);
	}

	@Override
	public Resolution solve(QueryTarget target) {
		return target.contains(s, p, o) ? Resolution.SUCCESS : Resolution.FAILURE;
	}

}
