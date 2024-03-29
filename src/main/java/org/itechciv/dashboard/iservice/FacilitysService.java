package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.Facilitys;

public interface FacilitysService extends GenericService<Facilitys, Long> {
	
	Facilitys getByCode(int code);
	Facilitys findFacilitysByOldCode(int code); 
	Facilitys findFacilitysByOldName(String name); 
	Facilitys findFacilitysByUniqueCode(int code);
	Facilitys findFacilitysByNewLongName(String name); 
	Facilitys findFacilitysByShortName(String name); 
	Facilitys findFacilitysByStatutId(String name); 




}
