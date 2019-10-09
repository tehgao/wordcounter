package com.alvingao.wordcounter.sentencestructure;

public abstract class AbstractSentenceElement implements SentenceElement {
    protected String value;

    public String getValue() {
        return this.value;
    }
}