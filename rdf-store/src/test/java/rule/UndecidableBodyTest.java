package rule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import query.QueryParser;
import query.Resolution;
import query.Substitution;
import query.Variable;
import core.DifferenceOntology;
import core.FastOntology;
import core.Ontology;
import core.Resource;
import core.SimpleOntology;
import core.UndecidableOntology;

public class UndecidableBodyTest {
	
	private static final Resource a = Resource.of("a");
	private static final Resource b = Resource.of("b");
	private static final Resource c = Resource.of("c");
	
	private static final Resource tp = Resource.of("rdf:type");
	private static final Resource man = Resource.of("Man");
	private static final Resource woman = Resource.of("Woman");
	
	private static final Variable x = Variable.of("x");
	

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void apply_() {
		Body left = QueryParser.parse("?x,rdf:type,Man.");
		Body right = QueryParser.parse("?x,rdf:type,Woman.");

		UndecidableBody sut = UndecidableBody.of(left, right);
		
		Resolution resolution = Resolution.of( Substitution.singleton(x, a) );

		Ontology ontology = new FastOntology();
		ontology.add( a, b, c );
		SimpleOntology diff1 = new SimpleOntology();
		diff1.add( a, tp, man );
		SimpleOntology diff2 = new SimpleOntology();
		diff2.add( a, tp, woman );
		
		Ontology child1 = new DifferenceOntology(ontology, diff1);
		Ontology child2 = new DifferenceOntology(ontology, diff2);
		
		UndecidableOntology expected = UndecidableOntology.of(child1, child2);
		
		assertThat(sut.apply(ontology, resolution), is(expected));
	}
	
	@Test
	public void apply_returnSingletonWhenResolutionIsFailure() {
		Body left = QueryParser.parse("?x,rdf:type,Man.");
		Body right = QueryParser.parse("?x,rdf:type,Woman.");

		UndecidableBody sut = UndecidableBody.of(left, right);
		
		Resolution resolution = Resolution.createEmpty();

		Ontology ontology = new FastOntology();
		ontology.add( a, b, c );
		
		UndecidableOntology expected = UndecidableOntology.of(ontology);
		
		assertThat(sut.apply(ontology, resolution), is(expected));
		
	}

}
