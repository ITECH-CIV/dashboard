package org.itechciv.dashboard.repository;

import java.util.List;

import org.itechciv.dashboard.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
	
	@Query(value = "select s.*" + 
			" from dashboard.site s" + 
			" where s.code_site =?1", nativeQuery = true)	
	Site findSiteByCode(int code); 
	
	@Query(value = "select s.*" + 
			" from dashboard.site s" + 
			" where s.old_code_site_dhis2 =?1", nativeQuery = true)	
	Site findSiteByOldCode(String code); 
	
	@Query(value = "select s.*" + 
			" from dashboard.site s" + 
			" where s.old_site_name =?1", nativeQuery = true)	
	Site findSiteByOldName(String name); 
	
	@Query(value = "select s.*" + 
			" from dashboard.site s" + 
			" where s.unique_site_id =?1", nativeQuery = true)	
	Site findSiteByUniqueCode(int code);
	
	@Query(value = "select s.*" + 
			" from dashboard.site s" + 
			" where s.new_site_long_name =?1", nativeQuery = true)	
	Site findSiteByNewLongName(String name); 
	
	@Query(value = "select s.*" + 
			" from dashboard.site s" + 
			" where s.new_site_short_name =?1", nativeQuery = true)	
	Site findSiteByShortName(String name); 
	
	@Query(value = "select s.*" + 
			" from dashboard.site s" + 
			" where s.statutId =?1", nativeQuery = true)	
	Site findSiteByStatutId(String name); 
	
	@Query(value = "select s.*" + 
			" from dashboard.site s" + 
			" where s.code_site_datim =?1 and code_site_datim <> '' limit 1 ", nativeQuery = true)	
	Site findSiteByCodeSiteDatim(String code); 
	
	List<Site> findByCodeSiteDatim(String codeSiteDatim);


    @Query(value = "SELECT * FROM dashboard.site s WHERE s.district_id =?1", nativeQuery = true)
    List<Site> findSiteByDistrictId(@Param("districtId") Long districtId);


}
