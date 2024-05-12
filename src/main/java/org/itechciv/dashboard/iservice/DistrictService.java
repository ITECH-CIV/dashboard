package org.itechciv.dashboard.iservice;

import java.time.LocalDateTime;
import java.util.List;

import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.response.Response;

public interface DistrictService extends GenericService<District, Long>  {
	
	District findDistrictByCode(int code); 
	District findDistrictBySequence(int code); 
	District findDistrictByName(String name); 
    List<District> findAllDistrict();
	List<District> findDistrictsByRegionId(Long regionId);
	List<String> getAllDistrictNames();





}
