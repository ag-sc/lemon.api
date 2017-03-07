# lemon.api


## Description

The API can be used to load, create, manipulate and serialize [*lemon*](http://lemon-model.net/) lexica.
In this section we present some examples of how to use the API. It is build with running the following commands:

`mvn clean && mvn install`


In the following we describe the core classes and their functions.

### LexicalEntry

The class *LexicalEntry* is one of the core classes of the API. With this class *lemon* entries are generated.

```java
LexicalEntry entry = new LexicalEntry(language);
```
initializes a lexical entry for a given language. Currently four different languages are supported:

```java
public enum Language {    
    EN, DE, ES, JA
}
```

For each entry the canonical form, the POS tag and the URI of the entry have to be specified, e.g. as follows:

```java
setCanonicalForm("wife")
setPOS("http://www.lexinfo.net/ontology/2.0/lexinfo#commonNoun")
setURI("http://dblexipedia.org/LexicalEntry_wife_as_Noun_withPrep_of")
```
The URI serves as a unique identifier of the entry.
Additionally, each lexical entry contains one or more senses, with a reference to the ontology element(s) that is verbalized.
This API implements two different types of references: simple reference pointing to a URI and references for restriction classes.

+ Simple reference:
    ```java
    Sense sense = new Sense();
    Reference ref = new SimpleReference
        ("http://dbpedia.org/ontology/spouse");
    sense.setReference(ref);
    ```
+ Reference for a restriction class:
    ```java
    Sense sense = new Sense();
    Reference ref = new Restriction
        (lexicon.getBaseURI()+"RestrictionClass_gender_female",
        "http://dbpedia.org/resource/female",
        "http://dbpedia.org/ontology/gender");
    sense.setReference(ref);
    ```


While creating a lexical entry it is also possible to define the syntactic behaviour of this entry which is then bound to the sense.

```java
SyntacticBehaviour behaviour  = new SyntacticBehaviour();
String lexinfo = "http://www.lexinfo.net/ontology/2.0/lexinfo#";
String lemon = "http://lemon-model.net/lemon#";

behaviour.setFrame(lexinfo + "NounPossessiveFrame");

behaviour.add(new SyntacticArgument(lexinfo + "prepositionalObject","object",preposition));
	
behaviour.add(new SyntacticArgument(lexinfo + "copulativeArg","subject",null));

sense.addSenseArg(new SenseArgument(lemon + "subjOfProp","subject"));

sense.addSenseArg(new SenseArgument(lemon + "objOfProp","object"));

entry.addSyntacticBehaviour(behaviour,sense);
``` 
We use [*lexinfo*](http://lexinfo.net/) as vocabulary for the frames, however, this can be simply changed to any other linguistic ontology.


By iterating over the pairs of SyntacticBehaviour and Sense, the URI of the lexicalized property can be retrieved.
```java
for(Sense sense : entry.getSenseBehaviours().keySet()){
     Reference ref = sense.getReference();
     //for a simple reference
     if (ref instanceof de.citec.sc.matoll.core.SimpleReference)  
     		System.out.println(ref.getURI());
     		
     //for a restriction class
     if (ref instanceof de.citec.sc.matoll.core.Restriction)
          System.out.println(refgetProperty());
}           
```


Each lexical entry can also be linked to provenance information:
```java
Provenance provenance = new Provenance();
provenance.setFrequency(1);
entry.addProvenance(provenance,sense);
```

### Lexicon
If not empty, a lexicon consists of a set of lexical entries. A new lexical entry is added to the lexicon with
`lexicon.addEntry(entry)`

Based on the URI of the lexical entry it is automatically verified whether the entry already exists or not. If so, both are merged.


The lexicon class has some build-in functions to retrieve different lexical entries:

* `lexicon.getEntries()` retrieves all lexical entries
   

* `lexicon.getEntries('EN')` retrieves all lexical entries for a the English language


* `lexicon.getEntriesWithCanonicalForm("wife")` retrieves all lexical entries with a certain canonical form, e.g *wife*

* `lexicon.getEntriesForReference("http://dbpedia.org/ontology/spouse")` retrieves all lexical entries for a certain property, e.g. *dbo:spouse*





Additionally some other helpful functions are available:
* `lexicon.getPrepositions()` returns all prepositions occurring in lexical entries, e.g. *to*, *for*, *while*, etc. 
* `lexicon.getReferences()` returns all references (e.g. *dbo:spouse*) in a given lexicon.
* `lexicon.size()` returns the number of lexical entries. Note that as one entry can lexicalize multiple properties ( i.e. have multiple references), it is possible to have more references than lexical entries. And because several entries can lexicalize the same property, it is also possible to have less references than entries.
* `lexicon.setBaseURI(http://localhost:8080/)` sets the baseURI for the lexicon to [*http://localhost:8080*](http://localhost:8080). If this function is not used, the baseURI is automatically set to [*http://dblexipedia.org/*](http://dblexipedia.org/)




### LexiconLoader
The lexiconloader allows to load a lexicon from a file:
```java
 LexiconLoader loader = new LexiconLoader();
 Lexicon lexicon = loader.loadFromFile("example.ttl");
```
Afterwards this lexicon can be used as described above.




### LexiconSerialization

With the help of the class \emph{LexiconSerialization} a lexicon is written to an RDF file.

```java
LexiconSerialization serializer = new LexiconSerialization(true);
Model model = ModelFactory.createDefaultModel();

serializer.serialize(lexicon, model);

FileOutputStream out = new FileOutputStream(new File("lexicon.ttl"));
RDFDataMgr.write(out, model, RDFFormat.TURTLE) ;
out.close();
```

```java
\begin{lstlisting}[language=Java,frame=single]
LexiconSerialization serializer = new LexiconSerialization(true);
```
the Boolean argument enables or disables the function to automatically remove those lexical entries where the canonical form is a stopword. 


## General

This API was developed by [Sebastian Walter](https://github.com/swalter2) in his Thesis [*Generation of Multilingual Ontology Lexica with M-ATOLL*](http://nbn-resolving.de/urn:nbn:de:0070-pub-29077066). The description above was also extracted from this thesis.

## License

This API is published under the Apache 2.0 License
 