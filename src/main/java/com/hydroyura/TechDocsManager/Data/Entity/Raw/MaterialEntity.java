package com.hydroyura.TechDocsManager.Data.Entity.Raw;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.ExtendedAbstractEntity;

@Entity
@Table(name = "material")
public class MaterialEntity extends ExtendedAbstractEntity {

	public MaterialEntity() {}
	
	public MaterialEntity(long id, String number, String name, MaterialTypeEntity type) {
		setId(id);
		setNumber(number);
		setName(name);
		setType(type);
	}

	@ManyToOne
	@JoinColumn(name = "type_id", nullable = false)
	private MaterialTypeEntity type;
	
	@OneToMany(mappedBy = "material")
	private Set<BlankEntity> blanks;

	public Set<BlankEntity> getBlanks() {
		return blanks;
	}

	public void setBlanks(Set<BlankEntity> blanks) {
		this.blanks = blanks;
	}

	public MaterialTypeEntity getType() {
		return type;
	}

	public void setType(MaterialTypeEntity type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MaterialEntity : [ID = " + getId() + "; number = " + getNumber() + "; standart = " + getName() + ", type = " + getType() + "]";
	}
	
}
