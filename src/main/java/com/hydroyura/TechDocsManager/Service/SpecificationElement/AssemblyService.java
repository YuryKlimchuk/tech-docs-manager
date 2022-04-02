package com.hydroyura.TechDocsManager.Service.SpecificationElement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hydroyura.TechDocsManager.Data.Converters.IConverter;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyRate.BaseAssemblyRateDTO;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.BuyEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.PartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;
import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.VzkEntity;
import com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate.AbstractAssemblyRateService;

@Service(value = "AssemblyService")
public class AssemblyService extends AbstractSpecificationElementService<AssemblyDTO, AssemblyEntity, Long>{
		
	
	@Autowired @Qualifier(value = "AssemblyBuyRateService")
	private AbstractAssemblyRateService<BuyEntity, BuyDTO> buyRateService;
	
	@Autowired @Qualifier(value = "AssemblyPartRateService")
	private AbstractAssemblyRateService<PartEntity, PartDTO> partRateService;
	
	@Autowired @Qualifier(value = "AssemblyAssemblyRateService")
	private AbstractAssemblyRateService<AssemblyEntity, AssemblyDTO> assemblyRateService;
	
	@Autowired @Qualifier(value = "AssemblyStandartRateService")
	private AbstractAssemblyRateService<StandartEntity, StandartDTO> standartRateService;
	
	@Autowired @Qualifier(value = "AssemblyVzkRateService")
	private AbstractAssemblyRateService<VzkEntity, VzkDTO> vzkRateService;
	
	
	
	@Autowired
	public AssemblyService(			
			@Qualifier(value = "AssemblyRepository") JpaRepository<AssemblyEntity, Long> repository,
			@Qualifier(value = "AssemblyConverter") IConverter<AssemblyEntity, AssemblyDTO> converter) {
		this.converter = converter;
		this.repository = repository;
	}
	
	
	public boolean copyFromTo(long fromId, long toId) {
		
		try {
			
			Optional<AssemblyDTO> fromAssembly = getById(fromId);
			Optional<AssemblyDTO> toAssembly = getById(toId);
			
			if(!(fromAssembly.isPresent() || toAssembly.isPresent())) {
				return false;
			}
			
	
			partRateService.getByAssembly(fromAssembly.get())
				.forEach(t -> partRateService.save(new BaseAssemblyRateDTO<PartDTO>(toAssembly.get(), t.getItem(), t.getCount())));
			
			vzkRateService.getByAssembly(fromAssembly.get())
				.forEach(t -> vzkRateService.save(new BaseAssemblyRateDTO<VzkDTO>(toAssembly.get(), t.getItem(), t.getCount())));
			
			buyRateService.getByAssembly(fromAssembly.get())
				.forEach(t -> buyRateService.save(new BaseAssemblyRateDTO<BuyDTO>(toAssembly.get(), t.getItem(), t.getCount())));
			
			standartRateService.getByAssembly(fromAssembly.get())
				.forEach(t -> standartRateService.save(new BaseAssemblyRateDTO<StandartDTO>(toAssembly.get(), t.getItem(), t.getCount())));
			
			assemblyRateService.getByAssembly(fromAssembly.get())
				.forEach(t -> assemblyRateService.save(new BaseAssemblyRateDTO<AssemblyDTO>(toAssembly.get(), t.getItem(), t.getCount())));

			
			
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
}
