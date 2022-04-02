package com.hydroyura.TechDocsManager.Service.Product;

import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Product.CustomerDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;

public interface IProductService {
	
	public Optional<ProductDTO> getById(long id);
	public Iterable<ProductDTO> getAll();
	public Optional<ProductDTO> save(ProductDTO dto);
	public void deleteById(long id);
	
	public Iterable<CustomerDTO> getCustomersByProductId(long id);
	public Iterable<AssemblyDTO> getAssembliesByProductId(long id);

}
