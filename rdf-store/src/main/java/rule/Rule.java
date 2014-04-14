package rule;

import java.util.HashSet;
import java.util.Set;

import query.Query;
import query.QueryTarget;
import core.Triple;

public class Rule {
	
	private final Query head;
	private final Query body;

	public Rule(Query head, Query body) {
		super();
		
		// TODO: head <= body でないときのエラー処理
		this.head = head;
		this.body = body;
	}
	
	public Set<Triple> execute(QueryTarget target) {
		Set<Query> solved = body.apply( head.solve(target) );
		
		Set<Triple> triples = new HashSet<>( solved.size() );
		for ( final Query q : solved ) {
			triples.addAll( q.toTriple() );
		}
		
		return triples; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((head == null) ? 0 : head.hashCode());
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
		Rule other = (Rule) obj;
		if (body == null) {
			if (other.body != null) {
				return false;
			}
		} else if (!body.equals(other.body)) {
			return false;
		}
		if (head == null) {
			if (other.head != null) {
				return false;
			}
		} else if (!head.equals(other.head)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return head + "=>" + body;
	}
	
}
