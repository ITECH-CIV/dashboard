package org.itechciv.dashboard.iservice;

import java.util.List;

import org.itechciv.dashboard.dto.PageViewDto;
import org.itechciv.dashboard.model.PageView;

public interface PageViewService extends GenericService<PageView, Long> {

    Object savePageView(PageViewDto pageViewDto);
    List<PageView> findListViewForLastTime(); 
 

}
