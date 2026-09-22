package com.kurkboard.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TournamentEntityTest {

    @Test
    void shouldCreateTournament() {
        LocalDate date = LocalDate.of(2026, 9, 21);

        TournamentEntity tournament = new TournamentEntity(
                "Test Tournament",
                date,
                "https://example.com/image.jpg"
        );

        assertThat(tournament.getName())
                .as("Expected: Test Tournament, Result: %s", tournament.getName())
                .isEqualTo("Test Tournament");

        assertThat(tournament.getDate())
                .as("Expected: %s, Result: %s", date, tournament.getDate())
                .isEqualTo(date);

        assertThat(tournament.getImageUrl())
                .as("Expected: https://example.com/image.jpg, Result: %s", tournament.getImageUrl())
                .isEqualTo("https://example.com/image.jpg");
    }

    @Test
    void shouldSetAndGetName() {
        TournamentEntity tournament = new TournamentEntity();

        tournament.setName("Test Tournament");

        assertThat(tournament.getName())
                .as("Expected: Test Tournament, Result: %s", tournament.getName())
                .isEqualTo("Test Tournament");
    }

    @Test
    void shouldSetAndGetDate() {
        TournamentEntity tournament = new TournamentEntity();

        LocalDate expectedDate = LocalDate.of(2026, 9, 21);

        tournament.setDate(expectedDate);

        assertThat(tournament.getDate())
                .as("Expected: %s, Result: %s", expectedDate, tournament.getDate())
                .isEqualTo(expectedDate);
    }

    @Test
    void shouldSetAndGetImageUrl() {
        TournamentEntity tournament = new TournamentEntity();

        tournament.setImageUrl("https://example.com/image.jpg");

        assertThat(tournament.getImageUrl())
                .as("Expected: https://example.com/image.jpg, Result: %s", tournament.getImageUrl())
                .isEqualTo("https://example.com/image.jpg");
    }
}