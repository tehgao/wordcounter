package com.alvingao.wordcounter.factories;

import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;
import com.alvingao.wordcounter.sentencestructure.Word;

public class SentenceElementFactory {
    public static SentenceElement getSentenceElement(String token) {
        SentenceElement output;

        // we assume the tokenizer has done its job correctly -- all non-word
        // punctuation marks are of length 1.
        if (token.length() == 1) {
            output = new Punctuation(token);
        } else {
            output = new Word(token);
        }

        return output;
    }
}