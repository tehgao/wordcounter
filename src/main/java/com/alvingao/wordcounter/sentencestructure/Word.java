package com.alvingao.wordcounter.sentencestructure;

/**
 * Represents a word.
 */
public class Word extends AbstractSentenceElement {

    /**
     * Constructs a {@code Word} represented by {@code val}.
     * 
     * @param val The string representation of {@code this}.
     */
    public Word(String val) {
        super(val);
    }

    /**
     * Constructs an instance of {@code Word} as a copy of
     * {@code word}.
     * 
     * @param element The {@code Word} to copy.
     */
    public Word(Word word) {
        super(word);
    }
}