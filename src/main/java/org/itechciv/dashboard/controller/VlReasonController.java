package org.itechciv.dashboard.controller;

import java.util.Optional;
import org.itechciv.dashboard.iservice.VlReasonService;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.model.VlReason;
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
public class VlReasonController {
	
	@Autowired
	private VlReasonService vlReasonService;
	
	@RequestMapping(method = RequestMethod.POST, value="vlreason/save")
	@ResponseBody
	public ResponseEntity<Response> saveVlReason(@RequestBody VlReason vlReason) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			
			res =  vlReasonService.create(vlReason) ; 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="vlreason/getAll")
	@ResponseBody
	public ResponseEntity<Response> getVlReasonAll() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = vlReasonService.getAll(); 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="vlreason/getById")
	@ResponseBody
	public ResponseEntity<Response> getVlReason(String id) { 
		
		Response res = new Response(); 
		
		Optional<VlReason> vlReason;
		ResponseEntity<Response> result;
		
		try {
		
			vlReason= vlReasonService.getOne(Long.parseLong(id)); 
		
		 if(vlReason!=null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,vlReason,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Vl reason introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(value ="vlreason/update", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateVlReason(@RequestBody VlReason vl, String id) {
		
		Response res = new Response();
		Optional<VlReason> vlReason; 
		ResponseEntity<Response> result;
		
		try  {
			
			vlReason = vlReasonService.getOne(Long.parseLong(id)); 
			 
			 if(vlReason!=null) 
				 
			 { 
				 VlReason vr = vlReason.get();
					
				 vr.setName(vl.getName()); 
					 
				 res = vlReasonService.update(vr); 
				 
				 if(res!=null) 
				 {
					 result =new ResponseEntity<>(res, HttpStatus.OK);
				 }
				 else 
					 
				 { 
					 result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				 }
				 
			 } else {
			 
				 res=  new  Response(ResponseStatusEnum.ERROR,null,"Vl reason introuvable", false); 
			     result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
			 }
		
		} catch(Exception ex) { 
			res=  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result= new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="vlreason/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deleteVlReason(String id) { 
		
		Response res = new Response(); 
		
		Optional<VlReason> vlReason;
		ResponseEntity<Response> result;
		
		try {
		
			vlReason= vlReasonService.getOne(Long.parseLong(id)); 
		
		 if(vlReason!=null) {
			 
			 vlReasonService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,vlReason,"Suppression effectuée", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Suppression échouée", false); 
	        result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	} 
	

	@RequestMapping(method = RequestMethod.GET, value="vlreason/getByName")
	@ResponseBody
	public ResponseEntity<Response> getVlReasonByName(String name) { 
		
		Response res = new Response(); 
		
		VlReason vlReason;
		ResponseEntity<Response> result;
		
		try {
		
			vlReason= vlReasonService.getByName(name); 
		
		 if(vlReason != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,vlReason,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Vl reason introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	} 
	

}
