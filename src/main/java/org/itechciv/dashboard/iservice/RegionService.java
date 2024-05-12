package org.itechciv.dashboard.iservice;

import java.util.List;

import org.itechciv.dashboard.model.Region;

public interface RegionService extends GenericService<Region, Long> {
	
	Region findRegionByCode(int code); 
	Region findRegionByName(String name);
	List<Region> findAllRegion(); 
	List<String> getAllRegionNames();

     

}
