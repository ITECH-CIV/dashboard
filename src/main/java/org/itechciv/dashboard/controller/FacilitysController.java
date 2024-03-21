package org.itechciv.dashboard.controller;

import java.util.Optional;
import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.itechciv.dashboard.service.FacilitysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@ResponseBody
@CrossOrigin
public class FacilitysController {
	
	@Autowired
	private FacilitysService facilitysService;
	
	@RequestMapping(method = RequestMethod.GET, value="/facilitys/getAll")
	@ResponseBody
	public ResponseEntity<Response> getFacilitysAll() { 
		
		Response res = new Response(); 
		ResponseEntity<Response> result;
		
		try  
		 
		{ 
			res = facilitysService.getAll(); 
			
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
	
	@RequestMapping(method = RequestMethod.GET, value="/facilitys/getById")
	@ResponseBody
	public ResponseEntity<Response> getFacilitysOne(String id) { 
		
		Response res = new Response(); 
		
		Optional<Facilitys> facilitys;
		ResponseEntity<Response> result;
		
		try {
		
			facilitys= facilitysService.getOne(Long.parseLong(id)); 
			
			//System.out.printf("Region:", region.toString());
	
		 if(facilitys.isPresent()) {
			
			 res =  new  Response(ResponseStatusEnum.SUCCESS,facilitys,"Enregistrement trouvé", true);
			 result =new ResponseEntity<>(res, HttpStatus.OK);
			
		} else { 
			res =  new  Response(ResponseStatusEnum.ERROR,null,"Facilitys introuvable", false); 
	        result =new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		
		} catch(Exception ex) {
			
			res =  new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
			result = new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
		}
		return result;
	}
	
	/*
	 * @RequestMapping(method = RequestMethod.POST, value="uploadfichier/region")
	 * 
	 * @ResponseBody public ResponseEntity<Response>
	 * uploadFileRegion(@RequestParam("file") MultipartFile file) {
	 * 
	 * Response rep = new Response();
	 * 
	 * ResponseEntity<Response> result;
	 * 
	 * 
	 * try
	 * 
	 * { rep =facilitysService.storeRegionFile(file);
	 * 
	 * if(rep!=null) {
	 * 
	 * result = new ResponseEntity<>(rep, HttpStatus.OK);
	 * 
	 * } else { result = new ResponseEntity<>(rep,HttpStatus.NOT_FOUND);
	 * 
	 * } } catch (Exception ex) {
	 * 
	 * result = new ResponseEntity<>(rep,HttpStatus.INTERNAL_SERVER_ERROR); } return
	 * result; }
	 */
}
