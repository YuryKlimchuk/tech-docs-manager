package com.hydroyura.TechDocsManager.Service.DOCGenerator;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.hydroyura.TechDocsManager.Service.SpecificationFacade.ISpecificationFacade;

public interface IDOCGenerator {
	
	
	public XWPFDocument generateSpecification(ISpecificationFacade specificationFacade);

}
