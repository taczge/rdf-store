package experiment;

import io.RDFFileReader;

import java.io.File;

import query.Query;
import query.QueryTarget;
import query.primitive.PrimitiveQueryParser;

import com.google.common.base.Joiner;

public class QueryExperiment {
	
	public static QueryTarget load() {
		final String path = Joiner.on(File.separator).join( 
				System.getProperty("user.dir"), "src", "main", "resources", "onto.rdf");

		return new RDFFileReader().read(path);
	}

	public static void main(String[] args) {
		QueryTarget target = load();
		
		Query q = new Query(
				PrimitiveQueryParser.parse("?x,rdfs:subClassOf,?y"),
				PrimitiveQueryParser.parse("?y,rdfs:subClassOf,?z"));

		/*
		Query q = new Query(
				PrimitiveQueryParser.parse("?x,?y,?z"),
				PrimitiveQueryParser.parse("?s,?p,?o"));
		 */

		System.out.println(q);
		System.out.println(q.solve(target));
	}
}
