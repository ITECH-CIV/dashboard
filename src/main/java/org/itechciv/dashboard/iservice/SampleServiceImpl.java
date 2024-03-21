package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.Sample;
import org.itechciv.dashboard.repository.SampleRepository;
import org.itechciv.dashboard.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SampleServiceImpl extends GenericServiceImpl<Sample, Long> implements SampleService {

	@Autowired
	private SampleRepository sampleRepository;

	@Override
	public Sample findSampleByCode(String code) {
		
	Sample s = new Sample();
		
       try 
		
		{ 
			s = sampleRepository.findSampleByCode(code);
			
			if( s!=null)
			{ 
				return s;
			} 
			else 
			{ 
				return null;
			}
		}
		catch(Exception ex) { 
			ex.printStackTrace();
			return null;
		}	
	}
	
}
