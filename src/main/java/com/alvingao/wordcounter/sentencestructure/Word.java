package com.alvingao.wordcounter.sentencestructure;

public class Word extends AbstractSentenceElement {
    public Word(String val) {
        super(val);
    }

    public Word(Word word) {
        super(word);
    }
}