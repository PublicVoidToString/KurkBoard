package com.kurkboard.controller;

import com.kurkboard.entity.CategoryEntity;
import com.kurkboard.entity.CategoryTypeEntity;
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
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCategory() throws Exception {
        TournamentEntity tournament = createTournament();
        CategoryTypeEntity categoryType = createCategoryType();

        CategoryEntity category = new CategoryEntity(
                "Create Test Category",
                new BigDecimal("20.00"),
                new BigDecimal("10.00"),
                3,
                tournament,
                categoryType,
                "https://category.example.com"
        );

        String response = mockMvc.perform(
                        post("/api/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(category))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Create Test Category"))
                .andExpect(jsonPath("$.initialFee").value(20.00))
                .andExpect(jsonPath("$.additionalFee").value(10.00))
                .andExpect(jsonPath("$.attemptLimit").value(3))
                .andReturn()
                .getResponse()
                .getContentAsString();

        CategoryEntity created =
                objectMapper.readValue(response, CategoryEntity.class);

        mockMvc.perform(
                        get("/api/categories/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Create Test Category"));
    }

    @Test
    void shouldFindAllCategories() throws Exception {
        mockMvc.perform(
                get("/api/categories")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForUnknownCategory() throws Exception {
        mockMvc.perform(
                get("/api/categories/999999")
        ).andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateCategory() throws Exception {
        TournamentEntity tournament = createTournament();
        CategoryTypeEntity categoryType = createCategoryType();

        CategoryEntity category = new CategoryEntity(
                "Update Test Category",
                new BigDecimal("20.00"),
                new BigDecimal("10.00"),
                3,
                tournament,
                categoryType,
                "https://update.example.com"
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

        CategoryEntity created =
                objectMapper.readValue(response, CategoryEntity.class);

        CategoryEntity update = new CategoryEntity(
                "Updated Category",
                new BigDecimal("30.00"),
                new BigDecimal("15.00"),
                5,
                tournament,
                categoryType,
                "https://updated.example.com"
        );

        mockMvc.perform(
                        put("/api/categories/" + created.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(update))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Updated Category"))
                .andExpect(jsonPath("$.initialFee").value(30.00))
                .andExpect(jsonPath("$.additionalFee").value(15.00))
                .andExpect(jsonPath("$.attemptLimit").value(5));

        mockMvc.perform(
                        get("/api/categories/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Updated Category"));
    }

    @Test
    void shouldDeleteCategory() throws Exception {
        TournamentEntity tournament = createTournament();
        CategoryTypeEntity categoryType = createCategoryType();

        CategoryEntity category = new CategoryEntity(
                "Delete Test Category",
                new BigDecimal("20.00"),
                new BigDecimal("10.00"),
                3,
                tournament,
                categoryType,
                "https://delete.example.com"
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

        CategoryEntity created =
                objectMapper.readValue(response, CategoryEntity.class);

        mockMvc.perform(
                delete("/api/categories/" + created.getId())
        ).andExpect(status().isNoContent());

        mockMvc.perform(
                get("/api/categories/" + created.getId())
        ).andExpect(status().isNotFound());
    }

    private TournamentEntity createTournament() throws Exception {
        TournamentEntity tournament = new TournamentEntity(
                "Category Test Tournament",
                LocalDate.of(2026, 7, 1),
                "https://tournament.example.com"
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

        return objectMapper.readValue(response, TournamentEntity.class);
    }

    private CategoryTypeEntity createCategoryType() throws Exception {
        CategoryTypeEntity categoryType = new CategoryTypeEntity(
                "CATEGORY_TEST",
                "Category test type",
                3
        );

        String response = mockMvc.perform(
                        post("/api/category-types")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(categoryType))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readValue(response, CategoryTypeEntity.class);
    }
}