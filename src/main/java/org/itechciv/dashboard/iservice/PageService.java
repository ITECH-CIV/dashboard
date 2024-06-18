package org.itechciv.dashboard.iservice;


import org.itechciv.dashboard.model.Page;

public interface PageService extends GenericService<Page, Long> {
    long getTotalViews();
    Page getByLabel(String label);
    long getTotalViewsForOnePage(Long pageId);  
    long getTotalViewsForAllPagesForOneMonth();
    long getTotalViewsForOnePageForOneMonth(Long pageId); 
    long getTotalViewsForAllPagesForPreviousMonth(); 
    long getTotalViewsForOnePageForPreviousMonth(Long pageId);  
 
 




}
