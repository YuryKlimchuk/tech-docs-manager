package com.hydroyura.TechDocsManager.Service.Product;

import com.hydroyura.TechDocsManager.Data.DTO.Product.CustomerDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Service.IBaseService;

public interface ICustomerService extends IBaseService<CustomerDTO, Long> {
	
	public Iterable<ProductDTO> getProducts(CustomerDTO customer);
	
}
