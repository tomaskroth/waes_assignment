package com.waes.assignment.service.enumerator;

/**
 * <p>
 * This enumerator holds the possible outcomes of a Diff evaluation
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
public enum ResultEnum {

    EQUAL("Values are equal"),
    DIFFERENT_SIZE("Values are of different size"),
    DIFFERENT("Values have differences"),
    NOT_FOUND("Entity not found to evaluate")
    ;

    private final String stringValue;

    ResultEnum(final String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }
}
