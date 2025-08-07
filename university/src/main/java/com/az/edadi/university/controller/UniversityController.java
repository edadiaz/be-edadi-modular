package com.az.edadi.university.controller;

 import com.az.edadi.model.request.UniversityRequestModel;
 import com.az.edadi.model.response.institution.InstitutionPageResponse;
import com.az.edadi.university.service.UniversityService;
import jakarta.validation.Valid;
 import jdk.jfr.Timespan;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/university")
public class UniversityController {

    private final UniversityService universityService;


    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }
@Timespan
    @GetMapping
    public ResponseEntity<List<InstitutionPageResponse>> findAll() {
        return ResponseEntity.ok(universityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionPageResponse> getUniversityById(@PathVariable String id){
        InstitutionPageResponse universityRes = universityService.getUniversityById(id);
        return new ResponseEntity<>(universityRes, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<InstitutionPageResponse> createUniversity(@Valid @RequestBody UniversityRequestModel universityReq){
        System.out.println(universityReq);
        InstitutionPageResponse universityRes = universityService.createUniversity(universityReq);
        return new ResponseEntity<>(universityRes,HttpStatus.CREATED);
    }

    @PostMapping("/createAll")
    public ResponseEntity<List<InstitutionPageResponse>> createAllUniversity(@RequestBody List<UniversityRequestModel> universityReqs){
        InstitutionPageResponse universityRes= new InstitutionPageResponse();
        List<InstitutionPageResponse> universityRes1 = new ArrayList<>();
        for (int i = 0; i < universityReqs.size(); i++) {
             universityRes = universityService.createUniversity(universityReqs.get(i));
             universityRes1.add(universityRes);
        }
        return new ResponseEntity<>(universityRes1,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionPageResponse> updateUniversity(@Valid @PathVariable String id, @RequestBody UniversityRequestModel universityReq){
        InstitutionPageResponse universityRes = universityService.update(id,universityReq);
        return new ResponseEntity<>(universityRes,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUniversity(@PathVariable String id){
        String deletedMsg = universityService.delete(id);
        return new ResponseEntity<>(deletedMsg,HttpStatus.OK);
    }

}
