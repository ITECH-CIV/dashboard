package org.itechciv.dashboard.service;

import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.response.Response;
import org.springframework.web.multipart.MultipartFile;

public interface FacilitysService extends GenericService<Facilitys, Long> {
	
	Response storeFacilitysFile(MultipartFile file);

	Facilitys getByCode(int code);
}
