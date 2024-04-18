package org.itechciv.dashboard.model;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient", schema = "dashboard")
public class Patient {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

	 @Column(name = "gender")
     private String gender;

     @Column(name = "birth_date")
     private Date birthDate;
     
     @Column(name = "age_years")
     private int ageYears;
     
     @Column(name = "age_months")
     private int ageMonths;
     
     @Column(name = "age_weeks")
     private int ageWeeks;
     
     @Column(name = "arv_init_date")
     private Date arvInitDate;
     
     @Column(name = "subjectno" , nullable = true)
     private String subjectno;
     
     @Column(name = "subjectid")
     private String subjectid;
     
     @ManyToOne
	 @JoinColumn(name ="siteId", nullable = false)
	 private Site site; 
     
	 @ManyToOne
	 @JoinColumn(name ="vihTypeId", nullable = false)
	 private VihType vihType; 
     
     @OneToMany(mappedBy = "patient")
	 private List<Analysis> analysis = new ArrayList<>();

	public Patient() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
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

	public Date getArvInitDate() {
		return arvInitDate;
	}

	public void setArvInitDate(Date arvInitDate) {
		this.arvInitDate = arvInitDate;
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

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public VihType getVihType() {
		return vihType;
	}

	public void setVihType(VihType vihType) {
		this.vihType = vihType;
	}

	public List<Analysis> getAnalysis() {
		return analysis;
	}

	public void setAnalysis(List<Analysis> analysis) {
		this.analysis = analysis;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", gender=" + gender + ", birthDate=" + birthDate + ", ageYears=" + ageYears
				+ ", ageMonths=" + ageMonths + ", ageWeeks=" + ageWeeks + ", arvInitDate=" + arvInitDate
				+ ", subjectno=" + subjectno + ", subjectid=" + subjectid + ", site=" + site + ", vihType=" + vihType
				+ ", analysis=" + analysis + "]";
	}
}
