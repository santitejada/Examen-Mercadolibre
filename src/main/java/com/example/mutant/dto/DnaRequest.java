package com.example.mutant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.util.List;

@Data
@Schema(description = "Request con secuencia de ADN")
public class DnaRequest {

    @NotNull
    @NotEmpty
    @Size(min = 4, message = "Debe contener al menos 4 filas de ADN")
    @Schema(example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]")
    private List<
            @NotBlank
            @Pattern(regexp = "^[ATCG]+$", message = "Solo se permiten letras A,T,C,G")
                    String
            > dna;
}
