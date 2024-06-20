package org.itechciv.dashboard.impservice;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
@Transactional
public class PageViewServiceImpl extends GenericServiceImpl<PageView, Long> implements PageViewService {

@Autowired
private PageRepository pageRepository;

@Autowired
private PageViewRepository pageViewRepository;

@PersistenceContext
private EntityManager em;


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

public Object savePageView(PageViewDto pageViewDto){


    String ipVisitor = pageViewDto.getVisitorIp();
    String pageLabel = pageViewDto.getLabelPage();
    String pageUrl = pageViewDto.getPageUrl();
    Page page =  null;
    Page inPage = null;
    Page result = new Page();
    PageView pageView = new PageView();
    Object res = null;
    Long pageId = null;
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int month = calendar.get(Calendar.MONTH) + 1;
    int year = calendar.get(Calendar.YEAR);

    PageView resultat = null;



    try{

        page = pageRepository.findPageByLabel(pageLabel);

        if(page == null){

            inPage = new Page();
            inPage.setLabel(pageLabel);  
            inPage.setUrl(pageUrl);  
            inPage.setTotalViews(1);
            inPage.setMonth(month);
            inPage.setYear(year);

            page = pageRepository.save(inPage);
  
            pageView.setPage(page);
            pageView.setVisitorIp(ipVisitor);
            pageView.setViewDate(new Date());
            pageView.setNbVisit(1);

        res = pageViewRepository.save(pageView);
          
        }else{
            pageId = page.getId();
            System.out.println("page-id:" +pageId );

            resultat = pageViewRepository.findFirstVisitForOnePage(ipVisitor, pageId);
            
            System.out.println("resultat:" +resultat );

            if(resultat == null){

                pageRepository.updateTotalViews(pageId, page.getLabel(), page.getUrl(), page.getMonth(), page.getYear());

                pageView.setPage(page);
                pageView.setVisitorIp(ipVisitor);
                pageView.setViewDate(new Date());
                pageView.setNbVisit(1);

                res = pageViewRepository.save(pageView);
               
            }else{

                pageRepository.updateTotalViews(pageId, page.getLabel(), page.getUrl(), page.getMonth(), page.getYear());

                res = pageViewRepository.updateViews(pageId, ipVisitor, date); 

            }
        }
    }catch(Exception ex){
        ex.printStackTrace();
    }
    return res;
}

    
}
