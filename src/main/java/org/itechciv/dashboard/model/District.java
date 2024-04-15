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
	    
	    @ManyToOne
		@JoinColumn(name ="regionId", nullable = false)
		private Region region; 
	    
	    @OneToMany(mappedBy = "district")
		private List<Site> sites = new ArrayList<>();

		public District() {
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

		public Region getRegion() {
			return region;
		}

		public void setRegion(Region region) {
			this.region = region;
		}

		public List<Site> getSites() {
			return sites;
		}

		public void setSites(List<Site> sites) {
			this.sites = sites;
		}

		@Override
		public String toString() {
			return "District [id=" + id + ", name=" + name + ", region=" + region + ", sites=" + sites + "]";
		}
}
