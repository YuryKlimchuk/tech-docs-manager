package com.hydroyura.TechDocsManager.Service.SpecificationFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hydroyura.TechDocsManager.Data.DTO.Product.ProductDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Raw.BlankRateDTO;
import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;
import com.hydroyura.TechDocsManager.Data.DTO.SpecificationElement.AssemblyDTO;
import com.hydroyura.TechDocsManager.Service.Composite.ICompositeUtilities;
import com.hydroyura.TechDocsManager.Service.Composite.Create.ICompositeStructureCreator;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.ISpecificationRow;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowPart;
import com.hydroyura.TechDocsManager.Service.Composite.Elements.SpecificationRowType;

@Component(value = "SpecificationFacade")
public class SpecificationFacade implements ISpecificationFacade {
	
	@Autowired @Qualifier(value = "CompositeCreatorV1")
	private ICompositeStructureCreator compositeCreator;
	
	private Map<ProductDTO, Long> products = new LinkedHashMap<>();
	private Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> compressSpecification;
	
	public SpecificationFacade() {}

	@Override
	public boolean addProduct(ProductDTO product, long count) {
		if(products.containsKey(product)) return false;
		
		products.put(product, Long.valueOf(count));
		return true;
	}

	@Override
	public Map<ProductDTO, Long> getProducts() {
		return products;
	}

	@Override
	public boolean removeProduct(ProductDTO product) {
		if(!products.containsKey(product)) return false;
		
		products.remove(product);
		return true;
	}

	@Override
	public Optional<ProductDTO> getProductById(long id) {
		return products.entrySet().stream().map(p -> p.getKey()).filter(p -> p.getId() == id).findFirst();
	}

	@Override
	public Optional<AssemblyDTO> getAssemblyById(long idAssembly, long idProduct) {
		
		Optional<ProductDTO> product = getProductById(idProduct);
	
		if(product.isEmpty()) return Optional.empty();
		
		return product.get().getAssemblies().stream().filter(p -> p.getId() == idAssembly).findFirst();
	}

	@Override
	public boolean setSelectedAssembly(long idAssembly, long idProduct) {
		
		Optional<ProductDTO> product = getProductById(idProduct);
		Optional<AssemblyDTO> assembly = getAssemblyById(idAssembly, idProduct);
		if(assembly.isEmpty() || product.isEmpty()) return false;
		
		product.get().setSelectedAssembly(assembly.get());
	
		return true;
	}

	@Override
	public boolean isAllAssembliesSelected() {
		return
				products.entrySet().stream()
					.map(p -> p.getKey())
					.filter(p -> p.getSelectedAssembly() == null)
					.collect(Collectors.toList()).size() == 0 ? true : false;
	}

	@Override
	public boolean generateSpecification() {
		
		List<Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>>> listSpecification = new ArrayList<>();
		getProducts().forEach((key, value) -> listSpecification.add(compositeCreator.createChildren(key.getSelectedAssembly(), value).getValue()));
		
		Map<SpecificationRowType, Map<ISpecificationRow, List<Long>>> specification = new HashMap<>();
		listSpecification.forEach(value -> ICompositeUtilities.mergeMap1(specification, value));
		
		compressSpecification = ICompositeUtilities.compressMap(specification);
		return true;
	}

	@Override
	public Map<ISpecificationRow, Long> getSpecificationElement(SpecificationRowType type) {
		
		if(compressSpecification.get(type) != null) 
			
			return compressSpecification.get(type).entrySet().stream()
							.sorted(Map.Entry.comparingByKey())
							.collect(Collectors.toMap(
									Map.Entry::getKey, 
									value -> value.getValue().stream().mapToLong(t -> t).sum(), 
									(oldVal, newVal) -> oldVal, 
									LinkedHashMap::new));

		return new HashMap<ISpecificationRow, Long>();
	}

	@Override
	public List<SpecificationRowPart> getParts() {
		return 
			getSpecificationElement(SpecificationRowType.PART).entrySet().stream()
				.map(new Function<Map.Entry<ISpecificationRow, Long>, SpecificationRowPart>() {
	
					@Override
					public SpecificationRowPart apply(Entry<ISpecificationRow, Long> t) {
						t.getKey().setCount(t.getValue());
						return ((SpecificationRowPart) t.getKey());
					}})
				.collect(Collectors.toList());
	}

	
	@Override
	public boolean setSelectedRoute(long idRoute, long idPart) {
		
		Optional<SpecificationRowPart> part = getParts().stream().filter(p -> p.getId() == idPart).findFirst();
		if(part.isEmpty()) return false;
		
		Optional<RouteDTO> route = part.get().getRoutes().stream().filter(p -> p.getId() == idRoute).findFirst();
		if(route.isEmpty()) return false;
		
		part.get().setSelectedRoute(route.get());
		
		return true;
	}

	@Override
	public boolean isAllRoutesSelected() {
		return 
			getParts().stream()
				.filter(p -> p.getSelectedRoute() == null)
				.collect(Collectors.toList()).size() == 0 ? true : false; 
	}

	@Override
	public boolean setSelectedBlankRate(long idBlankRate, long idPart) {
		
		Optional<SpecificationRowPart> part = getParts().stream().filter(p -> p.getId() == idPart).findFirst();
		if(part.isEmpty()) return false;
		
		Optional<BlankRateDTO> blankRate = part.get().getSelectedRoute().getRates().stream().filter(p -> p.getId() == idBlankRate).findFirst(); 
		if(blankRate.isEmpty()) return false;
		
		part.get().getSelectedRoute().setSelectedRate(blankRate.get());
		
		return true;
	}

	@Override
	public boolean isAllBlankRatesSelected() {
		return getParts().stream()
					.filter(p -> p.getSelectedRoute().getSelectedRate() == null)
					.collect(Collectors.toList()).size() == 0 ? true : false;
	}

	@Override
	public boolean setNewProductCount(long id, long newCount) {
		
		Optional<ProductDTO> product = getProductById(id);
		if(product.isEmpty() || newCount < 1) return false;
		
		long oldValue = getProducts().get(product.get());
		if(oldValue == newCount) return false;
		
		getProducts().put(product.get(), newCount);
		
		return true;
	}

}

