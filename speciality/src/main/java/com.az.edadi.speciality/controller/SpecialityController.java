package com.az.edadi.speciality.controller;

import com.az.edadi.model.request.speciality.SpecialityRequest;
import com.az.edadi.model.response.speciality.SpecialityPageResponse;
import com.az.edadi.speciality.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/speciality")
@RequiredArgsConstructor
public class SpecialityController {
    private final SpecialityService specialityService;

    @GetMapping()
    public ResponseEntity<List<SpecialityPageResponse>> findAll(){
        return ResponseEntity.ok(specialityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityPageResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(specialityService.getSpecialityById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<SpecialityPageResponse> create(@RequestBody SpecialityRequest specialityReq){
        return ResponseEntity.ok(specialityService.create(specialityReq));
    }

    @PostMapping("/createAll")
    public ResponseEntity<List<SpecialityPageResponse>> create(@RequestBody List<SpecialityRequest> specialityReq){
        List<SpecialityPageResponse> specialityRes = new ArrayList<>();
        for (int i = 0; i < specialityReq.size(); i++) {
            specialityRes.add( specialityService.create(specialityReq.get(i)));
        }
        return ResponseEntity.ok(specialityRes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialityPageResponse> update(@RequestBody SpecialityRequest specialityReq, @PathVariable UUID id ){
        return ResponseEntity.ok(specialityService.update(id,specialityReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        return ResponseEntity.ok(specialityService.delete(id));
    }
    @GetMapping("/specialities")
    public ResponseEntity<List<SpecialityPageResponse>> findByInstitutionId(@RequestParam (required = false) String institutionId){
        return ResponseEntity.ok(specialityService.findByInstitutionId(institutionId));
    }
}
