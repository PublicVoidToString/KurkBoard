package com.kurkboard.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class AttemptEntityTest {

    @Test
    void shouldCreateAttempt() {
        CompetitorEntity competitor = new CompetitorEntity(
                "Jan",
                "Kowalski",
                null
        );

        TournamentEntity tournament = new TournamentEntity(
                "Test Tournament",
                LocalDate.of(2026, 9, 21),
                null
        );

        CategoryTypeEntity categoryType = new CategoryTypeEntity(
                "SINGLE",
                "Single score category",
                3
        );

        CategoryEntity category = new CategoryEntity(
                "Test Category",
                new BigDecimal("100.00"),
                new BigDecimal("50.00"),
                5,
                tournament,
                categoryType,
                null
        );

        AttemptEntity attempt = new AttemptEntity(
                competitor,
                category
        );

        assertThat(attempt.getCompetitor())
                .as("Expected: %s, Result: %s", competitor, attempt.getCompetitor())
                .isEqualTo(competitor);

        assertThat(attempt.getCategory())
                .as("Expected: %s, Result: %s", category, attempt.getCategory())
                .isEqualTo(category);
    }

    @Test
    void shouldSetAndGetCompetitor() {
        AttemptEntity attempt = new AttemptEntity();

        CompetitorEntity competitor = new CompetitorEntity(
                "Jan",
                "Kowalski",
                null
        );

        attempt.setCompetitor(competitor);

        assertThat(attempt.getCompetitor())
                .as("Expected: %s, Result: %s", competitor, attempt.getCompetitor())
                .isEqualTo(competitor);
    }

    @Test
    void shouldSetAndGetCategory() {
        AttemptEntity attempt = new AttemptEntity();

        CategoryEntity category = new CategoryEntity();

        attempt.setCategory(category);

        assertThat(attempt.getCategory())
                .as("Expected: %s, Result: %s", category, attempt.getCategory())
                .isEqualTo(category);
    }
}