package experiment;

import solver.PLSATSolverImpl;

public class SATSolverExperiment {
	
	private SATSolverExperiment() {};
	
	private static void check(String exp) {
		System.out.println(
				String.format("%3s: %s", PLSATSolverImpl.check(exp), exp));
	}
	
	public static void main(String[] args) {
		check("(and (and p1 q2) (not (or q1 (or q2 (not (not q3))))))");

		check("(and p q)");
		check("(and p (not p))");

		check("(or p q)");
		check("(or p (not p))");
	}

}
