package com.ewaterman.genderinfilm.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Boolean-like answers to yes or no questions where the answer might not be clear.
 */
@Getter
@AllArgsConstructor
public enum BooleanAnswer {

    YES("Yes"),
    NO("No"),
    UNCLEAR("Unclear");

    /**
     * The text to display the field as.
     */
    final String displayText;

    public String toString() {
        return displayText;
    }
}
