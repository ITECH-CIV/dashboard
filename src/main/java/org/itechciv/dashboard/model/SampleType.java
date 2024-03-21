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
	private List<Sample> samples = new ArrayList<>();

	public SampleType() {
		super();
	}

	public SampleType(String label, String description, List<Sample> samples) {
		super();
		this.label = label;
		this.description = description;
		this.samples = samples;
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

	public List<Sample> getSamples() {
		return samples;
	}

	public void setSamples(List<Sample> samples) {
		this.samples = samples;
	}
	
}
