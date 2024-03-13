package org.itechciv.dashboard.iservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.itechciv.dashboard.response.Response;
import org.itechciv.dashboard.response.Response.ResponseStatusEnum;
import org.itechciv.dashboard.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {
	
	@Autowired
	private JpaRepository<T,ID > repository; 
    
	@Override
	public 	Response create(T e) { 
		
		Response res = new Response();
		
		
		try { 
			
			e=repository.save(e);  
			
			res = new Response(ResponseStatusEnum.SUCCESS, e, "Insertion effectuée avec succès",true); 
			
			return res ;
		} 
		
		catch(Exception ex) {  
			
			return new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);	
		}	
	}

	@Override
	public Response getAll() { 
		
		List<T> liste = new ArrayList<>(); 
		
		Response res = new Response();
		
		try { 
			
			liste = (List<T>) repository.findAll(); 
			
			res = new Response(ResponseStatusEnum.SUCCESS,liste,liste.size()+" enregistrement(s) trouvé(s)", true);
			
			return res ;
		}
		  catch(Exception ex){ 
			
			  return new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
		}
		
	} 
	
	@Override
	public Optional<T> getOne(ID id) {
        
		Optional<T> e;
		
		try {
			
			e = repository.findById(id);
			
			return e;
			
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
			return null; 
		}
		
	} 
	
	@Override
	public Response update(T e) {
		
		T search; 
		
		Response res = new Response();
		
		
		try { 
			
			search = repository.saveAndFlush(e); 
			
			res = new Response(ResponseStatusEnum.SUCCESS, search,"Modification effectuée avec succès",true); 
			
	       return res ;
			
			
		
	} catch(Exception ex)  {
		
		return new  Response(ResponseStatusEnum.ERROR,null,ex.getMessage(), false);
		
	}
	
	}
	
	 @Override
	    public boolean delete(ID id) {
		 
		   try {
			   
		        repository.deleteById(id);
		        
		        return true;
		        
		   }catch(Exception ex) {
				return false;
		   }
	    }

}
