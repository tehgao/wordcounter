package com.alvingao.wordcounter.exceptions;

/**
 * Signals that a tokenizer has encountered an illegal token.
 */
public class IllegalTokenException extends WordCounterException {

    /**
     * trying to get the class to play nicely with Serializable
     */
    private static final long serialVersionUID = 2L;

    /**
     * Constructs a {@code IllegalTokenException} with a specified error message.
     * 
     * @param message The message.
     */
    public IllegalTokenException(String message) {
        super(message);
    }
}