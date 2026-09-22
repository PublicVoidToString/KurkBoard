package com.kurkboard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attempt")
public class AttemptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "competitor_id", nullable = false)
    private CompetitorEntity competitor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    public AttemptEntity() {
    }

    public AttemptEntity(
            CompetitorEntity competitor,
            CategoryEntity category
    ) {
        this.competitor = competitor;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public CompetitorEntity getCompetitor() {
        return competitor;
    }

    public void setCompetitor(CompetitorEntity competitor) {
        this.competitor = competitor;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}