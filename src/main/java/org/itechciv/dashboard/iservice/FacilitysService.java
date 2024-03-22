package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.response.Response;
import org.springframework.web.multipart.MultipartFile;

public interface FacilitysService extends GenericService<Facilitys, Long> {
	
	Facilitys getByCode(int code);
}
