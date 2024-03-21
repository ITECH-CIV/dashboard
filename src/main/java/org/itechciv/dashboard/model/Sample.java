package org.itechciv.dashboard.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sample", schema = "dashboard")
public class Sample  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name="label")
    private String label;
    
    @Column(name="description")
    private String description;
    
    @Column(name="lab_no")
    private String labno;
    
    @Column(name="sample_status")
    private String sampleStatus;
    
    @Column(name = "drcpt")
    private LocalDateTime drcpt;
    
    @Column(name = "dintv")
    private LocalDateTime dintv;
    
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    
    @ManyToOne
  	@JoinColumn(name ="sampleTypeId", nullable = false)
  	private SampleType sampleType; 
    
    @ManyToOne
  	@JoinColumn(name ="analysisId", nullable = false)
  	private Analysis analysis;

	public Sample() {
		super();
	}

	public Sample(String label, String description, String labno, String sampleStatus, LocalDateTime drcpt,
			LocalDateTime dintv, LocalDateTime timestamp, SampleType sampleType, Analysis analysis) {
		super();
		this.label = label;
		this.description = description;
		this.labno = labno;
		this.sampleStatus = sampleStatus;
		this.drcpt = drcpt;
		this.dintv = dintv;
		this.timestamp = timestamp;
		this.sampleType = sampleType;
		this.analysis = analysis;
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

	public String getLabno() {
		return labno;
	}

	public void setLabno(String labno) {
		this.labno = labno;
	}

	public String getSampleStatus() {
		return sampleStatus;
	}

	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}

	public LocalDateTime getDrcpt() {
		return drcpt;
	}

	public void setDrcpt(LocalDateTime drcpt) {
		this.drcpt = drcpt;
	}

	public LocalDateTime getDintv() {
		return dintv;
	}

	public void setDintv(LocalDateTime dintv) {
		this.dintv = dintv;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public SampleType getSampleType() {
		return sampleType;
	}

	public void setSampleType(SampleType sampleType) {
		this.sampleType = sampleType;
	}

	public Analysis getAnalysis() {
		return analysis;
	}

	public void setAnalysis(Analysis analysis) {
		this.analysis = analysis;
	}
}
