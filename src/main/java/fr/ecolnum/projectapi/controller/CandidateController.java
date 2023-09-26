package fr.ecolnum.projectapi.controller;

import fr.ecolnum.projectapi.exception.FileNotUpdatableException;
import fr.ecolnum.projectapi.exception.MultipartFileIsNotImageException;
import fr.ecolnum.projectapi.model.Candidate;
import fr.ecolnum.projectapi.service.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Class made to represent Controller on candidate operation
 */
@RestController
@RequestMapping("/api/admin/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    /**
     * function made to answer a post request on serveur
     *
     * @param candidate candidate that must be created on database
     * @return the candidate created
     * @author aflori
     */
    @PostMapping
    @Operation(
            summary = "Create a new candidate on database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "first and last name of a candidate",
                    required = true
            )
    )
    @ApiResponse(
            description = "Candidate created by server",
            responseCode = "201"
    )
    public ResponseEntity<Candidate> createCandidate(@RequestPart String firstName,
                                                     @RequestPart String lastName,
                                                     @RequestPart(name = "photo") MultipartFile photoCandidate) {
        Candidate candidate = new Candidate(firstName, lastName);

        try {

            Candidate createdCandidate = candidateService.createCandidate(candidate, photoCandidate);
            return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);

        } catch (MultipartFileIsNotImageException e) {

            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);

        } catch (FileNotUpdatableException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
