package com.hydroyura.TechDocsManager.Service.SpecificationElement.AssemblyRate;

import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyRate.BaseAssemblyRateDTO;

public interface IAssemblyRateService<E, D> {
	
	public Optional<BaseAssemblyRateDTO<D>> save(BaseAssemblyRateDTO<D> item);
	public Iterable<BaseAssemblyRateDTO<D>> getByAssembly(AssemblyDTO dto);
	public boolean deleteById(long id);
	public boolean deleteByAssembly(AssemblyDTO dto);
	public boolean copy(AssemblyDTO from, AssemblyDTO to);
	public Iterable<BaseAssemblyRateDTO<D>> getAll();

}
