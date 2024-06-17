package org.itechciv.dashboard.repository;

import java.util.List;

import org.itechciv.dashboard.model.Page;
import org.itechciv.dashboard.model.PageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

	//Nombre total de vues pour toutes les pages - toute periode confondue
	@Query(value = " SELECT SUM(total_views) as total_views FROM dashboard.page ", nativeQuery = true)	
	long getTotalViews(); 

	//Nombre total de vues pour une page - toute periode confondue
	@Query(value = " SELECT SUM(total_views) as total_views " + 
			" FROM dashboard.page p" + 
			" WHERE p.id =?1", nativeQuery = true)	
	long getTotalViewsForOnePage(@Param("pageId") Long pageId);  


	//Nombre total de vues pour un mois choisi

	  //Pour toutes les pages 

	  //Pour une page 

	//Nombre total de vues pour le mois précédent  

	  //Pour toutes les pages 


	  //Pour une page


	@Query(value = "select * " + 
			" from dashboard.page pg" + 
			" where pg.label =?1", nativeQuery = true)	
	Page findPageByLabel(String label); 


	@Modifying(clearAutomatically = true)
    @Query(value = " UPDATE dashboard.page  " + 
			" SET total_views = total_views + 1 , label = :label, url = :url  " + 
			" WHERE id = :pageId ", nativeQuery = true)	
	Object updateTotalViews(@Param("pageId") Long pageId, @Param("label") String label, @Param("url") String url); 
	
}
