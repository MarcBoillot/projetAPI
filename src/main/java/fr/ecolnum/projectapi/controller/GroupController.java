package fr.ecolnum.projectapi.controller;

import fr.ecolnum.projectapi.DTO.CriteriaDto;
import fr.ecolnum.projectapi.DTO.GroupDto;
import fr.ecolnum.projectapi.exception.IdNotFoundException;
import fr.ecolnum.projectapi.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static fr.ecolnum.projectapi.util.GenericUtility.convertStringToJsonData;

@RestController
@RequestMapping("/api/admin/group")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Operation(summary = "Create a group", description = "Add a new Group object to the database.")
    @PostMapping
    @ApiResponse(
            description = "Return the created group and the created HTTP response",
            responseCode = "201"
    )
    public ResponseEntity<?> createGroup(@RequestBody GroupDto group) {
        GroupDto createdGroup = groupService.createGroup(group);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }
    @Operation(summary = "Return all criterias", description = "Return the list of all the criterias from the database.")
    @GetMapping
    @ApiResponse(
            description = "Return criterias list and the OK HTTP response",
            responseCode = "200"
    )
    public ResponseEntity<?> getAllGroup() {
        Iterable<GroupDto> groupList = groupService.getAllGroup();
        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

}
