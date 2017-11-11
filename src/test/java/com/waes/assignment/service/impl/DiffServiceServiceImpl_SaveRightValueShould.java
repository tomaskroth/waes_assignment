package com.waes.assignment.service.impl;

import com.waes.assignment.domain.DiffEntity;
import com.waes.assignment.service.DiffRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.InvalidParameterException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * <p>
 * This class tests the behaviours on the DiffServiceImpl.saveRightValue method
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
public class DiffServiceServiceImpl_SaveRightValueShould extends BaseDiffServiceTest {

    @Mock
    private DiffRepository diffRepository;

    @InjectMocks
    private DiffServiceImpl diffService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidParameterException.class)
    public void failWithEmptyId() {
        diffService.saveRightValue(StringUtils.EMPTY, VALUE);
    }

    @Test(expected = InvalidParameterException.class)
    public void failWithNullId() {
        diffService.saveRightValue(null, VALUE);
    }

    @Test(expected = InvalidParameterException.class)
    public void failWithEmptyValue() {
        diffService.saveRightValue(ID, StringUtils.EMPTY);
    }

    @Test(expected = InvalidParameterException.class)
    public void failWithNullValue() {
        diffService.saveRightValue(ID, null);
    }

    @Test
    public void updateOldValueWhenDiffAlreadyExists() {
        DiffEntity oldDiffEntity = new DiffEntity(ID);
        oldDiffEntity.setRight(VALUE);
        oldDiffEntity.setLeft(VALUE);

        DiffEntity newDiffEntity = new DiffEntity(ID);
        newDiffEntity.setRight(TWO_DIFF_VALUE);
        newDiffEntity.setLeft(VALUE);

        when(diffRepository.getDiffEntity(ID)).thenReturn(oldDiffEntity);
        diffService.saveRightValue(ID, TWO_DIFF_VALUE);
        verify(diffRepository).saveDiffEntity(newDiffEntity);
    }

    @Test
    public void saveIfNewDiff() {
        DiffEntity newDiffEntity = new DiffEntity(ID);
        newDiffEntity.setRight(TWO_DIFF_VALUE);

        when(diffRepository.getDiffEntity(ID)).thenReturn(null);
        diffService.saveRightValue(ID, TWO_DIFF_VALUE);
        verify(diffRepository).saveDiffEntity(newDiffEntity);
    }

}