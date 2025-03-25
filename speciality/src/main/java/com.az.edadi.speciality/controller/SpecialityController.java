package com.az.edadi.speciality.controller;

import com.az.edadi.speciality.model.SpecialityReq;
import com.az.edadi.speciality.model.SpecialityRes;
import com.az.edadi.speciality.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/speciality")
@RequiredArgsConstructor
public class SpecialityController {
    private final SpecialityService specialityService;

    @GetMapping()
    public ResponseEntity<List<SpecialityRes>> findAll(){
        return ResponseEntity.ok(specialityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityRes> findById(@PathVariable UUID id){
        return ResponseEntity.ok(specialityService.getSpecialityById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<SpecialityRes> create(@RequestBody SpecialityReq specialityReq){
        return ResponseEntity.ok(specialityService.create(specialityReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialityRes> update(@RequestBody SpecialityReq specialityReq,@PathVariable UUID id ){
        return ResponseEntity.ok(specialityService.update(id,specialityReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        return ResponseEntity.ok(specialityService.delete(id));
    }
    @GetMapping("/specialities")
    public ResponseEntity<List<SpecialityRes>> findByInstitutionId(@RequestParam (required = false) String institutionId){
        return ResponseEntity.ok(specialityService.findByInstitutionId(institutionId));
    }
}
