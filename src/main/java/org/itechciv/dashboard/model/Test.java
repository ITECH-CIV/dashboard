package org.itechciv.dashboard.model;

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
@Table(name = "test", schema = "dashboard")
public class Test {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date")
    private Date date;
    
    @Column(name = "study")
    private String study;
    
    @OneToMany(mappedBy = "test")
	private List<Analysis> analysis = new ArrayList<>();

	public Test() {
		super();
	}

	public Test(Date date, String study, List<Analysis> analysis) {
		super();
		this.date = date;
		this.study = study;
		this.analysis = analysis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStudy() {
		return study;
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public List<Analysis> getAnalysis() {
		return analysis;
	}

	public void setAnalysis(List<Analysis> analysis) {
		this.analysis = analysis;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", date=" + date + ", study=" + study + ", analysis=" + analysis + "]";
	}
	
	
}
