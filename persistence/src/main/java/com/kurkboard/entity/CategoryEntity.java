package com.kurkboard.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "initial_fee", nullable = false, precision = 6, scale = 2)
    private BigDecimal initialFee;

    @Column(name = "additional_fee", nullable = false, precision = 6, scale = 2)
    private BigDecimal additionalFee;

    @Column(name = "attempt_limit", nullable = false)
    private Integer attemptLimit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tournament_id", nullable = false)
    private TournamentEntity tournament;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_type_id", nullable = false)
    private CategoryTypeEntity categoryType;

    @Column(name = "image_url")
    private String imageUrl;

    public CategoryEntity() {
    }

    public CategoryEntity(
            Integer id,
            String name,
            BigDecimal initialFee,
            BigDecimal additionalFee,
            Integer attemptLimit,
            TournamentEntity tournament,
            CategoryTypeEntity categoryType,
            String imageUrl
    ) {
        this.id = id;
        this.name = name;
        this.initialFee = initialFee;
        this.additionalFee = additionalFee;
        this.attemptLimit = attemptLimit;
        this.tournament = tournament;
        this.categoryType = categoryType;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInitialFee() {
        return initialFee;
    }

    public void setInitialFee(BigDecimal initialFee) {
        this.initialFee = initialFee;
    }

    public BigDecimal getAdditionalFee() {
        return additionalFee;
    }

    public void setAdditionalFee(BigDecimal additionalFee) {
        this.additionalFee = additionalFee;
    }

    public Integer getAttemptLimit() {
        return attemptLimit;
    }

    public void setAttemptLimit(Integer attemptLimit) {
        this.attemptLimit = attemptLimit;
    }

    public TournamentEntity getTournament() {
        return tournament;
    }

    public void setTournament(TournamentEntity tournament) {
        this.tournament = tournament;
    }

    public CategoryTypeEntity getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryTypeEntity categoryType) {
        this.categoryType = categoryType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}