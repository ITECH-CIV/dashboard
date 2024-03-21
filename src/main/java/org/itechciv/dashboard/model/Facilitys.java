package org.itechciv.dashboard.model;

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
@Table(name = "facilitys", schema = "dashboard")
public class Facilitys {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

	  @Column(name = "code")
	  private String code;
	  
	  @Column(name = "name")
	  private String name;
	  
	  @Column(name = "code_site")
	  private String codeSite;
	  
	  @Column(name = "name_site")
	  private String nameSite;
	  
	  @Column(name = "code_site_datim")
	  private String codeSiteDatim;
	  
	  @Column(name = "name_site_datim")
	  private String nameSiteDatim;
	  
	  @ManyToOne
	  @JoinColumn(name ="districtId", nullable = false)
	  private District district; 
	  
	  @OneToMany(mappedBy = "facilitys")
	  private List<Patient> patients = new ArrayList<>();

	public Facilitys() {
		super();
	}

	public Facilitys(String code, String name, String codeSite, String nameSite, String codeSiteDatim,
			String nameSiteDatim, District district, List<Patient> patients) {
		super();
		this.code = code;
		this.name = name;
		this.codeSite = codeSite;
		this.nameSite = nameSite;
		this.codeSiteDatim = codeSiteDatim;
		this.nameSiteDatim = nameSiteDatim;
		this.district = district;
		this.patients = patients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeSite() {
		return codeSite;
	}

	public void setCodeSite(String codeSite) {
		this.codeSite = codeSite;
	}

	public String getNameSite() {
		return nameSite;
	}

	public void setNameSite(String nameSite) {
		this.nameSite = nameSite;
	}

	public String getCodeSiteDatim() {
		return codeSiteDatim;
	}

	public void setCodeSiteDatim(String codeSiteDatim) {
		this.codeSiteDatim = codeSiteDatim;
	}

	public String getNameSiteDatim() {
		return nameSiteDatim;
	}

	public void setNameSiteDatim(String nameSiteDatim) {
		this.nameSiteDatim = nameSiteDatim;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
 
}
