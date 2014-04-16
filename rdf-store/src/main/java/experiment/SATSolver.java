package experiment;

import logic.PLParser;
import logic.Proposition;
import solver.PLSATSolver;

public class SATSolver {
	
	private static void check(String exp) {
		String msg = String.format("%3s: %s", PLSATSolver.check(exp), exp);
		
		System.out.println(msg);
	}
	
	public static void main(String[] args) {
		Proposition p = PLParser.parse(
				"(and (and p1 q2) (not (or q1 (or q2 (not (not q3))))))");
		System.out.println(p + " => " + p.simplify());
		check("(and (and p1 q2) (not (or q1 (or q2 (not (not q3))))))");
		check("(and p q)");
		check("(and p (not p))");
	}

}
