package org.itechciv.dashboard.model;

import java.util.ArrayList;
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
@Table(name = "facilitys", schema = "dashboard")
public class Facilitys {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.UUID) 
	  private UUID id;

	  @Column(name = "code")
	  private String code;
	  
	  @Column(name = "name")
	  private String name;
	  
	  @ManyToOne
	  @JoinColumn(name ="districtId", nullable = false)
	  private District district; 
	  
	  @OneToMany(mappedBy = "facilitys")
	  private List<Patient> patients = new ArrayList<>();


}
