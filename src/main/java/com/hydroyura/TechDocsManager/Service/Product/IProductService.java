package com.hydroyura.TechDocsManager.Service.Product;

import com.hydroyura.TechDocsManager.Data.DTO.Product.CustomerDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Service.IBaseService;

public interface IProductService extends IBaseService<ProductDTO, Long> {
	
	public Iterable<CustomerDTO> getCustomersByProductId(long id);
	public Iterable<AssemblyDTO> getAssembliesByProductId(long id);
	//public void set

}
