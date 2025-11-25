package com.example.mutant.repository;

import com.example.mutant.model.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {

    Optional<DnaRecord> findByDnaHash(String dnaHash);

    long countByMutant(boolean mutant);
}
