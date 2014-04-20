package util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class UtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void union() {
		Set<Integer> a = new HashSet<>();
		a.add(1);
		a.add(2);
		
		Set<Integer> b = new HashSet<>();
		b.add(2);
		b.add(3);
		
		Set<Integer> expected = new HashSet<>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		
		assertThat(Util.union(a, b), is(expected));
	}

}
