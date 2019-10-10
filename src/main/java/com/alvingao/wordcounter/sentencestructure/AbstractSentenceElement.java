package com.alvingao.wordcounter.sentencestructure;

import java.util.Objects;

public abstract class AbstractSentenceElement implements SentenceElement {
    protected String value;

    public AbstractSentenceElement(String value) {
        this.value = value;
    }

    public AbstractSentenceElement(AbstractSentenceElement element) {
        this.value = element.getValue();
    }

    public String getValue() {
        return this.value;
    }

    // final -- all child classes should be immutable
    private final void setValue(String value) {
        this.value = value;
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