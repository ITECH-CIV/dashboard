package org.itechciv.dashboard.service;

import java.io.Serializable;
import java.util.Optional;

import org.itechciv.dashboard.response.Response;

public interface GenericService<T, ID extends Serializable> {

	Response create(T e);

	Response getAll();
	
	Optional<T> getOne(ID id);

	Response update(T e);

	boolean delete(ID id);
	
}
