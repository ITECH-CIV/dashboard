package org.itechciv.dashboard.controller;

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
	  @ResponseBody public ResponseEntity<Response>
	  uploadFile(@RequestParam("file") MultipartFile file) {
	 
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
}
