package com.alvingao.wordcounter.factories;

import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;
import com.alvingao.wordcounter.sentencestructure.Word;

public class SentenceElementFactory {
    public static SentenceElement getSentenceElement(String token) {
        SentenceElement output;

        if (token.length() == 1 && token.matches("[:;\",.!]")) {
            output = new Punctuation(token);
        } else {
            output = new Word(token);
        }

        return output;
    }
}