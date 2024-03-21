package org.itechciv.dashboard.service;

import org.itechciv.dashboard.response.Response;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	Response storeFile(MultipartFile file);
}
