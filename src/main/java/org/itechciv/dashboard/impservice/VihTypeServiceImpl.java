package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.VihTypeService;
import org.itechciv.dashboard.model.VihType;
import org.itechciv.dashboard.repository.VihTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VihTypeServiceImpl extends GenericServiceImpl<VihType, Long> implements VihTypeService {

	@Autowired
	private VihTypeRepository vihTypeRepository;

	@Override
	public VihType getByName(String name) {
	
	VihType vt = new VihType();  
		
		try 
		
		{ 
			vt = vihTypeRepository.findVihTypeByName(name);
			
			if( vt!=null)
			{ 
				return vt;
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


