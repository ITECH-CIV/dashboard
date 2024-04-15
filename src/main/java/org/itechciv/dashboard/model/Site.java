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
@Table(name = "site", schema = "dashboard")
public class Site {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

	  @Column(name = "code")
	  private String code;
	  
	  @Column(name = "name")
	  private String name;
	  
	  @Column(name = "code_site")
	  private int codeSite;
	  
	  @Column(name = "name_site", nullable= true)
	  private String nameSite;
	  
	  @Column(name = "code_site_datim", nullable= true)
	  private String codeSiteDatim;
	  
	  @Column(name = "name_site_datim", nullable= true)
	  private String nameSiteDatim;
	  
	  @Column(name = "old_code_site_dhis2")
	  private String oldCodeSiteDHIS2;
	  
	  @Column(name = "old_site_name")
	  private String oldSiteName;
	  
	  @Column(name = "unique_site_id")
	  private int uniqueSiteId;
	  
	  @Column(name = "new_site_long_name")
	  private String newSiteLongName;
	  
	  @Column(name = "new_site_short_name")
	  private String newSiteShortName;
	  
	  @Column(name = "statut_id")
	  private String statutId;
	  
	  @Column(name = "siteCode")
	  private String siteCode;
	    
	  @ManyToOne
	  @JoinColumn(name ="districtId", nullable = true)
	  private District district; 
	  
	  @OneToMany(mappedBy = "site")
	  private List<Patient> patients = new ArrayList<>();

	public Site() {
		super();
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

	public String getOldCodeSiteDHIS2() {
		return oldCodeSiteDHIS2;
	}

	public void setOldCodeSiteDHIS2(String oldCodeSiteDHIS2) {
		this.oldCodeSiteDHIS2 = oldCodeSiteDHIS2;
	}

	public String getOldSiteName() {
		return oldSiteName;
	}

	public void setOldSiteName(String oldSiteName) {
		this.oldSiteName = oldSiteName;
	}

	public int getUniqueSiteId() {
		return uniqueSiteId;
	}

	public void setUniqueSiteId(int uniqueSiteId) {
		this.uniqueSiteId = uniqueSiteId;
	}

	public String getNewSiteLongName() {
		return newSiteLongName;
	}

	public void setNewSiteLongName(String newSiteLongName) {
		this.newSiteLongName = newSiteLongName;
	}

	public String getNewSiteShortName() {
		return newSiteShortName;
	}

	public void setNewSiteShortName(String newSiteShortName) {
		this.newSiteShortName = newSiteShortName;
	}

	public String getStatutId() {
		return statutId;
	}

	public void setStatutId(String statutId) {
		this.statutId = statutId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
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
		return "Site [id=" + id + ", code=" + code + ", name=" + name + ", codeSite=" + codeSite + ", nameSite="
				+ nameSite + ", codeSiteDatim=" + codeSiteDatim + ", nameSiteDatim=" + nameSiteDatim
				+ ", oldCodeSiteDHIS2=" + oldCodeSiteDHIS2 + ", oldSiteName=" + oldSiteName + ", uniqueSiteId="
				+ uniqueSiteId + ", newSiteLongName=" + newSiteLongName + ", newSiteShortName=" + newSiteShortName
				+ ", statutId=" + statutId + ", siteCode=" + siteCode + ", district=" + district + ", patients="
				+ patients + "]";
	}	
}
