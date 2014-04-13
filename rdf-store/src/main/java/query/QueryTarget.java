package query;


public interface QueryTarget {

	boolean contains(Constant s, Constant p, Constant o);
	ConstantIter listXPO(Constant p, Constant o);
	ConstantIter listSXO(Constant s, Constant o);
	ConstantIter listSPX(Constant s, Constant p);
	
}
