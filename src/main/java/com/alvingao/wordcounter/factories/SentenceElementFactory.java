package com.alvingao.wordcounter.factories;

import com.alvingao.wordcounter.exceptions.IllegalTokenException;
import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;
import com.alvingao.wordcounter.sentencestructure.Word;

public class SentenceElementFactory {
    public static SentenceElement getSentenceElement(String token) throws IllegalTokenException {
        if(token.contains(" ")) {
            throw new IllegalTokenException("Token cannot contain spaces!");
        }
        
        SentenceElement output;

        if (token.length() == 1 && token.matches("[:;\",.!?]")) {
            output = new Punctuation(token);
        } else if(!token.matches(".*[:;\",.!]+.*")) {
            output = new Word(token);
        } else {
            throw new IllegalTokenException("Invalid token: " + token);
        }

        return output;
    }

    public static SentenceElement getSentenceElement(SentenceElement element) {
        if(element instanceof Word) {
            return new Word((Word) element);
        } else if(element instanceof Punctuation) {
            return new Punctuation((Punctuation) element);
        } else {
            throw new IllegalArgumentException("Unsupported SentenceElement");
        }
    }
}