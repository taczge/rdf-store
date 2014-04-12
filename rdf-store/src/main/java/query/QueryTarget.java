package query;


public interface QueryTarget {

	boolean contains(Constant s, Constant p, Constant o);
	ConstantIter listSubject(Constant p, Constant o);
	ConstantIter listPredicate(Constant s, Constant o);
	ConstantIter listObject(Constant s, Constant p);
	
}
