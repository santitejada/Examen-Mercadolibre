package com.example.mutant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MutantResponse {
    private boolean isMutant;
    private String message;
}
