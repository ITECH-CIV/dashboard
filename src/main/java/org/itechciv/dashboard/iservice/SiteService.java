package org.itechciv.dashboard.iservice;

import java.util.List;

import org.itechciv.dashboard.model.Site;

public interface SiteService extends GenericService<Site, Long> {
	
	Site getByCode(int code);
	Site findSiteByOldCode(String code); 
	Site findSiteByOldName(String name); 
	Site findSiteByUniqueCode(int code);
	Site findSiteByNewLongName(String name); 
	Site findSiteByShortName(String name); 
	Site findSiteByStatutId(String name); 
	List<Site> findAllSite();
	List<Site> findSiteByDistrictId(Long districtId);




}
