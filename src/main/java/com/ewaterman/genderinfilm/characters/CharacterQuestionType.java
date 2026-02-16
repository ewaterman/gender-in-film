package com.ewaterman.genderinfilm.characters;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.ewaterman.genderinfilm.common.BooleanAnswer;

/**
 * Enumerates the different types of questions that can be asked about a character.
 */
@Getter
@AllArgsConstructor
public enum CharacterQuestionType {

    ALIVE_AT_END(
            "Do they survive?",
            "Does the character survive through the whole movie?",
            "The character survives",
            "The character does not survive",
            "It is ambiguous if the character survives"),

    TRANS_ACTOR(
            "Are they played by a transgender actor?",
            "Is the character played by an an actor that openly identifies as transgender?",
            "The actor is transgender",
            "The actor is not transgender",
            "It is not known if the actor is transgender"),

    IN_MULTIPLE_SCENES(
            "Are they in multiple scenes?",
            "Does the character appear in more than one scene of the movie?",
            "The character is in multiple scenes",
            "The character is in only one scene",
            "It is unclear how many scenes the character is in"),

    VILLAINOUS(
            "How is the character portrayed?",
            "Is the character a villain or antagonist to the protagonists? Are they a murderer, a criminal, or otherwise portrayed as morally wrong by the film?",
            "The character is not an antagonist",
            "The character is an antagonist",
            "It is ambiguous if the character is an antagonist"),

    IS_NAMED(
            "Is their name mentioned in the movie?",
            "Is the character's name directly stated in the movie itself? The credits don't count.",
            "The character is named in the movie",
            "The character is not named in the movie",
            "It is unclear if the character is named"),

    IS_GENDER_JOKE(
            "Is their gender taken seriously by the film?",
            "Is the character's gender expression played off as a joke or otherwise not taken seriously by the movie itself? Is there a shocking gender reveal designed to get a laugh out of the viewer at the character's expense?",
            "The character's gender is taken seriously",
            "The character's gender is not taken seriously",
            "It is ambiguous if the character's gender is taken seriously");

    // Think about how to refine this question before adding it. Could frame it around trans panic.
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
