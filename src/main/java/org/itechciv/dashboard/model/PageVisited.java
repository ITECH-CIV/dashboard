package org.itechciv.dashboard.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "page_visited", schema = "dashboard")
public class PageVisited {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

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

	public PageVisited() {
		super();
	}

	public PageVisited(String pageUrl, int visitedCount, int yearMonth, Date firstVisitedDate, LocalDateTime timestamp,
			Visitor visitor) {
		super();
		this.pageUrl = pageUrl;
		this.visitedCount = visitedCount;
		this.yearMonth = yearMonth;
		this.firstVisitedDate = firstVisitedDate;
		this.timestamp = timestamp;
		this.visitor = visitor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public int getVisitedCount() {
		return visitedCount;
	}

	public void setVisitedCount(int visitedCount) {
		this.visitedCount = visitedCount;
	}

	public int getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(int yearMonth) {
		this.yearMonth = yearMonth;
	}

	public Date getFirstVisitedDate() {
		return firstVisitedDate;
	}

	public void setFirstVisitedDate(Date firstVisitedDate) {
		this.firstVisitedDate = firstVisitedDate;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	} 
	  
}
