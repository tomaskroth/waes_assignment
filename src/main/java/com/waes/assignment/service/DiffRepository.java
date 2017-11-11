package com.waes.assignment.service;

import com.waes.assignment.domain.DiffEntity;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class represents the persistence contract that should be respected by implementations of this repository
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
@Repository
public interface DiffRepository {

    /**
     * This method retrieves an already save DiffEntity by its ID
     * @param id ID of the entity being fetched
     * @return DiffEntity requested or null if not found
     */
    DiffEntity getDiffEntity(final String id);

    /**
     * Save a DiffEntity to the provided repository
     * @param diffEntity the Entity to be saved
     * @return Boolean on whether the process was successful.
     */
    Boolean saveDiffEntity(final DiffEntity diffEntity);

}
