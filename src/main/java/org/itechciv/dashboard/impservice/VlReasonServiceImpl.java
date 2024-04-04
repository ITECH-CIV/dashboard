package org.itechciv.dashboard.impservice;

import org.itechciv.dashboard.iservice.VlReasonService;
import org.itechciv.dashboard.model.VlReason;
import org.itechciv.dashboard.repository.VlReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VlReasonServiceImpl extends GenericServiceImpl<VlReason, Long> implements VlReasonService {

	@Autowired
	private VlReasonRepository vlReasonRepository;
	
	@Override
	public VlReason getByName(String name) {
		
		VlReason vr = new VlReason();  
		
		try 
		
		{ 
			vr = vlReasonRepository.findVlReasonByName(name);
			
			if( vr!=null)
			{ 
				return vr;
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


