package org.itechciv.dashboard.impservice;


import org.itechciv.dashboard.iservice.SiteService;
import org.itechciv.dashboard.model.Site;
import org.itechciv.dashboard.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SiteServiceImpl extends GenericServiceImpl<Site, Long> implements SiteService{

	@Autowired
	private SiteRepository siteRepo;
	
	@Override
	public Site getByCode(int code) {
		
		Site s = new Site();  
		
		try 
		
		{ 
			s = siteRepo.findSiteByCode(code);
			
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

	@Override
	public Site findSiteByOldCode(String code) {

        Site s = new Site();  
		
		try 
		
		{ 
			s = siteRepo.findSiteByOldCode(code);
			
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

	@Override
	public Site findSiteByOldName(String name) {

       Site s = new Site();  
		
		try 
		
		{ 
			s = siteRepo.findSiteByOldName(name);
			
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

	@Override
	public Site findSiteByUniqueCode(int code) {
		
       Site s = new Site();  
		
		try 
		
		{ 
			s = siteRepo.findSiteByUniqueCode(code);
			
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

	@Override
	public Site findSiteByNewLongName(String name) {

		 Site s = new Site();  
			
			try 
			
			{ 
				s = siteRepo.findSiteByNewLongName(name);
				
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

	@Override
	public Site findSiteByShortName(String name) {

		Site s = new Site();  
		
		try 
		
		{ 
			s = siteRepo.findSiteByShortName(name);
			
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

	@Override
	public Site findSiteByStatutId(String name) {


       Site s = new Site();  
		
		try 
		
		{ 
			s = siteRepo.findSiteByStatutId(name);
			
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
