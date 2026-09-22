package com.kurkboard.controller;

import com.kurkboard.entity.AssociationEntity;
import com.kurkboard.entity.AttemptEntity;
import com.kurkboard.entity.CategoryEntity;
import com.kurkboard.entity.CategoryTypeEntity;
import com.kurkboard.entity.CompetitorEntity;
import com.kurkboard.entity.TournamentEntity;
import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AttemptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAttempt() throws Exception {
        CompetitorEntity competitor = createCompetitor();
        CategoryEntity category = createCategory();

        AttemptEntity attempt = new AttemptEntity(
                competitor,
                category
        );

        String response = mockMvc.perform(
                        post("/api/attempts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(attempt))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AttemptEntity created =
                objectMapper.readValue(response, AttemptEntity.class);

        mockMvc.perform(
                        get("/api/attempts/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()));
    }

    @Test
    void shouldFindAllAttempts() throws Exception {
        mockMvc.perform(
                get("/api/attempts")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForUnknownAttempt() throws Exception {
        mockMvc.perform(
                get("/api/attempts/999999")
        ).andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateAttempt() throws Exception {
        CompetitorEntity competitor = createCompetitor();
        CategoryEntity category = createCategory();

        AttemptEntity attempt = new AttemptEntity(
                competitor,
                category
        );

        String response = mockMvc.perform(
                        post("/api/attempts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(attempt))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AttemptEntity created =
                objectMapper.readValue(response, AttemptEntity.class);

        AttemptEntity update = new AttemptEntity(
                competitor,
                category
        );

        mockMvc.perform(
                        put("/api/attempts/" + created.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(update))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()));

        mockMvc.perform(
                get("/api/attempts/" + created.getId())
        ).andExpect(status().isOk());
    }

    @Test
    void shouldDeleteAttempt() throws Exception {
        CompetitorEntity competitor = createCompetitor();
        CategoryEntity category = createCategory();

        AttemptEntity attempt = new AttemptEntity(
                competitor,
                category
        );

        String response = mockMvc.perform(
                        post("/api/attempts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(attempt))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AttemptEntity created =
                objectMapper.readValue(response, AttemptEntity.class);

        mockMvc.perform(
                delete("/api/attempts/" + created.getId())
        ).andExpect(status().isNoContent());

        mockMvc.perform(
                get("/api/attempts/" + created.getId())
        ).andExpect(status().isNotFound());
    }

    private CompetitorEntity createCompetitor() throws Exception {
        AssociationEntity association = new AssociationEntity(
                "Attempt Test Association",
                "https://attempt.example.com",
                "Gdańsk"
        );

        String associationResponse = mockMvc.perform(
                        post("/api/associations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(association))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AssociationEntity createdAssociation =
                objectMapper.readValue(
                        associationResponse,
                        AssociationEntity.class
                );

        CompetitorEntity competitor = new CompetitorEntity(
                "Attempt",
                "Test",
                createdAssociation
        );

        String response = mockMvc.perform(
                        post("/api/competitors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(competitor))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue(response, CompetitorEntity.class);
    }

    private CategoryEntity createCategory() throws Exception {
        TournamentEntity tournament = new TournamentEntity(
                "Attempt Test Tournament",
                LocalDate.of(2026, 1, 1),
                "https://tournament.example.com"
        );

        String tournamentResponse = mockMvc.perform(
                        post("/api/tournaments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(tournament))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TournamentEntity createdTournament =
                objectMapper.readValue(
                        tournamentResponse,
                        TournamentEntity.class
                );

        CategoryTypeEntity categoryType = new CategoryTypeEntity(
                "ATTEMPT",
                "Attempt test category type",
                3
        );

        String categoryTypeResponse = mockMvc.perform(
                        post("/api/category-types")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(categoryType))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CategoryTypeEntity createdCategoryType =
                objectMapper.readValue(
                        categoryTypeResponse,
                        CategoryTypeEntity.class
                );

        CategoryEntity category = new CategoryEntity(
                "Attempt Test Category",
                new BigDecimal("10.00"),
                new BigDecimal("5.00"),
                3,
                createdTournament,
                createdCategoryType,
                "https://category.example.com"
        );

        String response = mockMvc.perform(
                        post("/api/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(category))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue(response, CategoryEntity.class);
    }
}