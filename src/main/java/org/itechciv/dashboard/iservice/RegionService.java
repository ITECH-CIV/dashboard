package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.Region;

public interface RegionService extends GenericService<Region, Long> {
	
	Region findRegionByCode(int code); 
	Region findRegionByName(String name); 
     

}
