package org.itechciv.dashboard.controller;
import java.util.Optional;

import org.itechciv.dashboard.iservice.PartnerService;
import org.itechciv.dashboard.iservice.RegimenService;
import org.itechciv.dashboard.iservice.VihTypeService;
import org.itechciv.dashboard.model.Partner;
import org.itechciv.dashboard.model.Regimen;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.model.VihType;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PartnerController {
	
	@Autowired
	private PartnerService partnerService;
	
	
	@RequestMapping(method = RequestMethod.POST, value="partner/save")
	@ResponseBody
	public ResponseEntity<Response> savePartner(@RequestBody Partner partner) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			
			res =  partnerService.create(partner) ; 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="partner/getAll")
	@ResponseBody
	public ResponseEntity<Response> getPartner() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = partnerService.getAll(); 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="partner/getById")
	@ResponseBody
	public ResponseEntity<Response> getPartner(String id) { 
		
		Response res = new Response(); 
		
		Optional<Partner> partner;
		ResponseEntity<Response> result;
		
		try {
		
			partner= partnerService.getOne(Long.parseLong(id)); 
		
		 if(partner!=null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,partner,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Partner introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(value ="partner/update", method = RequestMethod.PUT)
	public ResponseEntity<Response> updatePartner(@RequestBody Partner pt, String id) {
		
		Response res = new Response();
		Optional<Partner> partner; 
		ResponseEntity<Response> result;
		
		try  {
			
			partner = partnerService.getOne(Long.parseLong(id)); 
			 
			 if(partner!=null) 
				 
			 { 
				 Partner ptn = partner.get();
					
				 ptn.setName(pt.getName()); 
				 ptn.setCode(pt.getCode());
					 
				 res = partnerService.update(ptn); 
				 
				 if(res!=null) 
				 {
					 result =new ResponseEntity<>(res, HttpStatus.OK);
				 }
				 else 
					 
				 { 
					 result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				 }
				 
			 } else {
			 
				 res=  new  Response(ResponseStatusEnum.ERROR,null,"Partner introuvable", false); 
			     result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
			 }
		
		} catch(Exception ex) { 
			res=  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result= new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="partner/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deletePartner(String id) { 
		
		Response res = new Response(); 
		
		Optional<Partner> partner;
		ResponseEntity<Response> result;
		
		try {
		
			partner= partnerService.getOne(Long.parseLong(id)); 
		
		 if(partner!=null) {
			 
			 partnerService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,partner,"Suppression effectuée", true);
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
}
