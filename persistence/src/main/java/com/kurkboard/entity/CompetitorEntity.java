package com.kurkboard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "competitor")
public class CompetitorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id")
    private AssociationEntity association;

    public CompetitorEntity() {
    }

    public CompetitorEntity(
            String firstName,
            String lastName,
            AssociationEntity association
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.association = association;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AssociationEntity getAssociation() {
        return association;
    }

    public void setAssociation(AssociationEntity association) {
        this.association = association;
    }
}