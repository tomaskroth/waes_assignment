package com.waes.assignment.service.dto;

/**
 * <p>
 * This class holds a single difference, representing the offset and length of the difference
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/10/2017
 */
public class DiffDetailDTO {

    private Integer offset;
    private Integer length;

    /**
     * Constructor for the DiffDetailDTO
     * @param offset The offset of the start of the change
     * @param length The length of the difference, starting on the offset
     */
    public DiffDetailDTO(final Integer offset, final Integer length) {
        this.offset = offset;
        this.length = length;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLength() {
        return length;
    }
}
