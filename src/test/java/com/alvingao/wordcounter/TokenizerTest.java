package com.alvingao.wordcounter;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import com.alvingao.wordcounter.exceptions.TokenizationException;
import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;
import com.alvingao.wordcounter.sentencestructure.Word;
import com.alvingao.wordcounter.tokenizer.Tokenizer;

public class TokenizerTest {
    @Test
    public void testTokenizerSingleton() throws TokenizationException {
        Tokenizer classUnderTest = new Tokenizer();

        List<SentenceElement> actual = classUnderTest.tokenize("hi");
        List<SentenceElement> expected = new ArrayList<SentenceElement>();
        expected.add(new Word("hi"));

        assertEquals(expected, actual);
    }

    @Test
    public void testTokenizerComplex() throws TokenizationException {
        Tokenizer classUnderTest = new Tokenizer();

        List<SentenceElement> actual = classUnderTest.tokenize("The quick brown fox jumps over the lazy dog's house.");
        List<SentenceElement> expected = new ArrayList<SentenceElement>();
        expected.add(new Word("The"));
        expected.add(new Word("quick"));
        expected.add(new Word("brown"));
        expected.add(new Word("fox"));
        expected.add(new Word("jumps"));
        expected.add(new Word("over"));
        expected.add(new Word("the"));
        expected.add(new Word("lazy"));
        expected.add(new Word("dog's"));
        expected.add(new Word("house"));
        expected.add(new Punctuation("."));

        assertEquals(expected, actual);
    }

    @Test
    public void testCorpusToSentences() throws TokenizationException {
        Tokenizer classUnderTest = new Tokenizer();

        List<Sentence> actual = classUnderTest.corpusToSentences("Hello world. I am here!");
        List<Sentence> expected = new ArrayList<>();

        expected.add(new Sentence(new Word("Hello"), new Word("world"), new Punctuation(".")));
        expected.add(new Sentence(new Word("I"), new Word("am"), new Word("here"), new Punctuation("!")));

        assertEquals(expected, actual);
    }

    @Test
    public void testCorpusToSentencesNoEndingPunctuation() throws TokenizationException {
        Tokenizer classUnderTest = new Tokenizer();

        List<Sentence> actual = classUnderTest.corpusToSentences("Hello world. I am here");
        List<Sentence> expected = new ArrayList<>();

        expected.add(new Sentence(new Word("Hello"), new Word("world"), new Punctuation(".")));
        expected.add(new Sentence(new Word("I"), new Word("am"), new Word("here")));

        assertEquals(expected, actual);
    }
}