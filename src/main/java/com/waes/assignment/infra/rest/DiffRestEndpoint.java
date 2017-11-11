package com.waes.assignment.infra.rest;

import com.waes.assignment.service.DiffService;
import com.waes.assignment.service.dto.ResultDTO;
import com.waes.assignment.service.enumerator.ResultEnum;
import com.waes.assignment.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

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

    @Autowired
    DiffService diffService;

    /**
     * This method is reponsible for executing the difference between the right and left side of the ID provided
     *
     * @param id The identification of the diff being evaluated
     * @return The evalution result
     */
    @GetMapping(value = "")
    public ResponseEntity<ResultDTO> executeDiff(@PathVariable(value = "ID") final String id) {
        try {
            ResultDTO resultDTO = diffService.evaluateDifference(id);
            return new ResponseEntity<>(resultDTO, HttpStatus.OK);
        }
        catch (EntityNotFoundException entityNotFoundException) {
            return new ResponseEntity<>(new ResultDTO(ResultEnum.NOT_FOUND), HttpStatus.NOT_FOUND);
        }

    }

    /**
     * This method is responsible for saving the right value for the given Diff
     *
     * @param id    The ID of the Diff being altered
     * @param value The value of the right part of the diff
     * @return
     */
    @PostMapping(value = "/right")
    public ResponseEntity<Boolean> saveRightValue(@PathVariable(value = "ID") final String id, @RequestBody final byte[] value) {
        return new ResponseEntity<>(diffService.saveRightValue(id, decode(value)), HttpStatus.CREATED);
    }

    /**
     * This method is responsible for saving the left value for the given Diff
     *
     * @param id    The ID of the Diff being altered
     * @param value The value of the left part of the diff
     * @return
     */
    @PostMapping(value = "/left")
    public ResponseEntity<Boolean> saveLeftValue(@PathVariable(value = "ID") final String id, @RequestBody final byte[] value) {
        return new ResponseEntity<>(diffService.saveLeftValue(id, decode(value)), HttpStatus.CREATED);
    }

    private String decode(final byte[] encodedValue) {
        return new String(Base64.getDecoder().decode(encodedValue));
    }


}
