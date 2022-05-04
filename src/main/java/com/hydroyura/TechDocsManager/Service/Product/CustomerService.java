package com.hydroyura.TechDocsManager.Service.Product;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Product.CustomerDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Product.CustomerEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Product.ProductEntity;

@Service(value = "CustomerService")
public class CustomerService implements ICustomerService {
	
	@Autowired @Qualifier(value = "CustomerRepository")
	private JpaRepository<CustomerEntity, Long> customerRepository;
	
	@Autowired @Qualifier(value = "CustomerConverter")
	private IConverter<CustomerEntity, CustomerDTO> customerConverter;
	
	@Autowired @Qualifier(value = "ProductConverter")
	private IConverter<ProductEntity, ProductDTO> productConverter;

	@Override
	public Optional<CustomerDTO> getById(Long id) {
		Optional<CustomerEntity> entity = customerRepository.findById(id);
		return entity.isPresent() ? Optional.of(customerConverter.convertFromEntityToDto(entity.get())) : Optional.empty();
	}

	@Override
	public Iterable<CustomerDTO> getAll() {
		return customerConverter.convertListFromEntityToDto(customerRepository.findAll());
	}

	@Override
	public Optional<CustomerDTO> save(CustomerDTO dto) {
		try {
			return Optional.of(customerConverter.convertFromEntityToDto(customerRepository.save(customerConverter.convertFromDtoToEntity(dto))));
		} catch (DataAccessException  e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Iterable<ProductDTO> getProducts(CustomerDTO customerDTO) {
		Optional<CustomerEntity> customer = customerRepository.findById(customerDTO.getId());
		return customer.isPresent() ? productConverter.convertListFromEntityToDto(customer.get().getProducts()) : new ArrayList<>();
	}

}
