package com.ewaterman.genderinfilm.movies;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.ewaterman.genderinfilm.common.BooleanAnswer;

/**
 * Enumerates the different types of questions that can be asked about a movie.
 */
@Getter
@AllArgsConstructor
public enum MovieQuestionType {

    TRANS_WRITER(
            "Does the movie have a transgender writer?",
            "Does one or more of the writers of the movie openly identify as transgender?",
            "The movie has a transgender writer",
            "The movie does not have a transgender writer",
            "It is not known if the movie has a transgender writer");

    /**
     * The text to display the field as.
     */
    final String displayText;

    /**
     * An expanded explanation of what the question means, to be used in a hover-over tooltip.
     */
    final String extendedDisplayText;

    /**
     * The text to display when the question has the answer YES.
     */
    final String yesAnswerText;

    /**
     * The text to display when the question has the answer NO.
     */
    final String noAnswerText;

    /**
     * The text to display when the question has the answer UNCLEAR.
     */
    final String unclearAnswerText;

    public String getTextForAnswer(BooleanAnswer answer) {
        return switch (answer) {
            case YES -> yesAnswerText;
            case NO -> noAnswerText;
            case UNCLEAR -> unclearAnswerText;
        };
    }

    public String toString() {
        return displayText;
    }
}
