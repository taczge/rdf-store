package experiment;

import io.RDFFileReader;

import java.io.File;

import query.Query;
import query.QueryParser;
import rule.Rule;
import rule.RuleTarget;

import com.google.common.base.Joiner;

public class RuleExperiment {
	
	public static RuleTarget load() {
		final String path = Joiner.on(File.separator).join( 
				System.getProperty("user.dir"), "src", "main", "resources", "onto.rdf");

		return new RDFFileReader().read(path);
	}

	public static void main(String[] args) {
		RuleTarget target = load();
		
		Query head = QueryParser.parse("?x,rdfs:subClassOf,?y.");
		Query body = QueryParser.parse("?x,rdfs:subClassOf,?x.?y,rdfs:subClassOf,?y.");
		
		Rule rule = new Rule(head, body);

		System.out.println(rule);
		System.out.println(rule.apply(target));
	}
	
}
