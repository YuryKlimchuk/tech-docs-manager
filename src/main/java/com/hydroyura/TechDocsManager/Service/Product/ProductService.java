package com.hydroyura.TechDocsManager.Service.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.Product.CustomerDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.Entity.Product.CustomerEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Product.ProductEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;

@Service(value = "ProductService")
public class ProductService implements IProductService {
	
	@Autowired @Qualifier(value = "ProductRepository")
	private JpaRepository<ProductEntity, Long> productRepository;
	
	@Autowired @Qualifier(value = "ProductConverter")
	private IConverter<ProductEntity, ProductDTO> productConverter;
	
	@Autowired @Qualifier(value = "CustomerConverter")
	private IConverter<CustomerEntity, CustomerDTO> customerConverter;
	
	@Autowired @Qualifier(value = "AssemblyConverter")
	private IConverter<AssemblyEntity, AssemblyDTO> assemblyConverter;
	
	
	@Override
	public Optional<ProductDTO> getById(Long id) {
		Optional<ProductEntity> entity = productRepository.findById(id);
		
		ProductDTO dto = null;
		
		if(entity.isPresent()) {
			dto = productConverter.convertFromEntityToDto(entity.get());
			List<AssemblyDTO> assemblies = StreamSupport.stream(getAssembliesByProductId(id).spliterator(), false).collect(Collectors.toList());
			dto.setAssemblies(assemblies);
			if(assemblies.size() == 1) dto.setSelectedAssembly(assemblies.get(0));
		}
		
		return entity.isPresent() ? Optional.of(dto) : Optional.empty();
	}

	@Override
	public Iterable<ProductDTO> getAll() {
		return productConverter.convertListFromEntityToDto(productRepository.findAll());
	}

	@Override
	public Optional<ProductDTO> save(ProductDTO dto) {
		try {
			
			ProductEntity entity = productConverter.convertFromDtoToEntity(dto);
			entity.setAssemblies(assemblyConverter.convertListFromDtoToEntity(dto.getAssemblies()));
			entity.setCustomers(customerConverter.convertListFromDtoToEntity(dto.getCustomers()));
			
			return Optional.of(productConverter.convertFromEntityToDto(productRepository.save(entity)));
		} catch (DataAccessException  e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	
	@Override
	public Iterable<CustomerDTO> getCustomersByProductId(long id) {
		Optional<ProductEntity> entity = productRepository.findById(id);
		return entity.isPresent() ? customerConverter.convertListFromEntityToDto(entity.get().getCustomers()) : new ArrayList<CustomerDTO>();
	}

	@Override
	public Iterable<AssemblyDTO> getAssembliesByProductId(long id) {
		Optional<ProductEntity> entity = productRepository.findById(id);
		return entity.isPresent() ? assemblyConverter.convertListFromEntityToDto(entity.get().getAssemblies()) : new ArrayList<AssemblyDTO>();
	}

	@Override
	public Iterable<ProductDTO> getAll(Specification<Long> specification) {
		// TODO Auto-generated method stub
		return null;
	}



}
