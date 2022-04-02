package com.hydroyura.TechDocsManager.Service.Product;

import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Product.CustomerDTO;

public interface ICustomerService {
	
	public Optional<CustomerDTO> getById(long id);
	public Iterable<CustomerDTO> getAll();
	public Optional<CustomerDTO> save(CustomerDTO dto);
	public void deleteById(long id);
	
	//public Iterable<CustomerDTO> getCustomersByProductId(long id);

}
