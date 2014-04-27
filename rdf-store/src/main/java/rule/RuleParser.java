package rule;

import java.util.LinkedList;
import java.util.List;

import query.QueryParser;

public class RuleParser {

	public static Rule parse(String exp) {
		String[] tokens = exp.split("=>");
		
		if ( tokens.length != 2 ) {
			throw new IllegalArgumentException("invalid rule expression: " + exp);
		}
		
		String head = tokens[0];
		String body = tokens[1];
		
		return new Rule( QueryParser.parse(head), parseBody(body) );
	}
	
	private static Body parseBody(String bodyExp) {
		List<Body> bodies = new LinkedList<Body>();
		
		String[] exps = bodyExp.split("\\|");
		
		if ( exps.length == 1 ) {
			return QueryParser.parse( exps[0] );
		}
		
		for ( final String e : exps ) {
			bodies.add( QueryParser.parse(e) );
		}
		
		return new UndecidableBody( bodies );
	}
	
	private RuleParser() {};
}
