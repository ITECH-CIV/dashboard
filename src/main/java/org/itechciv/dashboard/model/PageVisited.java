package org.itechciv.dashboard.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "page_visited", schema = "dashboard")
public class PageVisited {
	
	   @Id
       @GeneratedValue(strategy = GenerationType.UUID) 
	   private UUID id;

	   @Column(name="page_url")
	   private String pageUrl;
	   
	   @Column(name="visited_count")
	   private int visitedCount;
	   
	   @Column(name="year_month")
	   private int yearMonth;
	   
	   @Column(name="first_visited_date")
	   private Date firstVisitedDate;
	   
	   @Column(name = "timestamp")
	   private LocalDateTime timestamp;
	   
	   @ManyToOne
	   @JoinColumn(name ="visitorId", nullable = false)
	   private Visitor visitor; 


}
