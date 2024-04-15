package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.RegimenService;
import org.itechciv.dashboard.model.Regimen;
import org.itechciv.dashboard.model.VihType;
import org.itechciv.dashboard.repository.RegimenRepository;
import org.itechciv.dashboard.repository.VihTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegimenServiceImpl extends GenericServiceImpl<Regimen, Long> implements RegimenService {

	@Autowired
	private RegimenRepository regimenRepository;
	
	@Override
	public Regimen getByName(String name) {
		
	Regimen d = new Regimen();  
		
		try 
		
		{ 
			d = regimenRepository.findRegimenByName(name);
			
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
