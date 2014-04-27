package rule;

import query.QueryParser;

public class UndecidableRuleParser {

	private UndecidableRuleParser() {};
	
	public static UndecidableRule parse(String exp) {
		String[] tokens = exp.split("=>");
		
		if ( tokens.length != 2 ) {
			throw new IllegalArgumentException(
					"invalid undecidable rule: " + exp);
		}
		
		Head head = QueryParser.parse( tokens[0] );
		UndecidableBody body = UndecidableBodyParser.parse( tokens[1] );
		
		return new UndecidableRule( head, body );
	}
}
