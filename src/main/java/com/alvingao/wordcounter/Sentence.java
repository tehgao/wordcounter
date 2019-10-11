package com.alvingao.wordcounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;

/**
 * Represents a sentence, usually a sequence of {@code Word}s terminated by a
 * terminating {@code Punctuation} mark.
 */
public class Sentence implements Iterable<SentenceElement> {
    private List<SentenceElement> sentenceElements;

    /**
     * Constructs an empty sentence. Not sure why anyone would want to do this since
     * {@code Sentence} is immutable, but it's here.
     */
    public Sentence() {
        this.sentenceElements = new ArrayList<>();
    }

    /**
     * Constructs a {@code Sentence} as a copy of the param.
     * 
     * @param sentence The sentence to copy.
     */
    public Sentence(Sentence sentence) {
        this.sentenceElements = new ArrayList<>(sentence.sentenceElements);
    }

    /**
     * Constructs a {@code Sentence} with {@code sentenceElements} as its
     * representation.
     * 
     * @param sentenceElements The {@code List<SentenceElement>} representation of
     *                         {@code this}.
     */
    public Sentence(List<SentenceElement> sentenceElements) {
        this.sentenceElements = new ArrayList<>(sentenceElements);
    }

    /**
     * Constructs a {@code Sentence} with {@code elements} as its representation.
     * 
     * @param sentenceElements The representation of {@code this}.
     */
    public Sentence(SentenceElement... elements) {
        this.sentenceElements = Arrays.asList(elements);
    }

    /**
     * Returns the {@code SentenceElement} at {@code index}.
     * 
     * @param index The index of the {@code SentenceElement} to return.
     * @return The {@code SentenceElement} at {@code index}.
     */
    public SentenceElement getElement(int index) {
        return this.sentenceElements.get(index);
    }

    @Override
    public String toString() {
        // TODO: fix this method
        String output = "";

        // flip flopping flag to ensure double quotes are spaced properly
        boolean quotation = false;
        for (SentenceElement elt : this.sentenceElements) {
            if (output.length() > 0 && !(elt instanceof Punctuation)) {
                output += " ";
            } else if (elt.equals(new Punctuation("\""))) {
                if (!quotation) {
                    output += " ";
                }
                quotation = !quotation;
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

    @Override
    public Iterator<SentenceElement> iterator() {
        return this.sentenceElements.iterator();
    }
}