package experiment;

import java.util.Set;

import logic.PLParser;
import logic.ResourceIssuer;
import logic.ResourceIssuerImpl;
import query.Query;
import query.QueryParser;
import rule.Rule;
import rule.RuleParser;
import rule.Rules;
import core.Resource;
import core.Triple;
import core.Triples;

public class SATSolver {
	
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
	
	

	private static void solve(String exp, boolean canSatisfy) {
		ResourceIssuer issuer = new ResourceIssuerImpl();
		Set<Triple> triples = PLParser.parse(exp).toTriples(issuer, _1);
		
		solve( new Triples(triples), canSatisfy ); 
	}
	
	private static void solve(Triples t, boolean canSatisfy) {
		System.out.println(
				String.format("%s: %s",
						canSatisfy ? "解なし" : "解あり",
								query.solve(rules.apply(t))));
	}
	
	public static void example() {
		solve("(and (and p1 q2) (not (or q1 (or q2 (not (not q3))))))", false);
	}

	public static void p_and_q() {
		solve("(and p q)", true);
	}
		
	public static void p_or_q() {
		solve("(or p q)", true);
	}
	
	public static void p_and_notp() {
		solve("(and p (not p))", false);
	}
	
	public static void p_or_notp() {
		solve("(or p (not p))", true);
	}
	
	public static void main(String[] args) {
		example();
		
		p_and_q();
		p_or_q();
		
		p_and_notp();
		p_or_notp();
	}

}
