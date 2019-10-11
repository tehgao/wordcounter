package com.alvingao.wordcounter.factories;

import com.alvingao.wordcounter.exceptions.IllegalTokenException;
import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;
import com.alvingao.wordcounter.sentencestructure.Word;

/**
 * Factory class for creation of {@code SentenceElements}.
 */
public class SentenceElementFactory {

    /**
     * Creates a {@code SentenceElement} based on the parameter. The param must
     * either be a word or a single non-word punctuation mark character (no spaces).
     * 
     * @param token A string to convert into a {@code SentenceElement}.
     * @return A class that implements {@code SentenceElement} based on the content
     *         of the string.
     * @throws IllegalTokenException whenever an invalid token is passed in.
     */
    public static SentenceElement getSentenceElement(String token) throws IllegalTokenException {
        if (token.contains(" ")) {
            throw new IllegalTokenException("Token cannot contain spaces!");
        }

        SentenceElement output;

        if (token.length() == 1 && token.matches("[:;\",.!?]")) {
            output = new Punctuation(token);
        } else if (!token.matches(".*[:;\",.!]+.*")) {
            output = new Word(token);
        } else {
            throw new IllegalTokenException("Invalid token: " + token);
        }

        return output;
    }
}