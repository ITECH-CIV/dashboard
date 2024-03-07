package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="facilitys")
public class Facilitys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @ManyToOne
    //join column pour designer l id de category
    @JoinColumn(name="idDistrict")
    private District district ;

    @OneToMany(mappedBy = "facility")
    private List<Patient> patient;

}
