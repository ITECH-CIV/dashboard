package org.itechciv.dashboard.controller;

import java.util.List;
import java.util.Optional;

import org.itechciv.dashboard.iservice.RegionService;
import org.itechciv.dashboard.model.Region;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region")
@ResponseBody
@CrossOrigin
public class RegionController {
	
	@Autowired
	private RegionService regionService;
	
	@RequestMapping(method = RequestMethod.POST, value="/save")
	public ResponseEntity<Response> saveRegion(@RequestBody Region region) {  
		
		Response res = new Response() ; 
		ResponseEntity<Response> result;
				
		try {
			 //System.out.println("Nom de la region : "  +region.getName());
			 
			res =  regionService.create(region) ; 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="/getAll")
	@ResponseBody
	public ResponseEntity<Response> getRegionAll() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = regionService.getAll(); 
			
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
	public ResponseEntity<Response> getRegionOne(String id) { 
		
		Response res = new Response(); 
		
		Optional<Region> region;
		ResponseEntity<Response> result;
		
		try {
		
			region= regionService.getOne(Long.parseLong(id)); 
			
			//System.out.printf("Region:", region.toString());
	
		 if(region.isPresent()) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,region,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Region introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value ="/update")
	public ResponseEntity<Response> updateRegion(@RequestBody Region rgn, String id) {
		
		Response res = new Response();
		Optional<Region> region ; 
		ResponseEntity<Response> result;
		
		try  {
			
			region = regionService.getOne(Long.parseLong(id)); 
			 
			 if(region!=null) 
				 
			 { 
				 Region rg = region.get();
					
				 rg.setName(rgn.getName()); 
					 
				 res = regionService.update(rg); 
				 
				 if(res!=null) 
				 {
					 result =new ResponseEntity<>(res, HttpStatus.OK);
				 }
				 else 
					 
				 { 
					 result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
				 } 
				
			 } else {
			 
				 res=  new  Response(ResponseStatusEnum.ERROR,null,"Region introuvable", false); 
			     result= new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
			 }
		
		} catch(Exception ex) { 
			res=  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result= new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		return result;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/deletedById")
	@ResponseBody
	public ResponseEntity<Response> deleteRegion(String id) { 
		
		Response res = new Response(); 
		
		Optional<Region> region;
		ResponseEntity<Response> result;
		
		try {
		
			region= regionService.getOne(Long.parseLong(id)); 
		
		 if(region!=null) {
			 
			 regionService.delete(Long.parseLong(id));
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,region,"Suppression effectuée", true);
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
	

	@RequestMapping(method = RequestMethod.GET, value="/getByCode")
	@ResponseBody
	public ResponseEntity<Response> findRegionByCode(int code) { 
		
		Response res = new Response(); 
		
		Region region;
		ResponseEntity<Response> result;
		
		try {
		
			region= regionService.findRegionByCode(code); 
		
		 if(region != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,region,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Region introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	} 
	
	@RequestMapping(method = RequestMethod.GET, value="/getByName")
	@ResponseBody
	public ResponseEntity<Response> findRegionByName(String name) { 
		
		Response res = new Response(); 
		
		Region region;
		ResponseEntity<Response> result;
		
		try {
		
			region= regionService.findRegionByName(name); 
		
		 if(region != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,region,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Region introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}


	//Retourner uniquement le nom de la region
    @GetMapping("/names")
    public ResponseEntity<List<String>> getRegionNames() {
        List<String> districtNames = regionService.getAllRegionNames();
        return new ResponseEntity<>(districtNames, HttpStatus.OK);
    } 

	@GetMapping("/all")
    public ResponseEntity<List<Region>> getAllRegion(){
        List<Region>region=regionService.findAllRegion();
        return new ResponseEntity<>(region, HttpStatus.OK);
    }



}
