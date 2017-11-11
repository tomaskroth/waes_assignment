package com.waes.assignment.infra.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * This class exposes the REST endpoints used to access the Diff endpoint in this application
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/10/2017
 */
@RestController()
@RequestMapping("/v1/diff/{ID}")
public class DiffRestEndpoint {

    /**
     * This method is reponsible for executing the difference between the right and left side of the ID provided
     *
     * @param id The identification of the diff being evaluated
     * @return The evalution result
     */
    @GetMapping(value = "")
    public String executeDiff(@PathVariable(value = "ID") final String id) {
        return id;
    }


}
