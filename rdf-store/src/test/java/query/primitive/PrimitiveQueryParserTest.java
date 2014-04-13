package query.primitive;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import query.PrimitiveQuery;
import query.VariableImpl;

public class PrimitiveQueryParserTest {

	@Test
	public void test() {
		PrimitiveQuery expected = new XYZ(
				VariableImpl.of("x"), VariableImpl.of("y"), VariableImpl.of("z"));
		
		assertThat(PrimitiveQueryParser.parse("?x,?y,?z"), is(expected));
	}

	@Test
	public void test_trim() {
		PrimitiveQuery expected = new XYZ(
				VariableImpl.of("x"), VariableImpl.of("y"), VariableImpl.of("z"));
		
		assertThat(PrimitiveQueryParser.parse(" ?x  , ?y , ?z "), is(expected));
	}

	

}
