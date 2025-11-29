package com.example.mutant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Request con secuencia de ADN")
public class DnaRequest {

    @NotNull(message = "El campo dna no puede ser nulo")
    @NotEmpty(message = "El campo dna no puede estar vacío")
    @Size(min = 4, message = "El campo dna debe contener al menos 4 cadenas")
    @Schema(example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]")
    private List<
            @NotBlank(message = "Las cadenas de ADN no pueden estar vacías")
            @Pattern(regexp = "^[ATCG]+$", message = "Solo se permiten letras A,T,C,G")
                    String> dna;
}
