package org.itechciv.dashboard.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="page_visited", schema = "clinlims")
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
