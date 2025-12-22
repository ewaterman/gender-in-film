package com.ewaterman.genderinfilm.characters;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates the different types of questions that can be asked about a character.
 */
@Getter
@AllArgsConstructor
public enum CharacterQuestionType {

    ALIVE_AT_END(
            "Do they survive?",
            "Does the character survive through the whole movie?"),

    TRANS_ACTOR(
            "Are they played by a transgender actor?",
            "Is the character played by a transgender or gender non-conforming identifying actor?"),

    IN_MULTIPLE_SCENES(
            "Are they in multiple scenes?",
            "Does the character appear in multiple scenes?"),

    VILLAINOUS(
            "Are they a villain?",
            "Is the character a villain or antagonist to the protagonists? Are they a murderer, a criminal, or otherwise portrayed as morally wrong by the film?"),

    IS_NAMED(
            "Are they named in the movie?",
            "Is the character named in the movie? The credits don't count, it must be said in the movie."),

    IS_GENDER_JOKE(
            "Is their gender made fun of?",
            "Is the character's gender expression played off as a joke or otherwise not taken seriously by another character or the movie itself? Is there a shocking gender reveal designed to get a laugh out of the viewer at the character's expense?");

    // Think about how to refine this question before adding it.
    //IS_GENDER_A_TRICK(
    //        "Do they use their gender trick someone?",
    //        "Is the character's gender-nonconformity directly and intentionally hidden from another character with intent trick, coerce, or deceive them?");


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
