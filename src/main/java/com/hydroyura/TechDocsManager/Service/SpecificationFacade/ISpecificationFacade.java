package com.hydroyura.TechDocsManager.Service.SpecificationFacade;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowPart;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowType;

public interface ISpecificationFacade {

	public Map<ProductDTO, Long> getProducts();
	public boolean addProduct(ProductDTO product, long count);
	public boolean removeProduct(ProductDTO product);
	public Optional<ProductDTO> getProductById(long id);
	public boolean setNewProductCount(long id, long newCount);
	public Optional<AssemblyDTO> getAssemblyById(long idAssembly, long idProduct);
	
	public boolean setSelectedAssembly(long idAssembly, long idProduct);
	public boolean setSelectedRoute(long idRoute, long idPart);
	public boolean setSelectedBlankRate(long idBlankRate, long idPart);
	
	public boolean isAllAssembliesSelected();
	public boolean isAllRoutesSelected();
	public boolean isAllBlankRatesSelected();
	
	public boolean generateSpecification();
	
	public Map<ISpecificationRow, Long> getSpecificationElement(SpecificationRowType type);
	
	public List<SpecificationRowPart> getParts();
}
