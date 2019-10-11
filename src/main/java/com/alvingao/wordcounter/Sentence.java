package com.alvingao.wordcounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.alvingao.wordcounter.sentencestructure.Punctuation;
import com.alvingao.wordcounter.sentencestructure.SentenceElement;
import com.alvingao.wordcounter.sentencestructure.Word;

/**
 * Represents a sentence, usually a sequence of {@code Word}s terminated by a
 * terminating {@code Punctuation} mark.
 */
public class Sentence implements Iterable<SentenceElement> {
    private List<SentenceElement> sentenceElements;

    /**
     * Constructs an empty sentence. Not sure why anyone would want to do this since
     * {@code Sentence} is immutable, but it's here.
     */
    public Sentence() {
        this.sentenceElements = new ArrayList<>();
    }

    /**
     * Constructs a {@code Sentence} as a copy of the param.
     * 
     * @param sentence The sentence to copy.
     */
    public Sentence(Sentence sentence) {
        this.sentenceElements = new ArrayList<>(sentence.sentenceElements);
    }

    /**
     * Constructs a {@code Sentence} with {@code sentenceElements} as its
     * representation.
     * 
     * @param sentenceElements The {@code List<SentenceElement>} representation of
     *                         {@code this}.
     */
    public Sentence(List<SentenceElement> sentenceElements) {
        this.sentenceElements = new ArrayList<>(sentenceElements);
    }

    /**
     * Constructs a {@code Sentence} with {@code elements} as its representation.
     * 
     * @param sentenceElements The representation of {@code this}.
     */
    public Sentence(SentenceElement... elements) {
        this.sentenceElements = Arrays.asList(elements);
    }

    /**
     * Returns the {@code SentenceElement} at {@code index}.
     * 
     * @param index The index of the {@code SentenceElement} to return.
     * @return The {@code SentenceElement} at {@code index}.
     */
    public SentenceElement getElement(int index) {
        return this.sentenceElements.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // flag for tracking quotation marks
        boolean quotation = false;

        // iterate through the sentence elements pairwise
        sb.append(this.getElement(0));
        for (int index = 0; index < this.sentenceElements.size() - 1; index++) {
            SentenceElement first = this.getElement(index);
            SentenceElement second = this.getElement(index + 1);

            // if both elements are Words, then we need a space between them.
            if (first instanceof Word && second instanceof Word) {
                sb.append(" ");
                sb.append(second.getValue());
            } else {
                // we are assuming that non-word elements are all punctuation elements

                // if one of them is a quotation mark, then things get tricky.
                if (second.equals(new Punctuation("\""))) {
                    if (!quotation) {
                        // case: second is an opening quotation mark
                        sb.append(" ");
                        sb.append(second.getValue());
                    } else {
                        // case: second is a closing quotation mark
                        sb.append(second.getValue());
                    }

                    quotation = !quotation;
                } else {
                    // if the second element is not punctuation or following an open quotation mark,
                    // we need a space.
                    if (!(second instanceof Punctuation) && !(first.equals(new Punctuation("\"")) && quotation)) {
                        sb.append(" ");
                        sb.append(second.getValue());
                    } else {
                        // Otherwise, there's no space.
                        sb.append(second.getValue());
                    }
                }
            }
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sentenceElements);
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

        Sentence other = (Sentence) obj;
        return Objects.equals(this.sentenceElements, other.sentenceElements);
    }

    @Override
    public Iterator<SentenceElement> iterator() {
        return this.sentenceElements.iterator();
    }
}