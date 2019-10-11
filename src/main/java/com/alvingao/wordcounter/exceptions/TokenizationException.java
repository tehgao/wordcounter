package com.alvingao.wordcounter.exceptions;

/**
 * Signals that a general error has occured during tokenization.
 */
public class TokenizationException extends WordCounterException {

    /**
     * trying to get the class to play nicely with Serializable
     */
    private static final long serialVersionUID = 3L;

    /**
     * Constructs a {@code TokenizationException} with a given message.
     * 
     * @param message The message.
     */
    public TokenizationException(String message) {
        super(message);
    }
}