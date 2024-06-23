package org.itechciv.dashboard.repository;

import org.itechciv.dashboard.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    /*UNIQUEMENT AVEC LA TABLE PAGE */

	//Nombre total de vues pour toutes les pages - toute periode confondue
	@Query(value = " SELECT SUM(total_views) as total_views FROM dashboard.page ", nativeQuery = true)	
	long getTotalViews(); 

	//Nombre total de visites pour toutes les pages - le mois en cours

	//Nombre total de visites pour toutes les pages - le mois dernier

	//Nombre total de visites

	//Nombre total de vues pour une page - toute periode confondue
	@Query(value = " SELECT SUM(total_views) as total_views " + 
			" FROM dashboard.page p" + 
			" WHERE p.label =?1", nativeQuery = true)	
	long getTotalViewsForOnePage(@Param("name") String label);  

	//Nombre total de vues - mois en cours 

	  //Pour toutes les pages - Methode 1
	  @Query(value = " SELECT SUM(nb_visit) as total_views " + 
	  " FROM dashboard.page p " + 
	  " JOIN dashboard.page_view pv ON pv.page_id = p.id " +
	  " WHERE EXTRACT(month FROM pv.view_date) = EXTRACT(month FROM now()) ", nativeQuery = true)	
	long getTotalViewsForAllPagesForOneMonth();  

	  //Pour toutes les pages - Methode 2
	  @Query(value = " SELECT SUM(nb_visit) as total_views " + 
	  " JOIN dashboard.page_view pv ON pv.page_id = p.id " +
	  " FROM dashboard.page p " + 
	  " WHERE pv.month = :month AND pv.year = :year", nativeQuery = true)	
	long getTotalViewsForAllPagesOtherForOneMonth(@Param("month") int month, @Param("year") int year);  


    //Pour une page - Méthode 1
	@Query(value = " SELECT SUM(nb_visit) as total_views " + 
	" FROM dashboard.page p " + 
	" JOIN dashboard.page_view pv ON pv.page_id = p.id " +
	" WHERE EXTRACT(month FROM pv.view_date) = EXTRACT(month FROM now()) AND p.id = :pageId ", nativeQuery = true)	
  long getTotalViewsForOnePageForOneMonth(@Param("pageId") Long pageId);  

    //Pour une page - Méthode 2
  @Query(value = " SELECT SUM(total_views) as total_views " + 
	" FROM dashboard.page p " + 
	" WHERE p.id = :pageId AND p.month = :month AND p.year = :year", nativeQuery = true)	
  long getTotalViewsForOnePageOtherForOneMonth(@Param("pageId") Long pageId, @Param("month") int month, @Param("year") int year);  


	//Nombre total de vues pour le mois précédent  

	//Pour toutes les pages - Méthode 1
	@Query(value = " SELECT SUM(nb_visit) as total_views " + 
	" FROM dashboard.page p " + 
	" JOIN dashboard.page_view pv ON pv.page_id = p.id " +
	" WHERE EXTRACT(month FROM pv.view_date) = EXTRACT(MONTH FROM CURRENT_TIMESTAMP - INTERVAL '1' MONTH) ", nativeQuery = true)	
  long getTotalViewsForAllPagesForPreviousMonth(); 
  
  //Pour toutes les pages - Méthode 2
	@Query(value = " SELECT SUM(nb_visit) as total_views " + 
	" FROM dashboard.page p " + 
	" JOIN dashboard.page_view pv ON pv.page_id = p.id " +
	" WHERE p.id = :pageId AND p.month = :month AND p.year = :year ", nativeQuery = true)	
  long getTotalViewsOtherForAllPagesForPreviousMonth(@Param("pageId") Long pageId, @Param("month") int month, @Param("year") int year);  

	//Pour une page - Méthode 1
	@Query(value = " SELECT SUM(nb_visit) as total_views " + 
	" FROM dashboard.page p " + 
	" JOIN dashboard.page_view pv ON pv.page_id = p.id " +
	" WHERE EXTRACT(month FROM pv.view_date) = EXTRACT(MONTH FROM CURRENT_TIMESTAMP - INTERVAL '1' MONTH) AND p.id = :pageId ", nativeQuery = true)	
  long getTotalViewsForOnePageForPreviousMonth(@Param("pageId") Long pageId);  


	//Pour une page - Méthode 2
	@Query(value = " SELECT SUM(nb_visit) as total_views " + 
	" FROM dashboard.page p " + 
	" JOIN dashboard.page_view pv ON pv.page_id = p.id " +
	" WHERE p.id = :pageId AND p.month = :month, p.year = :year ", nativeQuery = true)	
  long getTotalViewsOtherForOnePageForPreviousMonth(@Param("pageId") Long pageId, @Param("month") int month, @Param("year") int year);  
  
  
	@Query(value = "select * " + 
			" from dashboard.page pg" + 
			" where pg.label =?1", nativeQuery = true)	
	Page findPageByLabel(String label); 

	@Modifying(clearAutomatically = true)
    @Query(value = " UPDATE dashboard.page  " + 
			" SET total_views = total_views + 1 , label = :label, url = :url , month = :month, year = :year " + 
			" WHERE id = :pageId ", nativeQuery = true)	
	Object updateTotalViews(@Param("pageId") Long pageId, @Param("label") String label, @Param("url") String url, @Param("month") int month, @Param("year") int year); 
	
}
