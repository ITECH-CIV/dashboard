package org.itechciv.dashboard.iservice;


import org.itechciv.dashboard.model.Page;

public interface PageService extends GenericService<Page, Long> {
    long getTotalViews();
    Page getByLabel(String label);
    long getTotalViewsForOnePage(String label);  
    long getTotalViewsForAllPagesForOneMonth();
    long getTotalViewsForAllPagesOtherForOneMonth(int month, int year);  
    long getTotalViewsForOnePageForOneMonth(Long pageId);
    long getTotalViewsForOnePageOtherForOneMonth(Long pageId, int month, int year);  
    long getTotalViewsForAllPagesForPreviousMonth(); 
    long getTotalViewsOtherForAllPagesForPreviousMonth(int month, int year);  
    long getTotalViewsForOnePageForPreviousMonth(Long pageId);
    long getTotalViewsOtherForOnePageForPreviousMonth(Long pageId, int month, int year);  
  
 
 




}
