package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
<<<<<<< HEAD
@Table(name="page_visited", schema = "clinlims")
=======
@Table(name="page_visited")
>>>>>>> 370ee252d22176f73344c1d4099d2d0e7eff3b74
@Data


public class PageVisited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="pageUrl")
    private String page_url;


    @ManyToOne
    //join column pour designer l id de category
    @JoinColumn(name="idVisitor")
    private Visitor visitor ;
}
