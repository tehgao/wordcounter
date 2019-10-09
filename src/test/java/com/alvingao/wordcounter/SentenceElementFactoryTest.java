package com.alvingao.wordcounter;

import org.junit.Test;
import static org.junit.Assert.*;

import com.alvingao.wordcounter.exceptions.IllegalTokenException;
import com.alvingao.wordcounter.factories.SentenceElementFactory;
import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;
import com.alvingao.wordcounter.sentencestructure.Word;

public class SentenceElementFactoryTest {
    @Test
    public void testCreatePunctuation() throws IllegalTokenException {
        SentenceElement output = SentenceElementFactory.getSentenceElement(".");

        assertTrue(output instanceof Punctuation);
    }

    @Test
    public void testCreatePunctuationSingletonStringNotPunctuation() throws IllegalTokenException {
        SentenceElement output = SentenceElementFactory.getSentenceElement("a");

        assertFalse(output instanceof Punctuation);
    }

    @Test
    public void testCreatePunctuationWordNotPunctuation() throws IllegalTokenException {
        SentenceElement output = SentenceElementFactory.getSentenceElement("hello");

        assertFalse(output instanceof Punctuation);
    }

    @Test
    public void testCreateWord() throws IllegalTokenException {
        SentenceElement output = SentenceElementFactory.getSentenceElement("hi");

        assertTrue(output instanceof Word);
    }

    @Test(expected = IllegalTokenException.class)
    public void testCreateWordIncludesPunctuation() throws IllegalTokenException {
        SentenceElementFactory.getSentenceElement("hi.");

        // test should fail if the above statement does not throw an exception
        fail();
    }

    @Test(expected = IllegalTokenException.class)
    public void testCreateWordIsSentence() throws IllegalTokenException {
        SentenceElementFactory.getSentenceElement("hi my name is alvin");

        // test should fail if the above statement does not throw an exception
        fail();
    }
}