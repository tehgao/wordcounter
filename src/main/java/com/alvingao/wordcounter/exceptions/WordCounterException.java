package com.alvingao.wordcounter.exceptions;

public class WordCounterException extends Exception {

    /**
     * trying to get the class to play nicely with Serializable
     */
    private static final long serialVersionUID = 1L;
    
    public WordCounterException(String message) {
        super(message);
    }
}