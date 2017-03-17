package de.citec.sc.lemon.core;

import java.util.HashSet;
import java.util.Set;

import org.apache.jena.rdf.model.Resource;

/**
 * 
 * @author Alessandro Di Diego
 * @author Giuseppe Vertulli
 */
public class LexicalEntryWithResources extends LexicalEntry {

	Resource canonicalFormResource;
	Set<Resource> alternativeFormResources = new HashSet<Resource>();

	public LexicalEntryWithResources(Language language) {
		super(language);
	}

	public LexicalEntryWithResources(String uri, Language language) {
		super(uri, language);
	}

	public Resource getCanonicalFormResource() {
		return canonicalFormResource;
	}

	public void setCanonicalFormResource(Resource canonicalFormResource) {
		this.canonicalFormResource = canonicalFormResource;
	}

	public Set<Resource> getAlternativeFormsResources() {
		return alternativeFormResources;
	}

	public void setAlternativeFormsResources(Set<Resource> alternativeFormResources) {
		this.alternativeFormResources = alternativeFormResources;
	}
	
    public void addAlternativeFormsResource(Resource alternativeForm) {
        this.alternativeFormResources.add(alternativeForm);
    }
    public void addAlternativeFormsAllResources(Set<Resource> alternativeForms) {
        this.alternativeFormResources.addAll(alternativeForms);
    }
	

}
