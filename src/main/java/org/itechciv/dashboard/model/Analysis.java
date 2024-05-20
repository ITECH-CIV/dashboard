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
	  
	  @Column(name = "completed_date")
	  private LocalDateTime completedDate;
	  
	  @Column(name = "released_date")
	  private LocalDateTime releasedDate;
	  
	  @Column(name = "gross_result")
	  private String grossResult;
	  
	  @Column(name = "converted_result")
	  private int convertedResult;
	  
	  @Column(name="analysis_status")
	  private int analysisStatus;
	  
	  @Column(name="lab_no")
	  private String labno;
	  
	  @Column(name = "drcpt")
	  private LocalDateTime drcpt;
	    
	  @Column(name = "dintv")
	  private LocalDateTime dintv;
	  
	  @ManyToOne
	  @JoinColumn(name ="sampleTypeId", nullable = false)
	  private SampleType sampleType; 
	     
	  @ManyToOne
	  @JoinColumn(name ="testId", nullable = false)
	  private Test test; 
	  
	  @ManyToOne
	  @JoinColumn(name ="patientId", nullable = false)
	  private Patient patient;  
	 	
	  @ManyToOne
	  @JoinColumn(name ="regimenId", nullable = false)
	  private Regimen regimen; 
	
	  @ManyToOne
	  @JoinColumn(name ="vlReasonId", nullable = false)
	  private VlReason vlReason;
	  
	  @ManyToOne
	  @JoinColumn(name ="labId", nullable = false)
	  private Lab lab;

	  @ManyToOne
	  @JoinColumn(name ="ageCdcId", nullable = true)
	  private AgeCategory ageCdc; 

	  @ManyToOne
	  @JoinColumn(name ="ageNationalId", nullable = true)
	  private AgeCategory ageNational; 

		public Analysis() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public String getGrossResult() {
			return grossResult;
		}

		public void setGrossResult(String grossResult) {
			this.grossResult = grossResult;
		}

		public int getConvertedResult() {
			return convertedResult;
		}

		public void setConvertedResult(int convertedResult) {
			this.convertedResult = convertedResult;
		}

		public int getAnalysisStatus() {
			return analysisStatus;
		}

		public void setAnalysisStatus(int analysisStatus) {
			this.analysisStatus = analysisStatus;
		}

		public String getLabno() {
			return labno;
		}

		public void setLabno(String labno) {
			this.labno = labno;
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

		public SampleType getSampleType() {
			return sampleType;
		}

		public void setSampleType(SampleType sampleType) {
			this.sampleType = sampleType;
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

		public Regimen getRegimen() {
			return regimen;
		}

		public void setRegimen(Regimen regimen) {
			this.regimen = regimen;
		}

		public VlReason getVlReason() {
			return vlReason;
		}

		public void setVlReason(VlReason vlReason) {
			this.vlReason = vlReason;
		}

		public Lab getLab() {
			return lab;
		}

		public void setLab(Lab lab) {
			this.lab = lab;
		} 

		public AgeCategory getAgeCdc() {
			return ageCdc;
		}

		public void setAgeCdc(AgeCategory ageCdc) {
			this.ageCdc = ageCdc;
		}

		public AgeCategory getAgeNational() {
			return ageNational;
		}

		public void setAgeNational(AgeCategory ageNational) {
			this.ageNational = ageNational;
		}

		@Override
		public String toString() {
			return "Analysis [id=" + id + ", completedDate=" + completedDate + ", releasedDate=" + releasedDate
					+ ", grossResult=" + grossResult + ", convertedResult=" + convertedResult + ", analysisStatus="
					+ analysisStatus + ", labno=" + labno + ", drcpt=" + drcpt + ", dintv=" + dintv + ", sampleType="
					+ sampleType + ", test=" + test + ", patient=" + patient + ", regimen=" + regimen + ", vlReason="
					+ vlReason + ", lab=" + lab + ", ageCdc=" + ageCdc + ", ageNational=" + ageNational + "]";
		}

		

		
}
