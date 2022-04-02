package com.hydroyura.TechDocsManager.Data.Entity.Route;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.hydroyura.TechDocsManager.Data.Entity.IdAbstractEntity;

@Entity
@Table(name = "operation", uniqueConstraints = {@UniqueConstraint(columnNames = {"route_id", "type_id", "index"})})
public class OperationEntity extends IdAbstractEntity {

	@ManyToOne
	@JoinColumn(name = "route_id")
	private RouteEntity route;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private OperationTypeEntity type;
	
	@ManyToOne
	@JoinColumn(name = "equipment_id")
	private EquipmentEntity equipment;
	
	@Column(name = "time")
	private float time;
	
	@Column(name = "index")
	private int index;
	
	
	public OperationEntity() {}


	public EquipmentEntity getEquipment() {
		return equipment;
	}

	public void setEquipment(EquipmentEntity equipment) {
		this.equipment = equipment;
	}

	public RouteEntity getRoute() {
		return route;
	}

	public OperationTypeEntity getType() {
		return type;
	}

	public float getTime() {
		return time;
	}

	public int getIndex() {
		return index;
	}

	public void setRoute(RouteEntity route) {
		this.route = route;
	}

	public void setType(OperationTypeEntity type) {
		this.type = type;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
