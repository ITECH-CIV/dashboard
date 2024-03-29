package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
<<<<<<< HEAD
@Table(name="patient", schema = "clinlims")
=======
@Table(name="patient")
>>>>>>> 370ee252d22176f73344c1d4099d2d0e7eff3b74
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sex")
    private String sex;
    @Column(name = "birth_date")
    private Date birthDate;
    private String gender;

    @Column(name = "identity_data")
    private String identity_data;

    @Column(name = "upid_code")
    private String upid_code;

    @Column(name = "national_code")
    private String national_code;

    @ManyToOne
    //join column pour designer l id de category
    @JoinColumn(name="idFacility")
    private Facilitys facility ;


    @OneToMany(mappedBy = "patient")
    private List<Analisys> analisys;


}
