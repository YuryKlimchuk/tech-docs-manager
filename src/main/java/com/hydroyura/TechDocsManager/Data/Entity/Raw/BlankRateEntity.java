package com.hydroyura.TechDocsManager.Data.Entity.Raw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.IdAbstractEntity;
import com.hydroyura.TechDocsManager.Data.Entity.Route.RouteEntity;

@Entity
@Table(name = "blank_rate")
public class BlankRateEntity extends IdAbstractEntity {
	
	
	@Column(name = "rate", nullable = false)
	private float rate;
	
	@ManyToOne
	@JoinColumn(name = "route_id", nullable = false)
	private RouteEntity route;
	
	@ManyToOne
	@JoinColumn(name = "entity_id", nullable = false)
	private BlankEntity blank;
	
	public BlankRateEntity() {}
	
	public BlankRateEntity(BlankEntity blank, RouteEntity route, float rate) {
		setBlank(blank);
		setRoute(route);
		setRate(rate);
	}

	public float getRate() {
		return rate;
	}

	public RouteEntity getRoute() {
		return route;
	}

	public BlankEntity getBlank() {
		return blank;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public void setRoute(RouteEntity route) {
		this.route = route;
	}

	public void setBlank(BlankEntity blank) {
		this.blank = blank;
	}

	@Override
	public String toString() {
		return "BlankRateEntity [ID = " + getId() + "; route = " + route.getNumber() + "; blank = " + blank.getSortament().getNumber() + " " + blank.getMaterial().getNumber() + "; rate = " + rate + "]";
	}
	
}
