package org.itechciv.dashboard.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "site_partner", schema = "dashboard")
public class SitePartner {
	
	  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
	  
	  @ManyToOne
	  @JoinColumn(name = "site_id")
	  private Site site;
	  
	  @ManyToOne
	  @JoinColumn(name = "partner_id")
	  private Partner partner;
	  
	  @Column(name = "date", nullable = true)
	  private Date date;

	  public SitePartner() {
		super();
	  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SitePartner [id=" + id + ", site=" + site + ", partner=" + partner + ", date=" + date + "]";
	} 
}
