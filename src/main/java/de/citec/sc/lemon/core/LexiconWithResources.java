package de.citec.sc.lemon.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * @author Alessandro Di Diego
 */
public class LexiconWithResources extends Lexicon {

	List<LexicalEntryWithResources> entriesWithResources;

	public LexiconWithResources() {
		super();
		this.entriesWithResources = new ArrayList<>();
	}

	public LexiconWithResources(String baseURI) {
		super(baseURI);
		this.entriesWithResources = new ArrayList<>();
	}

	public List<LexicalEntryWithResources> getEntriesWithResources() {
		return entriesWithResources;
	}

	private LexicalEntryWithResources getLexicalEntry(LexicalEntryWithResources entry) {
		String uri = entry.getURI();
		for (LexicalEntryWithResources containedEntry : entriesWithResources) {
			if (containedEntry.getURI().equals(uri))
				return containedEntry;
		}
		return null;
	}

	public void addEntry(LexicalEntryWithResources entry) {

		if (!entriesWithResources.contains(entry)) {
			entriesWithResources.add(entry);
			entries.add(entry);
		} else {
			LexicalEntryWithResources containedEntry;

			containedEntry = getLexicalEntry(entry);
			// if entry of URI is the same, but different forms appear, add
			// alternative forms
			if (!containedEntry.getCanonicalFormResource().equals(entry.getCanonicalFormResource())) {
				containedEntry.addAlternativeFormsResource(entry.getCanonicalFormResource());
			}
			if (!entry.getAlternativeFormsResources().isEmpty())
				containedEntry.addAlternativeFormsAllResources(entry.getAlternativeFormsResources());

			HashMap<Sense, HashSet<SyntacticBehaviour>> senseBehaviours = entry.getSenseBehaviours();
			senseBehaviours.keySet().stream().map((sense) -> {
				HashSet<SyntacticBehaviour> behaviours = senseBehaviours.get(sense);
				containedEntry.addAllSyntacticBehaviour(behaviours, sense);
				return sense;
			}).forEach((sense) -> {

				Provenance provenance = entry.getProvenance(sense);
				containedEntry.addProvenance(provenance, sense);
			});

		}

		if (entry.getSenseBehaviours() != null)
			entry.getReferences().stream().forEach((reference) -> {
				references.add(reference);
			});

	}

}
