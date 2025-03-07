package com.az.edadi.university.controller;

import com.az.edadi.university.model.request.UniversityReq;
import com.az.edadi.university.model.response.UniversityRes;
import com.az.edadi.university.service.UniversityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/university")
public class UniversityController {

    private final UniversityService universityService;


    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public ResponseEntity<List<UniversityRes>> findAll() {
        return ResponseEntity.ok(universityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityRes> getUniversityById(@PathVariable UUID id){
        UniversityRes universityRes = universityService.getUniversityById(id);
        return new ResponseEntity<>(universityRes, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UniversityRes> createUniversity(@Valid @RequestBody UniversityReq universityReq){
        System.out.println(universityReq);
        UniversityRes universityRes = universityService.createUniversity(universityReq);
        return new ResponseEntity<>(universityRes,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityRes> updateUniversity(@Valid @PathVariable UUID id, @RequestBody UniversityReq universityReq){
        UniversityRes universityRes = universityService.update(id,universityReq);
        return new ResponseEntity<>(universityRes,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUniversity(@PathVariable UUID id){
        String deletedMsg = universityService.delete(id);
        return new ResponseEntity<>(deletedMsg,HttpStatus.OK);
    }

}
