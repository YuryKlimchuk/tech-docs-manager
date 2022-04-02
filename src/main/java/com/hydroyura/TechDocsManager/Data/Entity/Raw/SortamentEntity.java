package com.hydroyura.TechDocsManager.Data.Entity.Raw;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hydroyura.TechDocsManager.Data.Entity.ExtendedAbstractEntity;

@Entity
@Table(name = "sortament")
public class SortamentEntity extends ExtendedAbstractEntity {

	public SortamentEntity() {}
	
	public SortamentEntity(long id, String number, String name, SortamentTypeEntity type) {
		setId(id);
		setNumber(number);
		setName(name);
		setType(type);
	}

	@ManyToOne
	@JoinColumn(name = "type_id", nullable = false)
	private SortamentTypeEntity type;
	
	@OneToMany(mappedBy = "sortament")
	private Set<BlankEntity> blanks;
	
	public Set<BlankEntity> getBlanks() {
		return blanks;
	}

	public void setBlanks(Set<BlankEntity> blanks) {
		this.blanks = blanks;
	}

	public SortamentTypeEntity getType() {
		return type;
	}

	public void setType(SortamentTypeEntity type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SortamentEntity : [ID = " + getId() + "; number = " + getNumber() + "; standart = " + getName() + ", type = " + getType() + "]";
	}
	
}
