package org.itechciv.dashboard.repository;

import java.util.List;

import org.itechciv.dashboard.model.PageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageViewRepository extends JpaRepository<PageView, Long> {

   @Query(value = "SELECT * " + 
			" FROM dashboard.page_view pv" + 
			" WHERE pv.visitor_ip =?2 AND pv.page_id =?1", nativeQuery = true)	
	List<PageView> findListViewForOneVisitor(@Param("pageId") Long pageId, @Param("visitorIp") String visitorIp);  


    @Query(value = " UPDATE dashboard.page p " + 
			" SET p.totalViews = p.totalViews + 1 " + 
			" WHERE p.id =?1 AND p.label =?2 and p.url =?3", nativeQuery = true)	
	List<PageView> updateTotalViews(@Param("pageId") Long pageId, @Param("label") String label, @Param("url") String url);  

	
}
