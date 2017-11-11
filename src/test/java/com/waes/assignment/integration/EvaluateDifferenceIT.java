package com.waes.assignment.integration;

import com.waes.assignment.infra.Application;
import com.waes.assignment.service.enumerator.ResultEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * <p>
 * This class aims to test the EvaluateDiff rest endpoint
 * </p>
 *
 * @author PID The Gauchos Team
 * @since 11/11/2017
 */
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EvaluateDifferenceIT {

    private static final String EVALUATE_URL = "/v1/diff/%s";
    private static final String SAMPLE_JSON      = "{\"key\":\"StringKey\",\"value\":\"StringValue\"}";
    private static final String SAMPLE_JSON_DIFF = "{\"key\":\"KeyString\",\"value\":\"ValueString\"}";
    private static final String SAMPLE_JSON_DIFF_SIZE = "{\"key\":\"StringKey\"}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnNotFoundWhenDiffDontExist() throws Exception {
        mockMvc.perform(get(String.format(EVALUATE_URL, 2)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnEqualWhenNoDifference() throws Exception {
        createRightValue(SAMPLE_JSON);
        createLeftValue(SAMPLE_JSON);

        mockMvc.perform(get(String.format(EVALUATE_URL, 1)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(ResultEnum.EQUAL.getStringValue())));
    }

    @Test
    public void shouldReturnDifferentSizeWhenSizesDontMatch() throws Exception {
        createRightValue(SAMPLE_JSON);
        createLeftValue(SAMPLE_JSON_DIFF_SIZE);

        mockMvc.perform(get(String.format(EVALUATE_URL, 1)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(ResultEnum.DIFFERENT_SIZE.getStringValue())));
    }

    @Test
    public void shouldReturnDifferentWhenThereAreDifferences() throws Exception {
        createRightValue(SAMPLE_JSON);
        createLeftValue(SAMPLE_JSON_DIFF);

        mockMvc.perform(get(String.format(EVALUATE_URL, 1)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(ResultEnum.DIFFERENT.getStringValue())))
                .andExpect(jsonPath("$.differences[0].offset", is(8)))
                .andExpect(jsonPath("$.differences[0].length", is(9)))
                .andExpect(jsonPath("$.differences[1].offset", is(28)))
                .andExpect(jsonPath("$.differences[1].length", is(11)));
    }

    private void createRightValue(final String body) throws Exception {
        mockMvc.perform(post(String.format(EVALUATE_URL, "1/right")).content(Base64.getEncoder().encode(body.getBytes()))).andExpect(status().isCreated());
    }

    private void createLeftValue(final String body) throws Exception {
        mockMvc.perform(post(String.format(EVALUATE_URL, "1/left")).content(Base64.getEncoder().encode(body.getBytes()))).andExpect(status().isCreated());
    }
}
