package com.alvingao.wordcounter;

import org.junit.Test;
import static org.junit.Assert.*;

import com.alvingao.wordcounter.factories.SentenceElementFactory;
import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;

public class SentenceElementFactoryTest {
    @Test
    public void testCreatePunctuation() {
        SentenceElement output = SentenceElementFactory.getSentenceElement(".");

        assertTrue(output instanceof Punctuation);
    }

    @Test
    public void testCreatePunctuationSingletonStringNotPunctuation() {
        SentenceElement output = SentenceElementFactory.getSentenceElement("a");

        assertFalse(output instanceof Punctuation);
    }

    @Test
    public void testCreatePunctuationWordNotPunctuation() {
        SentenceElement output = SentenceElementFactory.getSentenceElement("hello");

        assertFalse(output instanceof Punctuation);
    }
}