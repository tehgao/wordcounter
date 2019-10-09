package com.alvingao.wordcounter.exceptions;

public class TokenizationException extends WordCounterException {

    /**
     * trying to get the class to play nicely with Serializable
     */
    private static final long serialVersionUID = 3L;

    public TokenizationException(String message) {
        super(message);
    }
}