package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
<<<<<<< HEAD
@Table(name="age_category", schema = "clinlims")
=======
@Table(name="age_category")
>>>>>>> 370ee252d22176f73344c1d4099d2d0e7eff3b74
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
