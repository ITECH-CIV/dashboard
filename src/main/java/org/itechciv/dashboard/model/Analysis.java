package org.itechciv.dashboard.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "analysis", schema = "dashboard")
public class Analysis {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
	  
	  @Column(name = "completed_date")
	  private Date completedDate;
	  
	  @Column(name = "released_date")
	  private Date releasedDate;
	  
	  @Column(name = "gross_result")
	  private String grossResult;
	  
	  @Column(name = "converted_result")
	  private int convertedResult;
	  
	  @Column(name="analysis_status")
	  private String analysisStatus;
	  
	  @Column(name="lab_no")
	  private String labno;
	  
	  @Column(name = "drcpt")
	  private Date drcpt;
	    
	  @Column(name = "dintv")
	  private Date dintv;
	  
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

	  @Column(name ="ageCdcId", nullable = true)
	  private Integer ageCdc; 

	  @Column(name ="ageNationalId", nullable = true)
	  private Integer ageNational; 

		public Analysis() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public String getLabno() {
			return labno;
		}

		public void setLabno(String labno) {
			this.labno = labno;
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

		public Integer getAgeCdc() {
			return ageCdc;
		}

		public void setAgeCdc(Integer ageCdc) {
			this.ageCdc = ageCdc;
		}

		public Integer getAgeNational() {
			return ageNational;
		}

		public void setAgeNational(Integer ageNational) {
			this.ageNational = ageNational;
		}

		public String getAnalysisStatus() {
			return analysisStatus;
		}

		public void setAnalysisStatus(String analysisStatus) {
			this.analysisStatus = analysisStatus;
		}

		public Date getCompletedDate() {
			return completedDate;
		}

		public void setCompletedDate(Date completedDate) {
			this.completedDate = completedDate;
		}

		public Date getReleasedDate() {
			return releasedDate;
		}

		public void setReleasedDate(Date releasedDate) {
			this.releasedDate = releasedDate;
		}

		public Date getDrcpt() {
			return drcpt;
		}

		public void setDrcpt(Date drcpt) {
			this.drcpt = drcpt;
		}

		public Date getDintv() {
			return dintv;
		}

		public void setDintv(Date dintv) {
			this.dintv = dintv;
		}

		
		
}
