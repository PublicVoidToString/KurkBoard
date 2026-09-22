package com.kurkboard.controller;

import com.kurkboard.entity.CategoryTypeEntity;
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
@AutoConfigureMockMvc
@Transactional
class CategoryTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCategoryType() throws Exception {
        CategoryTypeEntity categoryType = new CategoryTypeEntity(
                "CREATE_TEST",
                "Create test category type",
                3
        );

        String response = mockMvc.perform(
                        post("/api/category-types")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(categoryType))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.shortName").value("CREATE_TEST"))
                .andExpect(jsonPath("$.description").value("Create test category type"))
                .andExpect(jsonPath("$.scoresPerAttempt").value(3))
                .andReturn()
                .getResponse()
                .getContentAsString();

        CategoryTypeEntity created =
                objectMapper.readValue(response, CategoryTypeEntity.class);

        mockMvc.perform(
                        get("/api/category-types/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()));
    }

    @Test
    void shouldFindAllCategoryTypes() throws Exception {
        mockMvc.perform(
                get("/api/category-types")
        ).andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForUnknownCategoryType() throws Exception {
        mockMvc.perform(
                get("/api/category-types/999999")
        ).andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateCategoryType() throws Exception {
        CategoryTypeEntity categoryType = new CategoryTypeEntity(
                "UPDATE_TEST",
                "Update test category type",
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

        CategoryTypeEntity created =
                objectMapper.readValue(response, CategoryTypeEntity.class);

        CategoryTypeEntity update = new CategoryTypeEntity(
                "UPDATED_TEST",
                "Updated category type",
                5
        );

        mockMvc.perform(
                        put("/api/category-types/" + created.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(update))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.shortName").value("UPDATED_TEST"))
                .andExpect(jsonPath("$.description").value("Updated category type"))
                .andExpect(jsonPath("$.scoresPerAttempt").value(5));

        mockMvc.perform(
                        get("/api/category-types/" + created.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.shortName").value("UPDATED_TEST"));
    }

    @Test
    void shouldDeleteCategoryType() throws Exception {
        CategoryTypeEntity categoryType = new CategoryTypeEntity(
                "DELETE_TEST",
                "Delete test category type",
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

        CategoryTypeEntity created =
                objectMapper.readValue(response, CategoryTypeEntity.class);

        mockMvc.perform(
                delete("/api/category-types/" + created.getId())
        ).andExpect(status().isNoContent());

        mockMvc.perform(
                get("/api/category-types/" + created.getId())
        ).andExpect(status().isNotFound());
    }
}