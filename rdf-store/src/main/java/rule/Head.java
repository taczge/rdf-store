package rule;

import core.Ontology;
import query.Resolution;

public interface Head {

	Resolution solve(Ontology target);

}
