package com.kurkboard.controller;

import com.kurkboard.entity.AssociationEntity;
import com.kurkboard.entity.CompetitorEntity;
import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class CompetitorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCompetitor() throws Exception {
        AssociationEntity association = createAssociation();

        CompetitorEntity competitor = new CompetitorEntity(
                "Create",
                "Test",
                association
        );

        String response = mockMvc.perform(
                        post("/api/competitors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(competitor))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.firstName").value("Create"))
                .andExpect(jsonPath("$.lastName").value("Test"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        CompetitorEntity created =
                objectMapper.readValue(response, CompetitorEntity.class);

        mockMvc.perform(
                        get("/api/competitors/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.firstName").value("Create"))
                .andExpect(jsonPath("$.lastName").value("Test"));
    }

    @Test
    void shouldFindAllCompetitors() throws Exception {
        mockMvc.perform(
                get("/api/competitors")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForUnknownCompetitor() throws Exception {
        mockMvc.perform(
                get("/api/competitors/999999")
        ).andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateCompetitor() throws Exception {
        AssociationEntity association = createAssociation();

        CompetitorEntity competitor = new CompetitorEntity(
                "Update",
                "Test",
                association
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

        CompetitorEntity created =
                objectMapper.readValue(response, CompetitorEntity.class);

        CompetitorEntity update = new CompetitorEntity(
                "Updated",
                "Competitor",
                association
        );

        mockMvc.perform(
                        put("/api/competitors/" + created.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(update))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.firstName").value("Updated"))
                .andExpect(jsonPath("$.lastName").value("Competitor"));

        mockMvc.perform(
                        get("/api/competitors/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.firstName").value("Updated"))
                .andExpect(jsonPath("$.lastName").value("Competitor"));
    }

    @Test
    void shouldDeleteCompetitor() throws Exception {
        AssociationEntity association = createAssociation();

        CompetitorEntity competitor = new CompetitorEntity(
                "Delete",
                "Test",
                association
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

        CompetitorEntity created =
                objectMapper.readValue(response, CompetitorEntity.class);

        mockMvc.perform(
                delete("/api/competitors/" + created.getId())
        ).andExpect(status().isNoContent());

        mockMvc.perform(
                get("/api/competitors/" + created.getId())
        ).andExpect(status().isNotFound());
    }

    private AssociationEntity createAssociation() throws Exception {
        AssociationEntity association = new AssociationEntity(
                "Competitor Test Association",
                "https://competitor.example.com",
                "Gdańsk"
        );

        String response = mockMvc.perform(
                        post("/api/associations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(association))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue(response, AssociationEntity.class);
    }
}