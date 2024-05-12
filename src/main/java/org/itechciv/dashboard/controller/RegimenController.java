package org.itechciv.dashboard.controller;
import java.util.Optional;

import org.itechciv.dashboard.iservice.RegimenService;
import org.itechciv.dashboard.model.Regimen;
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
public class RegimenController {
	
	@Autowired
	private RegimenService regimenService;
	
	@RequestMapping(method = RequestMethod.POST, value="regimen/save")
	@ResponseBody
	public ResponseEntity<Response> saveRegimen(@RequestBody Regimen regimen) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			
			res =  regimenService.create(regimen) ; 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="regimen/getAll")
	@ResponseBody
	public ResponseEntity<Response> getRegimen() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = regimenService.getAll(); 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="regimen/getById")
	@ResponseBody
	public ResponseEntity<Response> getRegimenById(String id) { 
		
		Response res = new Response(); 
		
		Optional<Regimen> reg;
		ResponseEntity<Response> result;
		
		try {
		
			reg= regimenService.getOne(Long.parseLong(id)); 
		
		 if(reg!=null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,reg,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Regimen introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(value ="regimen/update", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateRegimen(@RequestBody Regimen rg, String id) {
		
		Response res = new Response();
		Optional<Regimen> regimen; 
		ResponseEntity<Response> result;
		
		try  {
			
			regimen = regimenService.getOne(Long.parseLong(id)); 
			 
			 if(regimen!=null) 
				 
			 { 
				 Regimen reg = regimen.get();
					
				 reg.setName(rg.getName()); 
					 
				 res = regimenService.update(reg); 
				 
				 if(res!=null) 
				 {
					 result =new ResponseEntity<>(res, HttpStatus.OK);
				 }
				 else 
					 
				 { 
					 result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				 }
				 
			 } else {
			 
				 res=  new  Response(ResponseStatusEnum.ERROR,null,"Regimen introuvable", false); 
			     result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
			 }
		
		} catch(Exception ex) { 
			res=  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result= new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="regimen/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deleteRegimen(String id) { 
		
		Response res = new Response(); 
		
		Optional<Regimen> regimen;
		ResponseEntity<Response> result;
		
		try {
		
			regimen= regimenService.getOne(Long.parseLong(id)); 
		
		 if(regimen!=null) {
			 
			 regimenService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,regimen,"Suppression effectuée", true);
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
	

	@RequestMapping(method = RequestMethod.GET, value="regimen/getByName")
	@ResponseBody
	public ResponseEntity<Response> getRegimenName(String name) { 
		
		Response res = new Response(); 
		
		Regimen regimen;
		ResponseEntity<Response> result;
		
		try {
		
			regimen= regimenService.getByName(name); 
		
		 if(regimen != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,regimen,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Regimen introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	} 
	
}
