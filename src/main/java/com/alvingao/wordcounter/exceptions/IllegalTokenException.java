package com.alvingao.wordcounter.exceptions;

public class IllegalTokenException extends WordCounterException {

    /**
     * trying to get the class to play nicely with Serializable
     */
    private static final long serialVersionUID = 2L;

    public IllegalTokenException(String message) {
        super(message);
    }
}