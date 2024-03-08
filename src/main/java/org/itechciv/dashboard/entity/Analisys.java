package org.itechciv.dashboard.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
<<<<<<< HEAD
@Table(name = "analysis", schema = "clinlims")
=======
@Table(name = "analysis")
>>>>>>> 370ee252d22176f73344c1d4099d2d0e7eff3b74
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

<<<<<<< HEAD

    @OneToMany(mappedBy = "analisys")
=======
    @OneToMany(mappedBy = "analysis")
>>>>>>> 370ee252d22176f73344c1d4099d2d0e7eff3b74
    private List<Sample> sample;


}
