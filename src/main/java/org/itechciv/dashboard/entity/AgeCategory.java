package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="age_category")
@Data
public class AgeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="label")
    private String label;

    @Column(name="description")
    private String description;
}
