package org.itechciv.dashboard.iservice;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	String storeFile(MultipartFile file);
	
	boolean storeLocaliteImport(MultipartFile file); 
	boolean storeExcelImport(MultipartFile file);
	boolean storeLocalite(MultipartFile file);
}
