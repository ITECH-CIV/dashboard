package org.itechciv.dashboard.controller;
import java.util.Optional;
import org.itechciv.dashboard.iservice.VihTypeService;
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
public class VihTypeController {
	
	@Autowired
	private VihTypeService vihTypeService;
	
	@RequestMapping(method = RequestMethod.POST, value="vihtype/save")
	@ResponseBody
	public ResponseEntity<Response> saveVihType(@RequestBody VihType vihType) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			
			res =  vihTypeService.create(vihType) ; 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="vihtype/getAll")
	@ResponseBody
	public ResponseEntity<Response> getVihTypeAll() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = vihTypeService.getAll(); 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="vihtype/getById")
	@ResponseBody
	public ResponseEntity<Response> getVihType(String id) { 
		
		Response res = new Response(); 
		
		Optional<VihType> vihqType;
		ResponseEntity<Response> result;
		
		try {
		
			vihqType= vihTypeService.getOne(Long.parseLong(id)); 
		
		 if(vihqType!=null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,vihqType,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Vih type introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(value ="vihtype/update", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateVihType(@RequestBody VihType vhT, String id) {
		
		Response res = new Response();
		Optional<VihType> vihType; 
		ResponseEntity<Response> result;
		
		try  {
			
			vihType = vihTypeService.getOne(Long.parseLong(id)); 
			 
			 if(vihType!=null) 
				 
			 { 
				 VihType vt = vihType.get();
					
				 vt.setName(vhT.getName()); 
					 
				 res = vihTypeService.update(vt); 
				 
				 if(res!=null) 
				 {
					 result =new ResponseEntity<>(res, HttpStatus.OK);
				 }
				 else 
					 
				 { 
					 result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				 }
				 
			 } else {
			 
				 res=  new  Response(ResponseStatusEnum.ERROR,null,"Vih type introuvable", false); 
			     result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
			 }
		
		} catch(Exception ex) { 
			res=  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result= new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="vihtype/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deleteVihType(String id) { 
		
		Response res = new Response(); 
		
		Optional<VihType> vihType;
		ResponseEntity<Response> result;
		
		try {
		
			vihType= vihTypeService.getOne(Long.parseLong(id)); 
		
		 if(vihType!=null) {
			 
			 vihTypeService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,vihType,"Suppression effectuée", true);
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
	

	@RequestMapping(method = RequestMethod.GET, value="vihtype/getByName")
	@ResponseBody
	public ResponseEntity<Response> getVihTypeByName(String name) { 
		
		Response res = new Response(); 
		
		VihType vihType;
		ResponseEntity<Response> result;
		
		try {
		
			vihType= vihTypeService.getByName(name); 
		
		 if(vihType != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,vihType,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Vih type introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	} 
	
}
