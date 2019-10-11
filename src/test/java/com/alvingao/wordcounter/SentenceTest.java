package com.alvingao.wordcounter;

import static org.junit.Assert.assertEquals;

import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.Word;

import org.junit.Test;

public class SentenceTest {
    @Test
    public void testToString() {
        Sentence classUnderTest = new Sentence(new Word("Hello"), new Word("World"), new Punctuation("!"));

        assertEquals("Hello World!", classUnderTest.toString());
    }

    @Test
    public void testToStringQuotationMarks() {
        Sentence classUnderTest = new Sentence(new Word("Hello"), new Punctuation("\""), new Word("World"),
                new Punctuation("\""), new Punctuation("!"));

        assertEquals("Hello \"World\"!", classUnderTest.toString());
    }

    @Test
    public void testToStringQuotationMarksMultiple() {
        Sentence classUnderTest = new Sentence(new Word("Hello"), new Punctuation("\""), new Word("World"),
                new Punctuation("\""), new Punctuation("!"), new Word("Hello"), new Punctuation("\""),
                new Word("World"), new Punctuation("\""), new Punctuation("!"));

        assertEquals("Hello \"World\"! Hello \"World\"!", classUnderTest.toString());
    }

    @Test
    public void testToStringSentenceStartsWithQuotationMarks() {
        Sentence classUnderTest = new Sentence(new Punctuation("\""), new Word("Hello"), new Word("World"),
                new Punctuation("\""), new Punctuation("!"));

        assertEquals("\"Hello World\"!", classUnderTest.toString());
    }
}