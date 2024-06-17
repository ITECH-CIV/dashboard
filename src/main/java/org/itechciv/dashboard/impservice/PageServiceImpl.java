package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.PageService;
import org.itechciv.dashboard.model.Page;
import org.itechciv.dashboard.model.Regimen;
import org.itechciv.dashboard.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PageServiceImpl extends GenericServiceImpl<Page, Long> implements PageService {

    @Autowired
    private PageRepository pageRepository;

    @Override
    public long getTotalViews() {

        long total;

        try {
            total = pageRepository.getTotalViews();
        } catch (Exception ex) {
            return 0;
        }
        return total;
    } 

	@Override
	public long getTotalViewsForOnePage(Long pageId){

        long total;

        try {
            total = pageRepository.getTotalViewsForOnePage(pageId);
        } catch (Exception ex) {
            return 0;
        }
        return total;

	} 

@Override
public Page getByLabel(String label) {
		
	Page p = new Page();  
		
		try 
		
		{ 
			p = pageRepository.findPageByLabel(label);
			
			if(p!=null)
			{ 
				return p;
			} 
			else 
			{ 
				return null;
			}
		}
		catch(Exception ex) { 
			ex.printStackTrace();
			return null;
		}	
	}




}
