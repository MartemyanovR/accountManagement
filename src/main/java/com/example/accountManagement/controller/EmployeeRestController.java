package com.example.accountManagement.controller;

import com.example.accountManagement.model.dto.InputDataDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeRestController {

    private InputDataDto inputDataDto;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputDataDto> getEmployee() {
        if(inputDataDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(inputDataDto, HttpStatus.OK);
    }

    @PostMapping
    public InputDataDto createOrUpdateEmployee(@RequestBody InputDataDto inputDataDto ) {
        this.inputDataDto = inputDataDto;
        return this.inputDataDto;
    }

}
