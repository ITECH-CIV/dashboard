package org.itechciv.dashboard.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "analysis")
@Data

public class Analisys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "started_date")
    private Date started_date;

    @Column(name = "completed_date")
    private Date completed_date;

    @ManyToOne
    @JoinColumn(name="idPatient")
    private Patient patient ;

    @ManyToOne
    @JoinColumn(name="idTest")
    private Test test ;

    @OneToMany(mappedBy = "analysis")
    private List<Sample> sample;


}
