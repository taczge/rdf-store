package experiment;

import core.Resource;
import core.Triples;

public class SATSolver {
	
	private static final Resource c1  = Resource.of("c1");
	private static final Resource c2  = Resource.of("c2");
	private static final Resource c3  = Resource.of("c3");
	private static final Resource c4  = Resource.of("c4");
	private static final Resource c5  = Resource.of("c5");
	private static final Resource c6  = Resource.of("c5");
	private static final Resource c7  = Resource.of("c7");
	private static final Resource c8  = Resource.of("c8");
	private static final Resource c9  = Resource.of("c9");
	private static final Resource c10 = Resource.of("c10");
	private static final Resource c11 = Resource.of("c11");
	private static final Resource c12 = Resource.of("c12");
	private static final Resource c13 = Resource.of("c13");
	private static final Resource c14 = Resource.of("c14");
	private static final Resource c15 = Resource.of("c15");
	
	private static final Resource p1  = Resource.of("p1");
	private static final Resource q1  = Resource.of("q1");
	private static final Resource q2  = Resource.of("q2");
	private static final Resource q3  = Resource.of("q3");
	
	private static final Resource at  = Resource.of("ATOM");
	private static final Resource not = Resource.of("NEG");
	private static final Resource and = Resource.of("AND");
	private static final Resource or  = Resource.of("OR");
	
	private static final Resource a   = Resource.of("1");
	
	public static void main(String[] args) {
		Triples target = new Triples();
		target.add(a, and, c1);
		target.add(c1, c2, c3);
		target.add(c2, and, c4);
		target.add(c4, c5, c6);
		target.add(c5, at, p1);
		
		target.add(c6, at, q2);
		target.add(c3, not, c7);
		target.add(c7, or, c8);
		target.add(c8, c9, c10);
		target.add(c9, at, q1);
		
		target.add(c10, or, c11);
		target.add(c11, c12, c13);
		target.add(c12, at, q2);
		target.add(c13, not, c14);
		target.add(c14, not, c15);
		
		target.add(c15, at, q3);
		
	}
	
}
