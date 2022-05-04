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
	
	
	public static SpecificationRowPart create(PartDTO raw, long count) {
		
		return new SpecificationRowPart(
						raw.getId(), 
						raw.getNumber() + " " + raw.getName() + " (" + raw.getStatus() + ")",
						count, 
						SpecificationRowType.PART, raw.getName(), raw.getNumber(), raw.getStatus());
	}

	public static ISpecificationRow create(StandartDTO raw, long count) {
		
		return new SpecificationRowStandart(
						raw.getId(), 
						raw.getName() + " " + raw.getStandart() + " - " + raw.getNumber(),
						count, 
						SpecificationRowType.STANDART, raw.getName(), raw.getNumber(), raw.getStandart());
	}
	
	public static ISpecificationRow create(VzkDTO raw, long count) {
		
		return new SpecificationRowVzk(
						raw.getId(), 
						raw.getName() + " " + raw.getNumber(),
						count, 
						SpecificationRowType.VZK, raw.getName(), raw.getName());
	}
	
	public static ISpecificationRow create(BuyDTO raw, long count) {
		
		return new SpecificationRowBuy(
						raw.getId(), 
						raw.getName() + " " + raw.getNumber() + " (" + raw.getManufacturer() + ")",
						count, 
						SpecificationRowType.BUY, raw.getName(), raw.getNumber(), raw.getManufacturer());
	}
	
	public static SpecificationRowAssembly create(AssemblyDTO raw, long count) {
		
		return new SpecificationRowAssembly(
						raw.getId(), 
						raw.getNumber() + " " + raw.getName() + " (" + raw.getStatus() + ")",
						count, 
						SpecificationRowType.ASSEMBLY);
	}
		
}
