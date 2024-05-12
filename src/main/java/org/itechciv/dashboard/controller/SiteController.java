package org.itechciv.dashboard.controller;

import java.util.List;
import java.util.Optional;

import org.itechciv.dashboard.iservice.SiteService;
import org.itechciv.dashboard.model.Site;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/site")
@ResponseBody
@CrossOrigin
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping(method = RequestMethod.GET, value="/getAll")
	@ResponseBody
	public ResponseEntity<Response> getSiteAll() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = siteService.getAll(); 
			
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
	public ResponseEntity<Response> getSiteOne(String id) { 
		
		Response res = new Response(); 
		
		Optional<Site> site;
		ResponseEntity<Response> result;
		
		try {
		
			site= siteService.getOne(Long.parseLong(id)); 
			
			//System.out.printf("Region:", region.toString());
	
		 if(site.isPresent()) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,site,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Site introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getByCode")
	@ResponseBody
	public ResponseEntity<Response> getSiteByCode(int code) { 
		
		Response res = new Response(); 
		
		Site s;
		ResponseEntity<Response> result;
		
		try {
		
			s= siteService.getByCode(code); 
		
		 if(s != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,s,"Site trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Site introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getByOldCode")
	@ResponseBody
	public ResponseEntity<Response> getSiteByOldCode(String code) { 
		
		Response res = new Response(); 
		
		Site s;
		ResponseEntity<Response> result;
		
		try {
		
			s= siteService.findSiteByOldCode(code); 
		
		 if(s != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,s,"Site trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Site introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getByOldName")
	@ResponseBody
	public ResponseEntity<Response> getSiteByOldName(String name) { 
		
		Response res = new Response(); 
		
		Site s;
		ResponseEntity<Response> result;
		
		try {
		
			s= siteService.findSiteByOldName(name); 
		
		 if(s != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,s,"Site trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Site introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}

	@GetMapping("/all")
    public ResponseEntity<List<Site>> getAllSite(){
        List<Site>site=siteService.findAllSite();
        return new ResponseEntity<>(site, HttpStatus.OK);
    }


    @GetMapping("/SiteByDistrict")
    public List<Site> findSiteByDistrictId(@RequestParam Long districtId) {
        return siteService.findSiteByDistrictId(districtId);
    }
		
}
