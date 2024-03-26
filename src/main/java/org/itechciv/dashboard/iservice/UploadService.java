package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.helper.Reponse;
import org.itechciv.dashboard.response.Response;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	String storeFile(MultipartFile file);
}
