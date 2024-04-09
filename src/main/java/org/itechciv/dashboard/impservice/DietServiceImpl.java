package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.DietService;
import org.itechciv.dashboard.model.Diet;
import org.itechciv.dashboard.model.VihType;
import org.itechciv.dashboard.repository.DietRepository;
import org.itechciv.dashboard.repository.VihTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DietServiceImpl extends GenericServiceImpl<Diet, Long> implements DietService {

	@Autowired
	private DietRepository dietRepository;
	
	@Override
	public Diet getByName(String name) {
		
	Diet d = new Diet();  
		
		try 
		
		{ 
			d = dietRepository.findDietByName(name);
			
			if( d!=null)
			{ 
				return d;
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
