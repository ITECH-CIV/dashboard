package org.itechciv.dashboard.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.itechciv.dashboard.enums.Gender;

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
@Table(name = "patient", schema = "dashboard")
public class Patient {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.UUID) 
	 private UUID id;

	 @Column(name = "gender")
     private Gender gender;

     @Column(name = "birth_date")
     private Date birthDate;
     
     @Column(name = "identity_data")
     private String identityData;

     @Column(name = "upid_code")
     private String upidCode;

     @Column(name = "national_code")
     private String nationalCode;
     
     @ManyToOne
	 @JoinColumn(name ="facilitysId", nullable = false)
	 private Facilitys facilitys; 
     
     @OneToMany(mappedBy = "patient")
	 private List<Analysis> analysis = new ArrayList<>();

}
