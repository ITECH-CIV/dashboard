package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.VihType;

public interface VihTypeService extends GenericService<VihType, Long>{
	
	VihType getByName(String name);


}
