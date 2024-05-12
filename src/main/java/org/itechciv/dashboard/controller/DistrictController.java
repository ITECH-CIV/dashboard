package org.itechciv.dashboard.controller;

import java.util.List;

import org.itechciv.dashboard.iservice.DistrictService;
import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/district")
@ResponseBody
@CrossOrigin
public class DistrictController {
	
	@Autowired
	private DistrictService districtService;
	

	@RequestMapping(method = RequestMethod.GET, value="/getByCode")
	@ResponseBody
	public ResponseEntity<Response> findDistrictByCode(int code) { 
		
		Response res = new Response(); 
		
		District district;
		ResponseEntity<Response> result;
		
		try {
		
			district= districtService.findDistrictByCode(code); 
		
		 if(district != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,district,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"District introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	} 
	
	@RequestMapping(method = RequestMethod.GET, value="/getBySequence")
	@ResponseBody
	public ResponseEntity<Response> findRegionBySequence(int code) { 
		
		Response res = new Response(); 
		
		District district;
		ResponseEntity<Response> result;
		
		try {
		
			district= districtService.findDistrictBySequence(code); 
		
		 if(district != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,district,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"District introuvable", false); 
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
	public ResponseEntity<Response> findDistrictByName(String name) { 
		
		Response res = new Response(); 
		
		District district;
		ResponseEntity<Response> result;
		
		try {
		
			district= districtService.findDistrictByName(name); 
		
		 if(district != null) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,district,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"District introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	} 

	@GetMapping("/all")
    public ResponseEntity<List<District>> getAllDistrict(){
        List<District>district=districtService.findAllDistrict();
        return new ResponseEntity<>(district, HttpStatus.OK);
    }

	@GetMapping("/DistrictsByRegion")
		public List<District> getDistrictsByRegion(@RequestParam Long regionId) {
			return districtService.findDistrictsByRegionId(regionId);
		} 



    //Retourner uniquement le nom de district
    @GetMapping("/names")
    public ResponseEntity<List<String>> getDistrictNames() {
        List<String> districtNames = districtService.getAllDistrictNames();
        return new ResponseEntity<>(districtNames, HttpStatus.OK);
    }



}
