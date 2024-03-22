package org.itechciv.dashboard.iservice;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	String storeFile(MultipartFile file);
}
