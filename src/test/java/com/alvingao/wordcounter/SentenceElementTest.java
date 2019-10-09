package com.alvingao.wordcounter;

import org.junit.Test;
import static org.junit.Assert.*;

import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;
import com.alvingao.wordcounter.sentencestructure.Word;

public class SentenceElementTest {
    @Test
    public void testReflexiveEquals() {
        SentenceElement elt1 = new Word("hello");

        assertTrue(elt1.equals(elt1));
    }

    @Test
    public void testEqualsSame() {
        SentenceElement elt1 = new Word("hello");
        SentenceElement elt2 = new Word("hello");

        assertTrue(elt1.equals(elt2));
    }

    @Test
    public void testNotEquals() {
        SentenceElement elt1 = new Word("hello");
        SentenceElement elt2 = new Word("hiya");

        assertFalse(elt1.equals(elt2));
    }

    @Test
    public void testNotEqualsDifferentClasses() {
        // this should never happen if we use our factory, but it's worth testing for
        // just in case.
        SentenceElement elt1 = new Word("hello");
        SentenceElement elt2 = new Punctuation("hello");

        assertFalse(elt1.equals(elt2));
    }
}