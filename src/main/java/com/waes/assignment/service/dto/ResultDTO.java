package com.waes.assignment.service.dto;

import com.waes.assignment.service.enumerator.ResultEnum;

import java.util.List;

/**
 * <p>
 * This class holds the values for the results of a diff evaluation
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/10/2017
 */
public class ResultDTO {

    private String result;
    private List<DiffDetailDTO> differences;

    /**
     * Constructor for the ResultDTO
     * @param result And instance of ResultEnum
     */
    public ResultDTO(final ResultEnum result) {
        this.result = result.getStringValue();
        this.differences = null;
    }

    /**
     * Constructor for the ResultDTO
     * @param result An instance of ResultEnum
     * @param differences The list of differences found for this evaluation
     */
    public ResultDTO(final ResultEnum result, final List<DiffDetailDTO> differences) {
        this.result = result.getStringValue();
        this.differences = differences;
    }

    public String getResult() {
        return result;
    }

    public List<DiffDetailDTO> getDifferences() {
        return differences;
    }
}
