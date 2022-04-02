package com.hydroyura.TechDocsManager.Data.Entity.Raw;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.hydroyura.TechDocsManager.Data.Entity.IdAbstractEntity;


@Entity
@Table(name = "blank", uniqueConstraints = {@UniqueConstraint(columnNames = {"sortament_id", "material_id"})})
public class BlankEntity extends IdAbstractEntity {
	
	public BlankEntity() {}
	
	public BlankEntity(SortamentEntity sortament, MaterialEntity material) {
		setSortament(sortament);
		setMaterial(material);
	}

	@OneToMany(mappedBy = "blank")
	private Set<BlankRateEntity> rates = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "sortament_id", nullable = false)
	private SortamentEntity sortament;
	
	@ManyToOne
	@JoinColumn(name ="material_id", nullable = false)
	private MaterialEntity material;

	public SortamentEntity getSortament() {
		return sortament;
	}

	public MaterialEntity getMaterial() {
		return material;
	}

	public void setSortament(SortamentEntity sortament) {
		this.sortament = sortament;
	}

	public void setMaterial(MaterialEntity material) {
		this.material = material;
	}
	
	public Set<BlankRateEntity> getRates() {
		return rates;
	}

	public void setRates(Set<BlankRateEntity> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "BlankEntity : [ID = " + getId() + "; material = " + getMaterial() + "; sortament = " + getSortament() + "]";
	}
	
}
