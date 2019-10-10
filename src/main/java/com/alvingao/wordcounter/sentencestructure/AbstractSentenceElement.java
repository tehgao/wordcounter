package com.alvingao.wordcounter.sentencestructure;

import java.util.Objects;

public abstract class AbstractSentenceElement implements SentenceElement {
    private final String value;

    public AbstractSentenceElement(String value) {
        this.value = value;
    }

    public AbstractSentenceElement(AbstractSentenceElement element) {
        this.value = element.getValue();
    }

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