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
	  private int codeSite;
	  
	  @Column(name = "name_site")
	  private String nameSite;
	  
	  @Column(name = "code_site_datim")
	  private String codeSiteDatim;
	  
	  @Column(name = "name_site_datim")
	  private String nameSiteDatim;
	  
	  @Column(name = "old_code_facilitys_dhis2")
	  private String oldCodeFacilitysDHIS2;
	  
	  @Column(name = "old_facilitys_name")
	  private String oldFacilityName;
	  
	  @Column(name = "unique_facilitys_id")
	  private int uniqueFacilitysId;
	  
	  @Column(name = "new_facilitys_long_name")
	  private String newFacilitysLongName;
	  
	  @Column(name = "new_facilitys_short_name")
	  private String newFacilitysShortName;
	  
	  @Column(name = "statut_id")
	  private String statutId;
	  
	  @Column(name = "facilitysCode")
	  private String facilitysCode;
	    
	  @ManyToOne
	  @JoinColumn(name ="districtId", nullable = true)
	  private District district; 
	  
	  @OneToMany(mappedBy = "facilitys")
	  private List<Patient> patients = new ArrayList<>();

	public Facilitys() {
		super();
	}

	public Facilitys(String code, String name, int codeSite, String nameSite, String codeSiteDatim,
			String nameSiteDatim, String oldCodeFacilitysDHIS2, String oldFacilityName, int uniqueFacilitysId,
			String newFacilitysLongName, String newFacilitysShortName, String statutId, String facilitysCode,
			District district, List<Patient> patients) {
		super();
		this.code = code;
		this.name = name;
		this.codeSite = codeSite;
		this.nameSite = nameSite;
		this.codeSiteDatim = codeSiteDatim;
		this.nameSiteDatim = nameSiteDatim;
		this.oldCodeFacilitysDHIS2 = oldCodeFacilitysDHIS2;
		this.oldFacilityName = oldFacilityName;
		this.uniqueFacilitysId = uniqueFacilitysId;
		this.newFacilitysLongName = newFacilitysLongName;
		this.newFacilitysShortName = newFacilitysShortName;
		this.statutId = statutId;
		this.facilitysCode = facilitysCode;
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

	public int getCodeSite() {
		return codeSite;
	}

	public void setCodeSite(int codeSite) {
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

	public String getOldCodeFacilitysDHIS2() {
		return oldCodeFacilitysDHIS2;
	}

	public void setOldCodeFacilitysDHIS2(String oldCodeFacilitysDHIS2) {
		this.oldCodeFacilitysDHIS2 = oldCodeFacilitysDHIS2;
	}

	public String getOldFacilityName() {
		return oldFacilityName;
	}

	public void setOldFacilityName(String oldFacilityName) {
		this.oldFacilityName = oldFacilityName;
	}

	public int getUniqueFacilitysId() {
		return uniqueFacilitysId;
	}

	public void setUniqueFacilitysId(int uniqueFacilitysId) {
		this.uniqueFacilitysId = uniqueFacilitysId;
	}

	public String getNewFacilitysLongName() {
		return newFacilitysLongName;
	}

	public void setNewFacilitysLongName(String newFacilitysLongName) {
		this.newFacilitysLongName = newFacilitysLongName;
	}

	public String getNewFacilitysShortName() {
		return newFacilitysShortName;
	}

	public void setNewFacilitysShortName(String newFacilitysShortName) {
		this.newFacilitysShortName = newFacilitysShortName;
	}

	public String getStatutId() {
		return statutId;
	}

	public void setStatutId(String statutId) {
		this.statutId = statutId;
	}

	public String getFacilitysCode() {
		return facilitysCode;
	}

	public void setFacilitysCode(String facilitysCode) {
		this.facilitysCode = facilitysCode;
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

	@Override
	public String toString() {
		return "Facilitys [id=" + id + ", code=" + code + ", name=" + name + ", codeSite=" + codeSite + ", nameSite="
				+ nameSite + ", codeSiteDatim=" + codeSiteDatim + ", nameSiteDatim=" + nameSiteDatim
				+ ", oldCodeFacilitysDHIS2=" + oldCodeFacilitysDHIS2 + ", oldFacilityName=" + oldFacilityName
				+ ", uniqueFacilitysId=" + uniqueFacilitysId + ", newFacilitysLongName=" + newFacilitysLongName
				+ ", newFacilitysShortName=" + newFacilitysShortName + ", statutId=" + statutId + ", facilitysCode="
				+ facilitysCode + ", district=" + district + ", patients=" + patients + "]";
	} 
	
	
}
