package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Entity
@Table(name="test")
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="date")
    private Date date;

    @OneToMany(mappedBy = "test")
    private List<Analisys> analisys;


}
