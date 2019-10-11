package com.alvingao.wordcounter.exceptions;

/**
 * Signals that an error has occured during execution of the WordCounter
 * application.
 */
public class WordCounterException extends Exception {

    /**
     * trying to get the class to play nicely with Serializable
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new instance of {@code WordCounterException} with the given
     * message.
     * 
     * @param message The message.
     */
    public WordCounterException(String message) {
        super(message);
    }
}