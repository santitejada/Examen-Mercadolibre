package com.example.mutant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Respuesta con estadísticas de ADN")
public class StatsResponse {

    private long count_mutant_dna;
    private long count_human_dna;
    private double ratio;
}
