package query;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

public class Query {
	
	private final List<PrimitiveQuery> primitives;
	
	public Query(List<PrimitiveQuery> primitives) {
		super();

		this.primitives = primitives;
	}
	
	public Query(PrimitiveQuery... primitives) {
		this( Lists.newArrayList(primitives) );
	}
	
	private boolean hasTail() {
		return primitives.size() >= 2;
	}
	
	private PrimitiveQuery head() {
		return primitives.get(0);
	}
	
	private Query tail() {
		return new Query( primitives.subList(1, primitives.size()) );
	}
	
	private Query apply(Substitution substitution) {
		List<PrimitiveQuery> applied = new LinkedList<>();
		
		for ( final PrimitiveQuery primitive : primitives ) {
			applied.add( primitive.apply(substitution) );
		}

		return new Query(applied);
	}

	public Resolution solve(QueryTarget target) {
		return __solve( new Substitution(), target );
	}
	
	private Resolution __solve(Substitution previous, QueryTarget target) {
		Resolution current = this.apply(previous).head().solve(target);
		Resolution next    = current.concat(previous);
		
		System.out.println("   __solve:" + this + " " + previous);
		
		if ( !hasTail() ) {
			return next;
		}

		Resolution answer = Resolution.createEmpty();
		for ( final Substitution s : next ) {
			answer.addAll( this.tail().__solve(s, target) );
		}
		
		return answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((primitives == null) ? 0 : primitives.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Query other = (Query) obj;
		if (primitives == null) {
			if (other.primitives != null) {
				return false;
			}
		} else if (!primitives.equals(other.primitives)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for ( final PrimitiveQuery p : primitives ) {
			sb.append(p);
		}

		return sb.toString();
	}
}
