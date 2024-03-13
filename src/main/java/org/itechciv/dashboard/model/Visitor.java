package org.itechciv.dashboard.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visitor", schema = "dashboard")
public class Visitor {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.UUID) 
	 private UUID id;

     @Column(name="address")
     private String address;

     @Column(name = "year_month")
     private int yearMonth;

     @Column(name = "visited_count")
     private int visitedCount;

     @Column(name = "first_visited_date")
     private Date firstVisitedDate;

     @Column(name = "last_visited_date")
     private Date lastVisitedDate;

     @Column(name = "timestamp")
     private LocalDateTime timestamp;
     
     @OneToMany(mappedBy = "visitor")
	 private List<PageVisited> pageVisitedes = new ArrayList<>();

}
