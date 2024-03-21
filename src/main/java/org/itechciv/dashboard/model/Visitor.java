package org.itechciv.dashboard.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "visitor", schema = "dashboard")
public class Visitor {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
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

	public Visitor() {
		super();
	}

	public Visitor(String address, int yearMonth, int visitedCount, Date firstVisitedDate, Date lastVisitedDate,
			LocalDateTime timestamp, List<PageVisited> pageVisitedes) {
		super();
		this.address = address;
		this.yearMonth = yearMonth;
		this.visitedCount = visitedCount;
		this.firstVisitedDate = firstVisitedDate;
		this.lastVisitedDate = lastVisitedDate;
		this.timestamp = timestamp;
		this.pageVisitedes = pageVisitedes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(int yearMonth) {
		this.yearMonth = yearMonth;
	}

	public int getVisitedCount() {
		return visitedCount;
	}

	public void setVisitedCount(int visitedCount) {
		this.visitedCount = visitedCount;
	}

	public Date getFirstVisitedDate() {
		return firstVisitedDate;
	}

	public void setFirstVisitedDate(Date firstVisitedDate) {
		this.firstVisitedDate = firstVisitedDate;
	}

	public Date getLastVisitedDate() {
		return lastVisitedDate;
	}

	public void setLastVisitedDate(Date lastVisitedDate) {
		this.lastVisitedDate = lastVisitedDate;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public List<PageVisited> getPageVisitedes() {
		return pageVisitedes;
	}

	public void setPageVisitedes(List<PageVisited> pageVisitedes) {
		this.pageVisitedes = pageVisitedes;
	}
     
}
