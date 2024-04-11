package org.itechciv.dashboard.repository;
import org.itechciv.dashboard.model.VlReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VlReasonRepository extends JpaRepository<VlReason, Long> {
	
	@Query(value = "select vr.*" + 
			" from dashboard.vl_reason vr" + 
			" where vr.name =?1", nativeQuery = true)	
	VlReason findVlReasonByName(String name); 
}
