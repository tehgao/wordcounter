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
}