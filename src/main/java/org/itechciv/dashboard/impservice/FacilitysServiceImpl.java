package org.itechciv.dashboard.impservice;


import org.itechciv.dashboard.iservice.FacilitysService;
import org.itechciv.dashboard.model.Facilitys;
import org.itechciv.dashboard.repository.FacilitysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FacilitysServiceImpl extends GenericServiceImpl<Facilitys, Long> implements FacilitysService{

	@Autowired
	private FacilitysRepository facilitysRepo;
	
	@Override
	public Facilitys getByCode(int code) {
		
		Facilitys f = new Facilitys();  
		
		try 
		
		{ 
			f = facilitysRepo.findFacilitysByCode(code);
			
			if( f!=null)
			{ 
				return f;
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
	public Facilitys findFacilitysByOldCode(String code) {

        Facilitys f = new Facilitys();  
		
		try 
		
		{ 
			f = facilitysRepo.findFacilitysByOldCode(code);
			
			if( f!=null)
			{ 
				return f;
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
	public Facilitys findFacilitysByOldName(String name) {

       Facilitys f = new Facilitys();  
		
		try 
		
		{ 
			f = facilitysRepo.findFacilitysByOldName(name);
			
			if( f!=null)
			{ 
				return f;
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
	public Facilitys findFacilitysByUniqueCode(int code) {
		
       Facilitys f = new Facilitys();  
		
		try 
		
		{ 
			f = facilitysRepo.findFacilitysByUniqueCode(code);
			
			if( f!=null)
			{ 
				return f;
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
	public Facilitys findFacilitysByNewLongName(String name) {

		 Facilitys f = new Facilitys();  
			
			try 
			
			{ 
				f = facilitysRepo.findFacilitysByNewLongName(name);
				
				if( f!=null)
				{ 
					return f;
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
	public Facilitys findFacilitysByShortName(String name) {

		Facilitys f = new Facilitys();  
		
		try 
		
		{ 
			f = facilitysRepo.findFacilitysByShortName(name);
			
			if( f!=null)
			{ 
				return f;
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
	public Facilitys findFacilitysByStatutId(String name) {


       Facilitys f = new Facilitys();  
		
		try 
		
		{ 
			f = facilitysRepo.findFacilitysByStatutId(name);
			
			if( f!=null)
			{ 
				return f;
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
