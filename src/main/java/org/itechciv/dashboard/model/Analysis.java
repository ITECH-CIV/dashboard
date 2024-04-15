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
	  
	  @Column(name = "arv_reg")
	  private int arvReg;
	  
	  @Column(name = "gross_result")
	  private String grossResult;
	  
	  @Column(name = "converted_result")
	  private String convertedResult;
	  
	  @Column(name = "viral_load_log")
	  private double viralLoadLog;
	     
	  @Column(name="reason_other", nullable = true)
	  private String reasonother;
	  
	  @Column(name="analysis_status")
	  private int analysisStatus;
	  
	  @ManyToOne
	  @JoinColumn(name ="testId", nullable = false)
	  private Test test; 
	  
	  @ManyToOne
	  @JoinColumn(name ="patientId", nullable = false)
	  private Patient patient;  
	  
	  @OneToMany(mappedBy = "analysis")
	  private List<Sample> samples = new ArrayList<>();
		
	  @ManyToOne
	  @JoinColumn(name ="regimenId", nullable = false)
	  private Regimen regimen; 
	  
	  @ManyToOne
	  @JoinColumn(name ="vihTypeId", nullable = false)
	  private VihType vihType; 
	  
	  @ManyToOne
	  @JoinColumn(name ="vlReasonId", nullable = false)
	  private VlReason vlReason;

	public Analysis() {
		super();
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

	public int getArvReg() {
		return arvReg;
	}

	public void setArvReg(int arvReg) {
		this.arvReg = arvReg;
	}

	public String getGrossResult() {
		return grossResult;
	}

	public void setGrossResult(String grossResult) {
		this.grossResult = grossResult;
	}

	public String getConvertedResult() {
		return convertedResult;
	}

	public void setConvertedResult(String convertedResult) {
		this.convertedResult = convertedResult;
	}

	public double getViralLoadLog() {
		return viralLoadLog;
	}

	public void setViralLoadLog(double viralLoadLog) {
		this.viralLoadLog = viralLoadLog;
	}

	public String getReasonother() {
		return reasonother;
	}

	public void setReasonother(String reasonother) {
		this.reasonother = reasonother;
	}

	public int getAnalysisStatus() {
		return analysisStatus;
	}

	public void setAnalysisStatus(int analysisStatus) {
		this.analysisStatus = analysisStatus;
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

	public Regimen getRegimen() {
		return regimen;
	}

	public void setRegimen(Regimen regimen) {
		this.regimen = regimen;
	}

	public VihType getVihType() {
		return vihType;
	}

	public void setVihType(VihType vihType) {
		this.vihType = vihType;
	}

	public VlReason getVlReason() {
		return vlReason;
	}

	public void setVlReason(VlReason vlReason) {
		this.vlReason = vlReason;
	}

	@Override
	public String toString() {
		return "Analysis [id=" + id + ", startedDate=" + startedDate + ", completedDate=" + completedDate
				+ ", releasedDate=" + releasedDate + ", arvReg=" + arvReg + ", grossResult=" + grossResult
				+ ", convertedResult=" + convertedResult + ", viralLoadLog=" + viralLoadLog + ", reasonother="
				+ reasonother + ", analysisStatus=" + analysisStatus + ", test=" + test + ", patient=" + patient
				+ ", samples=" + samples + ", regimen=" + regimen + ", vihType=" + vihType + ", vlReason=" + vlReason
				+ "]";
	} 
}
