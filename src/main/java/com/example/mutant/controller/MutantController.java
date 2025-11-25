package com.example.mutant.controller;

import com.example.mutant.dto.DnaRequest;
import com.example.mutant.service.MutantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MutantController {

    private final MutantService mutantService;

    @PostMapping("/mutant")
    public ResponseEntity<Void> isMutant(@RequestBody DnaRequest request) {
        String[] dnaArray = request.getDna().toArray(new String[0]);

        boolean isMutant = mutantService.processDna(dnaArray);

        return isMutant
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(403).build();
    }
}
