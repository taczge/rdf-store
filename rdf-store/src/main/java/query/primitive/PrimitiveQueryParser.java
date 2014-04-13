package query.primitive;

import query.PrimitiveQuery;
import query.Token;
import query.VariableImpl;
import core.ResourceImpl;

public class PrimitiveQueryParser {
	
	private static final String VARIABLE_PREFIX = "?";
	
	private PrimitiveQueryParser() {}
	
	public static PrimitiveQuery parse(String exp) {
		// ?x,?y,?z. 終端の '.' は，QueryPerser で除去済みとする
		String[] tokens = exp.split(",");
		
		if ( tokens.length != 3 ) {
			throw new IllegalArgumentException("Invalid Primitive: " + exp);
		}
		
		return parse(tokens[0], tokens[1], tokens[2]);
	}
	
	private static PrimitiveQuery parse(String s, String p, String o) {
		return PrimitiveQueryFactory.create( toToken(s), toToken(p), toToken(o) );
	}
	
	private static Token toToken(String s) {
		String normalized = s.trim();
		
		if ( normalized.startsWith(VARIABLE_PREFIX) ) {
			return VariableImpl.of(removePrefix(normalized));
		}
		
		return ResourceImpl.of(normalized);  
	}
	
	private static String removePrefix(String s) {
		return s.substring( VARIABLE_PREFIX.length() );
	}

}
