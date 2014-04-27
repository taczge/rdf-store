package rule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import core.DifferenceOntology;
import core.Ontology;
import core.Resource;
import core.SimpleOntology;
import core.UndecidableOntology;

public class UndecidableRuleTest {
	
	private static final Resource a = Resource.of("a");
	private static final Resource b = Resource.of("b");
	private static final Resource c = Resource.of("c");
	private static final Resource d = Resource.of("d");
	
	private static final Resource p = Resource.of("p");

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void apply_returnUndecidableOntology() {
		UndecidableRule sut = UndecidableRuleParser.parse(
				"?x,p,?y.?y,p,?z. => ?x,p,?z. | ?z,p,?x.");
		
		Ontology original = new SimpleOntology();
		original.add(a, p, b);
		original.add(b, p, c);
		
		UndecidableOntology expected = DifferenceOntology.distribute(
				original, 
				SimpleOntology.singleton(a, p, c),
				SimpleOntology.singleton(c, p, a));
				
		assertThat(sut.apply(original), is(expected));
	}
	
	@Test
	public void apply_returnSingletonOfGivenOntology() {
		UndecidableRule sut = UndecidableRuleParser.parse(
				"?x,p,?y.?y,p,?z. => ?x,p,?z. | ?z,p,?x. | ?x,p,?y.");
		
		Ontology ontology = new SimpleOntology();
		ontology.add(a, p, b);
		ontology.add(b, p, c);
		
		UndecidableOntology expected = UndecidableOntology.of(ontology);

		assertThat(sut.apply(ontology), is(expected));
	}
	
	@Test
	public void apply_whenUndecidableOntolgyIsGiven() {
		UndecidableRule sut = UndecidableRuleParser.parse(
				"?x,p,?y.?y,p,?z. => ?x,p,?z. | ?z,p,?x.");
		
		Ontology parent = SimpleOntology.singleton(a, p, b);
		Ontology child1 = SimpleOntology.singleton(b, p, c);
		Ontology child2 = SimpleOntology.singleton(b, p, d);
		
		Ontology left = new DifferenceOntology(parent, child1);
		Ontology right = new DifferenceOntology(parent, child2);

		UndecidableOntology original = UndecidableOntology.of(left, right);
				
		UndecidableOntology expected = UndecidableOntology.of(
				new DifferenceOntology(left , SimpleOntology.singleton(a, p, c)),
				new DifferenceOntology(left , SimpleOntology.singleton(c, p, a)),
				new DifferenceOntology(right, SimpleOntology.singleton(a, p, d)),
				new DifferenceOntology(right, SimpleOntology.singleton(d, p, a)));

		assertThat(sut.apply(original), is(expected));
	}

	@Test
	public void apply_returnUncompleteTree() {
		UndecidableRule sut = UndecidableRuleParser.parse(
				"?x,p,?y.?y,p,?z. => ?x,p,?z. | ?z,p,?x.");
		
		Ontology parent = new SimpleOntology();
		parent.add(a, p, b);
		parent.add(b, p, c);
		
		Ontology child1 = SimpleOntology.singleton(a, p, c);
		Ontology child2 = SimpleOntology.singleton(c, p, a);
		
		Ontology left = new DifferenceOntology(parent, child1);
		Ontology right = new DifferenceOntology(parent, child2);

		UndecidableOntology original = UndecidableOntology.of(left, right);
				
		assertThat(sut.apply(original), is(original));
	}

}
