package experiment;

import query.Query;
import query.QueryParser;
import rule.Rule;
import rule.RuleParser;
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
	
	private static final Resource atom = Resource.of("ATOM");
	private static final Resource not = Resource.of("NOT");
	private static final Resource and = Resource.of("AND");
	private static final Resource or  = Resource.of("OR");
	
	private static final Resource _0  = Resource.of("0");
	private static final Resource _1  = Resource.of("1");
	
	private static final Rule r1 = RuleParser.parse(
			"1,AND,?v1.?v1,?v2,?v3.?v2,?v21,?v22.?v3,?v31,?v32.=>1,?v21,?v22.1,?v31,?v32.");
	private static final Rule r2 = RuleParser.parse(
			"0,OR,?v1.?v1,?v2,?v3.?v2,?v21,?v22.?v3,?v31,?v32.=>0,?v21,?v22.0,?v31,?v32.");
	private static final Rule r3 = RuleParser.parse(
			"1,NOT,?v1.?v1,?v11,?v12.=>0,?v11,?v12.");
	private static final Rule r4 = RuleParser.parse(
			"0,NOT,?v1.?v1,?v11,?v12.=>1,?v11,?v12.");
	private static final Rules rules = new Rules(r1, r2, r3, r4);
	private static final Query query = QueryParser.parse("1,ATOM,?x.0,ATOM,?x.");

	private static void solve(Triples t, boolean canSatisfy) {
		
		System.out.println(
				String.format("%s: %s",
						canSatisfy ? "解なし" : "解あり",
								query.solve(rules.apply(t))));
	}
	
	public static void example() {
		Triples triples = new Triples();
		triples.add(_1, and, c1);
		triples.add(c1, c2, c3);
		triples.add(c2, and, c4);
		triples.add(c4, c5, c6);
		triples.add(c5, atom, p1);
		
		triples.add(c6, atom, q2);
		triples.add(c3, not, c7);
		triples.add(c7, or, c8);
		triples.add(c8, c9, c10);
		triples.add(c9, atom, q1);
		
		triples.add(c10, or, c11);
		triples.add(c11, c12, c13);
		triples.add(c12, atom, q2);
		triples.add(c13, not, c14);
		triples.add(c14, not, c15);
		
		triples.add(c15, atom, q3);
		
		solve(triples, false);
	}

	public static void p_and_q() {
		Triples triples = new Triples();
		
		triples.add(_1, and, c1);
		triples.add(c1, c2, c3);
		triples.add(c2, atom, p1);
		triples.add(c3, atom, q1);
		
		solve(triples, true);
	}
		
	public static void p_or_q() {
		Triples triples = new Triples();
		
		triples.add(_0, or, c1);
		triples.add(c1, c2, c3);
		triples.add(c2, atom, p1);
		triples.add(c3, atom, q1);
		
		solve(triples, true);
	}
	
	public static void p_and_notp() {
		Triples triples = new Triples();
		
		triples.add(_1, and, c1);
		triples.add(c1, c2, c3);
		triples.add(c2, atom, p1);
		triples.add(c3, not, c4);
		triples.add(c4, atom, p1);
		
		solve(triples, false);
	}
	
	public static void p_or_notp() {
		// やり方は間違っていないはず
		Triples triples = new Triples();
		
		triples.add(_0, or, c1);
		triples.add(c1, c2, c3);
		triples.add(c2, atom, p1);
		triples.add(c3, not, c4);
		triples.add(c4, atom, p1);

		solve(triples, true);
	}
	
	public static void notp_and_notp() {
		Triples triples = new Triples();
		
		triples.add(_1, and, c1);
		triples.add(c1, c2, c3);
		triples.add(c2, not, c4);
		triples.add(c4, atom, p1);
		triples.add(c3, not, c5);
		triples.add(c5, atom, p1);

		solve(triples, true);
	}
	
	public static void notnotp_and_notnotp() {
		// and から始まらないとダメ? 
		Triples triples = new Triples();
		
		triples.add(_0, or, c1);
		triples.add(c1, c2, c3);
		triples.add(c2, not, c4);
		triples.add(c4, not, c5);
		triples.add(c5, atom, p1);
		triples.add(c3, not, c6);
		triples.add(c6, not, c7);
		triples.add(c7, atom, p1);

		solve(triples, true);
	}
	
	public static void main(String[] args) {
		example();
		
		p_and_q();
		p_or_q();
		
		p_and_notp();
		p_or_notp();
		
		notp_and_notp();
		notnotp_and_notnotp();
	}

}
