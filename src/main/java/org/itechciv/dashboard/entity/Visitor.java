package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
<<<<<<< HEAD
@Table(name="visitor", schema = "clinlims")
=======
@Table(name="visitor")
>>>>>>> 370ee252d22176f73344c1d4099d2d0e7eff3b74
@Data

public class Visitor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="address")
    private String address;


    @Column(name = "year_month")
    private Integer year_month;

    @Column(name = "visited_count")
    private Integer visitedCount;



    @Column(name = "first_visited_date")
    private Date first_visited_date;


    @Column(name = "last_visited_date")
    private Date last_visited_date;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "visitor")
    private List<PageVisited> pageVisited;


}
