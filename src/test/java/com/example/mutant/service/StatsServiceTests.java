package com.example.mutant.service;

import com.example.mutant.dto.StatsResponse;
import com.example.mutant.repository.DnaRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatsServiceTests {

    @Mock
    private DnaRecordRepository repository;

    @InjectMocks
    private StatsService statsService;

    @Test
    void getStats_shouldCalculateRatioCorrectly() {
        // 4 mutantes, 2 humanos → ratio = 2.0
        when(repository.countByMutant(true)).thenReturn(4L);
        when(repository.countByMutant(false)).thenReturn(2L);

        StatsResponse stats = statsService.getStats();

        assertEquals(4L, stats.getCount_mutant_dna());
        assertEquals(2L, stats.getCount_human_dna());
        assertEquals(2.0, stats.getRatio());
    }

    @Test
    void getStats_whenNoHumans_shouldReturnZeroRatio() {
        // 3 mutantes, 0 humanos → ratio = 0.0 (evitamos división por cero)
        when(repository.countByMutant(true)).thenReturn(3L);
        when(repository.countByMutant(false)).thenReturn(0L);

        StatsResponse stats = statsService.getStats();

        assertEquals(3L, stats.getCount_mutant_dna());
        assertEquals(0L, stats.getCount_human_dna());
        assertEquals(0.0, stats.getRatio());
    }
}
