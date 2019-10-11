package com.alvingao.wordcounter.sentencestructure;

/**
 * A {@code SentenceElement} is a representation of a single non-whitespace
 * element in a sentence, most commonly a word or non-word punctuation.
 */
public interface SentenceElement {

    /**
     * Returns the string representation of {@code this}.
     * 
     * @return the string representation of {@code this}.
     */
    public String getValue();
}