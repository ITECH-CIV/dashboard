package org.itechciv.dashboard.controller;
import java.util.Optional;

import org.itechciv.dashboard.iservice.DietService;
import org.itechciv.dashboard.iservice.VihTypeService;
import org.itechciv.dashboard.model.Diet;
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
public class DietController {
	
	@Autowired
	private DietService dietService;
	
	@RequestMapping(method = RequestMethod.POST, value="diet/save")
	@ResponseBody
	public ResponseEntity<Response> saveDiet(@RequestBody Diet diet) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			
			res =  dietService.create(diet) ; 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="diet/getAll")
	@ResponseBody
	public ResponseEntity<Response> getDiet() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = dietService.getAll(); 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="diet/getById")
	@ResponseBody
	public ResponseEntity<Response> getDiet(String id) { 
		
		Response res = new Response(); 
		
		Optional<Diet> dt;
		ResponseEntity<Response> result;
		
		try {
		
			dt= dietService.getOne(Long.parseLong(id)); 
		
		 if(dt!=null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,dt,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Diet introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(value ="diet/update", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateDiet(@RequestBody Diet dt, String id) {
		
		Response res = new Response();
		Optional<Diet> diet; 
		ResponseEntity<Response> result;
		
		try  {
			
			diet = dietService.getOne(Long.parseLong(id)); 
			 
			 if(diet!=null) 
				 
			 { 
				 Diet d = diet.get();
					
				 d.setName(dt.getName()); 
					 
				 res = dietService.update(d); 
				 
				 if(res!=null) 
				 {
					 result =new ResponseEntity<>(res, HttpStatus.OK);
				 }
				 else 
					 
				 { 
					 result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				 }
				 
			 } else {
			 
				 res=  new  Response(ResponseStatusEnum.ERROR,null,"Diet introuvable", false); 
			     result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
			 }
		
		} catch(Exception ex) { 
			res=  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result= new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="diet/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deleteDiet(String id) { 
		
		Response res = new Response(); 
		
		Optional<Diet> diet;
		ResponseEntity<Response> result;
		
		try {
		
			diet= dietService.getOne(Long.parseLong(id)); 
		
		 if(diet!=null) {
			 
			 dietService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,diet,"Suppression effectuée", true);
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
	

	@RequestMapping(method = RequestMethod.GET, value="diet/getByName")
	@ResponseBody
	public ResponseEntity<Response> getDietName(String name) { 
		
		Response res = new Response(); 
		
		Diet diet;
		ResponseEntity<Response> result;
		
		try {
		
			diet= dietService.getByName(name); 
		
		 if(diet != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,diet,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Diet introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	} 
	
}
