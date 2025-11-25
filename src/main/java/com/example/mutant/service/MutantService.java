package com.example.mutant.service;

import com.example.mutant.model.DnaRecord;
import com.example.mutant.repository.DnaRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MutantService {

    private final MutantDetector detector;
    private final DnaRecordRepository repository;

    public boolean processDna(String[] dna) {
        String hash = buildHash(dna);

        // si ya existe, reutilizamos resultado
        return repository.findByDnaHash(hash)
                .map(DnaRecord::isMutant)
                .orElseGet(() -> {
                    boolean isMutant = detector.isMutant(dna);

                    DnaRecord record = new DnaRecord();
                    record.setDnaHash(hash);
                    record.setMutant(isMutant);
                    repository.save(record);

                    return isMutant;
                });
    }

    private String buildHash(String[] dna) {
        // simple hash legible
        return Arrays.toString(dna);
    }
}
