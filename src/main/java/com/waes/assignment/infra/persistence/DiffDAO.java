package com.waes.assignment.infra.persistence;

import com.waes.assignment.domain.DiffEntity;
import com.waes.assignment.service.DiffRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class implements the methods of DiffRepository for an in-memory persistence
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
@Repository
public class DiffDAO implements DiffRepository {
    @Override
    public DiffEntity getDiffEntity(final String id) {
        return null;
    }

    @Override
    public Boolean saveDiffEntity(final DiffEntity diffEntity) {
        return null;
    }
}
