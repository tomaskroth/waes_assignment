package com.waes.assignment.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

/**
 * <p>
 * This class tests the DiffEntity object
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/11/2017
 */
public class DiffEntityTest {

    @Test
    public void equalsVerifier() {
        //Supressing the error for Non final fields because we are using non final fields in the equals
        EqualsVerifier.forClass(DiffEntity.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

}