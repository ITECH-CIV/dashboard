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
	
	  @RequestMapping(method = RequestMethod.POST, value="uploadfichier")
	  @ResponseBody 
	  public ResponseEntity<Response> uploadFiles(@RequestParam("file") MultipartFile file) {
	 
	  Response rep = new Response();
	
	  ResponseEntity<Response> result;
	  
	  String req = null;
	
	 try
	  
	 {
		 System.out.println("MESSAGE-1::::::::::::  " + file.getOriginalFilename());
		 System.out.println("MESSAGE-2::::::::::::  " + file.getName());
	     System.out.println("MESSAGE-3::::::::::::  " + file.getContentType());
	     System.out.println("MESSAGE-4::::::::::::  " +file.getOriginalFilename());
	     System.out.println("MESSAGE-5::::::::::::  " +file.getSize());

		 req = uploadService.storeFile(file);
	 
	 if(req!=null) {
	 
	  result = new ResponseEntity<>(rep, HttpStatus.OK);
	  
	 } else { 
		 result = new ResponseEntity<>(rep,HttpStatus.NOT_FOUND);
	 
	  } 
	 } catch (Exception ex) {
	 
	  result = new ResponseEntity<>(rep,HttpStatus.INTERNAL_SERVER_ERROR); 
	  } 
	 
	 return result; 
	 
	  }
	  
	  @RequestMapping(method = RequestMethod.POST, value="upload/excel")
	  @ResponseBody 
	  public ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file) {
	 
	  Response rep = new Response();
	
	  ResponseEntity<Response> result;
	  
	  String req = null;
	
	 try
	  
	 {
		 System.out.println("MESSAGE-1::::::::::::  " + file.getOriginalFilename());
		 System.out.println("MESSAGE-2::::::::::::  " + file.getName());
	     System.out.println("MESSAGE-3::::::::::::  " + file.getContentType());
	     System.out.println("MESSAGE-4::::::::::::  " +file.getOriginalFilename());
	     System.out.println("MESSAGE-5::::::::::::  " +file.getSize());

		 req = uploadService.storeFile(file);
	 
	 if(req!=null) {
	 
	  result = new ResponseEntity<>(rep, HttpStatus.OK);
	  
	 } else { 
		 result = new ResponseEntity<>(rep,HttpStatus.NOT_FOUND);
	 
	  } 
	 } catch (Exception ex) {
	 
	  result = new ResponseEntity<>(rep,HttpStatus.INTERNAL_SERVER_ERROR); 
	  } 
	 
	 return result; 
	 
	  }
	  
	  
		
	  @RequestMapping(method = RequestMethod.POST, value="uploadlocalite")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> uploadLocalite(@RequestParam("file") MultipartFile file) {		
	 
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
	       return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

	
	 } else { 
		 
		   message = ConstantMessage.FAILED;
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

	  } 
	 } catch (Exception ex) {
	  
		  message = ConstantMessage.FAILED;
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

	 } 
	}
	  
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
	       return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

	
	 } else { 
		 
		   message = ConstantMessage.FAILED;
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

	  } 
	 } catch (Exception ex) {
	  
		  message = ConstantMessage.FAILED;
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

	 } 
	}
	  
	  @RequestMapping(method = RequestMethod.POST, value="upload/fichier")
	  @ResponseBody
	  public ResponseEntity<ResponseMessage> uploadExcelLocalite(@RequestParam("file") MultipartFile file) {		
	 
	  boolean req;
	  String message = null;
	  
	  try
	  
	  { 
	   System.out.println("MESSAGE-1::::::::::::  " + file.getOriginalFilename());
	   System.out.println("MESSAGE-2::::::::::::  " + file.getName());
	   System.out.println("MESSAGE-3::::::::::::  " + file.getContentType());
	   System.out.println("MESSAGE-4::::::::::::  " +file.getOriginalFilename());
	   System.out.println("MESSAGE-5::::::::::::  " +file.getSize());
	  
	   req = uploadService.storeLocalite(file);
	  
	   if(req) {
	   
		   message = ConstantMessage.INSERTED;
	       return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

	
	 } else { 
		 
		   message = ConstantMessage.FAILED;
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

	  } 
	 } catch (Exception ex) {
	  
		  message = ConstantMessage.FAILED;
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

	 } 
	}
		 
}