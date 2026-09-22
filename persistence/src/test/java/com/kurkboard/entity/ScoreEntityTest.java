package com.kurkboard.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreEntityTest {

    @Test
    void shouldCreateScore() {
        AttemptEntity attempt = new AttemptEntity();

        ScoreEntity score = new ScoreEntity(
                new BigDecimal("8.50"),
                attempt,
                (short) 100,
                (short) 150
        );

        assertThat(score.getScore())
                .as("Expected: 8.50, Result: %s", score.getScore())
                .isEqualByComparingTo("8.50");

        assertThat(score.getAttempt())
                .as("Expected: %s, Result: %s", attempt, score.getAttempt())
                .isEqualTo(attempt);

        assertThat(score.getX())
                .as("Expected: 100, Result: %s", score.getX())
                .isEqualTo((short) 100);

        assertThat(score.getY())
                .as("Expected: 150, Result: %s", score.getY())
                .isEqualTo((short) 150);
    }

    @Test
    void shouldSetAndGetScore() {
        ScoreEntity score = new ScoreEntity();

        BigDecimal expectedScore = new BigDecimal("8.50");

        score.setScore(expectedScore);

        assertThat(score.getScore())
                .as("Expected: %s, Result: %s", expectedScore, score.getScore())
                .isEqualByComparingTo(expectedScore);
    }

    @Test
    void shouldSetAndGetAttempt() {
        ScoreEntity score = new ScoreEntity();

        AttemptEntity attempt = new AttemptEntity();

        score.setAttempt(attempt);

        assertThat(score.getAttempt())
                .as("Expected: %s, Result: %s", attempt, score.getAttempt())
                .isEqualTo(attempt);
    }

    @Test
    void shouldSetAndGetX() {
        ScoreEntity score = new ScoreEntity();

        score.setX((short) 100);

        assertThat(score.getX())
                .as("Expected: 100, Result: %s", score.getX())
                .isEqualTo((short) 100);
    }

    @Test
    void shouldSetAndGetY() {
        ScoreEntity score = new ScoreEntity();

        score.setY((short) 150);

        assertThat(score.getY())
                .as("Expected: 150, Result: %s", score.getY())
                .isEqualTo((short) 150);
    }
}