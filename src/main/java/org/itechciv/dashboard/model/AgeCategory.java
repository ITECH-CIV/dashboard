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
@Table(name = "age_category", schema = "dashboard")
public class AgeCategory  {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
	 
	  @Column(name="label")
	  private String label;

      @Column(name="type")
      private String type;

	   
	  @Column(name="min_cat", nullable = true)
	  private int minCat;
	  
	   
	  @Column(name="max_cat", nullable = true)
	  private int maxCat;
	  
	public AgeCategory() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AgeCategory [id=" + id + ", label=" + label + ", type=" + type + "]";
	}


	

	

}
