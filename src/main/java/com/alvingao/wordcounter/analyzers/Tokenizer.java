package com.alvingao.wordcounter.analyzers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
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
     * 
     * @param corpus the {@code List<SentenceElements>}
     * @return the generated {@code List<Sentence>}
     * @throws TokenizationException when an invalid token is detected.
     */
    public List<Sentence> corpusToSentences(String corpus) throws TokenizationException {
        List<Sentence> output = new ArrayList<>();

        List<SentenceElement> elements = this.tokenize(corpus);
        List<SentenceElement> currentSentence = new ArrayList<>();

        // flag to indicate the iterator is inside of quotation marks
        boolean isInsideQuotes = false;

        Iterator<SentenceElement> iter = elements.iterator();
        while (iter.hasNext()) {
            SentenceElement element = iter.next();

            currentSentence.add(element);

            if (element.equals(new Punctuation("\""))) {
                isInsideQuotes = !isInsideQuotes;
            }

            if (element instanceof Punctuation && element.getValue().matches("[.?!]")) {

                // if we're inside of quotation marks we need to "seek ahead" before completing
                // our sentence to look for the end quotation mark and flip the marker boolean.
                if (isInsideQuotes) {
                    SentenceElement quoteCandidate;

                    try {
                        quoteCandidate = iter.next();

                    } catch (NoSuchElementException e) {
                        // If the text is not well-formed, i.e. the sample ends on a punctuation mark
                        // without a closing quotation mark, then we need to throw a tokenization
                        // exception.
                        throw new TokenizationException(
                                "Sample ends on a punctuation mark without a closing quotation mark: " + e);
                    }

                    // If our quote candidate is a close quote, add it to the current sentence and
                    // close the sentence.
                    if (quoteCandidate.equals(new Punctuation("\""))) {
                        currentSentence.add(quoteCandidate);
                        isInsideQuotes = !isInsideQuotes;

                        output.add(new Sentence(currentSentence));
                        currentSentence = new ArrayList<>();
                    } else {
                        // ...otherwise, close the sentence and add our candidate to the next one.
                        output.add(new Sentence(currentSentence));
                        currentSentence = new ArrayList<>();

                        currentSentence.add(quoteCandidate);
                    }
                } else {
                    // If we're not currently inside quotation marks we can move on.
                    output.add(new Sentence(currentSentence));
                    currentSentence = new ArrayList<>();
                }
            }
        }

        // just in case the text doesn't end on a punctuation mark
        if (currentSentence.size() > 0) {
            output.add(new Sentence(currentSentence));
        }

        return output;
    }
}