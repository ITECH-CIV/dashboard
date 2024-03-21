package org.itechciv.dashboard.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "region", schema = "dashboard")
public class Region {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	 
	 @Column(name="name", unique = true)
	 private String name;
	 
	 @OneToMany(mappedBy = "region")
	 private List<District> districts = new ArrayList<>();

	public Region() {
		super();
	}

	public Region(String name, List<District> districts) {
		super();
		this.name = name;
		this.districts = districts;
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

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}	
}
