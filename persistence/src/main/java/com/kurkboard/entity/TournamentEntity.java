package com.kurkboard.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tournament")
public class TournamentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "image_url")
    private String imageUrl;

    public TournamentEntity() {
    }

    public TournamentEntity(
            String name,
            LocalDate date,
            String imageUrl
    ) {
        this.name = name;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}