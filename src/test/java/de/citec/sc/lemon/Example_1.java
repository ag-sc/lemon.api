/**
 * ********************************************************************************
 * Copyright (c) 2016, Semantic Computing Group, Bielefeld University All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. * Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. * Neither the name of the Semantic Computing Group nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE MONNET PROJECT BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ********************************************************************************
 */
package de.citec.sc.lemon;

import static de.citec.sc.lemon.core.Language.EN;
import de.citec.sc.lemon.core.LexicalEntry;
import de.citec.sc.lemon.core.Lexicon;
import de.citec.sc.lemon.io.CSV_LexiconLoader;
import de.citec.sc.lemon.io.CSV_LexiconSerialization;
import de.citec.sc.lemon.utils.Templates;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author swalter
 */
public class Example_1 {
    
     public static void main(String args[]) {
         Lexicon lexicon = new Lexicon();
         
         Templates.create_TransitiveVerb_Entry(lexicon, 
                 "run", 
                 "http://lemon-model.net/lemon#subjOfProp", 
                 "http://lemon-model.net/lemon#objOfProp", 
                 "http://dbpedia.org/ontology/running", EN);
         
         Templates.createAdjectiveForRestrictionClassEntry(lexicon,
                 "female",
                 "http://dbpedia.org/resource/Female", 
                 "http://dbpedia.org/ontology/gender");
         
         Templates.create_TransitiveVerb_Entry(lexicon,
                 "run",
                 "http://lemon-model.net/lemon#subjOfProp",
                 "http://lemon-model.net/lemon#objOfProp",
                 "http://dbpedia.org/ontology/running", EN);
        
         Templates.create_TransitiveVerb_Entry(lexicon,
                 "run2",
                 "http://lemon-model.net/lemon#objOfProp",
                 "http://lemon-model.net/lemon#subjOfProp",
                 "http://dbpedia.org/ontology/running", EN);
         
         for(LexicalEntry entry:lexicon.getEntries()){
             System.out.println(entry.getCanonicalForm());
         }
         
         CSV_LexiconSerialization serializer = new CSV_LexiconSerialization();
         try {
             serializer.serialize(lexicon, "example_1.csv");
         } catch (IOException ex) {
             Logger.getLogger(Example_1.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        //         CSV_LexiconLoader loader = new CSV_LexiconLoader();
        //         loader.loadFromFile("resources/matoll_test.tsv", EN);
         
     }
    
}
