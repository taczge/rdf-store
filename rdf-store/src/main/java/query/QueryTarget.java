package query;

import core.TripleIter;


public interface QueryTarget {


	boolean contains(Constant s, Constant p, Constant o);
	
	TripleIter listXPO(Constant p, Constant o);
	TripleIter listSXO(Constant s, Constant o);
	TripleIter listSPX(Constant s, Constant p);
	
	TripleIter listSXY(Constant s);
	TripleIter listXPZ(Constant p);
	TripleIter listXYO(Constant o);

	TripleIter listXYZ();
}
