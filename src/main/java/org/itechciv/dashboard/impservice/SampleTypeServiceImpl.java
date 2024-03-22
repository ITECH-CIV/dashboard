package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.SampleTypeService;
import org.itechciv.dashboard.model.SampleType;
import org.itechciv.dashboard.repository.SampleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SampleTypeServiceImpl extends GenericServiceImpl<SampleType, Long> implements SampleTypeService {

	@Autowired
	private SampleTypeRepository sampletypeRepository;


	@Override
	public SampleType getByName(String name) {
		
		SampleType st = new SampleType();  
		
		try 
		
		{ 
			st = sampletypeRepository.findSampleTypeByName(name);
			
			if( st!=null)
			{ 
				return st;
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



