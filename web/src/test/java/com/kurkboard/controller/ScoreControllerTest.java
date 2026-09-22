package com.kurkboard.controller;

import com.kurkboard.entity.AssociationEntity;
import com.kurkboard.entity.AttemptEntity;
import com.kurkboard.entity.CategoryEntity;
import com.kurkboard.entity.CategoryTypeEntity;
import com.kurkboard.entity.CompetitorEntity;
import com.kurkboard.entity.ScoreEntity;
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
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateScore() throws Exception {
        AttemptEntity attempt = createAttempt();

        ScoreEntity score = new ScoreEntity(
                new BigDecimal("8.50"),
                attempt,
                (short) 100,
                (short) 150
        );

        String response = mockMvc.perform(
                        post("/api/scores")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(score))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.score").value(8.50))
                .andExpect(jsonPath("$.x").value(100))
                .andExpect(jsonPath("$.y").value(150))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ScoreEntity created =
                objectMapper.readValue(response, ScoreEntity.class);

        mockMvc.perform(
                        get("/api/scores/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.score").value(8.50));
    }

    @Test
    void shouldFindAllScores() throws Exception {
        mockMvc.perform(
                get("/api/scores")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForUnknownScore() throws Exception {
        mockMvc.perform(
                get("/api/scores/999999")
        ).andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateScore() throws Exception {
        AttemptEntity attempt = createAttempt();

        ScoreEntity score = new ScoreEntity(
                new BigDecimal("8.50"),
                attempt,
                (short) 100,
                (short) 150
        );

        String response = mockMvc.perform(
                        post("/api/scores")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(score))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ScoreEntity created =
                objectMapper.readValue(response, ScoreEntity.class);

        ScoreEntity update = new ScoreEntity(
                new BigDecimal("9.75"),
                attempt,
                (short) 120,
                (short) 180
        );

        mockMvc.perform(
                        put("/api/scores/" + created.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(update))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.score").value(9.75))
                .andExpect(jsonPath("$.x").value(120))
                .andExpect(jsonPath("$.y").value(180));

        mockMvc.perform(
                        get("/api/scores/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.score").value(9.75));
    }

    @Test
    void shouldDeleteScore() throws Exception {
        AttemptEntity attempt = createAttempt();

        ScoreEntity score = new ScoreEntity(
                new BigDecimal("8.50"),
                attempt,
                (short) 100,
                (short) 150
        );

        String response = mockMvc.perform(
                        post("/api/scores")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(score))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ScoreEntity created =
                objectMapper.readValue(response, ScoreEntity.class);

        mockMvc.perform(
                delete("/api/scores/" + created.getId())
        ).andExpect(status().isNoContent());

        mockMvc.perform(
                get("/api/scores/" + created.getId())
        ).andExpect(status().isNotFound());
    }

    private AttemptEntity createAttempt() throws Exception {
        AssociationEntity association = new AssociationEntity(
                "Score Test Association",
                "https://score.example.com",
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
                "Score",
                "Test",
                createdAssociation
        );

        String competitorResponse = mockMvc.perform(
                        post("/api/competitors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(competitor))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CompetitorEntity createdCompetitor =
                objectMapper.readValue(
                        competitorResponse,
                        CompetitorEntity.class
                );

        TournamentEntity tournament = new TournamentEntity(
                "Score Test Tournament",
                LocalDate.of(2026, 8, 1),
                "https://score-tournament.example.com"
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
                "SCORE_TEST",
                "Score test type",
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
                "Score Test Category",
                new BigDecimal("10.00"),
                new BigDecimal("5.00"),
                3,
                createdTournament,
                createdCategoryType,
                "https://score-category.example.com"
        );

        String categoryResponse = mockMvc.perform(
                        post("/api/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(category))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CategoryEntity createdCategory =
                objectMapper.readValue(
                        categoryResponse,
                        CategoryEntity.class
                );

        AttemptEntity attempt = new AttemptEntity(
                createdCompetitor,
                createdCategory
        );

        String attemptResponse = mockMvc.perform(
                        post("/api/attempts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(attempt))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue(
                attemptResponse,
                AttemptEntity.class
        );
    }
}