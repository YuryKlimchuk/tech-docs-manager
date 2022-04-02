package com.hydroyura.TechDocsManager.Service.Composite.Elements;

import java.util.ArrayList;
import java.util.List;

import com.hydroyura.TechDocsManager.Data.DTO.Route.RouteDTO;

public class SpecificationRowPart extends BaseSpecificationRow{

	private List<RouteDTO> routes = new ArrayList<>();
	private RouteDTO selectedRoute;
	
	public SpecificationRowPart(long id, String label, long count, SpecificationRowType type) {
		super(id, label, count, type);
	}

	public List<RouteDTO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteDTO> routes) {
		this.routes = routes;
	}

	public RouteDTO getSelectedRoute() {
		return selectedRoute;
	}

	public void setSelectedRoute(RouteDTO selectedRoute) {
		this.selectedRoute = selectedRoute;
	}
	
}
