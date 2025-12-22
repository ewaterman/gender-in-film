package com.ewaterman.genderinfilm.characters;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates the different types of characters that we capture in our data.
 */
@Getter
@AllArgsConstructor
public enum CharacterType {

    TRANS(
            "Definitively Transgender",
            "An explicitly transgender or gender non-conforming character. The character specifically identifies as such."),

    /**
     * A common occurrence of this is a trans actor playing a character who isn't specifically mentioned as trans.
     *
     * Unfortunately, deciding whether a character is trans can be both intrusive and extremely subjective, however
     * it feels like a necessity if we want to be able to represent the full spectrum of characters/data.
     */
    MAYBE_TRANS(
            "Possibly Transgender",
            "The character isn't explicitly identified as trans or gender non-conforming but has the possibility to be."),

    CIS_PERFORMING_TRANS(
            "Cisgender In Drag",
            "A cisgender character performing gender non-conforming actions (ex: drag).");

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
