package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.SampleType;
import org.itechciv.dashboard.model.Test;
import org.itechciv.dashboard.repository.SampleTypeRepository;
import org.itechciv.dashboard.repository.TestRepository;
import org.itechciv.dashboard.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestServiceImpl extends GenericServiceImpl<Test, Long> implements TestService {
	
	@Autowired
	private TestRepository testRepository;

	@Override
	public Test getByName(String name) {
		
		Test t = new Test();  
		
		try 
		
		{ 
			t = testRepository.findTestByName(name);
			
			if( t!=null)
			{ 
				return t;
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
