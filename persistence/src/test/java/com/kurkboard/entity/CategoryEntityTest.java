package com.kurkboard.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryEntityTest {

    @Test
    void shouldCreateCategory() {
        TournamentEntity tournament = new TournamentEntity(
                "Test Tournament",
                java.time.LocalDate.of(2026, 9, 21),
                "https://example.com/tournament.jpg"
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
                "https://example.com/category.jpg"
        );

        assertThat(category.getName())
                .as("Expected: Test Category, Result: %s", category.getName())
                .isEqualTo("Test Category");

        assertThat(category.getInitialFee())
                .as("Expected: 100.00, Result: %s", category.getInitialFee())
                .isEqualByComparingTo("100.00");

        assertThat(category.getAdditionalFee())
                .as("Expected: 50.00, Result: %s", category.getAdditionalFee())
                .isEqualByComparingTo("50.00");

        assertThat(category.getAttemptLimit())
                .as("Expected: 5, Result: %s", category.getAttemptLimit())
                .isEqualTo(5);

        assertThat(category.getTournament())
                .as("Expected: %s, Result: %s", tournament, category.getTournament())
                .isEqualTo(tournament);

        assertThat(category.getCategoryType())
                .as("Expected: %s, Result: %s", categoryType, category.getCategoryType())
                .isEqualTo(categoryType);

        assertThat(category.getImageUrl())
                .as("Expected: https://example.com/category.jpg, Result: %s", category.getImageUrl())
                .isEqualTo("https://example.com/category.jpg");
    }

    @Test
    void shouldSetAndGetName() {
        CategoryEntity category = new CategoryEntity();

        category.setName("Test Category");

        assertThat(category.getName())
                .as("Expected: Test Category, Result: %s", category.getName())
                .isEqualTo("Test Category");
    }

    @Test
    void shouldSetAndGetInitialFee() {
        CategoryEntity category = new CategoryEntity();

        BigDecimal expectedFee = new BigDecimal("100.00");

        category.setInitialFee(expectedFee);

        assertThat(category.getInitialFee())
                .as("Expected: %s, Result: %s", expectedFee, category.getInitialFee())
                .isEqualByComparingTo(expectedFee);
    }

    @Test
    void shouldSetAndGetAdditionalFee() {
        CategoryEntity category = new CategoryEntity();

        BigDecimal expectedFee = new BigDecimal("50.00");

        category.setAdditionalFee(expectedFee);

        assertThat(category.getAdditionalFee())
                .as("Expected: %s, Result: %s", expectedFee, category.getAdditionalFee())
                .isEqualByComparingTo(expectedFee);
    }

    @Test
    void shouldSetAndGetAttemptLimit() {
        CategoryEntity category = new CategoryEntity();

        category.setAttemptLimit(5);

        assertThat(category.getAttemptLimit())
                .as("Expected: 5, Result: %s", category.getAttemptLimit())
                .isEqualTo(5);
    }

    @Test
    void shouldSetAndGetTournament() {
        CategoryEntity category = new CategoryEntity();

        TournamentEntity tournament = new TournamentEntity(
                "Test Tournament",
                java.time.LocalDate.of(2026, 9, 21),
                "https://example.com/tournament.jpg"
        );

        category.setTournament(tournament);

        assertThat(category.getTournament())
                .as("Expected: %s, Result: %s", tournament, category.getTournament())
                .isEqualTo(tournament);
    }

    @Test
    void shouldSetAndGetCategoryType() {
        CategoryEntity category = new CategoryEntity();

        CategoryTypeEntity categoryType = new CategoryTypeEntity(
                "SINGLE",
                "Single score category",
                3
        );

        category.setCategoryType(categoryType);

        assertThat(category.getCategoryType())
                .as("Expected: %s, Result: %s", categoryType, category.getCategoryType())
                .isEqualTo(categoryType);
    }

    @Test
    void shouldSetAndGetImageUrl() {
        CategoryEntity category = new CategoryEntity();

        category.setImageUrl("https://example.com/category.jpg");

        assertThat(category.getImageUrl())
                .as("Expected: https://example.com/category.jpg, Result: %s", category.getImageUrl())
                .isEqualTo("https://example.com/category.jpg");
    }
}