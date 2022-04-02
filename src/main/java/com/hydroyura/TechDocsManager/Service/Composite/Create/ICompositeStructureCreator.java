package com.hydroyura.TechDocsManager.Service.Composite.Create;

import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.BuyDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.PartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.StandartDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.VzkDTO;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowAssembly;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowBuy;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowPart;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowStandart;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowType;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowVzk;

public interface ICompositeStructureCreator {

	
	public SpecificationRowAssembly createChildren(AssemblyDTO assemblyDTO, long count);
	
	
	public static ISpecificationRow create(PartDTO raw, long count) {
		
		return new SpecificationRowPart(
						raw.getId(), 
						raw.getNumber() + " " + raw.getName() + " (" + raw.getStatus() + ")",
						count, 
						SpecificationRowType.PART);
	}

	public static ISpecificationRow create(StandartDTO raw, long count) {
		
		return new SpecificationRowStandart(
						raw.getId(), 
						raw.getName() + " " + raw.getStandart() + " - " + raw.getNumber(),
						count, 
						SpecificationRowType.STANDART);
	}
	
	public static ISpecificationRow create(VzkDTO raw, long count) {
		
		return new SpecificationRowVzk(
						raw.getId(), 
						raw.getName() + " " + raw.getNumber(),
						count, 
						SpecificationRowType.VZK);
	}
	
	public static ISpecificationRow create(BuyDTO raw, long count) {
		
		return new SpecificationRowBuy(
						raw.getId(), 
						raw.getName() + " " + raw.getNumber() + " (" + raw.getManufacturer() + ")",
						count, 
						SpecificationRowType.BUY);
	}
	
	public static SpecificationRowAssembly create(AssemblyDTO raw, long count) {
		
		return new SpecificationRowAssembly(
						raw.getId(), 
						raw.getNumber() + " " + raw.getName() + " (" + raw.getStatus() + ")",
						count, 
						SpecificationRowType.ASSEMBLY);
	}
		
}
