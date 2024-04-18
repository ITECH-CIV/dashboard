package org.itechciv.dashboard.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sample_type", schema = "dashboard")
public class SampleType  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name="label")
    private String label;
    
    @Column(name="description")
    private String description;
    
    @OneToMany(mappedBy = "sampleType")
	private List<Analysis> analysises = new ArrayList<>();

	public SampleType() {
		super();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Analysis> getAnalysises() {
		return analysises;
	}

	public void setAnalysises(List<Analysis> analysises) {
		this.analysises = analysises;
	}

	@Override
	public String toString() {
		return "SampleType [id=" + id + ", label=" + label + ", description=" + description + ", analysises="
				+ analysises + "]";
	}
}
