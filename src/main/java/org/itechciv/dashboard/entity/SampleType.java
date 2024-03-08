package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
<<<<<<< HEAD
@Table(name="sample_type", schema = "clinlims")
=======
@Table(name="sample_type")
>>>>>>> 370ee252d22176f73344c1d4099d2d0e7eff3b74
@Data
public class SampleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="label")
    private String label;
    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "sampleType")
    private List<Sample> sample;
}
