package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.District;

public interface DistrictService extends GenericService<District, Long>  {
	
	District findDistrictByCode(int code); 
	District findDistrictBySequence(int code); 
	District findDistrictByName(String name); 




}
