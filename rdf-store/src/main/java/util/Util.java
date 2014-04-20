package util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Util {
	
	private Util() {};
	
	public static <T> Set<T> union(Collection<T> a, Collection<T> b) {
		Set<T> union = new HashSet<>();
		
		union.addAll(a);
		union.addAll(b);
		
		return union;
	}

}
