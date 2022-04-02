package com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.AssemblyRate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.hydroyura.TechDocsManager.Data.Entity.SpecificationElement.StandartEntity;

@Entity
@Table(name = "assembly_standart_rate", uniqueConstraints = {@UniqueConstraint(columnNames = {"assembly_id", "item_id"})})
public class AssemblyStandartRateEntity extends AbstractAssemblyRateEntity<StandartEntity> {}
