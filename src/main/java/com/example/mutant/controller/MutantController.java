package com.example.mutant.controller;

import com.example.mutant.dto.DnaRequest;
import com.example.mutant.service.MutantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MutantController {

    private final MutantService mutantService;

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DnaRequest req) {
        String[] dna = req.getDna().toArray(new String[0]);
        boolean isMutant = mutantService.processDna(dna);

        if (isMutant) {
            return ResponseEntity
                    .ok("El ADN analizado corresponde a un MUTANTE.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("El ADN analizado corresponde a un HUMANO (no es mutante).");
        }
    }

}
