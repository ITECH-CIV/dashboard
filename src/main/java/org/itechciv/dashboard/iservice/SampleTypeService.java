package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.SampleType;

public interface SampleTypeService extends GenericService<SampleType, Long> {
	
	SampleType getByName(String name);
}
