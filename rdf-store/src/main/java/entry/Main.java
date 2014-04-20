package entry;

import io.RDFFileReader;

import java.io.File;

import com.google.common.base.Joiner;

import core.FastOntology;

public class Main {

	public static void main(String[] args) {
		final String path = Joiner.on(File.separator).join( 
				System.getProperty("user.dir"), "src", "main", "resources", "onto.rdf");

		FastOntology ts = new RDFFileReader().read(path);
		
		System.out.println(ts);
	}
}
