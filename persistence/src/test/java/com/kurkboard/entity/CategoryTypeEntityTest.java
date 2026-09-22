package com.kurkboard.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTypeEntityTest {

    @Test
    void shouldCreateCategoryType() {
        CategoryTypeEntity categoryType = new CategoryTypeEntity(
                "SINGLE",
                "Single score category",
                3
        );

        assertThat(categoryType.getShortName())
                .as("Expected: SINGLE, Result: %s", categoryType.getShortName())
                .isEqualTo("SINGLE");

        assertThat(categoryType.getDescription())
                .as("Expected: Single score category, Result: %s", categoryType.getDescription())
                .isEqualTo("Single score category");

        assertThat(categoryType.getScoresPerAttempt())
                .as("Expected: 3, Result: %s", categoryType.getScoresPerAttempt())
                .isEqualTo(3);
    }

    @Test
    void shouldSetAndGetShortName() {
        CategoryTypeEntity categoryType = new CategoryTypeEntity();

        categoryType.setShortName("SINGLE");

        assertThat(categoryType.getShortName())
                .as("Expected: SINGLE, Result: %s", categoryType.getShortName())
                .isEqualTo("SINGLE");
    }

    @Test
    void shouldSetAndGetDescription() {
        CategoryTypeEntity categoryType = new CategoryTypeEntity();

        categoryType.setDescription("Single score category");

        assertThat(categoryType.getDescription())
                .as("Expected: Single score category, Result: %s", categoryType.getDescription())
                .isEqualTo("Single score category");
    }

    @Test
    void shouldSetAndGetScoresPerAttempt() {
        CategoryTypeEntity categoryType = new CategoryTypeEntity();

        categoryType.setScoresPerAttempt(3);

        assertThat(categoryType.getScoresPerAttempt())
                .as("Expected: 3, Result: %s", categoryType.getScoresPerAttempt())
                .isEqualTo(3);
    }
}