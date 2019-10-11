package com.alvingao.wordcounter.sentencestructure;

import java.util.Objects;

/**
 * Abstract class implementing {@code SentenceElement} providing functionality
 * for {@code SentenceElement}s.
 */
public abstract class AbstractSentenceElement implements SentenceElement {
    private final String value;

    /**
     * Constructs an instance of {@code AbstractSentenceElement} with {@code value}
     * as its string representation.
     * 
     * @param value The string representation.
     */
    public AbstractSentenceElement(String value) {
        this.value = value;
    }

    /**
     * Constructs an instance of {@code AbstractSentenceElement} as a copy of
     * {@code element}.
     * 
     * @param element The {@code SentenceElement} to copy.
     */
    public AbstractSentenceElement(SentenceElement element) {
        this.value = element.getValue();
    }

    /**
     * Returns {@code this.value}.
     * 
     * @return {@code this.value}.
     */
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        AbstractSentenceElement other = (AbstractSentenceElement) obj;
        return Objects.equals(this.value, other.value);
    }
}