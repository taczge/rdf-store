package rule;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.FastOntology;
import core.Ontology;
import core.Resource;

public class RuleTest {
	
	private static final Resource a = Resource.of("a");
	private static final Resource b = Resource.of("b");
	private static final Resource c = Resource.of("c");
	private static final Resource p = Resource.of("p");
	private static final Resource o = Resource.of("o");
	private static final Resource o1 = Resource.of("o1");
	private static final Resource o2 = Resource.of("o2");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Rule sut = RuleParser.parse("?x,p,o=>?x,p,o2.|?x,p,o3.");
		
		Ontology onto = new FastOntology();
		onto.add(a, p, o);

		
		UndecidableOntology expected = new UndecidableOntology(onto, );
		
		assertThat(sut.apply(undecidable), is());
	}

}
