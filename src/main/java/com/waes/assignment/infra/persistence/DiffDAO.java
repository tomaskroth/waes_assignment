package com.waes.assignment.infra.persistence;

import com.waes.assignment.domain.DiffEntity;
import com.waes.assignment.service.DiffRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * DISCLAIMER: I chose to do an in-memory approach for simplicity, but this could also be an implementation of an H2 or
 * other more robust in-memory database. The point here is just to demonstrate where this class fits in the chosen architecture
 * <p>
 * <p>
 * This class implements the methods of DiffRepository for an in-memory persistence
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
@Repository
public class DiffDAO implements DiffRepository {

    //I was going to use only a List to demonstrate my skills with Lambda expressions, but in the end it'd be slower
    //due to not accessing directly the object you need, so I ended up using HashMap.
    private HashMap<String, DiffEntity> storage = new HashMap<>();

    @Override
    public DiffEntity getDiffEntity(final String id) {
        return storage.get(id);
    }

    @Override
    public Boolean saveDiffEntity(final DiffEntity diffEntity) {
        DiffEntity savedEntity = storage.put(diffEntity.getId(), diffEntity);

        return savedEntity != null;
    }
}
