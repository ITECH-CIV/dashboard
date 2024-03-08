package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
<<<<<<< HEAD
@Table(name="sample", schema = "clinlims")
@Data
public class Sample {

=======
@Table(name="sample_type")
@Data
public class Sample {


>>>>>>> 370ee252d22176f73344c1d4099d2d0e7eff3b74
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    //join column pour designer l id de category
    @JoinColumn(name="idSampleType")
    private SampleType sampleType ;

    @ManyToOne
    //join column pour designer l id de category
<<<<<<< HEAD
    @JoinColumn(name="id_analisys")
=======
    @JoinColumn(name="idAnalisys")
>>>>>>> 370ee252d22176f73344c1d4099d2d0e7eff3b74
    private Analisys analisys ;


}
