package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
<<<<<<< HEAD
@Table(name="district", schema = "clinlims")
=======
@Table(name="district")
>>>>>>> 370ee252d22176f73344c1d4099d2d0e7eff3b74
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
