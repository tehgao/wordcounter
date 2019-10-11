package com.alvingao.wordcounter.analyzers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alvingao.wordcounter.Sentence;
import com.alvingao.wordcounter.exceptions.TokenizationException;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;
import com.alvingao.wordcounter.sentencestructure.Word;

/**
 * Class for performing frequency analysis on text samples.
 */
public class FrequencyAnalyzer {
    private List<Sentence> corpus;

    /**
     * Creates an instance of {@code FrequencyAnalyzer} backed by {@code corpus}.
     * 
     * @param corpus A {@code List<Sentence>} representing the text.
     */
    public FrequencyAnalyzer(List<Sentence> corpus) {
        this.setCorpus(corpus);
    }

    /**
     * Tokenizes the string and creates an instance of {@code FrequencyAnalyzer} backed by
     * {@code corpus}.
     * 
     * @param corpus The text as a string.
     */
    public FrequencyAnalyzer(String corpus) throws TokenizationException {
        Tokenizer t = new Tokenizer();

        this.setCorpus(t.corpusToSentences(corpus));
    }

    /**
     * Returns a {@code Map<String, Integer>} mapping unique words in
     * {@code this.corpus} to the number of times that word appears.
     * 
     * @return a {@code Map<String, Integer>} frequency table.
     */
    public Map<String, Integer> getFrequencyTable() {
        Map<String, Integer> freq = new HashMap<>();

        for (Sentence sentence : this.corpus) {
            for (SentenceElement elt : sentence) {
                if (elt instanceof Word) {
                    String normalized = elt.toString().toLowerCase();

                    if (freq.containsKey(normalized)) {
                        freq.put(normalized, freq.get(normalized) + 1);
                    } else {
                        freq.put(normalized, 1);
                    }
                }
            }
        }

        return freq;
    }

    /**
     * Gets the number of {@code Word}s in {@code this.corpus}.
     * 
     * @return the number of {@code Word}s in {@code this.corpus}.
     */
    public int getWordCount() {
        int count = 0;
        for (Map.Entry<String, Integer> entry : this.getFrequencyTable().entrySet()) {
            count += entry.getValue();
        }
        return count;
    }

    /**
     * Returns the most common {@code number} words in the corpus, sorted descending
     * by frequency.
     * 
     * @param number The number of top words to return.
     * @return The {@code number} most common words sorted descending by frequency.
     */
    public List<String> getTopWords(int number) {
        List<Map.Entry<String, Integer>> freqs = new ArrayList<>(this.getFrequencyTable().entrySet());

        Collections.sort(freqs, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });

        List<String> output = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : freqs.subList(0, number)) {
            output.add(entry.getKey());
        }

        return output;
    }

    /**
     * Returns a {@code List<Sentence>} containing all of the sentences that contain
     * {@code param}, case insensitive.
     * 
     * @param word The string representation of the word to search for.
     * @return {@code List<Sentence>} containing all of the sentences that contain
     *         {@code param}, case insensitive.
     */
    public List<Sentence> getSentencesWithWord(String word) {
        List<Sentence> output = new ArrayList<>();
        for (Sentence s : this.corpus) {
            for (SentenceElement elt : s) {
                if (elt instanceof Word) {
                    if (elt.getValue().equalsIgnoreCase(word)) {
                        output.add(s);
                        break;
                    }
                }
            }
        }
        return output;
    }

    /**
     * Returns a copy of {@code this.corpus}.
     * 
     * @return a copy of {@code this.corpus}.
     */
    public List<Sentence> getCorpus() {
        return new ArrayList<Sentence>(this.corpus);
    }

    /**
     * Sets {@code this.corpus} as a copy of the param.
     * 
     * @param corpus The new corpus.
     */
    public void setCorpus(List<Sentence> corpus) {
        this.corpus = new ArrayList<>(corpus);
    }
}