package com.alvingao.wordcounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;

public class Sentence {
    private List<SentenceElement> sentenceElements;

    public Sentence() {
        this.sentenceElements = new ArrayList<>();
    }

    public Sentence(List<SentenceElement> sentenceElements) {
        this.sentenceElements = new ArrayList<>(sentenceElements);
    }

    public Sentence(SentenceElement... elements) {
        this.sentenceElements = Arrays.asList(elements);
    }

    public SentenceElement getElement(int index) {
        return this.sentenceElements.get(index);
    }

    @Override
    public String toString() {
        String output = "";

        for(SentenceElement elt : this.sentenceElements) {
            if(output.length() > 0 && !(elt instanceof Punctuation)) {
                output += " ";
            }

            output += elt.toString();
        }

        return output;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sentenceElements);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        Sentence other = (Sentence) obj;
        return Objects.equals(this.sentenceElements, other.sentenceElements);
    }
}