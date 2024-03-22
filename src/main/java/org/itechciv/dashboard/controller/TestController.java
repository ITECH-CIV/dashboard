package org.itechciv.dashboard.controller;

import java.util.Optional;

import org.itechciv.dashboard.iservice.TestService;
import org.itechciv.dashboard.model.Test;
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
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(method = RequestMethod.POST, value="/test/save")
	public ResponseEntity<Response> saveRegion(@RequestBody Test test) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			 
			res =  testService.create(test) ; 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="test/getAll")
	@ResponseBody
	public ResponseEntity<Response> getTestAll() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = testService.getAll(); 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="/test/getById")
	@ResponseBody
	public ResponseEntity<Response> getTestOne(String id) { 
		
		Response res = new Response(); 
		
		Optional<Test> test;
		ResponseEntity<Response> result;
		
		try {
		
			test= testService.getOne(Long.parseLong(id)); 
			
			System.out.printf("Test:", test.toString());
					
		 if(test.isPresent()) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,test,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Test introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	

	@RequestMapping(method = RequestMethod.GET, value="test/getByName")
	@ResponseBody
	public ResponseEntity<Response> getSampleTypeByName(String name) { 
		
		Response res = new Response(); 
		
		Test test;
		ResponseEntity<Response> result;
		
		try {
		
			test= testService.getByName(name); 
		
		 if(test != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,test,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Test introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
}
