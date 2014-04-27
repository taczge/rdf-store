package rule;

import core.Ontology;
import core.UndecidableOntology;

public class UndecidableRule {

	private final Head head;
	private final UndecidableBody body;
	
	public UndecidableRule(Head head, UndecidableBody body) {
		super();
		this.head = head;
		this.body = body;
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
		UndecidableRule other = (UndecidableRule) obj;
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
	
	public UndecidableOntology apply(Ontology ontology) {
		return body.apply( ontology, head.solve(ontology) );
	}
	
	public UndecidableOntology apply(UndecidableOntology ontology) {
		UndecidableOntology undecidable = UndecidableOntology.createEmpty();
		
		for ( final Ontology o : ontology) {
			undecidable.addAll( apply(o) );
		}
			
		return undecidable;
	}
}
