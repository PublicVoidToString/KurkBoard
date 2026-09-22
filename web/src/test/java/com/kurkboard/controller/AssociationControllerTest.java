package com.kurkboard.controller;

import com.kurkboard.entity.AssociationEntity;
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
class AssociationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAssociation() throws Exception {
        AssociationEntity association = new AssociationEntity(
                "Create Test Association",
                "https://create.example.com",
                "Gdańsk"
        );

        String response = mockMvc.perform(
                        post("/api/associations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(association))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Create Test Association"))
                .andExpect(jsonPath("$.website").value("https://create.example.com"))
                .andExpect(jsonPath("$.location").value("Gdańsk"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        AssociationEntity created =
                objectMapper.readValue(response, AssociationEntity.class);

        mockMvc.perform(
                        get("/api/associations/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Create Test Association"))
                .andExpect(jsonPath("$.website").value("https://create.example.com"))
                .andExpect(jsonPath("$.location").value("Gdańsk"));
    }

    @Test
    void shouldFindAllAssociations() throws Exception {
        mockMvc.perform(
                get("/api/associations")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForUnknownAssociation() throws Exception {
        mockMvc.perform(
                get("/api/associations/999999")
        ).andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateAssociation() throws Exception {
        AssociationEntity association = new AssociationEntity(
                "Update Test Association",
                "https://update.example.com",
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

        AssociationEntity created =
                objectMapper.readValue(response, AssociationEntity.class);

        AssociationEntity update = new AssociationEntity(
                "Updated Test Association",
                "https://updated.example.com",
                "Warsaw"
        );

        mockMvc.perform(
                        put("/api/associations/" + created.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(update))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Updated Test Association"))
                .andExpect(jsonPath("$.website").value("https://updated.example.com"))
                .andExpect(jsonPath("$.location").value("Warsaw"));

        mockMvc.perform(
                        get("/api/associations/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Updated Test Association"))
                .andExpect(jsonPath("$.website").value("https://updated.example.com"))
                .andExpect(jsonPath("$.location").value("Warsaw"));
    }

    @Test
    void shouldDeleteAssociation() throws Exception {
        AssociationEntity association = new AssociationEntity(
                "Delete Test Association",
                "https://delete.example.com",
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

        AssociationEntity created =
                objectMapper.readValue(response, AssociationEntity.class);

        mockMvc.perform(
                get("/api/associations/" + created.getId())
        ).andExpect(status().isOk());

        mockMvc.perform(
                delete("/api/associations/" + created.getId())
        ).andExpect(status().isNoContent());

        mockMvc.perform(
                get("/api/associations/" + created.getId())
        ).andExpect(status().isNotFound());
    }
}