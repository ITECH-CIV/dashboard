package org.itechciv.dashboard.service;

import org.itechciv.dashboard.model.Sample;

public interface SampleService extends GenericService<Sample, Long> {
	
	Sample findSampleByCode(String code); 


}
