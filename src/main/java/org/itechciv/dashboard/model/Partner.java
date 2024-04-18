package org.itechciv.dashboard.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "partner", schema = "dashboard")
public class Partner {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "code")
    private String code;
    
    @OneToMany(mappedBy = "partner")
	private List<SitePartner> sitepartners;

	public Partner() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<SitePartner> getSitepartners() {
		return sitepartners;
	}

	public void setSitepartners(List<SitePartner> sitepartners) {
		this.sitepartners = sitepartners;
	}

	@Override
	public String toString() {
		return "Partner [id=" + id + ", name=" + name + ", code=" + code + ", sitepartners=" + sitepartners + "]";
	}
}
