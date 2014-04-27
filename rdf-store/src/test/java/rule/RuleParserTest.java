package rule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import query.Query;
import query.QueryParser;
import query.Variable;
import query.primitive.XPY;
import query.primitive.XYZ;
import core.Resource;

public class RuleParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String exp = "   ?p , dom , ?c  .  ?s , ?p  , ?o . =>  ?s, tp, ?c.    ";
		
		Query head = new Query( 
				new XPY(Variable.of("p"), Resource.of("dom"), Variable.of("c")),
				new XYZ(Variable.of("s"), Variable.of("p"), Variable.of("o")));
		Query body = new Query(
				new XPY(Variable.of("s"), Resource.of("tp"), Variable.of("c")));
						
		Rule expected = new Rule(head, body);
		
		assertThat(RuleParser.parse(exp), is(expected));
	}
	
	@Test
	public void parse_undecidable() throws Exception {
		String exp = "?x, p ,a => ?x , p  , b .  ?x , q , b.   |  ?x , p , c. |  ?x , p , d.";
		Query head = QueryParser.parse("?x,p,a.");
		Query body1 = QueryParser.parse("?x,p,b.?x,q,b.");
		Query body2 = QueryParser.parse("?x,p,c.");
		Query body3 = QueryParser.parse("?x,p,d.");

		Rule expected = new Rule(head, new UndecidableBody(body1, body2, body3));

		assertThat(RuleParser.parse(exp), is(expected));
	}

}
