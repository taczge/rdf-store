package solver;


public interface PLSATSolver {
	
	enum Satisfiability { YES, NO };
	
	Satisfiability check(String exp);

}
