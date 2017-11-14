package com.waes.assignment.service.impl;

import com.waes.assignment.domain.DiffEntity;
import com.waes.assignment.service.DiffRepository;
import com.waes.assignment.service.dto.ResultDTO;
import com.waes.assignment.service.enumerator.ResultEnum;
import com.waes.assignment.service.exception.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.InvalidParameterException;

import static org.mockito.Mockito.when;

/**
 * <p>
 * This class tests the behaviours on the DiffServiceImpl.evaluateDifference method
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
public class DiffServiceServiceImpl_EvaluateDifferenceShould extends BaseDiffServiceTest {

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
        diffService.evaluateDifference(StringUtils.EMPTY);
    }

    @Test(expected = InvalidParameterException.class)
    public void failWithNullId() {
        diffService.evaluateDifference(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void failWithNonExistingDiffEntity() {
        when(diffRepository.getDiffEntity(ID)).thenReturn(null);
        diffService.evaluateDifference(ID);
    }

    @Test
    public void informIfValuesAreEqual() {
        DiffEntity diffEntity = new DiffEntity(ID);
        diffEntity.setLeft(VALUE);
        diffEntity.setRight(VALUE);

        when(diffRepository.getDiffEntity(ID)).thenReturn(diffEntity);
        ResultDTO resultDTO = diffService.evaluateDifference(ID);
        Assert.assertEquals(resultDTO.getResult(), ResultEnum.EQUAL.getStringValue());
    }

    @Test
    public void informIfValuesHaveDifferentLength_RightSmaller() {
        DiffEntity diffEntity = new DiffEntity(ID);
        diffEntity.setLeft(VALUE);
        diffEntity.setRight(SMALL_VALUE);

        when(diffRepository.getDiffEntity(ID)).thenReturn(diffEntity);
        ResultDTO resultDTO = diffService.evaluateDifference(ID);
        Assert.assertEquals(resultDTO.getResult(), ResultEnum.DIFFERENT_SIZE.getStringValue());
    }

    @Test
    public void informIfValuesHaveDifferentLength_LeftSmaller() {
        DiffEntity diffEntity = new DiffEntity(ID);
        diffEntity.setLeft(SMALL_VALUE);
        diffEntity.setRight(VALUE);

        when(diffRepository.getDiffEntity(ID)).thenReturn(diffEntity);
        ResultDTO resultDTO = diffService.evaluateDifference(ID);
        Assert.assertEquals(resultDTO.getResult(), ResultEnum.DIFFERENT_SIZE.getStringValue());
    }

    @Test
    public void informTheDifferenceIfValuesHaveSameSizeAndOneDifference() {
        DiffEntity diffEntity = new DiffEntity(ID);
        diffEntity.setLeft(DIFF_VALUE);
        diffEntity.setRight(VALUE);

        int expectedDifferences = 1;
        Integer expectedOffset = 11;
        Integer expectedLength = 1;


        when(diffRepository.getDiffEntity(ID)).thenReturn(diffEntity);

        ResultDTO resultDTO = diffService.evaluateDifference(ID);
        Assert.assertEquals("Result value incorrect", ResultEnum.DIFFERENT.getStringValue(), resultDTO.getResult());
        Assert.assertEquals("Number of differences found incorrect", expectedDifferences, resultDTO.getDifferences().size());
        Assert.assertEquals("Offset is not the expected", expectedOffset, resultDTO.getDifferences().get(0).getOffset());
        Assert.assertEquals("Length is not the expected", expectedLength, resultDTO.getDifferences().get(0).getLength());
    }

    @Test
    public void informTheDifferenceIfValuesHaveSameSizeAndMoreThanOneDifference() {
        DiffEntity diffEntity = new DiffEntity(ID);
        diffEntity.setLeft(TWO_DIFF_VALUE);
        diffEntity.setRight(VALUE);

        int expectedDifferences = 2;
        Integer expectedFirstOffset = 0;
        Integer expectedFirstLength = 4;

        Integer expectedSecondOffset = 11;
        Integer expectedSecondeLength = 1;


        when(diffRepository.getDiffEntity(ID)).thenReturn(diffEntity);

        ResultDTO resultDTO = diffService.evaluateDifference(ID);
        Assert.assertEquals("Result value incorrect", ResultEnum.DIFFERENT.getStringValue(), resultDTO.getResult());
        Assert.assertEquals("Number of differences found incorrect", expectedDifferences, resultDTO.getDifferences().size());
        Assert.assertEquals("Offset is not the expected", expectedFirstOffset, resultDTO.getDifferences().get(0).getOffset());
        Assert.assertEquals("Length is not the expected", expectedFirstLength, resultDTO.getDifferences().get(0).getLength());
        Assert.assertEquals("Offset is not the expected", expectedSecondOffset, resultDTO.getDifferences().get(1).getOffset());
        Assert.assertEquals("Length is not the expected", expectedSecondeLength, resultDTO.getDifferences().get(1).getLength());
    }


    @Test
    public void workWhenTheLastCharacterHasDifferences() {
        DiffEntity diffEntity = new DiffEntity(ID);
        diffEntity.setLeft("[Kevin Cox_]");
        diffEntity.setRight("[Kevin _Cox)");

        int expectedDifferences = 1;
        Integer expectedOffset = 7;
        Integer expectedLength = 5;


        when(diffRepository.getDiffEntity(ID)).thenReturn(diffEntity);

        ResultDTO resultDTO = diffService.evaluateDifference(ID);
        Assert.assertEquals("Result value incorrect", ResultEnum.DIFFERENT.getStringValue(), resultDTO.getResult());
        Assert.assertEquals("Number of differences found incorrect", expectedDifferences, resultDTO.getDifferences().size());
        Assert.assertEquals("Offset is not the expected", expectedOffset, resultDTO.getDifferences().get(0).getOffset());
        Assert.assertEquals("Length is not the expected", expectedLength, resultDTO.getDifferences().get(0).getLength());
    }

    /**
     * This test is just to show that I was not able to repoduce the reported bug
     */
    @Test
    public void bugReportedNotFound() {
        DiffEntity diffEntity = new DiffEntity(ID);
        diffEntity.setLeft("[Kevin Cox_]");
        diffEntity.setRight("[Kevin _Cox]");

        int expectedDifferences = 1;
        Integer expectedOffset = 7;
        Integer expectedLength = 4;


        when(diffRepository.getDiffEntity(ID)).thenReturn(diffEntity);

        ResultDTO resultDTO = diffService.evaluateDifference(ID);
        Assert.assertEquals("Result value incorrect", ResultEnum.DIFFERENT.getStringValue(), resultDTO.getResult());
        Assert.assertEquals("Number of differences found incorrect", expectedDifferences, resultDTO.getDifferences().size());
        Assert.assertEquals("Offset is not the expected", expectedOffset, resultDTO.getDifferences().get(0).getOffset());
        Assert.assertEquals("Length is not the expected", expectedLength, resultDTO.getDifferences().get(0).getLength());
    }


}