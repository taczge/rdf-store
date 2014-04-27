package rule;

import java.util.Set;

import query.Resolution;
import query.Substitution;
import core.Triple;

public interface Body {

	Set<Triple> toTriple(Resolution r);
	Set<Triple> toTriple(Substitution s);

}
