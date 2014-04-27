package query;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import rule.Body;
import rule.Head;

import com.google.common.collect.Lists;

import core.Ontology;
import core.Triple;
import core.UndecidableOntology;

public class Query implements Head, Body {
	
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
		if ( primitives.isEmpty() ) {
			throw new IllegalStateException( "This query does not have any head." );
		}

		return primitives.get(0);
	}
	
	private Query tail() {
		if ( !hasTail() ) {
			throw new IllegalStateException( "This query does not have any tail." );
		}

		return new Query( primitives.subList(1, primitives.size()) );
	}
	
	private Query apply(Substitution s) {
		List<PrimitiveQuery> applied = new LinkedList<>();
		
		for ( final PrimitiveQuery primitive : primitives ) {
			applied.add( primitive.apply(s) );
		}

		return new Query(applied);
	}

	public Resolution solve(Ontology target) {
		return __solve( new Substitution(), target );
	}
	
	private Resolution __solve(Substitution previous, Ontology target) {
		Resolution current = this.apply(previous).head().solve(target);
		Resolution next    = current.concat(previous);
		
		if ( !hasTail() ) {
			return next;
		}

		Resolution answer = Resolution.createEmpty();
		for ( final Substitution s : next ) {
			answer.addAll( this.tail().__solve(s, target) );
		}
		
		return answer.isEmpty() ? Resolution.FAILURE : answer;
	}
	
	public QueryAnswer solve(UndecidableOntology undecidable) {
		Set<Resolution> answer = new HashSet<>();
		
		for ( final Ontology o : undecidable ) {
			answer.add( solve(o) );
		}
		
		return new QueryAnswer( answer );
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

	@Override
	public Set<Triple> toTriple(Resolution r) {
		Set<Triple> result = new HashSet<>();
		
		for ( final Substitution s : r) {
			result.addAll( this.apply(s).toTriple() );
		}
		
		return result;
	}
	
	private Set<Triple> toTriple() {
		Set<Triple> triples = new HashSet<>();
		
		for ( final PrimitiveQuery p : primitives ) {
			triples.add(p.toTriple());
		}
		
		return triples;
	}


}
