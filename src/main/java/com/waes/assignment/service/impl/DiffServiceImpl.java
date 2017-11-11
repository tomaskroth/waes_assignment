package com.waes.assignment.service.impl;

import com.waes.assignment.domain.DiffEntity;
import com.waes.assignment.service.DiffRepository;
import com.waes.assignment.service.DiffService;
import com.waes.assignment.service.dto.ResultDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

/**
 * <p>
 * DiffService implementation
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
@Service
public class DiffServiceImpl implements DiffService{

    private static final String PARAMETER_MANDATORY = "%s is mandatory and should not be empty";

    @Autowired
    private DiffRepository diffRepository;

    @Override
    public ResultDTO evaluateDifference(final String id) {
        return null;
    }

    @Override
    public Boolean saveRightValue(final String id, final String value) {
        if (StringUtils.isEmpty(id)) {
            throw new InvalidParameterException(String.format(PARAMETER_MANDATORY, "ID"));
        }

        if (StringUtils.isEmpty(value)) {
            throw new InvalidParameterException(String.format(PARAMETER_MANDATORY, "value"));
        }

        DiffEntity diffEntity = diffRepository.getDiffEntity(id);

        //In case the diff is not yet in the system, prepare to create
        if (diffEntity == null) {
            diffEntity = new DiffEntity(id);
        }

        diffEntity.setRight(value);
        return diffRepository.saveDiffEntity(diffEntity);
    }

    @Override
    public Boolean saveLeftValue(final String id, final String value) {
        return null;
    }
}
