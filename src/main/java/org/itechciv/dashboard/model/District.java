package org.itechciv.dashboard.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "district", schema = "dashboard")
public class District {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		    
	    @Column(name="name")
	    private String name;
	    
	    @Column(name="sequence_district")
	    private int sequenceDistrict;
	    
	    @ManyToOne
		@JoinColumn(name ="regionId", nullable = false)
		private Region region; 
	    
	    @OneToMany(mappedBy = "district")
		private List<Facilitys> facilitys = new ArrayList<>();

		public District() {
			super();
		}

		public District(String name, int sequenceDistrict, Region region, List<Facilitys> facilitys) {
			super();
			this.name = name;
			this.sequenceDistrict = sequenceDistrict;
			this.region = region;
			this.facilitys = facilitys;
		}

		public District(Long id, String name, int sequenceDistrict, Region region, List<Facilitys> facilitys) {
			super();
			this.id = id;
			this.name = name;
			this.sequenceDistrict = sequenceDistrict;
			this.region = region;
			this.facilitys = facilitys;
		}	
}
