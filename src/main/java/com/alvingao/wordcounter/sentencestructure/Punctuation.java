package com.alvingao.wordcounter.sentencestructure;

public class Punctuation extends AbstractSentenceElement {
    public Punctuation(String val) {
        super(val);
    }

    public Punctuation(Punctuation punctuation) {
        super(punctuation);
    }
}