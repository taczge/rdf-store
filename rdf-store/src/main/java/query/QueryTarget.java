package query;

import core.TripleIter;


public interface QueryTarget {

	boolean contains(Constant s, Constant p, Constant o);
	
	TripleIter listXPO(Constant p, Constant o);
	TripleIter listSXO(Constant s, Constant o);
	TripleIter listSPX(Constant s, Constant p);
	
	TripleIter listSXY(Constant s);
	TripleIter listXPY(Constant p);
	TripleIter listXYO(Constant o);
	TripleIter listSXX(Constant s);
	TripleIter listXPX(Constant p);
	TripleIter listXXO(Constant o);
	
	TripleIter listXYZ();
	TripleIter listXXY();
	TripleIter listXYY();
	TripleIter listXYX();
	TripleIter listXXX();

}
