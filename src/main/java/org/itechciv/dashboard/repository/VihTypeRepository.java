package org.itechciv.dashboard.repository;
import org.itechciv.dashboard.model.VihType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VihTypeRepository extends JpaRepository<VihType, Long> {
	
	@Query(value = "select vt.*" + 
			" from dashboard.vih_type vt" + 
			" where vt.name =?1", nativeQuery = true)	
	VihType findVihTypeByName(String name); 

}
