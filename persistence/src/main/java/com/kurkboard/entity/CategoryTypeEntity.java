package com.kurkboard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category_type")
public class CategoryTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "short_name", nullable = false, unique = true)
    private String shortName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "scores_per_attempt", nullable = false)
    private Integer scoresPerAttempt;

    public CategoryTypeEntity() {
    }

    public CategoryTypeEntity(
            Integer id,
            String shortName,
            String description,
            Integer scoresPerAttempt
    ) {
        this.id = id;
        this.shortName = shortName;
        this.description = description;
        this.scoresPerAttempt = scoresPerAttempt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScoresPerAttempt() {
        return scoresPerAttempt;
    }

    public void setScoresPerAttempt(Integer scoresPerAttempt) {
        this.scoresPerAttempt = scoresPerAttempt;
    }
}