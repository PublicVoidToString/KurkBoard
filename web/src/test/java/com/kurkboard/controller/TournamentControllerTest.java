package com.kurkboard.controller;

import com.kurkboard.entity.TournamentEntity;
import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

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
class TournamentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateTournament() throws Exception {
        TournamentEntity tournament = new TournamentEntity(
                "Create Test Tournament",
                LocalDate.of(2026, 5, 10),
                "https://create.example.com"
        );

        String response = mockMvc.perform(
                        post("/api/tournaments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(tournament))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Create Test Tournament"))
                .andExpect(jsonPath("$.date").value("2026-05-10"))
                .andExpect(jsonPath("$.imageUrl").value("https://create.example.com"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        TournamentEntity created =
                objectMapper.readValue(response, TournamentEntity.class);

        mockMvc.perform(
                        get("/api/tournaments/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Create Test Tournament"));
    }

    @Test
    void shouldFindAllTournaments() throws Exception {
        mockMvc.perform(
                get("/api/tournaments")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForUnknownTournament() throws Exception {
        mockMvc.perform(
                get("/api/tournaments/999999")
        ).andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateTournament() throws Exception {
        TournamentEntity tournament = new TournamentEntity(
                "Update Test Tournament",
                LocalDate.of(2026, 5, 10),
                "https://update.example.com"
        );

        String response = mockMvc.perform(
                        post("/api/tournaments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(tournament))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TournamentEntity created =
                objectMapper.readValue(response, TournamentEntity.class);

        TournamentEntity update = new TournamentEntity(
                "Updated Test Tournament",
                LocalDate.of(2026, 6, 20),
                "https://updated.example.com"
        );

        mockMvc.perform(
                        put("/api/tournaments/" + created.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(update))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Updated Test Tournament"))
                .andExpect(jsonPath("$.date").value("2026-06-20"))
                .andExpect(jsonPath("$.imageUrl").value("https://updated.example.com"));

        mockMvc.perform(
                        get("/api/tournaments/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Updated Test Tournament"));
    }

    @Test
    void shouldDeleteTournament() throws Exception {
        TournamentEntity tournament = new TournamentEntity(
                "Delete Test Tournament",
                LocalDate.of(2026, 5, 10),
                "https://delete.example.com"
        );

        String response = mockMvc.perform(
                        post("/api/tournaments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(tournament))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TournamentEntity created =
                objectMapper.readValue(response, TournamentEntity.class);

        mockMvc.perform(
                delete("/api/tournaments/" + created.getId())
        ).andExpect(status().isNoContent());

        mockMvc.perform(
                get("/api/tournaments/" + created.getId())
        ).andExpect(status().isNotFound());
    }
}