package com.example.mutant.controller;

import com.example.mutant.dto.DnaRequest;
import com.example.mutant.dto.MutantResponse;
import com.example.mutant.service.MutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mutants", description = "Detección de mutantes")
@RestController
@RequiredArgsConstructor
public class MutantController {

    private final MutantService mutantService;

    @Operation(
            summary = "Detecta si un ADN pertenece a un mutante",
            description = "Devuelve 200 si es mutante, 403 si no lo es"
    )
    @ApiResponse(responseCode = "200", description = "Mutante detectado")
    @ApiResponse(responseCode = "403", description = "No es mutante")
    @ApiResponse(
            responseCode = "400",
            description = "Entrada inválida",
            content = @Content(schema = @Schema(implementation = String.class))
    )
    @PostMapping("/mutant")
    public ResponseEntity<MutantResponse> isMutant(@Valid @RequestBody DnaRequest request) {
        String[] dnaArray = request.getDna().toArray(new String[0]);
        boolean isMutant = mutantService.processDna(dnaArray);

        MutantResponse response = new MutantResponse(
                isMutant,
                isMutant ? "Es un mutante" : "No es un mutante"
        );

        return isMutant
                ? ResponseEntity.ok(response)                    // 200 + JSON
                : ResponseEntity.status(403).body(response);     // 403 + JSON
    }
}



