package com.hydroyura.TechDocsManager.Service.Product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Product.CustomerDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Product.CustomerEntity;

@Service(value = "CustomerService")
public class CustomerService implements ICustomerService {
	
	@Autowired @Qualifier(value = "CustomerRepository")
	private JpaRepository<CustomerEntity, Long> customerRepository;
	
	@Autowired @Qualifier(value = "CustomerConverter")
	private IConverter<CustomerEntity, CustomerDTO> customerConverter;

	@Override
	public Optional<CustomerDTO> getById(long id) {
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
	public void deleteById(long id) {
		customerRepository.deleteById(id);
	}

	


}
