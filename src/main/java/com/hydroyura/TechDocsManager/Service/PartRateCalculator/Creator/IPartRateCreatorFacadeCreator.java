package com.hydroyura.TechDocsManager.Service.PartRateCalculator.Creator;

import java.util.List;
import java.util.Map;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Service.PartRateCalculator.IPartRateCalculatorFacade;

public interface IPartRateCreatorFacadeCreator {
	
	public IPartRateCalculatorFacade create(AssemblyDTO assembly, Map<PartDTO, List<Long>> parts);

}
