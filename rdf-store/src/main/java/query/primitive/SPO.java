package query.primitive;

import query.Constant;
import query.QueryTarget;
import query.Resolution;

public class SPO extends AbstractPrimitiveQuery<Constant,Constant,Constant> {
	
	public SPO(Constant s, Constant p, Constant o) {
		super(s, p, o);
	}

	@Override
	public Resolution solve(QueryTarget target) {
		return target.contains(s, p, o) ? Resolution.SUCCESS : Resolution.FAILURE;
	}

}
