package com.waes.assignment.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>
 * This class holds the Diff object with its properties and behaviours
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
public class DiffEntity {

    private final String id;
    private String left;
    private String right;

    /**
     * Constructor of the DiffEntity class
     * @param id The ID of the DiffEntity
     */
    public DiffEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(final String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(final String right) {
        this.right = right;
    }

    @Override
    public boolean equals(final Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
