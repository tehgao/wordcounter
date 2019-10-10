package com.alvingao.wordcounter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alvingao.wordcounter.analyzers.FrequencyAnalyzer;
import com.alvingao.wordcounter.analyzers.Tokenizer;
import com.alvingao.wordcounter.exceptions.TokenizationException;

import org.junit.Test;

public class FrequencyAnalyzerTest {
    public final String mobyDick = "Call me Ishmael. Some years ago, never mind how long precisely, having little or no money in my purse, and nothing particular to interest me on shore, I thought I would sail about a little and see the watery part of the world. It is a way I have of driving off the spleen and regulating the circulation. Whenever I find myself growing grim about the mouth; whenever it is a damp, drizzly November in my soul; whenever I find myself involuntarily pausing before coffin warehouses, and bringing up the rear of every funeral I meet; and especially whenever my hypos get such an upper hand of me, that it requires a strong moral principle to prevent me from deliberately stepping into the street, and methodically knocking people’s hats off, then, I account it high time to get to sea as soon as I can. This is my substitute for pistol and ball. With a philosophical flourish Cato throws himself upon his sword; I quietly take to the ship. There is nothing surprising in this. If they but knew it, almost all men in their degree, some time or other, cherish very nearly the same feelings towards the ocean with me.";

    @Test
    public void testCreateFrequencyTable() throws TokenizationException {
        List<Sentence> corpus = (new Tokenizer()).corpusToSentences("Hello World! Test. Hello.");
        FrequencyAnalyzer classUnderTest = new FrequencyAnalyzer(corpus);

        Map<String, Integer> actual = classUnderTest.getFrequencyTable();
        Map<String, Integer> expected = new HashMap<>();

        expected.put("hello", 2);
        expected.put("world", 1);
        expected.put("test", 1);

        assertEquals(expected, actual);
    }

    @Test
    public void testCreateFrequencyTableEmpty() throws TokenizationException {
        List<Sentence> corpus = (new Tokenizer()).corpusToSentences("");
        FrequencyAnalyzer classUnderTest = new FrequencyAnalyzer(corpus);

        Map<String, Integer> actual = classUnderTest.getFrequencyTable();
        Map<String, Integer> expected = new HashMap<>();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetCount() throws TokenizationException {
        List<Sentence> corpus = (new Tokenizer()).corpusToSentences(this.mobyDick);
        FrequencyAnalyzer classUnderTest = new FrequencyAnalyzer(corpus);

        int actual = classUnderTest.getWordCount();
        int expected = 202;

        assertEquals(expected, actual);
    }

    @Test
    public void testGetTopWords() throws TokenizationException {
        List<Sentence> corpus = (new Tokenizer()).corpusToSentences(this.mobyDick);
        FrequencyAnalyzer classUnderTest = new FrequencyAnalyzer(corpus);

        List<String> actual = classUnderTest.getTopWords(3);
        List<String> expected = new ArrayList<>(Arrays.asList("the", "i", "and"));

        assertEquals(expected, actual);
    }
}