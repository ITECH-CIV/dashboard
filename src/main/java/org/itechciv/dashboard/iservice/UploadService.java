package org.itechciv.dashboard.iservice;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	boolean storeLocaliteImport(MultipartFile file); 
	boolean storeExcelImport(MultipartFile file);
	boolean uploadTestImport(MultipartFile file);
	
	boolean storeExcelImport2(MultipartFile file);

}
