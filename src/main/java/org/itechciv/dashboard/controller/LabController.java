package org.itechciv.dashboard.controller;
import java.util.Optional;

import org.itechciv.dashboard.iservice.LabService;
import org.itechciv.dashboard.iservice.RegimenService;
import org.itechciv.dashboard.iservice.VihTypeService;
import org.itechciv.dashboard.model.Lab;
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
public class LabController {
	
	@Autowired
	private LabService labService;
	
	@RequestMapping(method = RequestMethod.POST, value="lab/save")
	@ResponseBody
	public ResponseEntity<Response> saveLab(@RequestBody Lab lab) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			
			res =  labService.create(lab) ; 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="lab/getAll")
	@ResponseBody
	public ResponseEntity<Response> getLab() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = labService.getAll(); 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="lab/getById")
	@ResponseBody
	public ResponseEntity<Response> getLabById(String id) { 
		
		Response res = new Response(); 
		
		Optional<Lab> reg;
		ResponseEntity<Response> result;
		
		try {
		
			reg= labService.getOne(Long.parseLong(id)); 
		
		 if(reg!=null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,reg,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Lab introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(value ="lab/update", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateLab(@RequestBody Lab lb, String id) {
		
		Response res = new Response();
		Optional<Lab> lab; 
		ResponseEntity<Response> result;
		
		try  {
			
			lab = labService.getOne(Long.parseLong(id)); 
			 
			 if(lab!=null) 
				 
			 { 
				 Lab l = lab.get();
					
				 l.setName(lb.getName()); 
					 
				 res = labService.update(l); 
				 
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
	
	@RequestMapping(method = RequestMethod.DELETE, value="lab/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deleteLab(String id) { 
		
		Response res = new Response(); 
		
		Optional<Lab> lab;
		ResponseEntity<Response> result;
		
		try {
		
			lab= labService.getOne(Long.parseLong(id)); 
		
		 if(lab!=null) {
			 
			 labService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,lab,"Suppression effectuée", true);
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
