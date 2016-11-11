package de.citec.sc.lemon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import de.citec.sc.lemon.core.Language;
import de.citec.sc.lemon.core.LexicalEntry;
import de.citec.sc.lemon.core.Lexicon;
import de.citec.sc.lemon.core.Provenance;
import de.citec.sc.lemon.core.Reference;
import de.citec.sc.lemon.core.Restriction;
import de.citec.sc.lemon.core.Sense;
import de.citec.sc.lemon.core.SenseArgument;
import de.citec.sc.lemon.core.SyntacticArgument;
import de.citec.sc.lemon.core.SyntacticBehaviour;
import de.citec.sc.lemon.io.LexiconSerialization;

public class Example_6 {

	public static void main(String[] args) throws FileNotFoundException {
		
		Lexicon lexicon = new Lexicon();
		
		LexicalEntry entry = new LexicalEntry(Language.EN);
		
		//entry = lexicon.createNewEntry("female",Language.EN);
		
		entry.setCanonicalForm("female");
		
		Sense sense = new Sense();
		Reference ref = new Restriction(lexicon.getBaseURI()+"RestrictionClass_gender_Female","http://dbpedia.org/ontology/gender","http://dbpedia.org/resource/Female");
		sense.setReference(ref);
				
		entry.setPOS("http://www.lexinfo.net/ontology/2.0/lexinfo#adjective");
		
		SyntacticBehaviour behaviour = new SyntacticBehaviour();
		
		behaviour.setFrame("http://www.lexinfo.net/ontology/2.0/lexinfo#AdjectivePredicativeFrame");
				
		behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#copulativeSubject","1",null));
		
		sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#isA","1"));
		
		entry.addSyntacticBehaviour(behaviour,sense);
		
		Provenance provenance = new Provenance();
		
		provenance.setAgent("AdjectiveExtractor");
		provenance.setConfidence(0.8);
		
		entry.addProvenance(provenance,sense);
		
		//entry = lexicon.createNewEntry("female");
				
		entry.setPOS("http://www.lexinfo.net/ontology/2.0/lexinfo#adjective");

		behaviour = new SyntacticBehaviour();
		
		behaviour.setFrame("http://www.lexinfo.net/ontology/2.0/lexinfo#AdjectiveAttributiveFrame");
				
		behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#attributiveArg","1",null));
		
		sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#isA","1"));
		
		entry.addSyntacticBehaviour(behaviour,sense);
		
                lexicon.addEntry(entry);
		
		Model model = ModelFactory.createDefaultModel();
		
		LexiconSerialization serializer = new LexiconSerialization();
		
		serializer.serialize(lexicon, model);
		
		FileOutputStream out = new FileOutputStream(new File("female.ttl"));
		
		RDFDataMgr.write(out, model, RDFFormat.TURTLE) ;
		
		
	}
	

}
