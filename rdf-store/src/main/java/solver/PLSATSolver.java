package solver;

<<<<<<< HEAD
import java.util.Set;

import logic.PLParser;
import logic.ResourceIssuer;
import logic.ResourceIssuerImpl;
import query.Query;
import query.QueryParser;
import rule.Rule;
import rule.RuleParser;
import rule.Rules;
import core.Ontology;
import core.Resource;
import core.Triple;
import core.FastOntology;
=======
import core.Resource;
>>>>>>> origin/develop


public interface PLSATSolver {
	
	Resource ATOM = Resource.of("ATOM");
	Resource NOT  = Resource.of("NOT");
	Resource AND  = Resource.of("AND");
	Resource OR   = Resource.of("OR");
	Resource _0   = Resource.of("0");
	Resource _1   = Resource.of("1");
	
	enum Satisfiability { YES, NO };
	
	Satisfiability check(String exp);

<<<<<<< HEAD
	public static final Satisfiability check(String exp) {
		ResourceIssuer issuer = new ResourceIssuerImpl();
		Set<Triple> ts = PLParser.parse(exp).toTriples(issuer, _1);
		Ontology target = rules.apply( new FastOntology(ts) );
		
		return query.solve(target).isEmpty() ? Satisfiability.YES : Satisfiability.NO;
	}
=======
>>>>>>> origin/develop
}
