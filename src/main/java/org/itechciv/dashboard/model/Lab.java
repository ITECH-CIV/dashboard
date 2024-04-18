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
@Table(name = "lab", schema = "dashboard")
public class Lab {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

	 @Column(name = "name")
     private String name;
	 
	 @Column(name = "prefix")
	 private String prefix;
	 
	 @OneToMany(mappedBy = "lab")
	 private List<Analysis> analysises = new ArrayList<>();

	public Lab() {
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public List<Analysis> getAnalysises() {
		return analysises;
	}

	public void setAnalysises(List<Analysis> analysises) {
		this.analysises = analysises;
	}

	@Override
	public String toString() {
		return "Lab [id=" + id + ", name=" + name + ", prefix=" + prefix + ", analysises=" + analysises + "]";
	}	
}
