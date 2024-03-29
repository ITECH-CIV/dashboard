package org.itechciv.dashboard.impservice;


import org.itechciv.dashboard.iservice.RegionService;
import org.itechciv.dashboard.model.Region;
import org.itechciv.dashboard.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegionServiceImpl extends GenericServiceImpl<Region, Long> implements RegionService {

	@Autowired
	private RegionRepository regionRepo;
	
	@Override
	public Region findRegionByCode(int code) {
		
      Region r = new Region();  
		
		try 
		
		{ 
			r = regionRepo.findRegionByCode(code);
			
			if( r!=null)
			{ 
				return r;
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

	@Override
	public Region findRegionByName(String name) {
		
      Region r = new Region();  
		
		try 
		
		{ 
			r = regionRepo.findRegionByName(name);
			
			if( r!=null)
			{ 
				return r;
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
