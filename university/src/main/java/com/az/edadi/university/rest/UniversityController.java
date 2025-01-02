package com.az.edadi.university.rest;

import com.az.edadi.university.model.res.UniversityRes;
import com.az.edadi.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/university")
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping
    ResponseEntity<List<UniversityRes>> findAll() {
        return ResponseEntity.ok(universityService.getAllUniversities());
    }
}
