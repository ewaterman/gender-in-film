package com.ewaterman.genderinfilm.movies;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates the different types of questions that can be asked about a movie.
 */
@Getter
@AllArgsConstructor
public enum MovieQuestionType {

    TRANS_WRITER(
            "Has a transgender writer",
            "Is one or more of the writers of the movie transgender or gender non-conforming?");

    /**
     * The text to display the field as.
     */
    final String displayText;

    /**
     * An expanded explanation of what the question means, to be used in a hover-over tooltip.
     */
    final String extendedDisplayText;

    public String toString() {
        return displayText;
    }
}
