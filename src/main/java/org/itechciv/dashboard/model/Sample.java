package org.itechciv.dashboard.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "sample", schema = "dashboard")
public class Sample  {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
	private UUID id;
	
    @Column(name="label")
    private String label;
    
    @Column(name="description")
    private String description;
    
    @ManyToOne
  	@JoinColumn(name ="sampleTypeId", nullable = false)
  	private SampleType sampleType; 
    
    @ManyToOne
  	@JoinColumn(name ="analysisId", nullable = false)
  	private Analysis analysis; 
 
}
