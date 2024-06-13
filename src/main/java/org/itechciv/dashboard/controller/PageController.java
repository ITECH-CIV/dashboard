package org.itechciv.dashboard.controller;

import java.util.Optional;

import org.itechciv.dashboard.iservice.AnalysisService;
import org.itechciv.dashboard.iservice.PageService;
import org.itechciv.dashboard.model.Lab;
import org.itechciv.dashboard.model.Page;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
@ResponseBody
@CrossOrigin
public class PageController {


	@Autowired
	private PageService pageService;


	@RequestMapping(method = RequestMethod.POST, value="/save")
	@ResponseBody
	public ResponseEntity<Response> savePage(@RequestBody Page page) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			
			res =  pageService.create(page) ; 
			
			   if(res!=null) { 
					
					result= new ResponseEntity<>(res, HttpStatus.OK);
					
				} else 
				{
					result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				}	
				
			} 
			catch (Exception ex) { 
				
				res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
				result= new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
			} 
		return result;
	} 
    
    
	@RequestMapping(method = RequestMethod.GET, value="/getAll")
	@ResponseBody
	public ResponseEntity<Response> getPages() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = pageService.getAll(); 
			
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
	public ResponseEntity<Response> getPageById(String id) { 
		
		Response res = new Response(); 
		
		Optional<Page> page;
		ResponseEntity<Response> result;
		
		try {
		
			page= pageService.getOne(Long.parseLong(id)); 
		
		 if(page!=null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,page,"Enregistrement trouvé", true);
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


	@RequestMapping(value ="/update", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateLab(@RequestBody Page pg, String id) {
		
		Response res = new Response();
		Optional<Page> page; 
		ResponseEntity<Response> result;
		
		try  {
			
			page = pageService.getOne(Long.parseLong(id)); 
			 
			 if(page!=null) 
				 
			 { 
				 Page pag = page.get();
					
				 pag.setTotalViews(pg.getTotalViews()); 
					 
				 res = pageService.update(pag); 
				 
				 if(res!=null) 
				 {
					 result =new ResponseEntity<>(res, HttpStatus.OK);
				 }
				 else 
					 
				 { 
					 result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				 }
				 
			 } else {
			 
				 res=  new  Response(ResponseStatusEnum.ERROR,null,"Lab introuvable", false); 
			     result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
			 }
		
		} catch(Exception ex) { 
			res=  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result= new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		return result;
	}


	@RequestMapping(method = RequestMethod.DELETE, value="/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deletePage(String id) { 
		
		Response res = new Response(); 
		
		Optional<Page> page;
		ResponseEntity<Response> result;
		
		try {
		
			page = pageService.getOne(Long.parseLong(id)); 
		
		 if(page!=null) {
			 
            pageService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,page,"Suppression effectuée", true);
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


    @GetMapping("/totalviews")
    public long getTotalViews() {
        return pageService.getTotalViews();
    } 


    @GetMapping("/getByLabel")
    public Page getByLabel(String label) {
        return pageService.getByLabel(label);
    }
	
	

}
