package org.itechciv.dashboard.service;

import java.util.Optional;

import org.itechciv.dashboard.response.Response;

public interface GenericService<T, ID> {

	Response create(T e);

	Response getAll();

	//T getOne(ID id);
	
	Optional<T> getOne(ID id);

	Response update(T e);

	boolean delete(ID id);
	
}
