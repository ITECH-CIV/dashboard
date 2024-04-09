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
@Table(name = "diet", schema = "dashboard")
public class Diet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "current1", nullable = true)
    private String current1;
    
    @Column(name = "current2", nullable = true)
    private String current2;
    
    @Column(name = "current3", nullable = true)
    private String current3;
    
    @Column(name = "current4" , nullable = true)
    private String current4; 
    
    @OneToMany(mappedBy = "diet")
	private List<Analysis> analysis = new ArrayList<>();

	public Diet() {
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

	public String getCurrent1() {
		return current1;
	}

	public void setCurrent1(String current1) {
		this.current1 = current1;
	}

	public String getCurrent2() {
		return current2;
	}

	public void setCurrent2(String current2) {
		this.current2 = current2;
	}

	public String getCurrent3() {
		return current3;
	}

	public void setCurrent3(String current3) {
		this.current3 = current3;
	}

	public String getCurrent4() {
		return current4;
	}

	public void setCurrent4(String current4) {
		this.current4 = current4;
	}

	public List<Analysis> getAnalysis() {
		return analysis;
	}

	public void setAnalysis(List<Analysis> analysis) {
		this.analysis = analysis;
	}
}
