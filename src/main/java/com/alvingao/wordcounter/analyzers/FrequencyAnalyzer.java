package com.alvingao.wordcounter.analyzers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alvingao.wordcounter.Sentence;
import com.alvingao.wordcounter.sentencestructure.Word;

public class FrequencyAnalyzer {
    List<Sentence> corpus;

    public FrequencyAnalyzer(List<Sentence> corpus) {

    }

    public FrequencyAnalyzer(String corpus) {
        
    }

    public Map<String, Integer> getTopWords(int count) {
        Map<String, Integer> freq =  new TreeMap<>();



        return freq;
    }

    public int getWordCount() {
        return 0;
    }

    public List<Sentence> getSentencesWithWord(Word word) {
        return corpus;
    }

    public List<Sentence> getCorpus() {
        return new ArrayList<Sentence>(this.corpus);
    }
}