package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="sample_type")
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
