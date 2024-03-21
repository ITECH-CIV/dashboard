package org.itechciv.dashboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "analysis_result", schema = "dashboard")
public class AnalysisResult {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
	  
	  @Column(name="viral_load")
	  private int viralLoad;
	  
	  @Column(name="viral_load_log")
	  private double viralLoadLog;
	  
	  @ManyToOne
	  @JoinColumn(name ="analysisId", nullable = false)
	  private Analysis analysis;

	public AnalysisResult() {
		super();
	}

	public AnalysisResult(int viralLoad, double viralLoadLog, Analysis analysis) {
		super();
		this.viralLoad = viralLoad;
		this.viralLoadLog = viralLoadLog;
		this.analysis = analysis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getViralLoad() {
		return viralLoad;
	}

	public void setViralLoad(int viralLoad) {
		this.viralLoad = viralLoad;
	}

	public double getViralLoadLog() {
		return viralLoadLog;
	}

	public void setViralLoadLog(double viralLoadLog) {
		this.viralLoadLog = viralLoadLog;
	}

	public Analysis getAnalysis() {
		return analysis;
	}

	public void setAnalysis(Analysis analysis) {
		this.analysis = analysis;
	} 
}
