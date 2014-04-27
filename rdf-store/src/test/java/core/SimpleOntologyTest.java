package core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class SimpleOntologyTest {

	private Ontology sut;
	
	private static final Resource s = Resource.of("s");
	private static final Resource p = Resource.of("p");
	private static final Resource o = Resource.of("o");
	
	@Before
	public void setUp() throws Exception {
		sut = new SimpleOntology();
	}

	@Test
	public void add_Triple_returnTrue() {
		assertThat(sut.add(new Triple(s, p, o)), is(true));
	}
	
	@Test
	public void add_Triple_returnFalse() throws Exception {
		sut.add( new Triple(s, p, o) );
		
		assertThat(sut.add(new Triple(s, p, o)), is(false));
	}
	
	@Test
	public void add_SPO_returnTrue() {
		assertThat(sut.add(s, p, o), is(true));
	}
	
	@Test
	public void add_SPO_returnFalse() throws Exception {
		sut.add(s, p, o);
		
		assertThat(sut.add(s, p, o), is(false));
	}
	
	@Test
	public void addAll_returnTrue() throws Exception {
		sut.add(s, p, o);
		
		Set<Triple> in = new HashSet<>();
		in.add( new Triple(s, p, o) );
		in.add( new Triple(s, s, s) );
		
		assertThat(sut.addAll(in), is(true));
	}
	
	@Test
	public void addAll_returnFalse() throws Exception {
		sut.add(s, p, o);
		
		Set<Triple> in = new HashSet<>();
		in.add( new Triple(s, p, o) );
		
		assertThat(sut.addAll(in), is(false));
	}


}
