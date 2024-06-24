package org.itechciv.dashboard.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.itechciv.dashboard.dto.PageViewDto;
import org.itechciv.dashboard.helper.ResponseMessage;
import org.itechciv.dashboard.iservice.PageService;
import org.itechciv.dashboard.iservice.PageViewService;
import org.itechciv.dashboard.model.Page;
import org.itechciv.dashboard.model.PageView;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pageview")
@ResponseBody
@CrossOrigin
public class PageViewController {

    @Autowired
	private PageViewService pageViewService; 

    @RequestMapping(method = RequestMethod.POST, value="/save")
	@ResponseBody
	public Object savePageView(@RequestBody PageViewDto pageViewDto) {  
		
		Object result = null ; 
				
		result = pageViewService.savePageView(pageViewDto); 
			
		return result;	 
	} 
    
    
	@RequestMapping(method = RequestMethod.GET, value="/getAll")
	@ResponseBody
	public ResponseEntity<Response> getPageViews() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = pageViewService.getAll(); 
			
             if(res!=null) { 
				
				
				result= new ResponseEntity<>(res, HttpStatus.OK);
				
			} else { 
				
				result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				
			} 
			
		}catch(Exception ex) { 
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result =new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
	
		}
		return result;
	}
	

	@RequestMapping(method = RequestMethod.GET, value="/getById")
	@ResponseBody
	public ResponseEntity<Response> getPageViewById(String id) { 
		
		Response res = new Response(); 
		
		Optional<PageView> pageView;
		ResponseEntity<Response> result;
		
		try {
		
			pageView= pageViewService.getOne(Long.parseLong(id)); 
		
		 if(pageView!=null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,pageView,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Page introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}


	@RequestMapping(method = RequestMethod.DELETE, value="/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deletePage(String id) { 
		
		Response res = new Response(); 
		
		Optional<PageView> pageView;
		ResponseEntity<Response> result;
		
		try {
		
			pageView = pageViewService.getOne(Long.parseLong(id)); 
		
		 if(pageView!=null) {
			 
            pageViewService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,pageView,"Suppression effectuée", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Suppression échouée", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}


    @GetMapping("/listviewforcurrentdate")
    public List<PageView> findListViewForLastTime() {
        return pageViewService.findListViewForLastTime();
    }




	



}
