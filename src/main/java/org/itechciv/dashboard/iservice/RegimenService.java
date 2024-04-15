package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.Regimen;

public interface RegimenService extends GenericService<Regimen, Long> {
	Regimen getByName(String name);
}
