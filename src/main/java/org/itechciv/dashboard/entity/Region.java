package org.itechciv.dashboard.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="region" , schema = "clinlims")
@Data
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="region")
    private String region;
    @Column(name="lastUpdated")
    private Date lastUpdated;

    @OneToMany(mappedBy = "region")
    private List<District> district;
}
