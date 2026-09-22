package com.kurkboard.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssociationEntityTest {

    @Test
    void shouldCreateAssociation() {
        AssociationEntity association = new AssociationEntity(
                "Test Association",
                "https://example.com",
                "Gdańsk"
        );

        assertThat(association.getId())
                .as("Expected: null, Result: %s", association.getId())
                .isNull();

        assertThat(association.getName())
                .as("Expected: Test Association, Result: %s", association.getName())
                .isEqualTo("Test Association");

        assertThat(association.getWebsite())
                .as("Expected: https://example.com, Result: %s", association.getWebsite())
                .isEqualTo("https://example.com");

        assertThat(association.getLocation())
                .as("Expected: Gdańsk, Result: %s", association.getLocation())
                .isEqualTo("Gdańsk");
    }

    @Test
    void shouldSetAndGetName() {
        AssociationEntity association = new AssociationEntity();

        association.setName("Test Association");

        assertThat(association.getName())
                .as("Expected: Test Association, Result: %s", association.getName())
                .isEqualTo("Test Association");
    }

    @Test
    void shouldSetAndGetWebsite() {
        AssociationEntity association = new AssociationEntity();

        association.setWebsite("https://example.com");

        assertThat(association.getWebsite())
                .as("Expected: https://example.com, Result: %s", association.getWebsite())
                .isEqualTo("https://example.com");
    }

    @Test
    void shouldSetAndGetLocation() {
        AssociationEntity association = new AssociationEntity();

        association.setLocation("Gdańsk");

        assertThat(association.getLocation())
                .as("Expected: Gdańsk, Result: %s", association.getLocation())
                .isEqualTo("Gdańsk");
    }
}