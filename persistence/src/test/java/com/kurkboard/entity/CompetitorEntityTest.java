package com.kurkboard.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CompetitorEntityTest {

    @Test
    void shouldCreateCompetitor() {
        AssociationEntity association = new AssociationEntity(
                "Test Association",
                "https://example.com",
                "Gdańsk"
        );

        CompetitorEntity competitor = new CompetitorEntity(
                "Jan",
                "Kowalski",
                association
        );

        assertThat(competitor.getFirstName())
                .as("Expected: Jan, Result: %s", competitor.getFirstName())
                .isEqualTo("Jan");

        assertThat(competitor.getLastName())
                .as("Expected: Kowalski, Result: %s", competitor.getLastName())
                .isEqualTo("Kowalski");

        assertThat(competitor.getAssociation())
                .as("Expected: %s, Result: %s", association, competitor.getAssociation())
                .isEqualTo(association);
    }

    @Test
    void shouldSetAndGetFirstName() {
        CompetitorEntity competitor = new CompetitorEntity();

        competitor.setFirstName("Jan");

        assertThat(competitor.getFirstName())
                .as("Expected: Jan, Result: %s", competitor.getFirstName())
                .isEqualTo("Jan");
    }

    @Test
    void shouldSetAndGetLastName() {
        CompetitorEntity competitor = new CompetitorEntity();

        competitor.setLastName("Kowalski");

        assertThat(competitor.getLastName())
                .as("Expected: Kowalski, Result: %s", competitor.getLastName())
                .isEqualTo("Kowalski");
    }

    @Test
    void shouldSetAndGetAssociation() {
        CompetitorEntity competitor = new CompetitorEntity();

        AssociationEntity association = new AssociationEntity(
                "Test Association",
                "https://example.com",
                "Gdańsk"
        );

        competitor.setAssociation(association);

        assertThat(competitor.getAssociation())
                .as("Expected: %s, Result: %s", association, competitor.getAssociation())
                .isEqualTo(association);
    }
}