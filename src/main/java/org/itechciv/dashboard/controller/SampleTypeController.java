package org.itechciv.dashboard.controller;

import java.util.Optional;

import org.itechciv.dashboard.iservice.SampleTypeService;
import org.itechciv.dashboard.model.SampleType;
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
public class SampleTypeController {
	
	@Autowired
	private SampleTypeService sampleTypeService;
	
	@RequestMapping(method = RequestMethod.POST, value="sampletype/save")
	@ResponseBody
	public ResponseEntity<Response> saveSampleType(@RequestBody SampleType sampleType) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			
			res =  sampleTypeService.create(sampleType) ; 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="sampletype/getAll")
	@ResponseBody
	public ResponseEntity<Response> getSampleTypeAll() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = sampleTypeService.getAll(); 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="sampletype/getById")
	@ResponseBody
	public ResponseEntity<Response> getSampleTypeOne(String id) { 
		
		Response res = new Response(); 
		
		Optional<SampleType> sampleType;
		ResponseEntity<Response> result;
		
		try {
		
			sampleType= sampleTypeService.getOne(Long.parseLong(id)); 
		
		 if(sampleType.isPresent()) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,sampleType,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Sample type introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value ="sampletype/update")
	public ResponseEntity<Response> updateSampleType(@RequestBody SampleType sampT, String id) {
		
		Response res = new Response();
		Optional<SampleType> sampleType ; 
		ResponseEntity<Response> result;
		
		try  {
			
			sampleType = sampleTypeService.getOne(Long.parseLong(id)); 
			 
			 if(sampleType!=null) 
				 
			 { 
				 SampleType st = sampleType.get();
					
					 st.setLabel(sampT.getLabel()); 
					 st.setDescription(sampT.getDescription());
					 
				 res = sampleTypeService.update(st); 
				 
				 if(res!=null) 
				 {
					 result =new ResponseEntity<>(res, HttpStatus.OK);
				 }
				 else 
					 
				 { 
					 result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				 }
				 
				
			 } else {
			 
				 res=  new  Response(ResponseStatusEnum.ERROR,null,"Sample type introuvable", false); 
			     result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
			 }
		
		} catch(Exception ex) { 
			res=  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result= new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="sampletype/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deleteSampleType(String id) { 
		
		Response res = new Response(); 
		
		Optional<SampleType> sampleType;
		ResponseEntity<Response> result;
		
		try {
		
			sampleType= sampleTypeService.getOne(Long.parseLong(id)); 
		
		 if(sampleType!=null) {
			 
			 sampleTypeService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,sampleType,"Suppression effectuée", true);
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
	
	@RequestMapping(method = RequestMethod.GET, value="sampletype/getByName")
	@ResponseBody
	public ResponseEntity<Response> getSampleTypeByName(String name) { 
		
		Response res = new Response(); 
		
		SampleType sampleType;
		ResponseEntity<Response> result;
		
		try {
		
			sampleType= sampleTypeService.getByName(name); 
		
		 if(sampleType != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,sampleType,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Sample type introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}

}
