package org.itechciv.dashboard.controller;

import org.itechciv.dashboard.helper.ConstantMessage;
import org.itechciv.dashboard.helper.ResponseMessage;
import org.itechciv.dashboard.iservice.UploadService;
import org.itechciv.dashboard.response.Response;
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
public class UploadController {
	
	@Autowired
	private UploadService uploadService;
	
	 //Controller relatif à l'import de localité - Region, District et Facilitys
	  @RequestMapping(method = RequestMethod.POST, value="upload/localite")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> uploadLocalites(@RequestParam("file") MultipartFile file) {		
	 
	  boolean req;
	  String message = null;
	  
	  try
	  
	  { 
	   System.out.println("MESSAGE-1::::::::::::  " + file.getOriginalFilename());
	   System.out.println("MESSAGE-2::::::::::::  " + file.getName());
	   System.out.println("MESSAGE-3::::::::::::  " + file.getContentType());
	   System.out.println("MESSAGE-4::::::::::::  " +file.getOriginalFilename());
	   System.out.println("MESSAGE-5::::::::::::  " +file.getSize());
	  
	   req = uploadService.storeLocaliteImport(file);
	  
	   if(req) {
		   message = ConstantMessage.INSERTED;
	       return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, null));

	
	 } else { 
		   message = ConstantMessage.FAILED;
	       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message, null));
	  } 
	 } catch (Exception ex) {
		  message = ConstantMessage.ERROR;
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message, ex.getMessage()));
	 } 
	}
	  //Controller relatif à l'import de fichier - OpenElis
	  @RequestMapping(method = RequestMethod.POST, value="upload/excel")
	  @ResponseBody 
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	 
		  boolean req;
		  String message = null;
		  
	      try
	  
	    {
		 System.out.println("MESSAGE-1::::::::::::  " + file.getOriginalFilename());
		 System.out.println("MESSAGE-2::::::::::::  " + file.getName());
	     System.out.println("MESSAGE-3::::::::::::  " + file.getContentType());
	     System.out.println("MESSAGE-4::::::::::::  " +file.getOriginalFilename());
	     System.out.println("MESSAGE-5::::::::::::  " +file.getSize());

		 req = uploadService.storeExcelImport(file);
	 
		 if(req) {
			 message = ConstantMessage.INSERTED;
		     return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, null));
		 } else { 
			 message = ConstantMessage.FAILED;
		     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message, null));
		  } 
		 } catch (Exception ex) {
			  ex.printStackTrace();
			  message = ConstantMessage.ERROR;
		      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message, ex.getMessage()));
		  } 	 
	}	
	  
	//Controller relatif à l'import de fichier - OpenElis
	  @RequestMapping(method = RequestMethod.POST, value="upload/test")
	  @ResponseBody 
	  public ResponseEntity<ResponseMessage> uploadTest(@RequestParam("file") MultipartFile file) {
	 
		  boolean req;
		  String message = null;
		  
	      try
	  
	    {
		 System.out.println("MESSAGE-1::::::::::::  " + file.getOriginalFilename());
		 System.out.println("MESSAGE-2::::::::::::  " + file.getName());
	     System.out.println("MESSAGE-3::::::::::::  " + file.getContentType());
	     System.out.println("MESSAGE-4::::::::::::  " +file.getOriginalFilename());
	     System.out.println("MESSAGE-5::::::::::::  " +file.getSize());

		 req = uploadService.uploadTestImport(file);
	 
		 if(req) {
			 message = ConstantMessage.INSERTED;
		     return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, null));
		 } else { 
			 message = ConstantMessage.FAILED;
		     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message, null));
		  } 
		 } catch (Exception ex) {
			  ex.printStackTrace();
			  message = ConstantMessage.ERROR;
		      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message, ex.getMessage()));
		  } 	 
	}	
}