package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="sample", schema = "clinlims")
@Data
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    //join column pour designer l id de category
    @JoinColumn(name="idSampleType")
    private SampleType sampleType ;

    @ManyToOne
    //join column pour designer l id de category
    @JoinColumn(name="id_analisys")
    private Analisys analisys ;


}
