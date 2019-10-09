package com.alvingao.wordcounter;

import java.util.ArrayList;
import java.util.List;

import com.alvingao.wordcounter.sentencestructure.SentenceElement;

public class Sentence {
    private List<SentenceElement> sentenceElements;

    public Sentence() {
        this.sentenceElements = new ArrayList<>();
    }

    public Sentence(List<SentenceElement> sentenceElements) {
        this.sentenceElements = new ArrayList<>(sentenceElements);
    }

    public List<SentenceElement> getSentenceElements() {
        return new ArrayList<SentenceElement>(this.sentenceElements);
    }
}