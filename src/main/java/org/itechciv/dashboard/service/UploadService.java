package org.itechciv.dashboard.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	String storeFile(MultipartFile file);
}
