package org.itechciv.dashboard.service;

import org.itechciv.dashboard.model.Test;

public interface TestService extends GenericService<Test, Long> {
	
	Test getByName(String name);

}
