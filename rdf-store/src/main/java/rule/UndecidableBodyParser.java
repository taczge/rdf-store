package rule;

import java.util.LinkedList;
import java.util.List;

import query.QueryParser;

public class UndecidableBodyParser {

	private UndecidableBodyParser() {};
	
	public static UndecidableBody parse(String exp) {
		String[] tokens = exp.split("\\|");
		
		List<Body> bodies = new LinkedList<>();
		for ( final String t : tokens ) {
			bodies.add( QueryParser.parse(t) );
		}
		
		return new UndecidableBody( bodies );
	}
}
