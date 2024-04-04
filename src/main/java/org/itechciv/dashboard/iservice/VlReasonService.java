package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.VlReason;

public interface VlReasonService extends GenericService<VlReason, Long>{
	
	VlReason getByName(String name);
}
