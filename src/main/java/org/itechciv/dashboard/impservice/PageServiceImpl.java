package org.itechciv.dashboard.impservice;


import org.itechciv.dashboard.iservice.PageService;
import org.itechciv.dashboard.model.Page;
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
	public long getTotalViewsForOnePage(String label){

        long total;

        try {
            total = pageRepository.getTotalViewsForOnePage(label);
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

@Override
public long getTotalViewsForAllPagesForOneMonth() {
	long total;

	try{
      total = pageRepository.getTotalViewsForAllPagesForOneMonth();
	}catch(Exception ex){
		ex.printStackTrace();
		return 0;
	}
	return total;

}


@Override
public long getTotalViewsForAllPagesOtherForOneMonth(int month, int year) {
	long total;

	try{
      total = pageRepository.getTotalViewsForAllPagesOtherForOneMonth(month, year);
	}catch(Exception ex){
		ex.printStackTrace();
		return 0;
	}
	return total;

}

@Override
public long getTotalViewsForOnePageForOneMonth(Long pageId) {
	long total;

	try{
		total = pageRepository.getTotalViewsForOnePageForOneMonth(pageId);
	}catch(Exception ex){
		ex.printStackTrace();
		return 0;
	}
	return total;
}


@Override
public long getTotalViewsForOnePageOtherForOneMonth(Long pageId, int month, int year) {
	long total;

	try{
		total = pageRepository.getTotalViewsForOnePageOtherForOneMonth(pageId, month, year);
	}catch(Exception ex){
		ex.printStackTrace();
		return 0;
	}
	return total;
}


@Override
public long getTotalViewsForAllPagesForPreviousMonth() {
	long total;
	try{
      total = pageRepository.getTotalViewsForAllPagesForPreviousMonth();
	}catch(Exception ex){
		ex.printStackTrace();
		return 0;
	}
	return total;
}


@Override
public long getTotalViewsOtherForAllPagesForPreviousMonth(int month, int year) {
	long total;
	try{
      total = pageRepository.getTotalViewsOtherForAllPagesForPreviousMonth(month, year);
	}catch(Exception ex){
		ex.printStackTrace();
		return 0;
	}
	return total;
}


@Override
public long getTotalViewsForOnePageForPreviousMonth(Long pageId) {
	long total;
	try{
      total = pageRepository.getTotalViewsForOnePageForPreviousMonth(pageId);
	}catch(Exception ex){
		ex.printStackTrace();
		return 0;
	}
	return total;
}



@Override
public long getTotalViewsOtherForOnePageForPreviousMonth(Long pageId, int month, int year) {
	long total;
	try{
      total = pageRepository.getTotalViewsOtherForOnePageForPreviousMonth(pageId, month, year);
	}catch(Exception ex){
		ex.printStackTrace();
		return 0;
	}
	return total;
}



}
