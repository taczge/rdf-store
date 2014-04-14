package experiment;

import query.Query;
import query.QueryParser;
import rule.Rule;
import rule.RuleTarget;
import rule.Rules;
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
	private static final Resource not = Resource.of("NOT");
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
		
		Query head1 = QueryParser.parse(
				"1,AND,?v1." + "?v1,?v2,?v3." + "?v2,?v21,?v22." + "?v3,?v31,?v32.");
		Query body1 = QueryParser.parse(
				"1,?v21,?v22." + "1,?v31,?v32.");
		
		Query head2 = QueryParser.parse(
				"0,OR,?v1." + "?v1,?v2,?v3." + "?v2,?v21,?v22." + "?v3,?v31,?v32.");
		Query body2 = QueryParser.parse(
				"1,?v21,?v22." + "1,?v31,?v32.");
		
		Query head3 = QueryParser.parse("1,NOT,?v1." + "?v1,?v11,?v12.");
		Query body3 = QueryParser.parse("0,?v11,?v12.");

		Query head4 = QueryParser.parse("0,NOT,?v1." + "?v1,?v11,?v12.");
		Query body4 = QueryParser.parse("1,?v11,?v12.");
		
		Rules rule = new Rules(
				new Rule(head1, body1),
				new Rule(head2, body2),
				new Rule(head3, body3),
				new Rule(head4, body4));
		
		RuleTarget t = rule.apply(target);
		
		Query query = QueryParser.parse("1,ATOM,?x.0,ATOM,?x.");
		System.out.println( query.solve(t) );
	}
	
}
