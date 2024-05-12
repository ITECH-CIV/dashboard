package org.itechciv.dashboard.impservice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.itechciv.dashboard.iservice.DistrictService;
import org.itechciv.dashboard.model.District;
import org.itechciv.dashboard.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DistrictServiceImpl extends GenericServiceImpl<District, Long> implements DistrictService{

	@Autowired
	private DistrictRepository districtRepo;
	
	@Override
	public District findDistrictByCode(int code) {
		
       District d = new District();  
		
		try 
		
		{ 
			d = districtRepo.findDistrictByCode(code);
			
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

	@Override
	public District findDistrictBySequence(int code) {
		
		  District d = new District();  
			
			try 
			
			{ 
				d = districtRepo.findDistrictBySequenceDistrict(code);
				
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

	@Override
	public District findDistrictByName(String name) {

		 District d = new District();  
			
			try 
			
			{ 
				d = districtRepo.findDistrictByName(name);
				
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
		@Override
		public List<District> findAllDistrict(){
			try{
				return districtRepo.findAll();
			}catch(Exception ex){
				ex.printStackTrace();
				return Collections.emptyList();
			}

		}
	
	
		@Override
		public List<District> findDistrictsByRegionId(Long regionId) {
			try{
				return districtRepo.findDistrictsByRegionId(regionId);
			}catch(Exception ex){
             ex.printStackTrace();
			 return Collections.emptyList();
			}
		}

		@Override
		public List<String> getAllDistrictNames() {

		try{
			List<District> districts = districtRepo.findAll();
			return districts.stream()
                .map(District::getName)
                .collect(Collectors.toList());
		}catch(Exception ex){
			ex.printStackTrace();
			return Collections.emptyList();
		}
        
    }


}
       
