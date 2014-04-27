package rule;

import query.QueryAnswer;
import query.Resolution;
import core.Ontology;
import core.UndecidableOntology;

public interface Head {

	Resolution solve(Ontology ontology);
	QueryAnswer solve(UndecidableOntology ontology);

}
