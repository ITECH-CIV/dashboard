 package org.itechciv.dashboard.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "age_category", schema = "dashboard")
public class AgeCategory  {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.UUID) 
	  private UUID id;
	  
	  @Column(name="label")
	  private String label;

      @Column(name="description")
      private String description;

}
