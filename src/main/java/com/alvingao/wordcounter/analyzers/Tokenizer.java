package com.alvingao.wordcounter.analyzers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import com.alvingao.wordcounter.Sentence;
import com.alvingao.wordcounter.exceptions.IllegalTokenException;
import com.alvingao.wordcounter.exceptions.TokenizationException;
import com.alvingao.wordcounter.factories.SentenceElementFactory;
import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;

/**
 * A class for handling tokenization.
 */
public class Tokenizer {
    /**
     * Creates an instance of {@code this}.
     */
    public Tokenizer() {

    }

    /**
     * Given a string, returns a {@code List<SentenceElement>} representing the
     * tokenized string where each {@code SentenceElement} represents either a word
     * or a non-word punctuation mark.
     * 
     * @param corpus the string to tokenize
     * @return a {@code List<SentenceElement>} representing the tokenized string.
     * @throws TokenizationException when an invalid token is detected.
     */
    public List<SentenceElement> tokenize(String corpus) throws TokenizationException {
        String[] rawTokens = Pattern.compile("([\\w-']+)|[:;\",.!?]").matcher(corpus).results().map(MatchResult::group)
                .toArray(String[]::new);

        List<SentenceElement> elements = new ArrayList<>();

        for (String token : rawTokens) {
            try {
                elements.add(SentenceElementFactory.getSentenceElement(token));
            } catch (IllegalTokenException e) {
                throw new TokenizationException("Error occured during tokenization: " + e.getMessage());
            }
        }

        return elements;
    }

    /**
     * Converts a {@code List<SentenceElements>} to a {@code List<Sentence>}.
     * @param corpus the {@code List<SentenceElements>}
     * @return the generated {@code List<Sentence>}
     * @throws TokenizationException
     */
    public List<Sentence> corpusToSentences(String corpus) throws TokenizationException {
        List<Sentence> output = new ArrayList<>();

        List<SentenceElement> elements = this.tokenize(corpus);

        List<SentenceElement> currentSentence = new ArrayList<>();
        for (SentenceElement element : elements) {
            currentSentence.add(element);

            if (element instanceof Punctuation && element.getValue().matches("[.?!]")) {
                output.add(new Sentence(currentSentence));
                currentSentence = new ArrayList<>();
            }
        }

        // just in case the corpus doesn't end on a punctuation mark
        if (currentSentence.size() > 0) {
            output.add(new Sentence(currentSentence));
        }

        return output;
    }
}