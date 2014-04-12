package query;

public interface PrimitiveQuery {
	
	Resolution     solve(QueryTarget target);
	PrimitiveQuery apply(Substitusion substitusion);

}
