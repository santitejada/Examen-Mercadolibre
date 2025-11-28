package com.example.mutant.service;

import com.example.mutant.model.DnaRecord;
import com.example.mutant.repository.DnaRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MutantServiceTests {

    @Mock
    private MutantDetector detector;

    @Mock
    private DnaRecordRepository repository;

    @InjectMocks
    private MutantService service;

    @Test
    void whenDnaNotInDb_shouldCallDetectorAndSave() {
        String[] dna = {"AAAA", "CCCC", "GGGG", "TTTT"};

        when(repository.findByDnaHash(anyString())).thenReturn(Optional.empty());
        when(detector.isMutant(dna)).thenReturn(true);

        boolean result = service.processDna(dna);

        assertTrue(result);
        verify(detector).isMutant(dna);
        verify(repository).save(any(DnaRecord.class));
    }
}
