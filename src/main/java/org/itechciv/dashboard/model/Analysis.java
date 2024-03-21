package org.itechciv.dashboard.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "analysis", schema = "dashboard")
public class Analysis {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
	  
	  @Column(name = "started_date")
	  private LocalDateTime startedDate;

	  @Column(name = "completed_date")
	  private LocalDateTime completedDate;
	  
	  @Column(name = "released_date")
	  private LocalDateTime releasedDate;
	  
	  @Column(name="namemed")
	  private String namemed;
	   
	  @Column(name="nameprelev")
	  private String nameprelev;
	  
	  @Column(name="vl_reason")
	  private String vlreason;
	  
	  @Column(name="reason_other")
	  private String reasonother;
	  
	  @ManyToOne
	  @JoinColumn(name ="testId", nullable = false)
	  private Test test; 
	  
	  @ManyToOne
	  @JoinColumn(name ="patientId", nullable = false)
	  private Patient patient;  
	  
	  @OneToMany(mappedBy = "analysis")
	  private List<Sample> samples = new ArrayList<>();
	  
	  @OneToMany(mappedBy = "analysis")
	  private List<AnalysisResult> analysisResults = new ArrayList<>();


	public Analysis() {
		super();
	}


	public Analysis(LocalDateTime startedDate, LocalDateTime completedDate, LocalDateTime releasedDate, String namemed,
			String nameprelev, String vlreason, String reasonother, Test test, Patient patient, List<Sample> samples,
			List<AnalysisResult> analysisResults) {
		super();
		this.startedDate = startedDate;
		this.completedDate = completedDate;
		this.releasedDate = releasedDate;
		this.namemed = namemed;
		this.nameprelev = nameprelev;
		this.vlreason = vlreason;
		this.reasonother = reasonother;
		this.test = test;
		this.patient = patient;
		this.samples = samples;
		this.analysisResults = analysisResults;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getStartedDate() {
		return startedDate;
	}


	public void setStartedDate(LocalDateTime startedDate) {
		this.startedDate = startedDate;
	}


	public LocalDateTime getCompletedDate() {
		return completedDate;
	}


	public void setCompletedDate(LocalDateTime completedDate) {
		this.completedDate = completedDate;
	}


	public LocalDateTime getReleasedDate() {
		return releasedDate;
	}


	public void setReleasedDate(LocalDateTime releasedDate) {
		this.releasedDate = releasedDate;
	}


	public String getNamemed() {
		return namemed;
	}


	public void setNamemed(String namemed) {
		this.namemed = namemed;
	}


	public String getNameprelev() {
		return nameprelev;
	}


	public void setNameprelev(String nameprelev) {
		this.nameprelev = nameprelev;
	}


	public String getVlreason() {
		return vlreason;
	}


	public void setVlreason(String vlreason) {
		this.vlreason = vlreason;
	}


	public String getReasonother() {
		return reasonother;
	}


	public void setReasonother(String reasonother) {
		this.reasonother = reasonother;
	}


	public Test getTest() {
		return test;
	}


	public void setTest(Test test) {
		this.test = test;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public List<Sample> getSamples() {
		return samples;
	}


	public void setSamples(List<Sample> samples) {
		this.samples = samples;
	}


	public List<AnalysisResult> getAnalysisResults() {
		return analysisResults;
	}


	public void setAnalysisResults(List<AnalysisResult> analysisResults) {
		this.analysisResults = analysisResults;
	}

}
