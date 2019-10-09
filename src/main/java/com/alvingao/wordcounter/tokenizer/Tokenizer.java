package com.alvingao.wordcounter.tokenizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import com.alvingao.wordcounter.Sentence;
import com.alvingao.wordcounter.exceptions.IllegalTokenException;
import com.alvingao.wordcounter.exceptions.TokenizationException;
import com.alvingao.wordcounter.factories.SentenceElementFactory;
import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;

public class Tokenizer {
    public Tokenizer() {

    }

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

    public List<Sentence> corpusToSentences(String corpus) throws TokenizationException {
        List<Sentence> output = new ArrayList<>();

        List<SentenceElement> elements = this.tokenize(corpus);

        List<SentenceElement> currentSentence = new ArrayList<>();
        for(SentenceElement element : elements) {
            currentSentence.add(element);

            if(element instanceof Punctuation && element.getValue().matches("[.?!]")) {
                output.add(new Sentence(currentSentence));
                currentSentence = new ArrayList<>();
            }
        }

        // just in case the corpus doesn't end on a punctuation mark
        if(currentSentence.size() > 0) {
            output.add(new Sentence(currentSentence));
        }

        return output;
    }
}