package com.alvingao.wordcounter.sentencestructure;

/**
 * Represents a non-word punctuation mark.
 */
public class Punctuation extends AbstractSentenceElement {
    /**
     * Constructs a {@code Punctuation} represented by {@code val}.
     * 
     * @param val The string representation of {@code this}.
     */
    public Punctuation(String val) {
        super(val);
    }

    /**
     * Constructs an instance of {@code Punctuation} as a copy of
     * {@code punctuation}.
     * 
     * @param element The {@code Punctuation} to copy.
     */
    public Punctuation(Punctuation punctuation) {
        super(punctuation);
    }
}