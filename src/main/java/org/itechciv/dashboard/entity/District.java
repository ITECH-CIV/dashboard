package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="district")
@Data
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;

    @ManyToOne
    //join column pour designer l id de category
    @JoinColumn(name="idRegion")
    private Region region ;


    @OneToMany(mappedBy = "district")
    private List<Facilitys> facilitys;
}
