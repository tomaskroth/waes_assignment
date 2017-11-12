package com.waes.assignment.service.impl;

import com.waes.assignment.domain.DiffEntity;
import com.waes.assignment.service.DiffRepository;
import com.waes.assignment.service.DiffService;
import com.waes.assignment.service.dto.DiffDetailDTO;
import com.waes.assignment.service.dto.ResultDTO;
import com.waes.assignment.service.enumerator.ResultEnum;
import com.waes.assignment.service.exception.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * <p>
 * DiffService implementation
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
@Service
public class DiffServiceImpl implements DiffService {

    private static final String PARAMETER_MANDATORY = "%s is mandatory and should not be empty";

    @Autowired
    private DiffRepository diffRepository;

    @Override
    public ResultDTO evaluateDifference(final String id) {
        if (StringUtils.isEmpty(id)) {
            throw new InvalidParameterException(String.format(PARAMETER_MANDATORY, "ID"));
        }

        DiffEntity diffEntity = diffRepository.getDiffEntity(id);

        if (diffEntity == null) {
            throw new EntityNotFoundException("A DiffEntity with the provided ID does not exist");
        }

        ResultDTO resultDTO = processDifference(diffEntity);

        return resultDTO;
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

        diffEntity.setLeft(value);
        return diffRepository.saveDiffEntity(diffEntity);
    }

    /**
     * Process the difference from the right to left side of the entity
     * If equal return that it's equal
     * If the size is different, return that
     * If of same size returns the offset and length of each difference.
     *
     * @param diffEntity The entity being analyzed
     * @return ResultDTO with the result of the evalution
     */
    private ResultDTO processDifference(final DiffEntity diffEntity) {
        ResultDTO resultDTO;

        String leftValue = StringUtils.defaultString(diffEntity.getLeft());
        String rightValue = StringUtils.defaultString(diffEntity.getRight());

        if (StringUtils.equals(leftValue, rightValue)) {
            resultDTO = new ResultDTO(ResultEnum.EQUAL);
        } else if (leftValue.length() != rightValue.length()) {
            resultDTO = new ResultDTO(ResultEnum.DIFFERENT_SIZE);
        } else {
            ArrayList<DiffDetailDTO> differences = getDiffDetails(leftValue, rightValue);
            resultDTO = new ResultDTO(ResultEnum.DIFFERENT, differences);
        }
        return resultDTO;
    }

    /**
     * Process the differences inside two strings of equal size, returning the offset and length of each difference
     *
     * @param leftValue The first string
     * @param rightValue The second string
     * @return A list of DiffDetailDTO containing the offset and length of each difference
     */
    private ArrayList<DiffDetailDTO> getDiffDetails(final String leftValue, final String rightValue) {
        ArrayList<DiffDetailDTO> differences = new ArrayList<>();

        boolean capturingDiff = false;
        int diffLength = 0;
        int diffStartingOffset = 0;

        for (int offset = 0; offset < leftValue.length(); offset++) {
            if (leftValue.charAt(offset) != rightValue.charAt(offset)) {
                if (capturingDiff) {
                    diffLength++;
                } else {
                    capturingDiff = true;
                    diffStartingOffset = offset;
                    diffLength++;
                }
            } else {
                if (capturingDiff) {
                    capturingDiff = false;
                    differences.add(new DiffDetailDTO(diffStartingOffset, diffLength));
                    diffLength = 0;
                    diffStartingOffset = 0;
                }
            }
        }
        return differences;
    }
}
