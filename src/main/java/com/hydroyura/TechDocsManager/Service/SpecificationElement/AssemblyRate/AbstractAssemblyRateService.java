package com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate;

import java.util.HashSet;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.Converters.SpecificationElement.AssemblyRate.AbstractAssemblyRateConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyRate.BaseAssemblyRateDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate.AbstractAssemblyRateEntity;
import com.hydroyura.TechDocsManager.Data.Repository.SpecificationElement.AssemblyRate.BaseAssemblyRateRepository;

/*
 * 
 * 	TO DO:
 * - merge repository to one
 */

public abstract class AbstractAssemblyRateService<E, D> implements IAssemblyRateService<E, D>{

	protected JpaRepository<? extends AbstractAssemblyRateEntity<E>, Long> baseRepository;
	protected BaseAssemblyRateRepository<E> repository;
	protected AbstractAssemblyRateConverter<E, D> converter;
	
	
	@Autowired
	@Qualifier(value = "AssemblyConverter")
	private IConverter<AssemblyEntity, AssemblyDTO> assemblyConverter;
	
	
	@Override
	public Optional<BaseAssemblyRateDTO<D>> save(BaseAssemblyRateDTO<D> item) {
		AbstractAssemblyRateEntity<E> entity  = converter.convertFromDtoToEntity(item);
		try {
			
			return Optional.of(converter.convertFromEntityToDto(repository.save(entity)));
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	@Override
	public Iterable<BaseAssemblyRateDTO<D>> getByAssembly(AssemblyDTO dto) {
		try {
			return converter.convertListFromEntityToDto(repository.findAsseblyRateByAssembly(assemblyConverter.convertFromDtoToEntity(dto)));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return new HashSet<>();
	}
	
	@Override
	public boolean deleteById(long id) {
		try {
			// System.out.println("ID = " + id);
			baseRepository.deleteById(id);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteByAssembly(AssemblyDTO dto) {
		
		try {
			
			StreamSupport.stream(getByAssembly(dto)
					.spliterator(), false)
					.map(t -> t.getId())
					.forEach(t -> deleteById(t));
			return true;
			
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean copy(AssemblyDTO from, AssemblyDTO to) {
		
		Iterable<BaseAssemblyRateDTO<D>> rates = getByAssembly(from);
		
		try {
			StreamSupport.stream(rates.spliterator(), false)
				.forEach(new Consumer<BaseAssemblyRateDTO<D>>() {
	
					@Override
					public void accept(BaseAssemblyRateDTO<D> t) {
						save(new BaseAssemblyRateDTO<D>(to, t.getItem(), t.getCount()));	
					}
				});
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	


	
	
	
}
