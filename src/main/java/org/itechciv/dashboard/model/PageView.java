package org.itechciv.dashboard.model;

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
@Table(name = "page_view", schema = "dashboard")
public class PageView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
     @Column(name="visitor_ip", nullable = true)
     private String visitorIp;
    
     @ManyToOne
     @JoinColumn(name ="page_id", nullable = false)
     private Page page; 

     @Column(name = "view_date")
     private Date viewDate; 
    
     @Column(name = "nb_visit")
     private int nbVisit;
     
   
public PageView() {
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getVisitorIp() {
    return visitorIp;
}

public void setVisitorIp(String visitorIp) {
    this.visitorIp = visitorIp;
}

public Page getPage() {
    return page;
}

public void setPage(Page page) {
    this.page = page;
}

public Date getViewDate() {
    return viewDate;
}

public void setViewDate(Date viewDate) {
    this.viewDate = viewDate;
}

public int getNbVisit() {
    return nbVisit;
}

public void setNbVisit(int nbVisit) {
    this.nbVisit = nbVisit;
}

      
}
