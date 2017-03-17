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
package de.citec.sc.lemon.core;

import java.util.HashSet;
import java.util.Set;

import org.apache.jena.rdf.model.Resource;

/**
 * 
 * @author Alessandro Di Diego
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
