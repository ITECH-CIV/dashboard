package org.itechciv.dashboard.controller;

import java.util.Optional;

import org.itechciv.dashboard.iservice.AgeCategoryService;
import org.itechciv.dashboard.model.AgeCategory;
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
public class AgeCategoryController {
	
	@Autowired
	private AgeCategoryService ageCategoryService;
	
	@RequestMapping(method = RequestMethod.POST, value="agecategory/save")
	@ResponseBody
	public ResponseEntity<Response> saveAgeCategory(@RequestBody AgeCategory ageCategory) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			
			res =  ageCategoryService.create(ageCategory) ; 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="agecategory/getAll")
	@ResponseBody
	public ResponseEntity<Response> getAgeCategoryAll() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = ageCategoryService.getAll(); 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="agecategory/getById")
	@ResponseBody
	public ResponseEntity<Response> getAgeCategoryOne(String id) { 
		
		Response res = new Response(); 
		
		Optional<AgeCategory> agecategory;
		ResponseEntity<Response> result;
		
		try {
		
			agecategory= ageCategoryService.getOne(Long.parseLong(id)); 
		
		 if(agecategory!=null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,agecategory,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Age category introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(value ="agecategory/update", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateAgeCategory(@RequestBody AgeCategory ageCat, String id) {
		
		Response res = new Response();
		Optional<AgeCategory> ageCategory ; 
		ResponseEntity<Response> result;
		
		try  {
			
			ageCategory = ageCategoryService.getOne(Long.parseLong(id)); 
			 
			 if(ageCategory!=null) 
				 
			 { 
				 AgeCategory ac = ageCategory.get();
					
				ac.setLabel(ageCat.getLabel()); 
				ac.setDescription(ageCat.getDescription());
					 
				 res = ageCategoryService.update(ac); 
				 
				 if(res!=null) 
				 {
					 result =new ResponseEntity<>(res, HttpStatus.OK);
				 }
				 else 
					 
				 { 
					 result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				 }
				 
			 } else {
			 
				 res=  new  Response(ResponseStatusEnum.ERROR,null,"Age category introuvable", false); 
			     result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
			 }
		
		} catch(Exception ex) { 
			res=  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result= new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="agecategory/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deleteAgeCategory(String id) { 
		
		Response res = new Response(); 
		
		Optional<AgeCategory> agecategory;
		ResponseEntity<Response> result;
		
		try {
		
			agecategory= ageCategoryService.getOne(Long.parseLong(id)); 
		
		 if(agecategory!=null) {
			 
			 ageCategoryService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,agecategory,"Suppression effectuée", true);
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
