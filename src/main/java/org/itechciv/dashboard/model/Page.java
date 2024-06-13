package org.itechciv.dashboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "page", schema = "dashboard")
public class Page {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(name = "label", nullable = true)
private String label;

@Column(name = "url", nullable = true)
private String url;

 @Column(name="total_views", nullable = true)
 private int totalViews;

public Page() {
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getLabel() {
    return label;
}

public void setLabel(String label) {
    this.label = label;
}

public int getTotalViews() {
    return totalViews;
}

public void setTotalViews(int totalViews) {
    this.totalViews = totalViews;
}

public String getUrl() {
    return url;
}

public void setUrl(String url) {
    this.url = url;
}



	  
}
