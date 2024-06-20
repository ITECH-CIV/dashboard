package org.itechciv.dashboard.repository;

import java.util.Date;
import java.util.List;

import org.itechciv.dashboard.model.Page;
import org.itechciv.dashboard.model.PageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageViewRepository extends JpaRepository<PageView, Long> {

   @Query(value = "SELECT * " + 
			" FROM dashboard.page_view pv" + 
			" WHERE pv.visitor_ip =?2 AND pv.page_id =?1", nativeQuery = true)	
	List<PageView> findListViewForOneVisitor(@Param("pageId") Long pageId, @Param("visitorIp") String visitorIp);  


    @Query(value = " UPDATE dashboard.page as p " + 
			" SET p.total_views = p.total_views + 1 " + 
			" WHERE p.id =?1 AND p.label =?2 and p.url =?3", nativeQuery = true)	
	void updateTotalViews(@Param("pageId") Long pageId, @Param("label") String label, @Param("url") String url); 


	@Modifying(clearAutomatically = true)
    @Query(value = " UPDATE dashboard.page_view  " + 
			" SET nb_visit = nb_visit + 1 , view_date = :date , visitor_ip = :visitorIp, page_id = :pageId " + 
			" WHERE page_id = :pageId AND visitor_ip = :visitorIp ", nativeQuery = true)	
	Object updateViews(@Param("pageId") Long pageId, @Param("visitorIp") String visitorIp, @Param("date") Date date); 
	
	
	
    @Query(value = " INSERT INTO dashboard.page p " + 
			" SET p.total_views = p.total_views + 1 " + 
			" WHERE p.id =?1 AND p.label =?2 and p.url =?3", nativeQuery = true)	
	PageView addView(@Param("label") String label, @Param("url") String url, @Param("view") int view);  

	@Query(value = "select * " + 
			" from dashboard.page_view pv" + 
			" where pv.visitor_ip =?1 and pv.page_id =?2", nativeQuery = true)	
	PageView findFirstVisitForOnePage(@Param("addressIp") String addressIp, @Param("pageId") Long pageId ); 



}
