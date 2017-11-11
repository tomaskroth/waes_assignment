package com.waes.assignment.service;

import com.waes.assignment.service.dto.ResultDTO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * This interface exposes the DiffService functionalities
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/10/2017
 */
@Service
public interface DiffService {

    /**
     * This method evaluates the difference between the left and right sides of the provided ID
     *
     * @param id ID of the diff being evaluated
     * @return ResultDTO object containing the final evaluation
     */
    ResultDTO evaluateDifference(final String id);

    /**
     * This method saves the right value for the given ID
     *
     * @param id ID of the diff being saved
     * @param value Value of the right part of the given ID
     * @return Boolean on the status of the process
     */
    Boolean saveRightValue(final String id, final String value);

    /**
     * This method saves the left value for the given ID
     *
     * @param id ID of the diff being saved
     * @param value Value of the left part of the given ID
     * @return Boolean on the status of the process
     */
    Boolean saveLeftValue(final String id, final String value);

}
