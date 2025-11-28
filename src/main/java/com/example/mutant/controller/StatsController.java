package com.example.mutant.controller;

import com.example.mutant.dto.StatsResponse;
import com.example.mutant.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Stats", description = "Estadísticas de ADN verificado")
@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @Operation(summary = "Obtiene estadísticas de ADN humano/mutante")
    @ApiResponse(responseCode = "200", description = "Estadísticas devueltas correctamente")
    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats() {
        return ResponseEntity.ok(statsService.getStats());
    }
}
