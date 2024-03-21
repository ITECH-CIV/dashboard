package org.itechciv.dashboard.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.itechciv.dashboard.enums.Gender;

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
@Table(name = "patient", schema = "dashboard")
public class Patient {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

	 @Column(name = "gender")
     private Gender gender;

     @Column(name = "birth_date")
     private Date birthDate;
     
     @Column(name = "age_years")
     private int ageYears;
     
     @Column(name = "age_months")
     private int ageMonths;
     
     @Column(name = "age_weeks")
     private int ageWeeks;
     
     @Column(name = "current1")
     private String current1;
     
     @Column(name = "current2")
     private String current2;
     
     @Column(name = "current3")
     private String current3;
     
     @Column(name = "current4")
     private String current4;
     
     @Column(name = "vl_pregnancy")
     private int vlPregnancy;
     
     @Column(name = "vl_suckle")
     private int vlSuckle;
     
     @Column(name = "stat_vih")
     private int statVih;
     
     @Column(name = "arv_init_date")
     private Date arvInitDate;
     
     @Column(name = "identity_data")
     private String identityData;

     @Column(name = "upid_code")
     private String upidCode;

     @Column(name = "national_code")
     private String nationalCode;
     
     @Column(name = "subjectno")
     private String subjectno;
     
     @Column(name = "subjectid")
     private String subjectid;
     
     @ManyToOne
	 @JoinColumn(name ="facilitysId", nullable = false)
	 private Facilitys facilitys; 
     
     @OneToMany(mappedBy = "patient")
	 private List<Analysis> analysis = new ArrayList<>();

	public Patient() {
		super();
	}

	public Patient(Gender gender, Date birthDate, int ageYears, int ageMonths, int ageWeeks, String current1,
			String current2, String current3, String current4, int vlPregnancy, int vlSuckle, int statVih,
			Date arvInitDate, String identityData, String upidCode, String nationalCode, String subjectno,
			String subjectid, Facilitys facilitys, List<Analysis> analysis) {
		super();
		this.gender = gender;
		this.birthDate = birthDate;
		this.ageYears = ageYears;
		this.ageMonths = ageMonths;
		this.ageWeeks = ageWeeks;
		this.current1 = current1;
		this.current2 = current2;
		this.current3 = current3;
		this.current4 = current4;
		this.vlPregnancy = vlPregnancy;
		this.vlSuckle = vlSuckle;
		this.statVih = statVih;
		this.arvInitDate = arvInitDate;
		this.identityData = identityData;
		this.upidCode = upidCode;
		this.nationalCode = nationalCode;
		this.subjectno = subjectno;
		this.subjectid = subjectid;
		this.facilitys = facilitys;
		this.analysis = analysis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getAgeYears() {
		return ageYears;
	}

	public void setAgeYears(int ageYears) {
		this.ageYears = ageYears;
	}

	public int getAgeMonths() {
		return ageMonths;
	}

	public void setAgeMonths(int ageMonths) {
		this.ageMonths = ageMonths;
	}

	public int getAgeWeeks() {
		return ageWeeks;
	}

	public void setAgeWeeks(int ageWeeks) {
		this.ageWeeks = ageWeeks;
	}

	public String getCurrent1() {
		return current1;
	}

	public void setCurrent1(String current1) {
		this.current1 = current1;
	}

	public String getCurrent2() {
		return current2;
	}

	public void setCurrent2(String current2) {
		this.current2 = current2;
	}

	public String getCurrent3() {
		return current3;
	}

	public void setCurrent3(String current3) {
		this.current3 = current3;
	}

	public String getCurrent4() {
		return current4;
	}

	public void setCurrent4(String current4) {
		this.current4 = current4;
	}

	public int getVlPregnancy() {
		return vlPregnancy;
	}

	public void setVlPregnancy(int vlPregnancy) {
		this.vlPregnancy = vlPregnancy;
	}

	public int getVlSuckle() {
		return vlSuckle;
	}

	public void setVlSuckle(int vlSuckle) {
		this.vlSuckle = vlSuckle;
	}

	public int getStatVih() {
		return statVih;
	}

	public void setStatVih(int statVih) {
		this.statVih = statVih;
	}

	public Date getArvInitDate() {
		return arvInitDate;
	}

	public void setArvInitDate(Date arvInitDate) {
		this.arvInitDate = arvInitDate;
	}

	public String getIdentityData() {
		return identityData;
	}

	public void setIdentityData(String identityData) {
		this.identityData = identityData;
	}

	public String getUpidCode() {
		return upidCode;
	}

	public void setUpidCode(String upidCode) {
		this.upidCode = upidCode;
	}

	public String getNationalCode() {
		return nationalCode;
	}

	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}

	public String getSubjectno() {
		return subjectno;
	}

	public void setSubjectno(String subjectno) {
		this.subjectno = subjectno;
	}

	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public Facilitys getFacilitys() {
		return facilitys;
	}

	public void setFacilitys(Facilitys facilitys) {
		this.facilitys = facilitys;
	}

	public List<Analysis> getAnalysis() {
		return analysis;
	}

	public void setAnalysis(List<Analysis> analysis) {
		this.analysis = analysis;
	}

	
}
