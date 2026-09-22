package com.kurkboard.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "score")
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "score", nullable = false, precision = 4, scale = 2)
    private BigDecimal score;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attempt_id", nullable = false)
    private AttemptEntity attempt;

    @Column(name = "x")
    private Short x;

    @Column(name = "y")
    private Short y;

    public ScoreEntity() {
    }

    public ScoreEntity(
            BigDecimal score,
            AttemptEntity attempt,
            Short x,
            Short y
    ) {
        this.score = score;
        this.attempt = attempt;
        this.x = x;
        this.y = y;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public AttemptEntity getAttempt() {
        return attempt;
    }

    public void setAttempt(AttemptEntity attempt) {
        this.attempt = attempt;
    }

    public Short getX() {
        return x;
    }

    public void setX(Short x) {
        this.x = x;
    }

    public Short getY() {
        return y;
    }

    public void setY(Short y) {
        this.y = y;
    }
}