 package org.itechciv.dashboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "age_category", schema = "dashboard")
public class AgeCategory  {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
	 
	  @Column(name="label")
	  private String label;

      @Column(name="description")
      private String description;

	public AgeCategory() {
		super();
	}

	public AgeCategory(String label, String description) {
		super();
		this.label = label;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
