package org.itechciv.dashboard.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "analysis", schema = "dashboard")
public class Analysis {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.UUID) 
	  private UUID id;
	  
	  @Column(name = "started_date")
	  private Date startedDate;

	  @Column(name = "completed_date")
	  private Date completedDate;
	  
	  @ManyToOne
	  @JoinColumn(name ="testId", nullable = false)
	  private Test test; 
	  
	  @ManyToOne
	  @JoinColumn(name ="patientId", nullable = false)
	  private Patient patient;  
	  
	  @OneToMany(mappedBy = "analysis")
	  private List<Sample> samples = new ArrayList<>();
	  
	  

}
