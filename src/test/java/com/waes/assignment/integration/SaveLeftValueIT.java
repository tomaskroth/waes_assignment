package com.waes.assignment.integration;

import com.waes.assignment.infra.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * <p>
 * This class aims to test the SaveLeftValue API call
 * </p>
 *
 * @author PID The Gauchos Team
 * @since 11/11/2017
 */
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SaveLeftValueIT {

    private static final String SAVE_URL = "/v1/diff/%s/left";
    private static final String SAMPLE_JSON = "{\"key\":\"StringKey\",\"value\":\"StringValue\"}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnCreatedWithProperValues() throws Exception {
        mockMvc.perform(post(String.format(SAVE_URL, 1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(Base64.getEncoder().encode(SAMPLE_JSON.getBytes()))
        )
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}
