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
@Table(name = "test", schema = "dashboard")
public class Test {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID) 
	private UUID id;

    @Column(name="date")
    private Date date;
    
    @OneToMany(mappedBy = "test")
	private List<Analysis> analysis = new ArrayList<>();


}
