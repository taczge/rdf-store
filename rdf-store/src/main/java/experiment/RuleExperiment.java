package experiment;

import io.RDFFileReader;

import java.io.File;

import query.Query;
import query.QueryTarget;
import query.primitive.PrimitiveQueryParser;
import rule.Rule;

import com.google.common.base.Joiner;

public class RuleExperiment {
	
	public static QueryTarget load() {
		final String path = Joiner.on(File.separator).join( 
				System.getProperty("user.dir"), "src", "main", "resources", "onto.rdf");

		return new RDFFileReader().read(path);
	}

	public static void main(String[] args) {
		QueryTarget target = load();
		
		Query head = new Query(
				PrimitiveQueryParser.parse("?x,rdfs:subClassOf,?y"));
		Query body = new Query(
				PrimitiveQueryParser.parse("?x,rdfs:subClassOf,?x"),
				PrimitiveQueryParser.parse("?y,rdfs:subClassOf,?y"));
		
		Rule rule = new Rule(head, body);

		System.out.println(rule);
		System.out.println(Joiner.on("\n").join(rule.execute(target)));
	}
	
}
