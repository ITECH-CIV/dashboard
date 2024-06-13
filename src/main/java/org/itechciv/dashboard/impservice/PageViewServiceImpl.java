package org.itechciv.dashboard.impservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.itechciv.dashboard.dto.PageViewDto;
import org.itechciv.dashboard.helper.CategoryAge;
import org.itechciv.dashboard.iservice.PageViewService;
import org.itechciv.dashboard.model.Page;
import org.itechciv.dashboard.model.PageView;
import org.itechciv.dashboard.repository.PageRepository;
import org.itechciv.dashboard.repository.PageViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PageViewServiceImpl extends GenericServiceImpl<PageView, Long> implements PageViewService {

@Autowired
private PageRepository pageRepository;

@Autowired
private PageViewRepository pageViewRepository;

public boolean isUniqueView(String visitorIp, Long pageId) {

	List<PageView> res = new ArrayList<PageView>();
  
    try{

        res = pageViewRepository.findListViewForOneVisitor(pageId, visitorIp);

        if(res == null){
            return true;
           }else{
               return false;
           }
       }catch(Exception ex){
        ex.printStackTrace();
        return false;
       }
} 

PageView savePageView(PageViewDto pageViewDto){


    String ipVisitor = pageViewDto.getVisitorIp();
    String pageLabel = pageViewDto.getLabelPage();
    Page page =  new Page();
    PageView pageView = new PageView();
    PageView res = null;
    Long pageId = null;

    try{

        page = pageRepository.findPageByLabel(pageLabel);

        if(page != null){
            pageId = page.getId();

           boolean checkUniqueView = isUniqueView(ipVisitor, pageId) == true;

           if(checkUniqueView){

            pageView.setVisitorIp(ipVisitor);
            pageView.setPage(page);
            pageView.setViewDate(new Date());

            res = pageViewRepository.save(pageView);

            if(res != null){
                pageViewRepository.updateTotalViews(pageId, page.getLabel() , page.getUrl());
            }

           }

        }
   
    }catch(Exception ex){
        ex.printStackTrace();

    }
}


    
}
